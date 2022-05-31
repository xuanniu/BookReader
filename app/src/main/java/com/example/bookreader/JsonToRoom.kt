//package com.example.bookreader
//
//import android.content.Context
//import androidx.room.RoomDatabase
//import androidx.sqlite.db.SupportSQLiteDatabase
//import com.example.bookreader.books.Book
//import org.json.JSONArray
//import org.json.JSONException
//import java.io.BufferedReader
//
//class JsonToRoom(private val context: Context) : RoomDatabase.Callback() {
//    override fun onCreate(db: SupportSQLiteDatabase) {
//        super.onCreate(db)
//
//    }
//    private fun loadJSONArray(context: Context): JSONArray?{
//
//        val inputStream = context.resources.openRawResource(R.raw.books)
//
//        BufferedReader(inputStream.reader()).use {
//            return JSONArray(it.readText())
//        }
//    }
//    private suspend fun fillWithStartingNotes(context: Context){
//
//        val dao = AppDatabase.getInstance(context)?.bookDao()
//
//        try {
//            val book = loadJSONArray(context)
//            if (book != null){
//                for (i in 0 until book.length()){
//                    val item = book.getJSONObject(i)
//                    val id = item.getInt("id")
//                    val title = item.getString("title")
//                    val author = item.getString("author")
//                    val summary = item.getString("summary")
//                    val chapters = item.getInt("chapters")
//
//                    val bookEntity = Book(
//                        id, title, author, summary, chapters
//                    )
//
//                    dao?.insertBook(bookEntity)
//                }
//            }
//        }
//
//        catch (e: JSONException) {
//        }
//    }
//}