package com.example.help_me.presentation.main

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import com.example.help_me.base.BaseViewModel
import com.example.help_me.entities.Req
import com.example.help_me.presentation.auth.registration.RegRepository
import com.example.help_me.presentation.home.REQUESTS
import com.google.firebase.database.FirebaseDatabase

class AddReqViewModel(val repository: RegRepository) : BaseViewModel() {

    val database = FirebaseDatabase.getInstance().reference
    var ReqLiveData = MutableLiveData<List<Req>>()
    val picList = ArrayList<String>()


    fun addReq(req: Req) {
        database.child(REQUESTS).push().key?.let {
            database.child(REQUESTS).child(it).setValue(req)
        }
    }

    val downloadUriLiveData = MutableLiveData<ArrayList<String>>()

    fun uploadFile(uri: Uri) {
        makeRequest({repository.uploadFile(uri)}){ res->
            unwrap(res){uri->
                picList.add(uri.toString())
                downloadUriLiveData.value = picList
            }
        }
    }


}