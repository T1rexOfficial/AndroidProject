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
        photoAccountImageView.loadImage(App.user?.photoURL.orEmpty(), context?:return)
//        photoAccountImageView.loadImage(App.company?.photoURL.orEmpty(), context?:return)
    }

}
