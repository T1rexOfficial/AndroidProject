package com.example.help_me.presentation.req_detail.image_tabs

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class MainPagerAdapter(fm: FragmentManager,val fragments: ArrayList<Fragment>): FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment = fragments.get(position)

    override fun getCount(): Int = fragments.size

    override fun getPageTitle(position: Int): CharSequence? {
        when(position) {
            0 -> {
                return "Информация"
            }
            else -> {
                return "Участники"
            }
        }
    }

}