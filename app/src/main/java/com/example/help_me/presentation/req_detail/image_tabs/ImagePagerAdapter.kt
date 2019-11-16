package com.example.help_me.presentation.req_detail.image_tabs

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.help_me.presentation.req_detail.image_tabs.ImageFragment.Companion.REQ_IMAGE_LINK

class ImagePagerAdapter(fm: FragmentManager, var listItems: ArrayList<String>): FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        val data = Bundle()
        data.putString(REQ_IMAGE_LINK, listItems.get(position))
        return ImageFragment.newInstance(data)
    }
    override fun getCount(): Int = listItems.size
}