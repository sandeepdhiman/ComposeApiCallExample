package com.example.mycomposeapiexample.apiinterface

import com.example.mycomposeapiexample.model.Album
import com.example.mycomposeapiexample.model.AlbumData
import com.example.mycomposeapiexample.model.PhotoData
import com.example.mycomposeapiexample.model.PhotoDataItem
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
    @GET("/photos")
    suspend fun getPhotos(): List<PhotoDataItem>
}