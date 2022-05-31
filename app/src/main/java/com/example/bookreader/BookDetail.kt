package com.example.bookreader

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bookreader.viewmodels.BookViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class BookDetail : AppCompatActivity() {
    lateinit var adapter : ChapterAdapter
    lateinit var recyclerView : RecyclerView
    lateinit var vm : BookViewModel

    var chapterList = ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_detail)

        var title : TextView = findViewById(R.id.title_book_detail)
        var author : TextView = findViewById(R.id.author_book_detail)
        val menuBar : BottomNavigationView = findViewById(R.id.bottom_navigation)
        vm = BookViewModel(application)
        chapterList = vm.getChapters(intent.getIntExtra("chapters",0))


        title.setText(intent.getStringExtra("title"))
        author.setText(intent.getStringExtra("author"))

        recyclerView = findViewById(R.id.chapter_view)


        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = ChapterAdapter({position -> onChapterClick(position)},chapterList)
        recyclerView.adapter = adapter


        val mOnNavigationItemSelectedListener=
            BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.profile -> {
                    val intent = Intent(this,Profile::class.java)
                    startActivity(intent)
                }
                R.id.library -> {
                    val intent = Intent(this,Home::class.java)
                    startActivity(intent)
                }
            }
            false
        }
        menuBar.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    fun onChapterClick(position : Int) {
        val intentNext = Intent(this, BookReader::class.java)
        intentNext.putExtra("chapter", chapterList.get(position))
        startActivity(intentNext)
    }
}