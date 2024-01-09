package com.example.mycomposeapiexample.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mycomposeapiexample.model.Album
import com.example.mycomposeapiexample.model.PhotoData
import com.example.mycomposeapiexample.repository.Repository
import com.example.mycomposeapiexample.util.Status
import com.example.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(private val repository: Repository):ViewModel() {

    val photoList = MutableStateFlow(Resource(Status.LOADING, PhotoData(),""))

    init {
     getAlbumData()
    }

    private fun getAlbumData(){
        photoList.value = Resource.loading(null)
        viewModelScope.launch {


            repository.getAlbumList().catch {
                photoList.value = Resource.error(it.message.toString(),null)
            }.collect{
                photoList.value = Resource.success(it.data)
            }
        }
    }
}