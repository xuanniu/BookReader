package com.example.bookreader.viewmodels

import android.app.Application
import android.graphics.Color
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.bookreader.user.User
import com.example.bookreader.user.UserRepository
import kotlinx.coroutines.launch

class UserViewModel(app : Application) : AndroidViewModel(app) {
    private val repo : UserRepository
    var usernameValidationText = MutableLiveData<String>()
    var usernameValidationColor = MutableLiveData<Int>()
    var emailValidationText = MutableLiveData<String>()
    var emailValidationColor = MutableLiveData<Int>()
    var passwordValidationText = MutableLiveData<String>()

    init {
        repo = UserRepository(app)
        usernameValidationText.value = ""
        usernameValidationColor.value = Color.parseColor("#FF0000")
        emailValidationText.value = ""
        emailValidationColor.value = Color.parseColor("#FF0000")
        passwordValidationText.value = ""
    }

    fun validateUsername(username : String) {
        if (usernameExists(username)) {
            usernameValidationText.value = "This username already exists"
            usernameValidationColor.value = Color.parseColor("#FF0000")
        } else {
            usernameValidationText.value = "Username available"
            usernameValidationColor.value = Color.parseColor("#00FF00")

        }
    }

    private fun usernameExists(username : String) : Boolean {
        val matchingUsers = repo.getUser(username)
        return !(matchingUsers == null || matchingUsers.isEmpty())
    }


    fun validateEmail(email : String) {
        if (emailExists(email)) {
            emailValidationText.value = "This email already exists"
            emailValidationColor.value = Color.parseColor("#FF0000")
        } else {
            emailValidationText.value = "Email available"
            emailValidationColor.value = Color.parseColor("#00FF00")

        }
    }

    private fun emailExists(email : String) : Boolean {
        val matchingEmail = repo.getEmail(email)
        return !(matchingEmail == null || matchingEmail.isEmpty())
    }


    fun validatePassword(originalPassword : String, confirmedPassword : String) {
        if (originalPassword != confirmedPassword)
            passwordValidationText.value = "Your passwords does not match"
        else
            passwordValidationText.value = ""
    }

    fun register(username : String, email : String, password : String, passwordConfirm : String) : Boolean{
        if (!usernameExists(username) && !emailExists(email) && password == passwordConfirm) {
            repo.insertUser(User(null, username, email, password))
            return true
        }
        return false
    }

    fun login(username : String, password : String) : Boolean {
        var userList = repo.getUser(username)
        if (userList != null && !userList.isEmpty() && userList[0].password == password) {
            user = userList[0]
            return true
        } else {
            return false
        }

       // return userList != null && !userList.isEmpty() && userList[0].password == password
    }

    fun updateUser(passwordOld : String, passwordNew : String, passwordConfirm : String) : Boolean {
        if(user!!.password == passwordOld && passwordNew == passwordConfirm) {
            val updateUser = User(user!!.userId,user!!.username,user!!.email,passwordNew)
            repo.updateUser(updateUser)
            user = updateUser
            return true
        }
        return false
    }

    companion object {
        var user : User? = null
    }



}