package com.example.mobileirp.ui.customcomposable
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.mobileirp.model.MyRowItem
import com.example.mobileirp.vm.MyViewModel
import androidx.paging.compose.items
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MyScreen(
    viewModel: MyViewModel = MyViewModel(),
    contentPadding: PaddingValues = PaddingValues(20.dp),
) {
    val lazyPagingItems :LazyPagingItems<MyRowItem> =  viewModel.pagingDataFlow.collectAsLazyPagingItems()
    var pageNo :Int?= -1
    var text = ""
    LaunchedEffect(pageNo) {
        CoroutineScope(Dispatchers.IO).launch{
            text += ":$pageNo"
            Log.d("PageNo: in LaunchedEffect","$text:$pageNo")

        }
    }
    Text(text = text, color = Color.Blue, fontSize = 16.sp,
        fontWeight = FontWeight.Bold,fontStyle = FontStyle.Italic )
    LazyColumn(modifier = Modifier.padding(contentPadding)) {

        stickyHeader {
            Row(
                modifier = Modifier
                    .background(Color.Gray)
                    .fillMaxWidth()
                    .height(32.dp)
            ) {
                Text(text = "Sticky Header ${pageNo}")
            }
        }
        items (lazyPagingItems) { item ->
            val title by remember { mutableStateOf(item?.name ?: "Loading...")}
            text = title
            Text(text = text, color = Color.Blue, fontSize = 16.sp,
                fontWeight = FontWeight.Bold,fontStyle = FontStyle.Italic )
                pageNo = item?.pageId
            Log.d("PageNo: ",item?.name+":$pageNo")


        }



        lazyPagingItems.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                  // TODO()
                }
                loadState.append is LoadState.Loading -> {
                    item {
                        /**/

                        Box(
                            modifier = Modifier.fillMaxSize()
                                .padding(40.dp),
                            contentAlignment = Alignment.Center
                        ) {Row(
                            modifier = Modifier
                                .background(Color.Gray)
                                .fillMaxWidth()
                                .height(32.dp)
                        ) {
                            Text(text = "Sticky Footer $pageNo")
                        }
                            CircularProgressIndicator(
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                }
                loadState.append is LoadState.Error -> {
                    // Handle error
                }
            }
        }
        stickyHeader {

        }


    }
}
