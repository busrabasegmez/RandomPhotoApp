package com.example.randomphotogenerator.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.randomphotogenerator.HomeFeed
import com.example.randomphotogenerator.R
import com.example.randomphotogenerator.SearchActivity

class RecyclerViewAdapter(val homeFeed: HomeFeed) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        // how do we even create a view
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.recycler_view_photos, parent, false)
        return CustomViewHolder(cellForRow)
    }



    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val photo = homeFeed.photos[position]
        val url = photo.urls




    }

    override fun getItemCount(): Int {

        return homeFeed.photos.count()

    }

}

class CustomViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

    init {
        view.setOnClickListener {
            val intent = Intent(view.context, SearchActivity::class.java)

            view.context.startActivity(intent)
        }
    }
}



