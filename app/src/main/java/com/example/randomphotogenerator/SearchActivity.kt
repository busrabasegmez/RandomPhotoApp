package com.example.randomphotogenerator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.randomphotogenerator.adapters.RecyclerViewAdapter
import com.google.gson.GsonBuilder
import okhttp3.*
import java.io.IOException

class SearchActivity : AppCompatActivity() {
    lateinit var leftButton: ImageButton
    private lateinit var recyclerViewAdapter: RecyclerViewAdapter
    lateinit var photosRecyclerView :RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)


        val photosRecyclerView = findViewById<RecyclerView>(R.id.recycler_view)

        photosRecyclerView.layoutManager = LinearLayoutManager(this)
//        photosRecyclerView.adapter= RecyclerViewAdapter()

        leftButton = findViewById<ImageButton>(R.id.left)
        leftButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }

    fun fetchJson() {
        val query = ""
        val url = "https://api.unsplash.com/search/photos?query=${query}"
        val request = Request.Builder().url(url).build()


        val client = OkHttpClient()
        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                val body = response?.body?.string()
                println(body)

                val gson = GsonBuilder().create()
                val homeFeed = gson.fromJson(body, HomeFeed::class.java)


                runOnUiThread {
                    photosRecyclerView.adapter = RecyclerViewAdapter(homeFeed)

                }

            }

            override fun onFailure(call: Call, e: IOException) {
                Toast.makeText(this@SearchActivity,"failed",Toast.LENGTH_LONG).show()
            }
        })
    }


}