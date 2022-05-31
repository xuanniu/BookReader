package com.example.bookreader.books

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.bookreader.user.User

@Dao
interface BookDao {
    @Query("select * from Book")
    fun getAllBooks() : LiveData<List<Book>>

    @Insert
    fun insertBooks(book : Book)

    @Query("delete from Book")
    fun deleteAllBooks()

    @Query("select * from Book limit :limit offset :offset")
    fun getPage(limit : Int, offset : Int) : List<Book>

    @Query("select * from book " +
            "join book_fts on book.title = book_fts.title" +
            " where book_fts match :searchQuery")
    fun search(searchQuery : String) : List<Book>
}