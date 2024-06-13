package com.example.jetpackdemo.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.jetpackdemo.R
import com.example.jetpackdemo.utils.printLogD
import com.example.jetpackdemo.viewModel.BottomScreenViewModel
import com.example.model.Data
import com.example.retrofit.GsonHelper
import com.google.gson.reflect.TypeToken

@Composable
fun NetworkScreen(viewModel: BottomScreenViewModel){

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.teal_700))
            .wrapContentSize(Alignment.Center)
    ) {

        val any =  viewModel.getData()
        any?.apply {
            val playersList: List<Data> =
                GsonHelper.convertJsonStringToJavaObject(
                    any,
                    object : TypeToken<ArrayList<Data>>() {}.type
                ) as List<Data>

            printLogD("NetworkScreen",playersList[0].title)

            Text(
                text = playersList[0].title,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                textAlign = TextAlign.Center,
                fontSize = 20.sp
            )
        }



    }
}