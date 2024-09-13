package com.example.assignment2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.assignment2.ui.theme.Assignment2Theme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import java.time.LocalTime

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Assignment2Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val additionalText = arrayOf(
                        "Good Morning",
                        "Good Afternoon",
                        "Good Evening",
                        "Why are you here at the middle of the night?"
                    )
                    var name by remember { mutableStateOf("") }
                    var clicked by remember { mutableStateOf(false) }
                    val currentTime = LocalTime.now().hour

                    val greetingText = when (currentTime) {
                        in 6..11 -> additionalText[0]  // Morning
                        in 12..17 -> additionalText[1] // Afternoon
                        in 18..22 -> additionalText[2] // Evening
                        else -> additionalText[3]      // Late night
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                            .padding(16.dp) // I heard dp is the best use of
                        // pixel measurement in Android Studio
                    ) {
                        // insert text field
                        TextField(
                            value = name,
                            onValueChange = { name = it },
                            label = { Text("Enter your name") },
                            modifier = Modifier.fillMaxWidth()
                        )
                        Button(onClick = {
                            clicked = true
                        }) {
                            Text("Get Greeting")
                        }
                        if (clicked and name.isNotEmpty()) {
                            Greeting(
                                greeting = "Hello $name! $greetingText",
                                modifier = Modifier.padding(top = 16.dp)
                            )
                        }

                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(greeting: String, modifier: Modifier = Modifier) {
    Text(
        text = greeting,
        modifier = modifier,
        color = Color.Blue,
        fontStyle = FontStyle.Italic
    )
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Assignment2Theme {
        Greeting("Android")
    }
}