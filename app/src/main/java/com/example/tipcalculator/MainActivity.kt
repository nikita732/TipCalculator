package com.example.tipcalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tipcalculator.ui.theme.TipCalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TipCalculatorTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    val orderAmount = remember { mutableStateOf("") }
    val numberOfDishes = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 80.dp, start = 16.dp, end = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Сумма заказа
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Сумма заказа:", modifier = Modifier.width(160.dp))

            var focused1 by remember { mutableStateOf(false) }

            TextField(
                value = orderAmount.value,
                onValueChange = { value: String -> orderAmount.value = value },
                singleLine = true,
                textStyle = TextStyle(color = Color.Black),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color(0xFFFF69B4).copy(alpha = 0.2f),
                    unfocusedContainerColor = Color(0xFFFF69B4).copy(alpha = 0.2f),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                ),
                modifier = Modifier
                    .width(150.dp)
                    .height(50.dp)
                    .onFocusChanged { focused1 = it.isFocused }
                    .border(
                        width = 2.dp,
                        color = if (focused1) Color(0xFFFF69B4).copy(alpha = 0.8f)
                        else Color(0xFFFF69B4).copy(alpha = 0.5f),
                        shape = RoundedCornerShape(8.dp)
                    )
            )
        }

        // Количество блюд
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Количество блюд:", modifier = Modifier.width(160.dp))

            var focused2 by remember { mutableStateOf(false) }

            TextField(
                value = numberOfDishes.value,
                onValueChange = { value: String -> numberOfDishes.value = value },
                singleLine = true,
                textStyle = TextStyle(color = Color.Black),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color(0xFFFF69B4).copy(alpha = 0.2f),
                    unfocusedContainerColor = Color(0xFFFF69B4).copy(alpha = 0.2f),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                ),
                modifier = Modifier
                    .width(150.dp)
                    .height(50.dp)
                    .onFocusChanged { focused2 = it.isFocused }
                    .border(
                        width = 2.dp,
                        color = if (focused2) Color(0xFFFF69B4).copy(alpha = 0.8f)
                        else Color(0xFFFF69B4).copy(alpha = 0.5f),
                        shape = RoundedCornerShape(8.dp)
                    )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    TipCalculatorTheme {
        MainScreen()
    }
}
