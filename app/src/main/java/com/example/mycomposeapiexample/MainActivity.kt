package com.example.mycomposeapiexample

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.mycomposeapiexample.model.PhotoDataItem
import com.example.mycomposeapiexample.ui.theme.MyComposeApiExampleTheme
import com.example.mycomposeapiexample.viewmodel.MyViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyComposeApiExampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ShowPhotoList()
                }
            }
        }
    }
}

@Composable
fun ShowPhotoList(myViewModel: MyViewModel = viewModel()){
    val scope = rememberCoroutineScope()
Column {
    val context = LocalContext.current

    val photoData by myViewModel.photoList.collectAsState()



    val imagelist : List<PhotoDataItem> = photoData.data
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 128.dp)
    ) {
        items(imagelist.size){
            Image(
                painter = rememberAsyncImagePainter(imagelist[it].thumbnailUrl),
                contentDescription = null,
                modifier = Modifier.size(128.dp)
            )
        }
    }

}
}