package com.example.bookreader

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.bookreader.books.Book
import com.example.bookreader.books.BookDao
import com.example.bookreader.books.BookFTS
import com.example.bookreader.user.User
import com.example.bookreader.user.UserDao

@Database(entities = [User::class, Book::class, BookFTS::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao() : UserDao
    abstract fun bookDao() : BookDao

    companion object {
        var INSTANCE: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase? {
            destroyInstance()
            if(INSTANCE == null) {
                //6 we are acquiring an instance of RoomDB builder
                synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "bookreader.db")
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE
        }
        fun destroyInstance() {
            INSTANCE = null
        }
    }
}