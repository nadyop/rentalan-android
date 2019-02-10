package com.gdn.rentalan.util

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.ContentValues
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Matrix
import android.media.ExifInterface
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.util.Log
import android.widget.Toast
import com.gdn.rentalan.R
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream

class MediaUtils {

  internal var mActivity: Activity? = null
  internal var mFragment: Fragment? = null
  private var mGetImg: GetImg? = null
  private val REQ_CAMERA = 101
  private val REQ_GALLERY = 102
  private val MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 103

  private var imageUri: Uri? = null

  private val TAG = MediaUtils::class.java.simpleName

  constructor(activity: Activity) {
    mActivity = activity
    mGetImg = activity as GetImg
  }

  constructor(fragment: android.support.v4.app.Fragment) {
    mActivity = fragment.activity
    mFragment = fragment
    mGetImg = fragment as GetImg
  }

  fun openImageDialog() {
    val alertDialogBuilder = AlertDialog.Builder(mActivity)
    alertDialogBuilder.setTitle(R.string.select_source).setItems(R.array.source_array,
        DialogInterface.OnClickListener { dialog, which ->
          if (which == 0) {
            // camera
            if (Build.VERSION.SDK_INT > 23) {
              // check Permission
              checkPermission(REQ_CAMERA)
            } else {
              openCamera()
            }

          } else {
            // gallery
            if (Build.VERSION.SDK_INT > 23) {
              // check Permission
              checkPermission(REQ_GALLERY)
            } else {
              openGallery()
            }
          }
        })
    alertDialogBuilder.create().show()
  }

  private fun openGallery() {
    val intent2 = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
    intent2.type = "image/*"
    if (mFragment == null) {
      mActivity!!.startActivityForResult(intent2, REQ_GALLERY)
    } else {
      mFragment!!.startActivityForResult(intent2, REQ_GALLERY)
    }


  }

  private fun openCamera() {
    imageUri = mActivity!!.contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
        ContentValues())
    val camIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
    camIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
    if (mFragment == null) {
      mActivity!!.startActivityForResult(camIntent, REQ_CAMERA)
    } else {
      mFragment!!.startActivityForResult(camIntent, REQ_CAMERA)
    }
  }

  private fun checkPermission(reqSource: Int) {
    if (ContextCompat.checkSelfPermission(mActivity!!,
            Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

      // Permission is not granted
      ActivityCompat.requestPermissions(mActivity!!,
          arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), reqSource)

    } else {
      // Permission has already been granted
      if (reqSource == REQ_CAMERA) {
        openCamera()
      } else {
        openGallery()
      }
    }


  }

  fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>,
      grantResults: IntArray) {
    if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
      when (requestCode) {
        REQ_CAMERA -> openCamera()
        REQ_GALLERY -> openGallery()
      }
    } else {
      Toast.makeText(mActivity, mActivity!!.getString(R.string.permission_denied),
          Toast.LENGTH_SHORT).show()
    }
  }

  fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
    if (resultCode == Activity.RESULT_OK) {
      if (requestCode == REQ_CAMERA) {
        //camera
        //                String imgPath = getPath(mActivity, imageUri);
        val imgPath = getFileFromBitmap(mActivity!!, rotateImageIfNeed(mActivity, imageUri))
        mGetImg!!.imgdata(imgPath)
      } else {
        //gallery
        imageUri = data.data
        val imgPath = getPath(mActivity, imageUri)
        mGetImg!!.imgdata(imgPath)
      }
    }
  }

  fun getPath(context: Context?, uri: Uri?): String? {
    val projection = arrayOf(MediaStore.Images.Media.DATA)
    val cursor = context!!.contentResolver.query(uri!!, projection, null, null, null)
    var column_index = 0
    if (cursor != null) {
      column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
      cursor.moveToFirst()
      val path = cursor.getString(column_index)
      cursor.close()
      return path
    } else return uri.path
  }

  fun rotateImageIfNeed(context: Context?, uri: Uri?): Bitmap? {

    //        String filePath = getRealPathFromURI(context, uri);
    val filePath = getPath(context, uri)

    /*BitmapFactory.Options options = new BitmapFactory.Options();

        // by setting this field as true, the actual bitmap pixels are not loaded in the memory. Just the bounds are loaded. If
        // you try the use the bitmap here, you will get null.

        options.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeFile(filePath, options);*/

    var bitmap: Bitmap? = null
    try {
      bitmap = MediaStore.Images.Media.getBitmap(context!!.contentResolver, imageUri)
    } catch (e: IOException) {
      e.printStackTrace()
    }

    // check the rotation of the image and display it properly

    val exif: ExifInterface
    try {
      exif = ExifInterface(filePath)

      val orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, 0)
      Log.d("EXIF", "Exif: $orientation")
      val matrix = Matrix()
      if (orientation == 6) {
        matrix.postRotate(90f)
        Log.d("EXIF", "Exif: $orientation")
      } else if (orientation == 3) {
        matrix.postRotate(180f)
        Log.d("EXIF", "Exif: $orientation")
      } else if (orientation == 8) {
        matrix.postRotate(270f)
        Log.d("EXIF", "Exif: $orientation")
      }
      bitmap = Bitmap.createBitmap(bitmap!!, 0, 0, bitmap.width, bitmap.height, matrix, true)

      return bitmap
    } catch (e: IOException) {
      e.printStackTrace()
    }

    return null
  }

  private fun getFileFromBitmap(context: Context, bitmap: Bitmap?): String {
    val folderPath = Environment.getExternalStorageDirectory().toString() + File.separator + context.getString(
        R.string.app_name) + File.separator
    val file = File(folderPath)

    if (!file.exists()) {
      file.mkdir()
    }

    val imageFile = File(folderPath, System.currentTimeMillis().toString() + ".jpg")

    val os: OutputStream
    try {
      os = FileOutputStream(imageFile)
      bitmap!!.compress(Bitmap.CompressFormat.JPEG, 100, os)
      os.flush()
      os.close()
    } catch (e: Exception) {
      Log.e(TAG, "Error writing bitmap", e)
    }

    return imageFile.path
  }

  internal interface GetImg {
    fun imgdata(imgPath: String?)
  }

}
