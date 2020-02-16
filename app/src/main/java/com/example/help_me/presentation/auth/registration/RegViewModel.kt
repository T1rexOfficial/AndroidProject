package com.example.help_me.presentation.auth.registration

import android.content.SharedPreferences
import android.net.Uri
import androidx.lifecycle.MutableLiveData
import com.example.help_me.base.BaseViewModel
import com.example.help_me.base.Status
import com.example.help_me.entities.Company
import com.example.help_me.entities.User
import com.example.help_me.presentation.dialogs.ChoiceItem
import com.example.help_me.presentation.dialogs.ChoiceType
import com.google.gson.Gson
import org.koin.standalone.inject

class RegViewModel (val repository: RegRepository): BaseViewModel() {
    val regLiveData = MutableLiveData<String>()
    private val pref: SharedPreferences by inject()

    var userPhotoUrl:String? = null

    private fun storeUser(user: User) {
        val prefsEditor = pref.edit()
        val gson = Gson()
        val json = gson.toJson(user)
        prefsEditor.putString("myUser", json)
        prefsEditor.apply()
    }

    private fun storeCompany (company: Company) {
        val prefsEditor = pref.edit()
        val gson = Gson()
        val json = gson.toJson(company)
        prefsEditor.putString("myCompany", json)
        prefsEditor.apply()

    }

    fun register(login: String, password: String, city:String, gender:String, name:String, surname:String, age:String) {
        makeRequest({ repository.register(User(login, password, city, gender, name, surname, age, userPhotoUrl)) }) { res ->
            unwrap(res) {
                regLiveData.value = "${it.name}, вы успешно зарегистрировались!"
                storeUser(it)
            }
        }
    }

    fun registerCompany(login: String, password: String, city:String, title:String, website:String) {
        makeRequest({ repository.registerCompany(Company(login, password, city, title, website, userPhotoUrl)) }) { res ->
            unwrap(res) {
                regLiveData.value = "${it.title}, вы успешно зарегистрировались!"
                storeCompany(it)
            }
        }
    }

    val downloadUriLiveData = MutableLiveData<Uri>()

    fun uploadFile(uri: Uri) {
        statusMutableLiveData.value = Status.SHOW_LOADING
        makeRequest({repository.uploadFile(uri)}){ res->
            unwrap(res){
                downloadUriLiveData.value = it
                statusMutableLiveData.value = Status.HIDE_LOADING
            }
        }
    }


