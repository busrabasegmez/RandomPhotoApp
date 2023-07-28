package com.example.randomphotogenerator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.randomphotogenerator.adapters.RecyclerViewAdapter
import com.google.gson.GsonBuilder
import okhttp3.*
import java.io.IOException


class SearchActivity : AppCompatActivity() {
    lateinit var leftButton: ImageView
    private lateinit var recyclerViewAdapter: RecyclerViewAdapter
    lateinit var photosRecyclerView: RecyclerView
    lateinit var editTextSearch:EditText
    lateinit var searchImageIcon:ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)


        val photosRecyclerView = findViewById<RecyclerView>(R.id.photosRecyclerView)

        photosRecyclerView.layoutManager = LinearLayoutManager(this)


        leftButton = findViewById<ImageButton>(R.id.leftIconImageView)
        leftButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        editTextSearch = findViewById(R.id.editTextSearch)

        searchImageIcon=findViewById(R.id.searchImageIcon)
        searchImageIcon.setOnClickListener{
            val userInput = editTextSearch.getText().toString()
            fetchJson(userInput)
        }


    }


    fun fetchJson(userInput: String) {

        val url = "https://api.unsplash.com/search/photos?query=${userInput}"
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
                Toast.makeText(this@SearchActivity, "failed", Toast.LENGTH_LONG).show()
            }
        })
    }


}