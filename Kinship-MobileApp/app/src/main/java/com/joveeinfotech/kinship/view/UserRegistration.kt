package com.joveeinfotech.kinship.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.AppCompatButton
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import com.joveeinfotech.kinship.*
import com.joveeinfotech.kinship.contract.KinshipContract.*
import com.joveeinfotech.kinship.presenter.RegisterPresenterImpl
import kotlinx.android.synthetic.main.activity_user_registration.*
import kotlinx.android.synthetic.main.alert_otp_get.*


class UserRegistration : AppCompatActivity(), RegisterView {

    var blood_group: String? = null

    var registerPresenter : RegisterPresenterImpl? = null

<<<<<<< HEAD
=======
    var buttonConfirmOTP: AppCompatButton? = null
    var buttonConfirmPassword: AppCompatButton? = null

    var editTextotp: EditText? = null
    var editTextpassword: EditText? = null
    var editTextConfirmPassword: EditText? = null
    var alertDialog1: AlertDialog? = null
>>>>>>> 7254323e07aeaf179f3466d36a74a262858c47aa

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_registration)
        registerPresenter = RegisterPresenterImpl(this, this)


        var categories = ArrayList<String>()
        categories.add("Select Blood Group")
        categories.add("AB+")
        categories.add("AB-")
        categories.add("A+")
        categories.add("A-")
        categories.add("B+")
        categories.add("B-")
        categories.add("O+")
        categories.add("O-")

        val dataAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, categories)
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner_blood_group.adapter = dataAdapter

        spinner_blood_group.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                blood_group = categories.get(position).toString()
                //Toast.makeText(applicationContext,blood_group,Toast.LENGTH_LONG).show()
            }
        }

        button_register.setOnClickListener {
            registerPresenter?.userPhoneNumberAndBloodGroup(editText_phone_number.text.toString(), blood_group!!)
        }
    }

    override fun confirmOTP() {
        val li = LayoutInflater.from(this)
        val confirmDialog = li.inflate(com.joveeinfotech.kinship.R.layout.alert_otp_get, null)
        buttonConfirmOTP = confirmDialog.findViewById<AppCompatButton>(com.joveeinfotech.kinship.R.id.buttonConfirmOTP) as AppCompatButton
        editTextotp = confirmDialog.findViewById<EditText>(com.joveeinfotech.kinship.R.id.editTextOtp) as EditText

        val alert = AlertDialog.Builder(this)
        alert.setView(confirmDialog)

        val alertDialog = alert.create()
        alertDialog.show()
        alertDialog.setCancelable(false)

        buttonConfirmOTP!!.setOnClickListener {
            registerPresenter?.OtpContent(editTextotp?.text.toString())
        }
    }

    override fun confirmPassword() {
        val li1 = LayoutInflater.from(this)
        val confimDialog1 = li1.inflate(com.joveeinfotech.kinship.R.layout.alert_password_get, null)
        buttonConfirmPassword = confimDialog1.findViewById<AppCompatButton>(com.joveeinfotech.kinship.R.id.buttonConfirmPassword) as AppCompatButton
        editTextpassword = confimDialog1.findViewById<EditText>(com.joveeinfotech.kinship.R.id.editText_password) as EditText
        editTextConfirmPassword = confimDialog1.findViewById<EditText>(com.joveeinfotech.kinship.R.id.editText_confirm_password) as EditText

        val alert1 = AlertDialog.Builder(this)
        alert1.setView(confimDialog1)

        alertDialog1 = alert1.create()
        alertDialog1?.show()
        alertDialog1?.setCancelable(false)

        buttonConfirmPassword!!.setOnClickListener {
            if (editTextpassword?.text.toString() == editTextConfirmPassword?.text.toString()) {
                registerPresenter?.passwordContent(editTextpassword?.text.toString(),editText_phone_number.text.toString())
            }
        }
    }

    /*private fun userRegister1() {
        progressDialog = ProgressDialog(this@UserRegistration, R.style.MyAlertDialogStyle)
        progressDialog?.setMessage("Registering...")
        progressDialog?.show()
        mCompositeDisposable=mApiInterface?.UserRegister(editText_phone_number.text.toString(), this!!.blood_group!!)!!.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                        { result ->
                            progressDialog?.dismiss()
                            Toast.makeText(applicationContext,"${result.otp}",Toast.LENGTH_LONG).show()
                            //otp = result.otp
                            //user_id = result.user_id
                            if(result.status == true)
                            {
                                confirmotp()
                            }
                        },
                        { error ->
                            progressDialog?.dismiss()
                            //progressBar.visibility=View.GONE
                            Toast.makeText(this, error.localizedMessage, Toast.LENGTH_LONG).show()
                            Toast.makeText(this, error.message, Toast.LENGTH_LONG).show()
                            //displayLog("error")
                        }
                )
    }
*/

    override fun closeActivity() { finish() }
}

