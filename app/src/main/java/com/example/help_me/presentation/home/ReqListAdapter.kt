package com.example.help_me.presentation.home

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.help_me.R
import com.example.help_me.entities.Req
import com.example.help_me.extensions.loadImage
import kotlinx.android.synthetic.main.item_req.view.*

import java.io.Serializable


class ReqListAdapter : RecyclerView.Adapter<ReqListAdapter.ReqViewHolder>() {

    var fragment: HomeFragment? = null

    private var req_s: List<Req> = ArrayList()

    var reqClickListener: OnReqClickListener? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReqViewHolder {
        return ReqViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_req, parent, false))
    }

    override fun onBindViewHolder(holder: ReqViewHolder, position: Int) {
        holder.bindView(req_s[position])
    }

    override fun getItemCount(): Int = req_s.size

    fun setDataSet(it: List<Req>?) {
        req_s = it as ArrayList<Req>
        this.notifyDataSetChanged()
    }


    inner class ReqViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        fun bindView(item: Req) {

            view.titleReq.text = item.titleReq
            try {
                view.bgImage.loadImage(item.pictureUrls?.get(0) ?: "", view.context)
            } catch (e: Exception) {
                view.bgImage.loadImage("", view.context)
                e.printStackTrace()
            }

        }

        init {
            view.btnInfo.setOnClickListener {
                reqClickListener?.onClick(adapterPosition, req_s[adapterPosition])
            }
        }

    }

    interface OnReqClickListener {
        fun onClick(pos: Int, item: Req)
    }
}
