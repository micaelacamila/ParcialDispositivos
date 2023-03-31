package com.example.parcial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class BookView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_view)
        val btn = findViewById<Button>(R.id.save_btn2)
        btn.setOnClickListener{
            GlobalScope.launch {
                val bookDao = AppRoomDatabase.getDatabase(applicationContext).bookDato()
                val repository = BookRepository(bookDao)
                val title_t = findViewById<EditText>(R.id.et_title)
                val description_t= findViewById<EditText>(R.id.et_description)
                repository.insert(Book(title=title_t.getText().toString(), description = description_t.getText().toString()))
                val lista = repository.getListBooks()
                lista.forEach {
                    Log.d("DBTEST","Id book = ${it.id}, Title: ${it.title}, Description: ${it.description}")
                }
            }
        }
    }

}