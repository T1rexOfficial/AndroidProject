package com.example.help_me.presentation.req_detail.image_tabs

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.help_me.R
import com.example.help_me.extensions.loadImage
import kotlinx.android.synthetic.main.fragment_image_req.view.*


class ImageFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_image_req, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val link = arguments?.getString(REQ_IMAGE_LINK)
        view.reqImage.loadImage(link ?: "", view.context)
    }

    companion object {
        fun newInstance(data: Bundle?): ImageFragment {
            val fragment = ImageFragment()
            fragment.arguments = data
            return fragment
        }
        const val REQ_IMAGE_LINK = "REQ_IMAGE_LINK"
    }

}
