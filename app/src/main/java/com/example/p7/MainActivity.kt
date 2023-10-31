package com.example.p7

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.magnifier
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var expanded = remember {mutableStateOf( false)}
            var currentWidget = remember {
                mutableStateOf("A")
            }
            Column(modifier = Modifier.fillMaxSize()){
                Row (horizontalArrangement = Arrangement.Center) {
                    IconButton(onClick = { expanded.value = true }) {
                        Icon(Icons.Default.MoreVert, contentDescription = "Показать меню")
                    }
                    DropdownMenu(expanded = expanded.value, onDismissRequest = { expanded.value = false }) {
                        DropdownMenuItem(text = { Text(text = "A")}, onClick = {currentWidget.value = "A"; expanded.value = false})
                        DropdownMenuItem(text = { Text(text = "B")}, onClick = {currentWidget.value = "B"; expanded.value = false})
                        DropdownMenuItem(text = { Text(text = "C")}, onClick = {currentWidget.value = "C"; expanded.value = false})
                        DropdownMenuItem(text = { Text(text = "D")}, onClick = {currentWidget.value = "D"; expanded.value = false})
                        DropdownMenuItem(text = { Text(text = "Metanit")}, onClick = {currentWidget.value = "Metanit"; expanded.value = false})
                    }
                }
                Box(modifier = Modifier.fillMaxSize()){
                    when(currentWidget.value) {
                        "A" -> {
                            WidgetA()
                        }
                        "B" -> {
                            WidgetB()
                        }
                        "C" -> {
                            WidgetC()
                        }
                        "D" -> {
                            WidgetD()
                        }
                        "Metanit" -> {
                            WidgetMetanit()
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun WidgetA() {
    var i = remember {
        mutableStateOf(0)
    }
    Text(
        text = "Нажатий: ${i.value}",
        modifier = Modifier
            .fillMaxWidth()
            .clickable { i.value += 2 },
        fontSize = 30.sp,
        textAlign = TextAlign.Center
    )
}

@Composable
fun WidgetB() {
    val ctx = LocalContext.current
    var checkedState by remember {
        mutableStateOf( false )
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .border(2.dp, Color.Red, RoundedCornerShape(10.dp))
            .clickable {
                Toast
                    .makeText(
                        ctx,
                        "Клик (Чекбокс ${if (checkedState) "нажат" else "не нажат"})",
                        Toast.LENGTH_SHORT
                    )
                    .show()
            }

    ) {
        Checkbox(checked = checkedState, onCheckedChange = {
            Toast
                .makeText(ctx, "Чекбокс", Toast.LENGTH_SHORT)
                .show()
            checkedState  = it
        } )
        Text(text = "Нажатие на кнопку регулирует чекбокс")
    }
}

@Composable
fun WidgetC() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircleItem()
    }
}

@Composable
fun WidgetD() {
    MutableStateInsideComposable()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WidgetMetanit() {
    val textTextField = remember {
        mutableStateOf("")
    }
    val radioState = remember {
        mutableStateOf(true)
    }
    val sliderPos = remember {
        mutableStateOf(0f)
    }
    Column {
        Row() {
            TextField(value = textTextField.value, onValueChange = {textTextField.value = it})
            Text(text = textTextField.value, modifier = Modifier.padding(20.dp))
        }
        Row{
            Text(text = radioState.value.toString())
            RadioButton(
                selected = radioState.value,
                onClick = { radioState.value = true }
            )
            RadioButton(
                selected = !radioState.value,
                onClick = { radioState.value = false }
            )
        }
        Row {
            Text(text = "Текущее значение: ${sliderPos.value}", fontSize = 22.sp)
            Slider(value = sliderPos.value, onValueChange = {sliderPos.value = it}, steps = 100, valueRange = 0f..100f)
        }
    }
}
