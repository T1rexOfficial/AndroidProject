package com.example.help_me.presentation.dialogs

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.help_me.R
import kotlinx.android.synthetic.main.item_choice_dialog.view.*
import java.lang.Exception


class MultiChoiceAdapter : RecyclerView.Adapter<MultiChoiceAdapter.ChoiceViewHolder>() {

    private var dataset = ArrayList<ChoiceItem>()
    private var realList = ArrayList<ChoiceItem>()
    private lateinit var listener: OnItemClickListener
    private var selectedPosition = -1

    fun setListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    fun setDataSet(dataset: ArrayList<ChoiceItem>) {
        this.dataset = dataset
        this.realList = dataset
        notifyDataSetChanged()
    }


    fun getDataSet() = realList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChoiceViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_choice_dialog, parent, false)
        return ChoiceViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(holder: ChoiceViewHolder, position: Int) {
        holder.bind(dataset[position])
    }

    fun setSingleChoice(position: Int) = try{
        dataset.mapIndexed { index, choiceItem ->
            if (choiceItem.chosen) {
                selectedPosition = index
                choiceItem.chosen = false
                setRealData(choiceItem)
            }
        }
        dataset[position].chosen = true
        setRealData(dataset[position])

        dataset[selectedPosition].chosen = false
        setRealData(dataset[selectedPosition])
        notifyItemChanged(selectedPosition)
    }catch (e: Exception){
        e.printStackTrace()
    }

    private fun setRealData(item: ChoiceItem){
        realList.map {
            if(it.code == item.code) it.chosen = item.chosen
        }
    }


    fun setChecked(checked: Boolean, position: Int) {
        dataset[position].chosen = checked
        setRealData(dataset[position])
    }

    fun filter(query: String){
        dataset = findByFilter(query)
        notifyDataSetChanged()
    }


    private fun findByFilter(query: String): ArrayList<ChoiceItem>{
        val ans = ArrayList<ChoiceItem>()
        realList.forEach {
            if (it.label.toLowerCase().contains(query.toLowerCase())){
                ans.add(it)
            }
        }
        return ans
    }


    inner class ChoiceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        fun bind(item: ChoiceItem) {
            itemView.choiceCheckBox.isChecked = item.chosen
            itemView.choiceLabelTextView.text = item.label
            itemView.choiceCheckBox.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            listener.onChoiceItemClicked(dataset[adapterPosition], adapterPosition)
        }

    }

    interface OnItemClickListener {
        fun onChoiceItemClicked(
            item: ChoiceItem,
            position: Int
        )
    }
}

enum class ChoiceType {
    CITY
}


data class ChoiceItem(val label: String, val code: String, var chosen: Boolean = false, var type: ChoiceType)