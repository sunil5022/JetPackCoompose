package com.example.jetpackdemo.screen

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.fragment.findNavController
import com.example.jetpackdemo.NavigationGraph
import com.example.jetpackdemo.R
import com.example.jetpackdemo.viewModel.BottomScreenViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFrag : Fragment() {
    private val viewModel: BottomScreenViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.P)
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                val navController = findNavController()
                val currentDestination = navController.currentDestination
                val currentNavGraphId = currentDestination?.parent?.id ?: -1

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


    @ExperimentalMaterial3Api
//@Preview(name = "Preview1", showBackground = true, showSystemUi = true)
    @RequiresApi(Build.VERSION_CODES.P)
    @Composable
    fun BottomNavigation(viewModel: BottomScreenViewModel) {
        val navController = rememberNavController()
        Scaffold(
            modifier = Modifier.background(Color.Black),
            bottomBar = { com.example.jetpackdemo.BottomNavigation(navController = navController) },
            content = {
                BottomNavigationBody(Modifier.padding(it), navController = navController,viewModel = viewModel)
            }
        )
    }

    @RequiresApi(Build.VERSION_CODES.P)
    @Composable
    fun BottomNavigationBody(navController1: Modifier, navController: NavHostController, viewModel: BottomScreenViewModel) {
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
}