package com.example.ext

sealed class Screen(val route:String){
    object Home : Screen(route = "home_screen")
    object DetailScreen : Screen(route = "detail_screen")


    fun withArgs(vararg args:String) : String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}