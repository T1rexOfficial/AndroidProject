package com.example.help_me.presentation.profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.help_me.App
import com.example.help_me.R
import com.example.help_me.base.BaseFragment
import com.example.help_me.extensions.loadImage
import kotlinx.android.synthetic.main.activity_profile_fragment.*

class ProfileFragment : BaseFragment() {

    override fun layoutId() = R.layout.activity_profile_fragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (App.company == null) {
            photoAccountImageView.loadImage(App.user?.photoURL.orEmpty(), context ?: return)
            if (App.user?.photoURL == null)
                photoAccountImageView.loadImage("https://lh3.googleusercontent.com/proxy/L-W2GAX4qpB4eO_knqf-dXvo1wqlcG4aSdpSS1N4Ubsl9NCn5YfU0zYN0Di3ARI0W_Jev753uwhxKMZeB7lgiUAFxvjIqmau8jJKgkLlBd3cBTPWN2Kh37meKFS5g7u37NOAlgjsTlV_CvHbFIpRiqws5WCnLPDhbgf10zbn3MGwbPJJExOn6GA", context ?: return)
        }
        else {
            photoAccountImageView.loadImage(App.company?.photoURL.orEmpty(), context?:return)
            if (App.company?.photoURL == null)
                photoAccountImageView.loadImage("https://cdn3.iconfinder.com/data/icons/business-people-2/512/Business_Group-512.png", context ?: return)
        }

        if (App.company == null) {
            profileTextNamesurname.text = App.user?.name + " " + App.user?.surname + "(" + App.user?.age + ")"
            profileOtherInfomation.text = App.user?.city
            ProfileEmailText1.text = App.user?.login
        } else {
            profileTextNamesurname.text = App.company?.title + " (" + App.company?.city + ")"
            profileOtherInfomation.text = App.company?.website
            ProfileEmailText1.text = App.company?.login
        }
    }

}
