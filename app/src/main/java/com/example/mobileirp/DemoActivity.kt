package com.example.mobileirp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
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

@Preview(showBackground = true)
@Composable
fun Counter(){
    val count = remember { mutableIntStateOf(0) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Button(onClick = {
            count.intValue++
        }, Modifier.fillMaxWidth().align(Alignment.CenterVertically).padding(40.dp, 100.dp, 40.dp, 40.dp)) {
            Text(text = "Counter")
        }
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