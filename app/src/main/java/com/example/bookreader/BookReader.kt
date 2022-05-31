package com.example.bookreader

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.widget.TextView

class BookReader : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_reader)

        val chapterTitle : TextView = findViewById(R.id.chapter_title)
        val bookText : TextView = findViewById(R.id.book_text)

        chapterTitle.text = intent.getStringExtra("chapter")
        var sampleText = ""
        for (i in 1..200)
            sampleText += "Sample Text $i "
        bookText.text = sampleText
        bookText.movementMethod = ScrollingMovementMethod()
    }
}