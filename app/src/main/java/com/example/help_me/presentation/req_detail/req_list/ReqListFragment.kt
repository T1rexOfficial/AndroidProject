package com.example.help_me.presentation.req_detail.req_list

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.help_me.R
import com.example.help_me.base.BaseFragment
import com.example.help_me.entities.Company
import com.example.help_me.entities.Req
import com.example.help_me.entities.User
import kotlinx.android.synthetic.main.fragment_req_list.*


class ReqListFragment : BaseFragment(), ReqListAdapter.OnItemClickListener {

    companion object {
        fun newInstance(data: Bundle? = null): ReqListFragment {
            val fragment = ReqListFragment()
            fragment.arguments = data
            return fragment
        }
    }
    override fun layoutId() = R.layout.fragment_req_list


    lateinit var adapter: ReqListAdapter
    private var partList = ArrayList<Any>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = ReqListAdapter()
        val list = ArrayList<Any>().apply {
            add (User (name = "Aaaaa1", surname = "Bbbbb1", city = "Almaty"))
            add (Company (title = "Aaaaa2", city = "Astana"))
            add (User (name = "Aaaaa3", surname = "Bbbbb3", city = "Taraz"))
            add (Company (title = "Aaaaa4", city = "Astana"))
        }
        adapter.setListener(this)
        adapter.setDataSet(list)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rq_lst_frt_rw.adapter = adapter
        rq_lst_frt_rw.layoutManager = LinearLayoutManager(context)
    }

    override fun onItemClicked(item: Any, position: Int) {
    }
}
