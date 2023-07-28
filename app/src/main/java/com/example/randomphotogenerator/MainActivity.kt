package com.example.randomphotogenerator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


class MainActivity : AppCompatActivity() {

    private lateinit var text: TextView
    private lateinit var imageView: ImageView
    private lateinit var button: Button
    private lateinit var imageButton: ImageButton
    private var currentPhotoUrl: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        text = findViewById(R.id.text)
        imageView = findViewById(R.id.imageView)
        button = findViewById(R.id.button)
        imageButton=findViewById(R.id.search)
        var leftButton = findViewById<ImageButton>(R.id.left)

        Glide.with(this).load(currentPhotoUrl).into(imageView)


        imageButton.setOnClickListener{
            val intent = Intent(this, SearchActivity::class.java)
            startActivity(intent)
        }

        leftButton=findViewById<ImageButton>(R.id.left)
        leftButton.setOnClickListener{
            onDestroy()
        }

        button.setOnClickListener {
            getRandomPhoto()
        }
    }


    private fun getRandomPhoto() {

        val apiKey = "n_I-t1mZak9H2oRU49axe6aGa3w2SMTFo9IdBL7WLKI"
        val baseUrl = "https://api.unsplash.com/"

        val retrofit =
            Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create())
                .build()

        val service = retrofit.create(ApiService::class.java)
        val call = service.getRandomPhoto(apiKey)

        call.enqueue(object : Callback<PhotoData> {
            override fun onResponse(call: Call<PhotoData>, response: Response<PhotoData>) {
                if (response.isSuccessful) {
                    val photoData = response.body()
                    val fullPhotoUrl = photoData?.urls?.full
                    val description = photoData?.description

                    text.setText(description)



                    if (!fullPhotoUrl.isNullOrEmpty()) {
                        Glide.with(this@MainActivity).load(fullPhotoUrl).into(imageView)
                    }
                }
            }

            override fun onFailure(call: Call<PhotoData>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }
}


