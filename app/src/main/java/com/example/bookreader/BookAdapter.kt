package com.example.bookreader

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bookreader.books.Book

class BookAdapter(private val onBookClick: (position: Int) -> Unit,
                  private val bookList : List<Book>) : RecyclerView.Adapter<ViewHolder>() {
    private val TYPE_BOOK = 0
    private val TYPE_SEE_MORE = 1
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.book_cards, parent, false)
        return ViewHolder(view, onBookClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    val bookVM = bookList[position]
        holder.title.text = bookVM.title
        holder.author.text = bookVM.author
    }

    override fun getItemCount(): Int {
        return bookList.size
    }

}

class ViewHolder(view : View, private val onBookClick: (position: Int) -> Unit)
    : RecyclerView.ViewHolder(view), View.OnClickListener {
    init {
        itemView.setOnClickListener(this)
    }
    val title : TextView = view.findViewById(R.id.book_title)
    val author : TextView = view.findViewById(R.id.book_author)

    override fun onClick(v: View?) {
        val position = adapterPosition
        onBookClick(position)
    }
}