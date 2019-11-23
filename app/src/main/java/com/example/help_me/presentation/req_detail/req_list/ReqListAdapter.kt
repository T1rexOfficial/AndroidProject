package com.example.help_me.presentation.req_detail.req_list


import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.help_me.R
import com.example.help_me.entities.Company
import com.example.help_me.entities.User
import kotlinx.android.synthetic.main.item_company.view.*
import kotlinx.android.synthetic.main.item_user.view.*

class ReqListAdapter : RecyclerView.Adapter <RecyclerView.ViewHolder>() {
    private var dataset = ArrayList<Any>()
    private lateinit var listener: OnItemClickListener


    fun setListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    fun setDataSet(dataset: ArrayList<Any>) {
        this.dataset = dataset
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            1 -> {
                UserViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false))
            }
            else -> {
                CompanyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_company, parent ,false))
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (dataset[position]) {
            is User -> 1
            else -> 2
        }
    }

    override fun getItemCount(): Int = dataset.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (dataset[position]) {
            is User -> {
                (holder as UserViewHolder).bindView(dataset[position] as User)
            }
            else -> {
                (holder as CompanyViewHolder).bindView(dataset[position] as Company)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClicked(item: Any, position: Int)
    }

}

class CompanyViewHolder(view: View): RecyclerView.ViewHolder(view) {

    fun bindView(company: Company) {
        itemView.item_company_title.text = company.title
        itemView.city_item_company.text = company.title
    }
}

class UserViewHolder(view: View): RecyclerView.ViewHolder(view) {

    fun bindView(user: User) {

        itemView.item_user_namesurname.text = user.name + " " + user.surname
        itemView.city_item_user.text = user.city
    }
}