    var cities = ArrayList<ChoiceItem>().apply {
        add(ChoiceItem("Абай", "1", false, ChoiceType.CITY))
        add(ChoiceItem("Акколь", "2", false, ChoiceType.CITY))
        add(ChoiceItem("Аксай", "3", false, ChoiceType.CITY))
        add(ChoiceItem("Аксу", "4", false, ChoiceType.CITY))
        add(ChoiceItem("Актау", "5", false, ChoiceType.CITY))
        add(ChoiceItem("Актобе", "6", false, ChoiceType.CITY))
        add(ChoiceItem("Алга", "7", false, ChoiceType.CITY))
        add(ChoiceItem("Алма-Ата", "8", false, ChoiceType.CITY))
        add(ChoiceItem("Аральск", "9", false, ChoiceType.CITY))
        add(ChoiceItem("Аркалык", "10", false, ChoiceType.CITY))
        add(ChoiceItem("Арыс", "11", false, ChoiceType.CITY))
        add(ChoiceItem("Нур-Султан", "12", false, ChoiceType.CITY))
        add(ChoiceItem("Атбасар", "13", false, ChoiceType.CITY))
        add(ChoiceItem("Атырау", "14", false, ChoiceType.CITY))
        add(ChoiceItem("Аягоз", "15", false, ChoiceType.CITY))
        add(ChoiceItem("Байконур", "16", false, ChoiceType.CITY))
        add(ChoiceItem("Балхаш", "17", false, ChoiceType.CITY))
        add(ChoiceItem("Булаево", "18", false, ChoiceType.CITY))
        add(ChoiceItem("Державинск", "19", false, ChoiceType.CITY))
        add(ChoiceItem("Ерейментау", "20", false, ChoiceType.CITY))
        add(ChoiceItem("Есик", "21", false, ChoiceType.CITY))
        add(ChoiceItem("Есиль", "22", false, ChoiceType.CITY))
        add(ChoiceItem("Жанаозен", "23", false, ChoiceType.CITY))
        add(ChoiceItem("Жанатас", "24", false, ChoiceType.CITY))
        add(ChoiceItem("Жаркент", "25", false, ChoiceType.CITY))
        add(ChoiceItem("Жем", "26", false, ChoiceType.CITY))
        add(ChoiceItem("Жетысай", "27", false, ChoiceType.CITY))
        add(ChoiceItem("Житикара", "28", false, ChoiceType.CITY))
        add(ChoiceItem("Зайсан", "29", false, ChoiceType.CITY))
        add(ChoiceItem("Зыряновск", "30", false, ChoiceType.CITY))
        add(ChoiceItem("Казалинск", "31", false, ChoiceType.CITY))
        add(ChoiceItem("Кандыагаш", "32", false, ChoiceType.CITY))
        add(ChoiceItem("Капшагай", "33", false, ChoiceType.CITY))
        add(ChoiceItem("Караганда", "34", false, ChoiceType.CITY))
        add(ChoiceItem("Каражап", "35", false, ChoiceType.CITY))
        add(ChoiceItem("Каратау", "36", false, ChoiceType.CITY))
        add(ChoiceItem("Каркаралинск", "37", false, ChoiceType.CITY))
        add(ChoiceItem("Каскелен", "38", false, ChoiceType.CITY))
        add(ChoiceItem("Кентау", "39", false, ChoiceType.CITY))
        add(ChoiceItem("Кокшетау", "40", false, ChoiceType.CITY))
        add(ChoiceItem("Костанай", "41", false, ChoiceType.CITY))
        add(ChoiceItem("Кульсары", "42", false, ChoiceType.CITY))
        add(ChoiceItem("Курчатов", "43", false, ChoiceType.CITY))
        add(ChoiceItem("Кызылорда", "44", false, ChoiceType.CITY))
        add(ChoiceItem("Ленгер", "45", false, ChoiceType.CITY))
        add(ChoiceItem("Лисаковск", "46", false, ChoiceType.CITY))
        add(ChoiceItem("Макинск", "47", false, ChoiceType.CITY))
        add(ChoiceItem("Мамлютка", "48", false, ChoiceType.CITY))
        add(ChoiceItem("Павлодар", "49", false, ChoiceType.CITY))
        add(ChoiceItem("Петропавловск", "50", false, ChoiceType.CITY))
        add(ChoiceItem("Приозёрск", "51", false, ChoiceType.CITY))
        add(ChoiceItem("Риддер", "52", false, ChoiceType.CITY))
        add(ChoiceItem("Рудный", "53", false, ChoiceType.CITY))
        add(ChoiceItem("Сарань", "54", false, ChoiceType.CITY))
        add(ChoiceItem("Сарканд", "55", false, ChoiceType.CITY))
        add(ChoiceItem("Сарыагаш", "56", false, ChoiceType.CITY))
        add(ChoiceItem("Сатпаев", "57", false, ChoiceType.CITY))
        add(ChoiceItem("Семей", "58", false, ChoiceType.CITY))
        add(ChoiceItem("Сергеевка", "59", false, ChoiceType.CITY))
        add(ChoiceItem("Серебрянск", "60", false, ChoiceType.CITY))
        add(ChoiceItem("Степногорск", "61", false, ChoiceType.CITY))
        add(ChoiceItem("Степняк", "62", false, ChoiceType.CITY))
        add(ChoiceItem("Тайынша", "63", false, ChoiceType.CITY))
        add(ChoiceItem("Талгар", "64", false, ChoiceType.CITY))
        add(ChoiceItem("Талдыкорган", "65", false, ChoiceType.CITY))
        add(ChoiceItem("Тараз", "66", false, ChoiceType.CITY))
        add(ChoiceItem("Текели", "67", false, ChoiceType.CITY))
        add(ChoiceItem("Темир", "68", false, ChoiceType.CITY))
        add(ChoiceItem("Темиртау", "69", false, ChoiceType.CITY))
        add(ChoiceItem("Туркестан", "70", false, ChoiceType.CITY))
        add(ChoiceItem("Уральск", "71", false, ChoiceType.CITY))
        add(ChoiceItem("Усть-Каменогорск", "72", false, ChoiceType.CITY))
        add(ChoiceItem("Ушарал", "73", false, ChoiceType.CITY))
        add(ChoiceItem("Уштобе", "74", false, ChoiceType.CITY))
        add(ChoiceItem("Форт-Шевченко", "75", false, ChoiceType.CITY))
        add(ChoiceItem("Хромтау", "76", false, ChoiceType.CITY))
        add(ChoiceItem("Шардара", "77", false, ChoiceType.CITY))
        add(ChoiceItem("Шалкар", "78", false, ChoiceType.CITY))
        add(ChoiceItem("Шар", "79", false, ChoiceType.CITY))
        add(ChoiceItem("Шахтинск", "80", false, ChoiceType.CITY))
        add(ChoiceItem("Шемонаиха", "81", false, ChoiceType.CITY))
        add(ChoiceItem("Шу", "82", false, ChoiceType.CITY))
        add(ChoiceItem("Шымкент", "83", false, ChoiceType.CITY))
        add(ChoiceItem("Щучинск", "84", false, ChoiceType.CITY))
        add(ChoiceItem("Экибастуз", "85", false, ChoiceType.CITY))
        add(ChoiceItem("Эмба", "86", false, ChoiceType.CITY))

    }

}