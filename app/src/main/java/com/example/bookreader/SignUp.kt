package com.example.bookreader

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doAfterTextChanged
import com.example.bookreader.viewmodels.UserViewModel

class SignUp : AppCompatActivity() {
//    lateinit var username : EditText
//    lateinit var email : EditText
//    lateinit var password : EditText
//    lateinit var passwordConfirm : EditText
//    lateinit var register : Button
    lateinit var vm : UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        val username : EditText = findViewById(R.id.username_s)
        val usernameError : TextView = findViewById(R.id.usernameError)
        val email : EditText = findViewById(R.id.email)
        val emailError : TextView = findViewById(R.id.emailError)
        val password : EditText = findViewById(R.id.password_s)
        val passwordConfirm : EditText  = findViewById(R.id.password_s2)
        val passwordError : TextView = findViewById(R.id.passwordError)
        val register : Button  = findViewById(R.id.register)
        val returnButton : Button  = findViewById(R.id.return_button)
        val resetButton : Button = findViewById(R.id.reset_button)
        vm = UserViewModel(application)

        vm.usernameValidationText.observe(this, {
            usernameError.text = it
        })
        vm.usernameValidationColor.observe(this, {
            usernameError.setTextColor(it)
        })
        vm.emailValidationText.observe(this, {
            emailError.text = it
        })
        vm.emailValidationColor.observe(this, {
            emailError.setTextColor(it)
        })
        vm.passwordValidationText.observe(this, {
            passwordError.text = it
        })

        username.addTextChangedListener {
            vm.validateUsername(username.text.toString()) }
        email.addTextChangedListener {
            vm.validateEmail(email.text.toString()) }
        passwordConfirm.addTextChangedListener {
            vm.validatePassword(password.text.toString(),passwordConfirm.text.toString()) }

        register.setOnClickListener {
            val registered = vm.register(username.text.toString(),email.text.toString(),
                password.text.toString(),passwordConfirm.text.toString())
            if (registered)
                Toast.makeText(this, "You have successfully signed up", Toast.LENGTH_LONG).show()
            else
                Toast.makeText(this, "There is an error signing you up, please check your" +
                        "information again", Toast.LENGTH_LONG).show()
//            register.setOnClickListener(null)
//            println("test")
//            val intentNext = Intent(this, MainActivity::class.java)
//            startActivity(intentNext)
        }

        returnButton.setOnClickListener {
            val intentNext = Intent(this, MainActivity::class.java)
            startActivity(intentNext)
        }
        resetButton.setOnClickListener {
            username.setText("")
            email.setText("")
            password.setText("")
            passwordConfirm.setText("")
        }




    }

}