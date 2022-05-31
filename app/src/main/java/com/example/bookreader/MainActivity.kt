package com.example.bookreader

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.bookreader.viewmodels.UserViewModel

class MainActivity : AppCompatActivity() {
    lateinit var vm : UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val username : EditText = findViewById(R.id.username)
        val password : EditText  = findViewById(R.id.password)
        val login : Button = findViewById(R.id.login)
        val signUp : TextView  = findViewById(R.id.sign_up)
        vm = UserViewModel(application)

        login.setOnClickListener {
            if (vm.login(username.text.toString(), password.text.toString())) {
                val intentNext = Intent(this, Home::class.java)
                startActivity(intentNext)
            } else {
                Toast.makeText(this, "The username or password is incorrect", Toast.LENGTH_LONG).show()
            }
        }

        signUp.setOnClickListener {
            val intentNext = Intent(this, SignUp::class.java)
            startActivity(intentNext)
        }
    }
}