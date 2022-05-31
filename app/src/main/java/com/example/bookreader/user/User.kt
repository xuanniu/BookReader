package com.example.bookreader.user

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo

@Entity
data class User(@PrimaryKey(autoGenerate = true) var userId : Int?,
                @ColumnInfo(name = "username") var username : String,
                @ColumnInfo(name = "email") var email : String,
                @ColumnInfo(name = "password") var password : String)