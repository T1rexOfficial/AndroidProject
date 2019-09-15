package com.example.help_me.presentation.auth.registration;

import androidx.lifecycle.MutableLiveData
import com.example.help_me.base.BaseViewModel
import com.example.help_me.presentation.dialogs.ChoiceItem
import com.example.help_me.presentation.dialogs.ChoiceType

class RegViewModel : BaseViewModel() {
    val loginLiveData = MutableLiveData<String>()

    var cities = ArrayList<ChoiceItem>().apply {
        add(ChoiceItem("Алматы", "1", false, ChoiceType.CITY))
        add(ChoiceItem("Астана", "2", false, ChoiceType.CITY))
        add(ChoiceItem("Тараз", "3", false, ChoiceType.CITY))
        add(ChoiceItem("Шымкент", "4", false, ChoiceType.CITY))
        add(ChoiceItem("Актобе", "5", false, ChoiceType.CITY))
        add(ChoiceItem("Атырау", "6", false, ChoiceType.CITY))
        add(ChoiceItem("Орал", "7", false, ChoiceType.CITY))
        add(ChoiceItem("Павлодар", "8", false, ChoiceType.CITY))
        add(ChoiceItem("Петропавлск", "9", false, ChoiceType.CITY))
        add(ChoiceItem("Караганда", "10", false, ChoiceType.CITY))
        add(ChoiceItem("Жезказган", "11", false, ChoiceType.CITY))
        add(ChoiceItem("Арыс", "12", false, ChoiceType.CITY))
        add(ChoiceItem("Талдыкорган", "13", false, ChoiceType.CITY))
        add(ChoiceItem("Семей", "14", false, ChoiceType.CITY))
        add(ChoiceItem("Оскемен", "15", false, ChoiceType.CITY))
        add(ChoiceItem("Кызылорда", "16", false, ChoiceType.CITY))
        add(ChoiceItem("Кентау", "17", false, ChoiceType.CITY))
        add(ChoiceItem("Темиртау", "18", false, ChoiceType.CITY))
        add(ChoiceItem("Каскелен", "19", false, ChoiceType.CITY))
        add(ChoiceItem("Талгар", "20", false, ChoiceType.CITY))
    }

}