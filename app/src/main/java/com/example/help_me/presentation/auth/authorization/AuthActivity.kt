package com.example.help_me.presentation.auth.authorization

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.example.help_me.Main2Activity
import com.example.help_me.R
import com.example.help_me.base.Status
import com.example.help_me.extensions.alert
import com.example.help_me.extensions.toast
import com.example.help_me.presentation.main.MainActivity
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import kotlinx.android.synthetic.main.activity_auth.*
import kotlinx.android.synthetic.main.activity_auth.progressBar
import kotlinx.android.synthetic.main.activity_reg.*
import org.koin.androidx.viewmodel.ext.android.getViewModel


class AuthActivity : AppCompatActivity() {

    lateinit var viewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        viewModel = getViewModel()

        authBtnIn.setOnClickListener{
            authBtnIn.isEnabled = false
            viewModel.login(
                    authMailEditId.text.toString(),
                    authPassEditId.text.toString()
            )
            viewModel.statusMutableLiveData.observe(this, Observer {
                when(it){
                    Status.SHOW_LOADING -> {
                        progressBar.visibility = View.VISIBLE
                    }
                    Status.HIDE_LOADING -> {
                        progressBar.visibility = View.GONE
                    }
                }
            })
        }

        /**
         * Показывает тост сообщение при успешной авторизации
         */

        viewModel.loginLiveData.observe(this, Observer {
            toast(it)
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        })

        /**
         * для отоброжения ошибок
         */
        viewModel.messageLiveData.observe(this, Observer {
            authBtnIn.isEnabled = true
            alert(message = it)
        })

    }


    override fun onResume() {
        super.onResume()
        authBtnIn.isEnabled = true
    }
}
