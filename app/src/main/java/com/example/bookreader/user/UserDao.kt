package com.example.bookreader.user

import androidx.room.*

@Dao
interface UserDao {

    @Insert
    fun insertUser(user : User)

    @Query ("select * from User where :name == username")
    fun getUser(name : String) : List<User>

    @Query ("select * from User where :email == email")
    fun getEmail(email : String) : List<User>

    @Update
    fun updateUser(user : User)

    @Delete
    fun deleteUser(user : User)

}