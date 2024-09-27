package com.example.jetpackdemo.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackdemo.R
import com.example.jetpackdemo.ui.theme.HeaderText
import com.example.jetpackdemo.ui.theme.JetPacKDemoTheme
import com.example.jetpackdemo.ui.theme.LoginTextFiled

val defaultPadding = 15.dp
val itemSpacing = 8.dp

@Composable()
fun LoginScreen() {

    val (userName, setUserName) = rememberSaveable {
        mutableStateOf("")
    }

    val (password, setPassword) = rememberSaveable {
        mutableStateOf("")
    }

    val (checked, onCheckedChange) = rememberSaveable {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxWidth()
            .background(color = Color.Magenta),

        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 30.dp, defaultPadding)
                .fillMaxSize()
                .fillMaxWidth(),

            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(30.dp))

            HeaderText(
                "Login",
                modifier = Modifier
                    .padding(vertical = defaultPadding)
                    .align(alignment = Alignment.Start)
            )
            Spacer(modifier = Modifier.height(defaultPadding))

            LoginTextFiled(
                value = userName,
                onValueChanged = setUserName,
                labelText = "Username",
                leadingIcon = Icons.Default.Person,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(defaultPadding))

            LoginTextFiled(
                value = password,
                onValueChanged = setPassword,
                labelText = "Password",
                leadingIcon = Icons.Default.Lock,
                modifier = Modifier.fillMaxWidth(),
                keyboardType = KeyboardType.Password,
                visualTransformation = PasswordVisualTransformation()
            )

            Spacer(modifier = Modifier.height(30.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically

            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = checked, onCheckedChange = onCheckedChange)
                    Text(
                        text = "Remember Me",
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )
                }
                TextButton(onClick = {}) {
                    Text(
                        text = "Forgot  Password?",
                        color = Color.Red,
                    )
                }
            }
            Spacer(modifier = Modifier.height(defaultPadding))

            Button(
                onClick = { /*TODO*/ }, modifier = Modifier.fillMaxWidth().height(50.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.White,
                    backgroundColor = Color.Red
                )
            ) {
                Text(
                    text = "Login",
                    color = Color.White,
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(align = Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Or Sign in With",
                    color = Color.White,
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row {
                    Image(painter = painterResource(id = R.drawable.ic_watcher), modifier = Modifier.width(50.dp).height(50.dp).clickable {  }, contentDescription = "Image")
                    Image(painter = painterResource(id = R.drawable.ic_watcher), modifier = Modifier.width(50.dp).height(50.dp).clickable {  }, contentDescription = "Image")
                    Image(painter = painterResource(id = R.drawable.ic_watcher), modifier = Modifier.width(50.dp).height(50.dp).clickable {  }, contentDescription = "Image")
                }
                Spacer(modifier = Modifier.height(8.dp))

                Row (modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center){
                    Text(
                        text = "Don't have an account",
                        color = Color.White,
                    )
                    Spacer(modifier = Modifier.width(5.dp))

                    TextButton(onClick = {}) {
                        Text(
                            text = "SignUp",
                            color = Color.Red,
                        )
                    }
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewLogin() {
    JetPacKDemoTheme {
        LoginScreen()
    }
}

