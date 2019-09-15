package com.example.help_me.presentation.dialogs

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.help_me.R
import kotlinx.android.synthetic.main.fragment_multi_choice_dialog.*

class MultiChoiceDialogFragment(
    private var choices: ArrayList<ChoiceItem>,
    private var singleChoice: Boolean,
    private var search: Boolean,
    private var title: String,
    private var listener: DataReceiver?): DialogFragment(), MultiChoiceAdapter.OnItemClickListener {

    data class Builder(
        private var choices: ArrayList<ChoiceItem> = arrayListOf(),
        private var singleChoice: Boolean = false,
        private var search: Boolean = false,
        private var title: String = "Диалог",
        private var listener: DataReceiver? = null) {

        fun setSingleChoice(isSingleChoice: Boolean) = apply { this.singleChoice = isSingleChoice }
        fun setChoises(list: ArrayList<ChoiceItem>) = apply { this.choices = list }
        fun setSearch(search: Boolean) = apply { this.search = search }
        fun setTitle(title: String) = apply { this.title = title }
        fun setCallback(callback: DataReceiver) = apply { this.listener = callback }

        fun build() = MultiChoiceDialogFragment(choices, singleChoice, search, title, listener)
    }

    var adapter = MultiChoiceAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_multi_choice_dialog, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dialogTitleTextView.text = title

        if(search){
            searchBox.visibility = View.VISIBLE
            searchEditText.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {

                }

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    adapter.filter(s.toString())
                }

            })
        }else{
            searchBox.visibility = View.GONE
        }

        mRecyclerView.layoutManager = LinearLayoutManager(activity)
        mRecyclerView.adapter = adapter

        adapter.setDataSet(choices)
        adapter.setListener(this)

        okImageButton.setOnClickListener{
            listener?.onDataSentFromDialog(adapter.getDataSet())
            this.dismiss()
        }
    }

    override fun onChoiceItemClicked(item: ChoiceItem, position: Int) {
        if(singleChoice){
            if(!item.chosen){
                adapter.setSingleChoice(position)
            }else{
                adapter.setChecked(!item.chosen, position)
            }
        }else{
            adapter.setChecked(!item.chosen, position)
        }
    }

    interface DataReceiver{
        fun onDataSentFromDialog(choices: ArrayList<ChoiceItem>)
    }
}




