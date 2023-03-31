package com.example.parcial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    lateinit var  recyclerView: RecyclerView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val list = arrayListOf<Post>();

        setContentView(R.layout.activity_main)


        val restApiAdapter = RestApiAdapter()
        val endPoint = restApiAdapter.connectionApi()
        val postResponseCall = endPoint.getAllPost()
        postResponseCall.enqueue(object : Callback<List<Post>> {
            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                t?.printStackTrace()
            }

            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                val posts = response?.body()
                Log.d("RESP POST", Gson().toJson(posts))
                posts?.forEach {
                    Log.d("RESP POST", it.body)
                    val post = Post(it.userId, it.id, it.title, it.body)
                    list.add(post)
                    Log.d("RESP POSTI", post.title)
                }
                showRecycler(list)

            }
        })
    }
    fun showRecycler(list:ArrayList<Post> ){


        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView=findViewById(R.id.recycler_view)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = PostListAdapter(list,this)


        /*GlobalScope.launch {
            val bookDao = AppRoomDatabase.getDatabase(applicationContext).bookDato()
            val repository = BookRepository(bookDao)
            repository.insert(Book("the best seller: Android", description = "This is the description for the book"))
            val lista = repository.getListBooks()
            lista.forEach {
                Log.d("DBTEST","Id book = ${it.id}, Title: ${it.title}, Description: ${it.description}")
            }
        }*/


    }
}