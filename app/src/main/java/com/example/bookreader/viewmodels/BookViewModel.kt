package com.example.bookreader.viewmodels

import android.app.Application
import android.util.JsonReader
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.bookreader.books.Book
import com.example.bookreader.books.BookRepository
import kotlinx.coroutines.launch

class BookViewModel(app : Application) : AndroidViewModel(app) {
    var books : MutableLiveData<List<Book>>? = MutableLiveData()
    private var repo : BookRepository
    private var currentPage = 0
    private val LIMIT = 10
    init {
        repo = BookRepository(app)
        getPage(LIMIT, LIMIT * currentPage)

        repo.deleteAllBooks()
        for (i in 0..50) {
            repo.insertBooks(Book(i,"Title$i","Author$i","Summary$i",i))
        }

    }
    fun getPage(limit : Int, offset : Int) = viewModelScope.launch   {
        books?.value = repo.getPage(limit, offset)
    }

    fun nextPage()  {
        currentPage++
        getPage(LIMIT*currentPage, 0)
    }

    fun search(searchQuery : String) {
        if (searchQuery == "")
            getPage(LIMIT*currentPage, 0)
        else
            books?.value = repo.search(searchQuery)
    }

    fun getChapters(numChapters : Int) : ArrayList<String> {
        var chapterList = ArrayList<String>()
        for (i in 0..numChapters) {
            chapterList.add("Chapter ${i+1}")
        }
        return chapterList
    }

}
