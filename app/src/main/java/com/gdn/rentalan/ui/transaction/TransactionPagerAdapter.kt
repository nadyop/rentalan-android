package com.gdn.rentalan.ui.transaction

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.gdn.rentalan.ui.transaction.owner.TransactionMyFragment
import com.gdn.rentalan.ui.transaction.renter.TransactionFragment

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
            0 -> "Penyewa"
            else -> {
                return "Pemilik"
            }
        }
    }
}