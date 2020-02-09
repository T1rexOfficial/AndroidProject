package com.example.help_me.presentation.req_detail.req_list

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.help_me.App

import com.example.help_me.R
import com.example.help_me.base.BaseFragment
import com.example.help_me.base.Status
import com.example.help_me.entities.Company
import com.example.help_me.entities.Req
import com.example.help_me.entities.User
import com.example.help_me.presentation.main.AddReqViewModel
import kotlinx.android.synthetic.main.activity_reg.*
import kotlinx.android.synthetic.main.fragment_req_list.*
import kotlinx.android.synthetic.main.fragment_req_list.progressBar
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.androidx.viewmodel.ext.koin.getViewModel


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
    lateinit var request: Req
    private lateinit var viewModel: ReqListViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        request = arguments?.getSerializable("req") as Req
        if (request.partListUsers == null) request.partListUsers = listOf()
        if (request.partListCompany == null) request.partListCompany = listOf()

        viewModel = getViewModel()

        var users: List<User>? = request.partListUsers
        var companies: List<Company>? = request.partListCompany

        adapter = ReqListAdapter()
        adapter.setListener(this)
        adapter.setDataSet(ArrayList<Any>().apply {
            if (users != null) addAll(users)
            if (companies != null) addAll(companies)
        })
    }

    private fun onParticipateClick() {
        if (App.company == null) {
            request.partListUsers = request.partListUsers?.plus(App.user!!)
        } else {
            request.partListCompany = request.partListCompany?.plus(App.company!!)
        }
        viewModel.statusMutableLiveData.observe(this, statusObserver)
        viewModel.changeReq(request)
    }

    override fun showProgress() {
        super.showProgress()

        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        super.hideProgress()
        progressBar.visibility = View.GONE
    }

    override fun success() {
        super.success()

        progressBar.visibility = View.GONE
        adapter.setDataSet(ArrayList<Any>().apply {
            if (request.partListUsers != null) addAll(request.partListUsers!!)
            if (request.partListCompany != null) addAll(request.partListCompany!!)
        })
        if (request.partListCompany != null && request.partListUsers != null) {
            add_part_reqList.visibility =
                when (request.partListUsers!!.contains(App.user) || request.partListCompany!!.contains(App.company)) {
                    true -> {
                        Toast.makeText(activity, "You are already in list", Toast.LENGTH_SHORT).show()
                        View.GONE
                    }
                    false -> View.VISIBLE
                }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rq_lst_frt_rw.adapter = adapter
        rq_lst_frt_rw.layoutManager = LinearLayoutManager(context)

        add_part_reqList.setOnClickListener {
            onParticipateClick()
        }

        val users: List<User>? = request.partListUsers
        val companies: List<Company>? = request.partListCompany

        if (users != null && companies != null) {
            add_part_reqList.visibility = when (users.contains(App.user) || companies.contains(App.company)) {
                true -> {
                    Toast.makeText(activity, "You are already in list", Toast.LENGTH_SHORT).show()
                    View.GONE
                }
                false -> View.VISIBLE
            }
        }
    }

    override fun onItemClicked(item: Any, position: Int) {
    }
}
