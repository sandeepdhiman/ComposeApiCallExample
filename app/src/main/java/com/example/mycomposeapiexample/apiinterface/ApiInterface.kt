package com.example.mycomposeapiexample.apiinterface

import com.example.mycomposeapiexample.model.Album
import com.example.mycomposeapiexample.model.AlbumData
import com.example.mycomposeapiexample.model.PhotoData
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
    @GET("/photos")
    suspend fun getPhotos(): PhotoData
}