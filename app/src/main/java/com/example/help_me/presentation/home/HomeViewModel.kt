package com.example.help_me.presentation.home

import androidx.lifecycle.MutableLiveData
import com.example.help_me.base.BaseViewModel
import com.example.help_me.entities.Req

class HomeViewModel : BaseViewModel() {
    var ReqLiveData = MutableLiveData<List<Req>>()

    fun getReq_s () {
        val list = ArrayList<Req>()
        list.apply {
            add (
                Req(
                    "Помощь со скамейками", "ул. Абулхаир хана, дом 46а, квартира 54",
                    "Бостандыкский район",
                    "qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq  aksfhskjadfhkjsadhfkjasdhfkjasdhfkjashd fahsdjfhskadhfkasdhfkajs",

                    pictureUrls = arrayListOf(
                        "https://images.ru.prom.st/431388385_w640_h640_kovanye-skamejki.jpg",
                        "https://cdn.bitrix24.kz/b8032533/landing/2de/2de11d654ef2d4260d5200108ea39325/bench1.jpg",
                        "http://uytvdome.ru/wp-content/uploads/2013/02/lavochka-15.jpg"
                    )
                )
            )
            ReqLiveData.value = list
        }
    }
}