package com.example.randomphotogenerator

import com.google.gson.annotations.SerializedName


data class PhotoData(
    val id: String,
    val slug: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    val description: String,
    val urls: Urls
)

data class Urls(
    val full: String
)