package com.gdn.rentalan.ui.account

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.gdn.rentalan.ui.account.product.ProductMyFragment
import com.gdn.rentalan.ui.account.profile.AccountFragment

class AccountPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
              AccountFragment()
            }
            else -> {
                return ProductMyFragment()
            }
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> "Akun"
            else -> {
                return "Barangku"
            }
        }
    }
}