package com.example.help_me.presentation.auth.registration

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.example.help_me.Main2Activity
import com.example.help_me.R
import com.example.help_me.extensions.alert
import com.example.help_me.extensions.toast
import com.example.help_me.presentation.auth.pre.PreActivity
import com.example.help_me.presentation.dialogs.ChoiceItem
import com.example.help_me.presentation.dialogs.ChoiceType
import com.example.help_me.presentation.dialogs.MultiChoiceDialogFragment
import com.example.help_me.presentation.main.MainActivity
import com.theartofdev.edmodo.cropper.CropImage
import id.zelory.compressor.Compressor
import kotlinx.android.synthetic.main.activity_reg.*
import org.koin.androidx.viewmodel.ext.android.getViewModel
import java.io.File

class RegActivity : AppCompatActivity(), MultiChoiceDialogFragment.DataReceiver {

    private var cityCode = ""

    private var isUser = true
    private var gender = "male"

    private lateinit var viewModel: RegViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reg)
        toolbarRegBack.setOnClickListener {
            onBackPressed()
        }

        reg_radio_button_male_all.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.reg_radio_button_male -> {
                    gender = "male"
                }
                R.id.reg_radio_button_female -> {
                    gender = "female"
                }
            }

        }




        viewModel = getViewModel()

        setCityChoices()


        reg_radio_button.setOnCheckedChangeListener { reg_radio_button, i->
            when (i) {
                R.id.reg_radio_button_human -> {
                    isUser = true

                    reg_const_company.visibility = View.GONE
                    reg_const_person.visibility = View.VISIBLE
                }
                R.id.reg_radio_button_company -> {
                    reg_const_company.visibility = View.VISIBLE
                    reg_const_person.visibility = View.GONE
                    isUser = false

                }
            }
        }

        reg_photoAccauntBox.setOnClickListener {
            setAccountListener()
        }

        regBtnReg.setOnClickListener {

            regBtnReg.isEnabled = false

            if (validate()){
                if (isUser == true) {

                    viewModel.register(
                        reg_MailEditId.text.toString(),
                        regPassEditId.text.toString(),
                        regCityEdit.text.toString(),
                        gender,
                        reg_edit_name_person.text.toString(),
                        reg_edit_surname_person.text.toString(),
                        reg_edit_surname_age.text.toString()
                    )
                } else {
                    viewModel.registerCompany (
                        reg_MailEditId.text.toString(),
                        regPassEditId.text.toString(),
                        regCityEdit.text.toString(),
                        reg_edit_name_company.text.toString(),
                        reg_edit_site_company.text.toString()
                    )
                }
            } else {
                this.toast("Пароли не совпадают!")
            }

        }

        /**
         * Показывает тост сообщение при успешной регистрации
         */
        viewModel.regLiveData.observe(this, Observer {
            toast(it)

            regBtnReg.isEnabled = true

            startActivity(Intent(this, MainActivity::class.java))
            finish()

        })

        /**
         * для отоброжения ошибок
         */
        viewModel.messageLiveData.observe(this, Observer {
            regBtnReg.isEnabled = true
            this.alert(message = it)
        })

    }

    private fun validate (): Boolean {
        return regPassEditId.text.toString() == regPassRewriteEdit.text.toString() && regPassEditId.text.toString().isNotEmpty()
    }

    private fun setAccountListener() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_DENIED) {
            CropImage.activity().start(this)
        } else {
            requestCameraPermission()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            try {
                when (requestCode) {
                    CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE -> {
                        val result = CropImage.getActivityResult(data)
                        photoAccountImageView.setImageURI(result.uri)
                        val file = Compressor(this).compressToFile(File(result.uri.path ?: ""))
                        //viewModel.uploadStatementFile(file)
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

    fun setDefaultState(editText: EditText) {
        editText.error = null
        editText.setBackgroundResource(R.drawable.selector_edittext)
    }


    val permissions = arrayOf(
        Manifest.permission.CAMERA,
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )

    private fun requestCameraPermission() {
        if (!ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE) ||
            !ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) ||
            !ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)
        ) {
            ActivityCompat.requestPermissions(this, permissions, 120)
            return
        }
    }

    override fun onDataSentFromDialog(choices: ArrayList<ChoiceItem>) {
        if (choices.isNotEmpty()) {
            when (choices[0].type) {
                ChoiceType.CITY -> {
                    choices.forEach {
                        if (it.chosen) {
                            cityCode = it.code
                            regCityEdit.setText(it.label)
                            return@forEach
                        }
                    }
                }
            }
        }
    }

    private fun setCityChoices() {
        regCityEdit.setOnClickListener {
            setDefaultState(regCityEdit)
            val fragment = MultiChoiceDialogFragment.Builder()
                .setSingleChoice(true)
                .setChoises(viewModel.cities)
                .setSearch(true)
                .setTitle("Выберите город")
                .setCallback(this)
                .build()

            val fragmentTransaction = supportFragmentManager.beginTransaction()
            fragment.show(fragmentTransaction, fragment.javaClass.name)
        }
    }


    override fun onResume() {
        super.onResume()
        regBtnReg.isEnabled = true
    }

}
