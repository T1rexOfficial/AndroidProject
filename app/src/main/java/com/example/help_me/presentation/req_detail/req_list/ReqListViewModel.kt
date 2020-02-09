package com.example.help_me.presentation.req_detail.req_list

import androidx.lifecycle.ViewModel
import com.example.help_me.base.BaseViewModel
import com.example.help_me.base.Status
import com.example.help_me.entities.Req
import com.example.help_me.presentation.auth.registration.RegRepository
import com.example.help_me.presentation.home.REQUESTS
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ReqListViewModel() : BaseViewModel() {
    val database = FirebaseDatabase.getInstance().reference

    fun changeReq(request: Req) {
        statusMutableLiveData.value = Status.SHOW_LOADING
        database.child(REQUESTS).updateChildren(mapOf(request.key to request)) { databaseError, _ ->
            if (databaseError != null) {
                statusMutableLiveData.value = Status.ERROR
            } else {
                statusMutableLiveData.value = Status.SUCCESS
            }
        }
    }
}
