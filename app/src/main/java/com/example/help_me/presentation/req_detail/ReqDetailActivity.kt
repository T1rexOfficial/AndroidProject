package com.example.help_me.presentation.req_detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.example.help_me.R
import com.example.help_me.entities.Req
import com.example.help_me.presentation.req_detail.image_tabs.ImagePagerAdapter
import com.example.help_me.presentation.req_detail.image_tabs.MainPagerAdapter
import kotlinx.android.synthetic.main.activity_req_detail.*
import androidx.fragment.app.Fragment
import com.example.help_me.entities.User
import com.example.help_me.entities.Company
import com.example.help_me.presentation.req_detail.req_info.ReqInfoFragment
import com.example.help_me.presentation.req_detail.req_list.ReqListFragment
import java.io.Serializable

const val REQ = "req"

class ReqDetailActivity : AppCompatActivity() {

    private lateinit var req: Req


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_req_detail)

        req = intent.getSerializableExtra("Req") as Req
        setSupportActionBar(toolbar)

        supportActionBar?.apply {
            title = "Help Me"
            setDisplayShowHomeEnabled(true)
            setDisplayHomeAsUpEnabled(true)
        }


        bindPagerAdapter()


    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            android.R.id.home -> {
                onBackPressed()
            }
        }
        return true
    }

    private fun bindPagerAdapter() {
        val fragments = ArrayList <Fragment> ()

        fragments.apply {
            val bundleInfo = Bundle()
            bundleInfo.putSerializable(REQ, req)
            add (ReqInfoFragment.newInstance(bundleInfo))

//            val bundleList = Bundle ()
//            bundleList.putSerializable("users", req.partListUsers as Serializable)
//            bundleList.putSerializable("companies", req.partListCompany as Serializable)

            add(ReqListFragment.newInstance(bundleInfo))
        }

        val imagePagerAdapter = ImagePagerAdapter(supportFragmentManager, req.pictureUrls as ArrayList<String>)
        val mainPagerAdapter = MainPagerAdapter(supportFragmentManager, fragments)

        imagePager.adapter = imagePagerAdapter
        viewpager.adapter = mainPagerAdapter
        tabs.setupWithViewPager(viewpager)
        dots.attachViewPager(imagePager)
    }
}
