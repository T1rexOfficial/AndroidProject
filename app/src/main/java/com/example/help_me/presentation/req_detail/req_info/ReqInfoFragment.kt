package com.example.help_me.presentation.req_detail.req_info

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.help_me.R


class ReqInfoFragment : Fragment() {

    companion object {
        fun newInstance(data: Bundle? = null): ReqInfoFragment {
            val fragment = ReqInfoFragment()
            fragment.arguments = data
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_req_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindView ()
    }

    private fun bindView () {

    }



}
