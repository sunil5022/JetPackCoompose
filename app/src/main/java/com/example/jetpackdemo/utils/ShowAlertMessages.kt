package com.example.jetpackdemo.utils

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.material.SnackbarResult
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch

@Composable
fun showSnackBar(msg:String, action:String, onClick: ((String) -> Unit)?) {
    val coroutineScope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState() // this contains the `SnackbarHostState`

    Column {
        coroutineScope.launch {
            val snackBarResult = scaffoldState.snackbarHostState.showSnackbar(
                message = msg,
                actionLabel = action
            )
            when (snackBarResult) {
                SnackbarResult.Dismissed -> if (onClick != null) {
                    onClick("")
                }
                SnackbarResult.ActionPerformed ->{
                    if (onClick != null) {
                        onClick("")
                    }
                    Log.d("SnackBarDemo", "SnackBar's button clicked")
                }
            }
        }
    }
}

fun showToast(context: Context,msg: String){
    Toast.makeText(context,msg,Toast.LENGTH_SHORT).show()
}

fun printLogD(tag: String,msg: String){
    if (checkBuildType()) {
        Log.d(tag,msg)
    }
}

fun printLogE(tag: String,msg: String){
    if (checkBuildType()) {
        Log.d(tag,msg)
    }
}

fun printLogV(tag: String,msg: String){
    if (checkBuildType()) {
        Log.d(tag,msg)
    }
}

private fun checkBuildType(): Boolean{
    return true
}