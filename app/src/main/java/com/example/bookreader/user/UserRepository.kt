package com.example.bookreader.user

import android.content.Context
import com.example.bookreader.AppDatabase

class UserRepository(context : Context) {
    var db : UserDao? = AppDatabase.getInstance(context)?.userDao()

    fun insertUser(user : User) {
        db?.insertUser(user)
    }

    fun getUser (name : String) : List<User>? {
        return db?.getUser(name)
    }


    fun getEmail (name : String) : List<User>? {
        return db?.getEmail(name)
    }

    fun updateUser(user : User) {
        db?.updateUser(user)
    }
}