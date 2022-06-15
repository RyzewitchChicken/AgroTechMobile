package com.example.agrotech

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.*
import android.widget.TextView.OnEditorActionListener
import androidx.appcompat.app.AppCompatActivity
import com.example.agrotech.MainActivity.Companion.globalVar
import com.example.agrotech.interfaces.UserService
import com.example.agrotech.models.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ClientEdit: AppCompatActivity() {


    lateinit var lastn:String
    lateinit var profileIm:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.client_edit)
        val button = findViewById<ImageView>(R.id.back)
        button.setOnClickListener {
            val intent = Intent(this, ClientData::class.java)
            startActivity(intent)
        }
        val name = findViewById<EditText>(R.id.namedit)
        val dni = findViewById<EditText>(R.id.dniedit)
        val email = findViewById<EditText>(R.id.emailedit)
        val pass = findViewById<EditText>(R.id.passedit)
        val phone = findViewById<EditText>(R.id.phoneedit)


        val button_edit = findViewById<ImageView>(R.id.imageedit)
        val button_dni = findViewById<ImageView>(R.id.imagedni)
        val button_pass = findViewById<ImageView>(R.id.imagepass)
        val button_phone = findViewById<ImageView>(R.id.imagephone)
        val button_email = findViewById<ImageView>(R.id.imageemail)

        //val send = findViewById<ImageView>(R.id.send)
        val userService: UserService = Retro().getRetroClient().create(UserService::class.java)
        userService.getUserData(globalVar).enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>?, response: Response<User>?) {

                if (response != null) {
                    name.setText(response.body().name)
                    email.setText(response.body().email)
                    dni.setText(response.body().dni.toString())
                    pass.setText(response.body().password)
                    phone.setText(response.body().cellphoneNumber.toString())
                    lastn=response.body().lastname.toString()
                    profileIm=response.body().profileImage.toString()

                }
            }

            override fun onFailure(call: Call<User>?, t: Throwable?) {
                Toast.makeText(this@ClientEdit, "Error", Toast.LENGTH_LONG).show()
            }

        })


        button_edit.setOnClickListener {
            name.isFocusableInTouchMode = true
        }
        button_dni.setOnClickListener{
            dni.isFocusableInTouchMode = true
        }
        button_pass.setOnClickListener{
            pass.isFocusableInTouchMode = true
        }
        button_phone.setOnClickListener{
            phone.isFocusableInTouchMode = true
        }
        button_email.setOnClickListener{
            email.isFocusableInTouchMode = true
        }
        name.setOnEditorActionListener(OnEditorActionListener { v, actionId, event ->
            if (event != null && event.keyCode === KeyEvent.KEYCODE_ENTER || actionId == EditorInfo.IME_ACTION_DONE) {
                val userData = User()
                userData.name = name.text.toString()
                userData.email = email.text.toString()
                userData.dni = dni.text.toString().toInt()
                userData.password = pass.text.toString()
                userData.cellphoneNumber = phone.text.toString().toInt()
                userData.lastname = lastn
                userData.profileImage = profileIm
                userData.accessType = true
                userService.updateUserData(globalVar, userData)  .enqueue(object: Callback<User> {
                    override fun onResponse(call: Call<User>?, response: Response<User>?) {
                        Toast.makeText(this@ClientEdit,"Datos Actualizados", Toast.LENGTH_LONG).show()


                    }

                    override fun onFailure(call: Call<User>?, t: Throwable?) {
                        Toast.makeText(this@ClientEdit,"Error", Toast.LENGTH_LONG).show()
                    }

                })
            }
            false
        })
        email.setOnEditorActionListener(OnEditorActionListener { v, actionId, event ->
            if (event != null && event.keyCode === KeyEvent.KEYCODE_ENTER || actionId == EditorInfo.IME_ACTION_DONE) {
                val userData = User()
                userData.name = name.text.toString()
                userData.email = email.text.toString()
                userData.dni = dni.text.toString().toInt()
                userData.password = pass.text.toString()
                userData.cellphoneNumber = phone.text.toString().toInt()
                userData.lastname = lastn
                userData.profileImage = profileIm
                userData.accessType = true
                userService.updateUserData(globalVar, userData)  .enqueue(object: Callback<User> {
                    override fun onResponse(call: Call<User>?, response: Response<User>?) {
                        Toast.makeText(this@ClientEdit,"Datos Actualizados", Toast.LENGTH_LONG).show()


                    }

                    override fun onFailure(call: Call<User>?, t: Throwable?) {
                        Toast.makeText(this@ClientEdit,"Error", Toast.LENGTH_LONG).show()
                    }

                })
            }
            false
        })
        dni.setOnEditorActionListener(OnEditorActionListener { v, actionId, event ->
            if (event != null && event.keyCode === KeyEvent.KEYCODE_ENTER || actionId == EditorInfo.IME_ACTION_DONE) {
                val userData = User()
                userData.name = name.text.toString()
                userData.email = email.text.toString()
                userData.dni = dni.text.toString().toInt()
                userData.password = pass.text.toString()
                userData.cellphoneNumber = phone.text.toString().toInt()
                userData.lastname = lastn
                userData.profileImage = profileIm
                userData.accessType = true
                userService.updateUserData(globalVar, userData)  .enqueue(object: Callback<User> {
                    override fun onResponse(call: Call<User>?, response: Response<User>?) {
                        Toast.makeText(this@ClientEdit,"Datos Actualizados", Toast.LENGTH_LONG).show()


                    }

                    override fun onFailure(call: Call<User>?, t: Throwable?) {
                        Toast.makeText(this@ClientEdit,"Error", Toast.LENGTH_LONG).show()
                    }

                })
            }
            false
        })
        pass.setOnEditorActionListener(OnEditorActionListener { v, actionId, event ->
            if (event != null && event.keyCode === KeyEvent.KEYCODE_ENTER || actionId == EditorInfo.IME_ACTION_DONE) {
                val userData = User()
                userData.name = name.text.toString()
                userData.email = email.text.toString()
                userData.dni = dni.text.toString().toInt()
                userData.password = pass.text.toString()
                userData.cellphoneNumber = phone.text.toString().toInt()
                userData.lastname = lastn
                userData.profileImage = profileIm
                userData.accessType = true
                userService.updateUserData(globalVar, userData)  .enqueue(object: Callback<User> {
                    override fun onResponse(call: Call<User>?, response: Response<User>?) {
                        Toast.makeText(this@ClientEdit,"Datos Actualizados", Toast.LENGTH_LONG).show()


                    }

                    override fun onFailure(call: Call<User>?, t: Throwable?) {
                        Toast.makeText(this@ClientEdit,"Error", Toast.LENGTH_LONG).show()
                    }

                })
            }
            false
        })
        phone.setOnEditorActionListener(OnEditorActionListener { v, actionId, event ->
            if (event != null && event.keyCode === KeyEvent.KEYCODE_ENTER || actionId == EditorInfo.IME_ACTION_DONE) {
                val userData = User()
                userData.name = name.text.toString()
                userData.email = email.text.toString()
                userData.dni = dni.text.toString().toInt()
                userData.password = pass.text.toString()
                userData.cellphoneNumber = phone.text.toString().toInt()
                userData.lastname = lastn
                userData.profileImage = profileIm
                userData.accessType = true
                userService.updateUserData(globalVar, userData)  .enqueue(object: Callback<User> {
                    override fun onResponse(call: Call<User>?, response: Response<User>?) {
                        Toast.makeText(this@ClientEdit,"Datos Actualizados", Toast.LENGTH_LONG).show()


                    }

                    override fun onFailure(call: Call<User>?, t: Throwable?) {
                        Toast.makeText(this@ClientEdit,"Error", Toast.LENGTH_LONG).show()
                    }

                })
            }
            false
        })


    }
}