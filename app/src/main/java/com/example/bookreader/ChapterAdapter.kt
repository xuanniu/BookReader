package com.example.bookreader

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bookreader.books.Book

class ChapterAdapter(private val onChapterClick: (position: Int) -> Unit,
                  private val chapterList : List<String>) : RecyclerView.Adapter<ChapterViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChapterViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.chapter_cards, parent, false)
        return ChapterViewHolder(view, onChapterClick)
    }

    override fun onBindViewHolder(holder: ChapterViewHolder, position: Int) {
        val chapter = chapterList[position]
        holder.chapter.text = chapter
    }

    override fun getItemCount(): Int {
        return chapterList.size
    }

}

class ChapterViewHolder(view : View, private val onChapterClick: (position: Int) -> Unit)
    : RecyclerView.ViewHolder(view), View.OnClickListener {
    init {
        itemView.setOnClickListener(this)
    }
    val chapter : TextView = view.findViewById(R.id.chapter)

    override fun onClick(v: View?) {
        val position = adapterPosition
        onChapterClick(position)
    }
}