package com.example.mycomposeapiexample.model

data class PhotoDataItem(
    val albumId: Int,
    val id: Int,
    val thumbnailUrl: String,
    val title: String,
    val url: String
)