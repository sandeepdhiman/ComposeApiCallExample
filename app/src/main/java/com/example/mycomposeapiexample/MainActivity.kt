package com.example.mycomposeapiexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.mycomposeapiexample.model.PhotoDataItem
import com.example.mycomposeapiexample.ui.theme.MyComposeApiExampleTheme
import com.example.mycomposeapiexample.viewmodel.MyViewModel
import dagger.hilt.android.AndroidEntryPoint

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
    //val scope = rememberCoroutineScope()
    var showProgressBar by remember { mutableStateOf(true) }
Column(
    modifier = Modifier.fillMaxSize(),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
) {
    val context = LocalContext.current

    val photoData by myViewModel.photoList.collectAsState()
    val imagelist : List<PhotoDataItem> = photoData.data

           if (showProgressBar) {
               CircularProgressIndicator(
                   progress = 0.5f,
                   color = Color.Magenta,
                   modifier = Modifier.size(100.dp),
                   strokeWidth = 10.dp,
               )
           }

    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 128.dp)
    ) {
        showProgressBar=false;
        items(imagelist.size){
            ElevatedCard(
                elevation = CardDefaults.cardElevation(
                defaultElevation = 6.dp),
                modifier = Modifier.padding(5.dp)){
                Column {
                    Image(
                        painter = rememberAsyncImagePainter(imagelist[it].thumbnailUrl),
                        contentDescription = null,
                        modifier = Modifier.size(128.dp)
                    )
                    //Text(text = imagelist[it].title,modifier =Modifier.padding(10.dp), textAlign = TextAlign.Center)
                }
            }
            
            
        }
    }

}
}