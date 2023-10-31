package com.example.p7

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CircleItem() {
    val counter = remember {
        mutableStateOf(0)
    }
    val color = remember {
        mutableStateOf(Color.Blue)
    }

        Box(modifier = Modifier
            .size(100.dp)
            .background(color = color.value, shape = CircleShape)
            .clickable {
                when ((counter.value++) % 2) {
                    0 -> color.value = Color.Red
                    1 -> color.value = Color.Blue
                }
            },
            contentAlignment = Alignment.Center
        ) {
            Text(text = counter.value.toString(), style = TextStyle(color = Color.Black, fontSize = 25.sp))
        }

}

@Composable
fun MutableStateInsideComposable() {
    val checkedState = remember { mutableStateOf(false) }
    Column {
        Row {
            Checkbox(checked = checkedState.value, onCheckedChange = {checkedState.value = it})
            Text(text = "Какой то текст...", fontSize = 18.sp)
        }
        if (checkedState.value) {
            Text(text = "Еще больше какого то текста", fontSize = 20.sp)
        }
    }

}