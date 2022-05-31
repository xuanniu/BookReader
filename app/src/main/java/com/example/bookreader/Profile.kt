package com.example.bookreader

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.bookreader.viewmodels.UserViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class Profile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        val username : TextView = findViewById(R.id.username_profile)
        val email : TextView = findViewById(R.id.email_profile)
        val menuBar : BottomNavigationView = findViewById(R.id.bottom_navigation)
        val changePassword : Button = findViewById(R.id.change_password_button)
        username.text = UserViewModel.user!!.username
        email.text = UserViewModel.user!!.email

        val mOnNavigationItemSelectedListener=
            BottomNavigationView.OnNavigationItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.profile -> {
                        val intent = Intent(this,Profile::class.java)
                        startActivity(intent)
                    }
                    R.id.library -> {
                        val intent = Intent(this,Home::class.java)
                        startActivity(intent)
                    }
                }
                false
            }
        menuBar.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        changePassword.setOnClickListener {
            val intent = Intent(this,ChangePassword::class.java)
            startActivity(intent)
        }
    }
}