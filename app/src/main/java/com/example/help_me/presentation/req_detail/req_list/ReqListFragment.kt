package com.example.help_me.presentation.req_detail.req_list

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.help_me.R
import com.example.help_me.base.BaseFragment
import com.example.help_me.entities.Req


class ReqListFragment : BaseFragment() {

    companion object {
        fun newInstance(data: Bundle? = null): ReqListFragment {
            val fragment = ReqListFragment()
            fragment.arguments = data
            return fragment
        }
    }
    override fun layoutId() = R.layout.fragment_req_list


    lateinit var adapter: ReqListAdapter
    private var reqList = ArrayList<Req>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = ReqListAdapter()
        adapter.setListener(this)
    }



}
