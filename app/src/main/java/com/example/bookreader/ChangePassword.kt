package com.example.bookreader

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.bookreader.viewmodels.BookViewModel
import com.example.bookreader.viewmodels.UserViewModel

class ChangePassword : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)
        val vm = UserViewModel(application)
        val oldPassword : EditText = findViewById(R.id.changePassword_old)
        val newPassword : EditText = findViewById(R.id.changePassword_new)
        val confirmPassword : EditText = findViewById(R.id.changePassword_confirm)
        val submit : Button = findViewById(R.id.submit_change)
        val returnButton : Button = findViewById(R.id.return_change)

        submit.setOnClickListener {
            val updated = vm.updateUser(oldPassword.text.toString(),newPassword.text.toString(),confirmPassword.text.toString())
            if (updated)
                Toast.makeText(this, "You password has been successfully changed", Toast.LENGTH_LONG).show()
            else
                Toast.makeText(this, "There was an error changing your password", Toast.LENGTH_LONG).show()
        }

        returnButton.setOnClickListener {
            val intentNext = Intent(this, Profile::class.java)
            startActivity(intentNext)
        }
    }
}