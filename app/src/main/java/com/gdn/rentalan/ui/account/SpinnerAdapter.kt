//package com.gdn.rentalan.ui.account
//
//import android.content.Context
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.BaseAdapter
//import android.widget.TextView
//import com.gdn.rentalan.R
//import java.util.*
//
//class SpinnerAdapter(context: Context, protected var items: List<String>) : BaseAdapter() {
//
//    protected var layoutInflater: LayoutInflater = LayoutInflater.from(context)
//
//    private var selectedPosition: Int = 0
//
//    constructor(context: Context, items: Array<String>) : this(context, Arrays.asList<String>(*items)) {}
//
//    override fun getCount(): Int {
//        return items.size
//    }
//
//    override fun getItem(position: Int): Any {
//        return items[position]
//    }
//
//    override fun getItemId(position: Int): Long {
//        return position.toLong()
//    }
//
//    override fun getView(position: Int, convertView: View, parent: ViewGroup): View {
//        return if (position == 0) {
//            createView(position, convertView, parent, R.layout.spinner_dropdown_hint)
//        } else if (position == selectedPosition) {
//            createView(position, convertView, parent, R.layout.spinner_dropdown_selected)
//        } else {
//            createView(position, convertView, parent, R.layout.spinner_dropdown_enabled)
//        }
//    }
//
//    override fun getDropDownView(position: Int, convertView: View, parent: ViewGroup): View {
//        return if (position == 0) {
//            createView(position, convertView, parent, R.layout.spinner_disabled_item)
//        } else if (position == selectedPosition) {
//            createView(position, convertView, parent, R.layout.spinner_selected_item)
//        } else {
//            createView(position, convertView, parent, R.layout.spinner_enabled_item)
//        }
//    }
//
//    protected fun createView(position: Int, convertView: View, parent: ViewGroup, resource: Int): View {
//        val textView = layoutInflater.inflate(resource, parent, false) as TextView
//        textView.text = getItem(position) as String
//
//        return textView
//    }
//
//    override fun isEnabled(position: Int): Boolean {
//        return if (position == 0 || position == selectedPosition) {
//            false
//        } else super.isEnabled(position)
//
//    }
//
//    fun setSelectedPosition(selectedPosition: Int) {
//        this.selectedPosition = selectedPosition
//    }
//}
