package com.example.bookreader.books

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.bookreader.AppDatabase
import com.example.bookreader.user.User
import com.example.bookreader.user.UserDao

class BookRepository(context : Context) {
    var db : BookDao? = AppDatabase.getInstance(context)?.bookDao()

    fun getAllBooks() : LiveData<List<Book>>? {
        return db?.getAllBooks()
    }

    fun insertBooks(book : Book) {
        db?.insertBooks(book)
    }

    fun deleteAllBooks() {
        db?.deleteAllBooks()
    }

    fun getPage(limit : Int, offset : Int) : List<Book>? {
        return db?.getPage(limit, offset)
    }

    fun search(searchQuery : String) : List<Book>? {
        return db?.search("*$searchQuery*")
    }
}