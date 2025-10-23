package com.example.tipcalculator

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Slider
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

@Composable
fun MainScreen() {
    val orderAmount = remember { mutableStateOf("") }
    val numberOfDishes = remember { mutableStateOf("") }
    val tipPercentage = remember { mutableStateOf(0f) }
    val discountPercentage = remember { mutableStateOf(0) }

    // Вспомогательные значения
    val orderAmountFloat = orderAmount.value.toFloatOrNull() ?: 0f
    val tipAmount = orderAmountFloat * tipPercentage.value / 100f
    val discountAmount = orderAmountFloat * discountPercentage.value / 100f
    val totalAmount = orderAmountFloat + tipAmount - discountAmount

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
                onValueChange = { orderAmount.value = it },
                singleLine = true,
                textStyle = TextStyle(color = Color.Black),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color(0xFFFF69B4).copy(alpha = 0.2f),
                    unfocusedContainerColor = Color(0xFFFF69B4).copy(alpha = 0.2f),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
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
                onValueChange = {
                    numberOfDishes.value = it

                    // Автоматическая скидка
                    val dishes = it.toIntOrNull() ?: 0
                    discountPercentage.value = when (dishes) {
                        in 1..2 -> 3
                        in 3..5 -> 5
                        in 6..10 -> 7
                        in 11..Int.MAX_VALUE -> 10
                        else -> 0
                    }
                },
                singleLine = true,
                textStyle = TextStyle(color = Color.Black),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color(0xFFFF69B4).copy(alpha = 0.2f),
                    unfocusedContainerColor = Color(0xFFFF69B4).copy(alpha = 0.2f),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
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

        // Чаевые
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "Чаевые:")
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "${tipPercentage.value.toInt()}%")
            }

            Slider(
                value = tipPercentage.value,
                onValueChange = { tipPercentage.value = it },
                valueRange = 0f..25f,
                modifier = Modifier.fillMaxWidth(),
                colors = androidx.compose.material3.SliderDefaults.colors(
                    thumbColor = Color.Blue,
                    activeTrackColor = Color.Blue,
                    inactiveTrackColor = Color.Blue.copy(alpha = 0.3f)
                )
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "0")
                Text(text = "25")
            }
        }

        // Скидка
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Скидка:", modifier = Modifier.width(80.dp))

                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    listOf(3, 5, 7, 10).forEach { percent ->
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            RadioButton(
                                selected = discountPercentage.value == percent,
                                onClick = {},
                                colors = androidx.compose.material3.RadioButtonDefaults.colors(
                                    selectedColor = Color.Blue,
                                    unselectedColor = Color.Gray
                                )
                            )
                            Text(text = "$percent%")
                        }
                    }
                }
            }
        }

        // Итоговая информация
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(text = "Сумма чаевых: ${"%.2f".format(tipAmount)}")
            Text(text = "Сумма скидки: ${"%.2f".format(discountAmount)}")
            Text(text = "Итоговая сумма: ${"%.2f".format(totalAmount)}")
        }
    }
}
