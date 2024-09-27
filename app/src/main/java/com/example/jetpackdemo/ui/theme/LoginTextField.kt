package com.example.jetpackdemo.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun LoginTextFiled(
    modifier: Modifier = Modifier,
    value: String,
    onValueChanged: (String) -> Unit,
    labelText: String,
    leadingIcon: ImageVector? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    visualTransformation: VisualTransformation = VisualTransformation.None

) {
    OutlinedTextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChanged,
        label = {Text( labelText,color = Color.White)},
        leadingIcon = {if (leadingIcon!=null) Icon(imageVector = leadingIcon,null, tint = Color.White) },
        keyboardOptions = KeyboardOptions(keyboardType=keyboardType),
        visualTransformation = visualTransformation,
        shape = RoundedCornerShape(30),
        textStyle = TextStyle(color = Color.White),
        placeholder = {
            Text(
                text = labelText,
                style = TextStyle(
                    color = MaterialTheme.colors.primaryVariant,
                    textAlign = TextAlign.Center
                )
            )
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Green,
            unfocusedBorderColor = Color.Yellow)
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewLogin(){
    LoginTextFiled(value = "", onValueChanged = {}, labelText = "password")
}