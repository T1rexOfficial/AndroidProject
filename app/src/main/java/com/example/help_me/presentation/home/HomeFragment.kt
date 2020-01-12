package com.example.help_me.presentation.home

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.help_me.App
import com.example.help_me.R
import com.example.help_me.base.BaseFragment
import com.example.help_me.entities.Req
import com.example.help_me.presentation.main.AddReqActivity
import com.example.help_me.presentation.req_detail.ReqDetailActivity
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.getViewModel
import java.io.Serializable

class HomeFragment : BaseFragment(), OnReqClickListener, HomeListAdapter.OnReqClickListener {

    private lateinit var viewModel: HomeViewModel
    private lateinit var HomeListAdapter: HomeListAdapter


    override fun layoutId() = R.layout.fragment_home

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = getViewModel()
        viewModel.getReqList()
        HomeListAdapter = HomeListAdapter()
        HomeListAdapter.fragment = this
        HomeListAdapter.reqClickListener = this
        viewModel.ReqLiveData.observe(this, ReqObserver)

    }

    val ReqObserver = Observer<List<Req>> {
        HomeListAdapter.setDataSet(it)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //viewModel.addReq(Req())
        setAdapter()
        fab_homepage.setOnClickListener {
            startActivity(Intent(context, AddReqActivity::class.java))
        }
        if (App.company == null) {
            fab_homepage.show()
        } else {
            fab_homepage.hide()
        }
    }

    private fun setAdapter() {
        req_list.adapter = HomeListAdapter
        req_list.layoutManager = LinearLayoutManager(context)
    }

    override fun onClick(pos: Int, item: Req) {
        val intent = Intent(context, ReqDetailActivity::class.java)
        intent.putExtra("Req", item as Serializable)
        startActivity(intent)
    }

}

interface OnReqClickListener {
    fun onClick(pos: Int, item: Req)
}

