package com.example.jetpackdemo

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.jetpackdemo.ui.theme.JetPacKDemoTheme
import com.example.jetpackdemo.viewModel.BottomScreenViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: BottomScreenViewModel by viewModels()


    @OptIn(ExperimentalMaterial3Api::class)
    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetPacKDemoTheme {
                ScaffoldDefaults.contentWindowInsets
                // A surface container using the 'background' color from the theme
                ScaffoldDefaults.contentWindowInsets

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //Greeting("Android")
                    BottomNavigation(viewModel)
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

//@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    JetPacKDemoTheme {
        Greeting("Android")
    }

}



@ExperimentalMaterial3Api
//@Preview(name = "Preview1", showBackground = true, showSystemUi = true)
@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun BottomNavigation(viewModel: BottomScreenViewModel) {
    val navController = rememberNavController()
    val screens = listOf(
        BottomNavItem.Home,
        BottomNavItem.MyNetwork,
        BottomNavItem.AddPost,
        BottomNavItem.Notification,
        BottomNavItem.Jobs
    )
    val showBottomBar = navController
        .currentBackStackEntryAsState().value?.destination?.route in screens.map { it.screen_route }

    Scaffold(
        modifier = Modifier.background(Color.Black),
        bottomBar = { if (showBottomBar) { BottomNavigation(navController = navController) } },
        content = {
            BottomNavigationBody(Modifier.padding(it), navController = navController,viewModel = viewModel)
        }
    )
}

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun BottomNavigationBody(navController1: Modifier, navController: NavHostController,viewModel: BottomScreenViewModel) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Text(text = "BottomNavigationBody Content", color = Color(0xFF0F9D58))
        NavigationGraph(
            navController,
            viewModel = viewModel
        )
    }
}
