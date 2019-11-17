package com.example.help_me.presentation.req_detail.req_info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.help_me.R
import com.example.help_me.entities.Req
import com.example.help_me.presentation.req_detail.REQ
import kotlinx.android.synthetic.main.fragment_req_info.*


class ReqInfoFragment : Fragment() {

    companion object {
        fun newInstance(data: Bundle? = null): ReqInfoFragment {
            val fragment = ReqInfoFragment()
            fragment.arguments = data
            return fragment
        }
    }

    private var req: Req? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_req_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        req = arguments?.getSerializable(REQ) as Req
        bindView()
    }

    private fun bindView() {
        firstText.text = req?.explainReq
        area_textview1.text = req?.addressReq
        address_textview1.text = req?.addressd
    }


}
