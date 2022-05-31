package com.example.bookreader.books

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo
import androidx.room.Fts4

@Entity
data class Book(@PrimaryKey(autoGenerate = true) var bookId : Int?,
                @ColumnInfo(name = "title") var title : String,
                @ColumnInfo(name = "author") var author : String,
                @ColumnInfo(name = "summary") var summary : String,
                @ColumnInfo(name = "chapters") var chapters : Int)

@Entity(tableName = "book_fts")
@Fts4(contentEntity = Book::class)
data class BookFTS(@ColumnInfo(name = "bookId") var bookId : Int?,
                   @ColumnInfo(name = "title") var title : String,
                   @ColumnInfo(name = "author") var author : String,
                   @ColumnInfo(name = "summary") var summary : String,
                   @ColumnInfo(name = "chapters") var chapters : Int)
