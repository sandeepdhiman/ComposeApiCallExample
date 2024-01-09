package com.example.mycomposeapiexample.repository

import com.example.mycomposeapiexample.apiinterface.ApiInterface
import com.example.mycomposeapiexample.model.Album
import com.example.mycomposeapiexample.model.AlbumData
import com.example.mycomposeapiexample.model.PhotoData
import com.example.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class Repository @Inject constructor( private val apiInterface: ApiInterface) {
    suspend fun getAlbumList() : Flow<Resource<PhotoData>>{ //// here Resource is A helper class to handle states
        return flow {
            val list = apiInterface.getPhotos()
            emit(Resource.success(list))
        }.flowOn(Dispatchers.IO)
    }
}