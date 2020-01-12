package com.example.help_me.presentation.home

import androidx.lifecycle.MutableLiveData
import com.example.help_me.base.BaseViewModel
import com.example.help_me.entities.Req
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

const val REQUESTS = "REQUESTS"

class HomeViewModel : BaseViewModel() {
    val database = FirebaseDatabase.getInstance().reference
    var ReqLiveData = MutableLiveData<List<Req>>()

    fun getReqList() {
        database.child(REQUESTS).addValueEventListener(object: ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {}
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val reqList = ArrayList<Req>()
                for (postSnapshot in dataSnapshot.children) {
                    val result = postSnapshot.getValue(Req::class.java)
                    if (result != null) {
                        reqList.add(result)
                    }
                }
                ReqLiveData.value = reqList
            }
        })
    }



    fun getReq_s () {
        val list = ArrayList<Req>()
        val apply = list.apply {
            add(
                Req(
                    "Помощь со скамейками", "ул. Абулхаир хана, дом 46а, квартира 54",
                    "Бостандыкский",
                    "Хочется, чтобы было удобно и комфортно. В нашем доме проживают пенсионеры, которым трудно выходить на улицу. А вот, если бы была бы лавочка они не только бы вышли из квартиры, но и еще бы общались с окружающим миром. В общем с помощью жителей дома были собраны средства на новые скамейки. Всё уже куплено, нужна лишь помощь с их установкой. Всем заранее спасибо!",

                    pictureUrls = arrayListOf(
                        "https://images.ru.prom.st/431388385_w640_h640_kovanye-skamejki.jpg",
                        "https://cdn.bitrix24.kz/b8032533/landing/2de/2de11d654ef2d4260d5200108ea39325/bench1.jpg",
                        "http://uytvdome.ru/wp-content/uploads/2013/02/lavochka-15.jpg"
                    )
                )
            )

            add(
                Req(
                    "Установка качелей", "мкр Алтын Бесик 540 — Райымбека",
                    "Ауэзовский",
                    "fahsdkjfhaskjdfhkjasdhfkjasdhfkjasdhkjfhasdkjfhkjasdhfkjsdahfkasfaskdfhkasjdhfjk asdfjkashdfkjhasdkj fhkajsdhfkjasdh kajsdhfkjashfkjhasdkjfhaskjdhfkjsaf",

                    pictureUrls = arrayListOf(
                        "https://images.ru.prom.st/573357369_w640_h640_kovanye-kacheli.jpg",
                        "https://dsk-atlet.ru/image/cache/catalog/products/2018/03/282105/%D0%9A%D0%B0%D1%87%D0%B5%D0%BB%D0%B8-800x800.jpg",
                        "https://media2.24aul.ru/imgs/57728927231ede284403be55/kacheli-derevyannye-trekhmestrye-2-7727728.jpg"
                    )
                )
            )
            ReqLiveData.value = list
        }
    }
}