package com.example.randomphotogenerator.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.randomphotogenerator.HomeFeed
import com.example.randomphotogenerator.R
import com.example.randomphotogenerator.SearchActivity
import com.squareup.picasso.Picasso

class RecyclerViewAdapter(val homeFeed: HomeFeed) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.recycler_view_photos, parent, false)
        return CustomViewHolder(cellForRow)
    }



    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val photo = homeFeed.photos[position]
        val url = photo.urls


        val searchedPhotoUrl =holder.itemView.findViewById<ImageView>(R.id.SearchedPhotoImageView)
        Picasso.get().load(url.full).into(searchedPhotoUrl)


        holder.itemView.findViewById<TextView>(R.id.descriptionText).text = photo.description
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



