package com.example.jetpackdemo



sealed class BottomNavItem(var title:String, var icon:Int, var screen_route:String){

    object Home : BottomNavItem("Home", android.R.drawable.ic_menu_add,"home")
    object MyNetwork: BottomNavItem("My Network", android.R.drawable.ic_search_category_default,"my_network")
    object AddPost: BottomNavItem("Post", android.R.drawable.ic_menu_add,"add_post")
    object Notification: BottomNavItem("Notification",android.R.drawable.ic_search_category_default,"notification")
    object Jobs: BottomNavItem("Jobs", android.R.drawable.ic_menu_add,"jobs")
    object Detail: BottomNavItem("DetailScreen", android.R.drawable.ic_menu_add,"DetailScreen")
   // object LoginScreen: BottomNavItem("LoginScreen", android.R.drawable.ic_menu_add,"LoginScreen")
}