package com.example.myjetpackcompose

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myjetpackcompose.ui.theme.MyAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        CounterApp()
                        Row(
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Text("Item 1")
                            Text("Item 2")
                            Text("Item 3")
                            Text("Item 1")
                            Text("Item 2")
                            Text("Item 3")
                            Text("Item 1")
                        }
                        // A surface container using the 'background' color from the theme
                        val items = remember {
                            listOf(
                                "Item 1",
                                "Item 2",
                                "Item 3",
                                "-",
                                ">",
                                "d",
                                "h",
                                "r",
                                "u",
                                "v",
                                "i",
                                "l"
                            )
                        }
                        ItemList(items) { clickedItem ->
                            // Handle item click, e.g., show a Toast or navigate to another screen
                            println("Clicked item: $clickedItem")
                        }

                    }
                }
            }
        }
    }
}

@Composable
fun CounterApp(modifier: Modifier = Modifier) {
    var count by remember { mutableIntStateOf(0) }

    var color by remember { mutableStateOf(Color.Red) }
    val animatedColor by animateColorAsState(targetValue = color)


    // Use SideEffect to log the current value of count
    SideEffect {
        // Called on every recomposition
        Log.d("TAG Count is", "Count is $count")
    }
    Column(modifier = modifier.padding(16.dp)) {
        Text(text = "Count: $count")
        Button(onClick = { count++ }) {
            Text(text = "Increment")
        }
        /* Text(
             "Hello, World!",
             Modifier
                 .padding(8.dp)
                 .background(color = Color.Green)
                 .padding(8.dp)
         )*/
        Box(
            modifier = Modifier
                .size(width = 100.dp, height = 100.dp) // Set specific width and height
                .pointerInput(Unit) {
                    detectTapGestures(
                        onTap = {
                            color = if (color == Color.Red) Color.Green else Color.Red
                        }
                    )
                }
                .background(animatedColor)
        )
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello, $name!")
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyAppTheme {
        Greeting("Android")
    }
}

@Preview(showBackground = true)
@Composable
fun CounterAppPreview() {
    MyAppTheme {
        CounterApp()
    }
}

@Composable
fun ItemList(items: List<String>, onItemClick: (String) -> Unit) {
    LazyColumn {
        items(items) { item ->
            Text(text = item,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .clickable { onItemClick(item) })
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewItemList() {
    val sampleItems = remember {
        listOf<String>(
            "Item 1",
            "Item 2",
            "Item 3",
            "Item 1",
            "Item 2",
            "Item 3",
        )
    }
    ItemList(items = sampleItems) {
        // Handle item click
    }
}
