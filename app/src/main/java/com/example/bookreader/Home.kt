package com.example.bookreader

import android.content.ClipData
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bookreader.books.Book
import com.example.bookreader.viewmodels.BookViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class Home : AppCompatActivity() {
    var bookList = ArrayList<Book>()
    lateinit var vm : BookViewModel
    lateinit var adapter : BookAdapter
    lateinit var recyclerView : RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val seeMore : Button = findViewById(R.id.see_more)
        val searchBar : EditText = findViewById(R.id.search_bar)
        val menuBar : BottomNavigationView = findViewById(R.id.bottom_navigation)
        vm = BookViewModel(application)
        recyclerView = findViewById(R.id.recyclerView)


        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = BookAdapter({position -> onBookClick(position)},bookList)
        recyclerView.adapter = adapter

        vm.books?.observe(this, {
            books -> getBooks(books)
        })

        searchBar.addTextChangedListener {
            vm.search(searchBar.text.toString())
        }
        seeMore.setOnClickListener {
            vm.nextPage()
        }
        val mOnNavigationItemSelectedListener=BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.profile -> {
                    val intent = Intent(this,Profile::class.java)
                    startActivity(intent)
                }
            }
            false
        }
        menuBar.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
//        menuBar.setOnMenuItemClickListener {
//            when(it.itemId) {
//                R.id.profile -> {
//                    startActivity(Intent(this, Profile::class.java))
//                    true
//                }
//                else -> false
//            }
//        }
    }

    fun onBookClick(position:Int) {
        val intentNext = Intent(this, BookDetail::class.java)
        intentNext.putExtra("title", bookList[position].title)
        intentNext.putExtra("author", bookList[position].author)
        intentNext.putExtra("chapters", bookList[position].chapters)
        startActivity(intentNext)
    }

    fun getBooks(books : List<Book>) {
        bookList.clear()
        this.bookList.addAll(books)
        adapter.notifyDataSetChanged()
    }
}