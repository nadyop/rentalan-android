package com.gdn.rentalan.ui.transaction

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.gdn.rentalan.ui.account.AccountFragment
import com.gdn.rentalan.ui.account.ProductMyFragment

class TransactionPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                TransactionFragment()
            }
            else -> {
                return TransactionMyFragment()
            }
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> "Disewa"
            else -> {
                return "Transaksiku"
            }
        }
    }
}