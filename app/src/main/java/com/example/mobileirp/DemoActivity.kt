package com.example.mobileirp

import android.os.Bundle
import android.util.Log
import android.widget.EditText
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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


@Composable
fun Counter(){
    val count = remember { mutableIntStateOf(0) }

    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        var text by remember { mutableStateOf(0) }
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

    LaunchedEffect(key) {

        Log.d("Counter Change to  ${count.intValue}: ",  key.toString())
    }
    }