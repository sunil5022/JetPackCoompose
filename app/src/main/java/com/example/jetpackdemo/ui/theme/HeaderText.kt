package com.example.jetpackdemo.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight

@Composable
fun HeaderText(
    text : String,
    modifier: Modifier=Modifier
){
    Text(text = text, color = Color.White, style = MaterialTheme.typography.h3, fontWeight = FontWeight.Bold,modifier=modifier)

}