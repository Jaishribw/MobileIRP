package com.example.mobileirp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mobileirp.ui.theme.MoBileIRPTheme

class DemoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MoBileIRPTheme {
                Counter()
            }
        }

        Log.d("DemoActivity LC: ","onCreate")
    }

    override fun onStart() {
        super.onStart()
        Log.d("DemoActivity LC: ","OnStart")

    }

    override fun onResume() {
        super.onResume()
        Log.d("DemoActivity LC: ", "OnResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("DemoActivity LC: ", "OnPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("DemoActivity LC: ", "OnStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("DemoActivity LC: ", "OnDestroy")
    }

    override fun onBackPressed() {
        super.onBackPressed()
        Log.d("DemoActivity LC: ", "OnBackPressed")
    }
}


@Preview
@Composable
fun SimpleOutlinedTextFieldSample() {
    var text by remember { mutableStateOf("") }

    OutlinedTextField(
        value = text,
        onValueChange = { text = it },
        label = { Text("Label") }
    )
}

@Preview
@Composable
fun Counter(){
    val count = remember { mutableIntStateOf(0) }

    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        var text by remember { mutableIntStateOf(0) }
        Button(onClick = {
            count.intValue = text
        }, Modifier.fillMaxWidth()/*.align(Alignment.CenterVertically)*/.padding(40.dp, 100.dp, 40.dp, 40.dp)) {
            Text(text = "Counter")
        }
        TextField(
            value = "$text",
            onValueChange = { text = if(it.isEmpty()) 0 else it.toInt() },
            label = { Text("Label") }

        )

    }
    /*LaunchedEffect(false) {
        Log.d("Counter Change to ${count.intValue}: ","")
    }*/
    val key = count.intValue % 3 == 0
    Log.d("Counter : ${count.intValue}",  key.toString())
    var element = 0
    val numList = listOf(1,1,2,2,3,4,4,5,9, 7)
    for (i in numList.indices){
        element = numList[i]
    }
    LaunchedEffect(count.intValue) {
        Log.d("Launched Effect: element", element.toString())
    }
    LaunchedEffect(count.intValue) {
        Log.d("Launched Effect: count", count.intValue.toString())
    }
    LaunchedEffect(key) {

        Log.d("Counter Change to  ${count.intValue}: ",  key.toString())
    }
}