package com.example.help_me.presentation.req_detail.req_list


import androidx.recyclerview.widget.RecyclerView
import android.view.View

class ReqListAdapter : RecyclerView.Adapter <ReqListAdapter.MyViewHolder>() {


    private var dataset = ArrayList<Any>()
    private lateinit var listener: OnItemClickListener


    fun setListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    fun setDataSet(dataset: ArrayList<Any>) {
        this.dataset = dataset
        notifyDataSetChanged()
    }


    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        fun bind (item: Any) {
x
        }
        init {
            itemView.setOnClickListener(this)
        }

    }

    interface OnItemClickListener {
        fun onItemClicked(item: Any, position: Int)
    }

}