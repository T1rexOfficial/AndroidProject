package com.example.help_me.presentation.main

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_reg.progressBar
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.example.help_me.R
import com.example.help_me.base.Status
import com.example.help_me.entities.Req
import com.theartofdev.edmodo.cropper.CropImage
import kotlinx.android.synthetic.main.activity_add_req.*
import org.koin.androidx.viewmodel.ext.android.getViewModel
import com.example.help_me.presentation.home.HomeFragment


class AddReqActivity : AppCompatActivity() {

    val permissions = arrayOf(
        Manifest.permission.CAMERA,
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )

    private lateinit var viewModel: AddReqViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_req)
        viewModel = getViewModel()
        toolbarAddReqBack.setOnClickListener {
            onBackPressed()
        }

        reg_photoAccauntBoxAddReq.setOnClickListener {
            setAccountListener()
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

        id_button_save_AddReq.setOnClickListener {
            val text0 = id_edit_text_AddTitle.text.toString()
            val text1 = id_edit_text_AddR.text.toString()
            val text2 = id_address_edit_text.text.toString()
            val text3 = id_edit_text_AddR_Op.text.toString()

            val req = Req(
                titleReq = text0,
                addressd = text2,
                addressReq = text1,
                explainReq = text3,
                pictureUrls = viewModel.downloadUriLiveData.value
            )
            viewModel.addReq(req)
            onBackPressed()
        }
    }

    private fun setAccountListener() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_DENIED) {
            CropImage.activity().start(this)
        } else {
            requestCameraPermission()
        }
    }

    private fun requestCameraPermission() {
        if (!ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE) ||
            !ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) ||
            !ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)
        ) {
            ActivityCompat.requestPermissions(this, permissions, 120)
            return
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            try {
                when (requestCode) {
                    CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE -> {
                        val result = CropImage.getActivityResult(data)
                        photoAccountImageViewAddReq.setImageURI(result.uri)
                        viewModel.uploadFile(result.uri)

                    }
                    CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE -> {
                        val result = CropImage.getActivityResult(data)
                        Toast.makeText(this, "Ошибка ${result.error}", Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (e: Exception) {
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show()
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

}
