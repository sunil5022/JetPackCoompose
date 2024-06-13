package com.example.jetpackdemo.screen

import android.Manifest
import android.content.Context
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavHostController
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.navigation.compose.rememberNavController
import com.example.ext.navigateWithAnimationWithPopUpto
import com.example.jetpackdemo.BottomNavItem
import com.example.jetpackdemo.R
import com.example.jetpackdemo.ui.theme.Loader
import com.example.jetpackdemo.ui.theme.OnLifecycleEvent
import com.example.jetpackdemo.utils.CheckPermission
import com.example.jetpackdemo.utils.printLogD
import com.example.jetpackdemo.utils.showSnackBar
import com.example.jetpackdemo.utils.showToast
import com.example.jetpackdemo.viewModel.BottomScreenViewModel
import com.example.model.Data
import com.example.retrofit.GsonHelper
import com.example.retrofit.ResponseHandler
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.MultiplePermissionsState
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.gson.reflect.TypeToken
import okhttp3.internal.Util


@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun HomeScreen(viewModel: BottomScreenViewModel,navController: NavHostController) {
    val counterState = viewModel.counter
    val usersState = viewModel.users.observeAsState()
    val context = LocalContext.current

    val multiplePermissionsState = rememberMultiplePermissionsState(listOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.CAMERA))
    val rationaleTextState: MutableState<String> = rememberSaveable { mutableStateOf("") }

    val showLoader = remember { mutableStateOf(true) }
    val isClicked = remember { mutableStateOf(false) }

    LaunchedEffect(true) {
        viewModel.fetchUsers()
    }
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(colorResource(id = R.color.teal_700))
//            .wrapContentSize(Alignment.Center)
//    ) {
//        Text(
//            text = "Home",
//            fontWeight = FontWeight.Bold,
//            color = Color.White,
//            modifier = Modifier.align(Alignment.CenterHorizontally),
//            textAlign = TextAlign.Center,
//            fontSize = 20.sp
//        )
//        Button(onClick = { viewModel.fetchUsers() }) {
//            Text(text = "Increment")
//        }
//        Text(text = "Counter: ${counterState.value}")
//    }

    when (usersState.value?.status) {
        ResponseHandler.Status.LOADING -> {
//            CircularProgressIndicator()

            Loader(isLoading = showLoader)

        }

        ResponseHandler.Status.SUCCESS -> {
            showLoader.value = false

            val playersList: List<Data> =
                GsonHelper.convertJsonStringToJavaObject(
                    usersState.value!!.data,
                    object : TypeToken<ArrayList<Data>>() {}.type
                ) as List<Data>

            Column {
                Text(
                    "User List:",
                    fontSize = 30.sp,
                    color = Color.Green,
                    modifier = Modifier.padding(6.dp).clickable {

                      val any =  viewModel.getData()

                        val playersList: List<Data> =
                            GsonHelper.convertJsonStringToJavaObject(
                                any,
                                object : TypeToken<ArrayList<Data>>() {}.type
                            ) as List<Data>
                       printLogD("HomeScreen",playersList[0].title)
                    }
                )
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(playersList) {
                        setUpUi(
                            isClicked = isClicked,
                            title = it.title,
                            multiplePermissionsState = multiplePermissionsState,
                            context = context,
                            navController = navController
                        )
                    }
                }
            }

        }

        ResponseHandler.Status.ERROR -> {
            showLoader.value = false
            showSnackBar("Are you sure delete account?", "Yes") {

            }
        }

        else -> {}
    }


    if (isClicked.value) {
        callBackRunTimePermission(multiplePermissionsState, rationaleTextState, context)
        isClicked.value = false
    }

    lifeCycleOfScreen(showLoader)
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun callBackRunTimePermission(
    multiplePermissionsState: MultiplePermissionsState,
    rationaleTextState: MutableState<String>,
    current: Context
) {


    CheckPermission(
        multiplePermissionsState = multiplePermissionsState,
        onPermissionGranted = {
            showToast(context = current, "onPermissionGranted")
        },
        onPermissionDenied = {
            rationaleTextState.value = it
            showToast(context = current, "onPermissionDenied")

        }
    )
}

@Composable
fun lifeCycleOfScreen(showLoader: MutableState<Boolean>) {
    OnLifecycleEvent { owner, event ->
        // do stuff on event
        when (event) {
            Lifecycle.Event.ON_RESUME -> {
                showLoader.value = true

                Log.d("OnLifecycleEvent", "ON_RESUME")
            }

            Lifecycle.Event.ON_PAUSE -> {
                Log.d("OnLifecycleEvent", "ON_PAUSE")
            }

            else -> { /* other stuff */
            }
        }
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable()
fun setUpUi(
    isClicked: MutableState<Boolean>,
    title: String,
    multiplePermissionsState: MultiplePermissionsState,
    context: Context,
    navController: NavHostController
) {

    Card(
        modifier = Modifier
            .height(120.dp)
            .fillMaxWidth().padding(10.dp).clickable {
                isClicked.value = true
                if (!multiplePermissionsState.allPermissionsGranted)
                    multiplePermissionsState.launchMultiplePermissionRequest()
                else
                    showToast(context, "Already Granted")

              //  navController.navigate("DetailScreen")
                navController.navigate(BottomNavItem.Detail.screen_route)

                Log.d("navController111", navController.currentDestination?.id.toString())
                //findNavController().safelyNavigate(R.id.user_profile_frag)
//                navigateWithAnimationWithPopUpto(navController, R.id.user_profile_frag)

            },
        shape = RoundedCornerShape(18.dp),
        elevation = 10.dp
    ) {
        
        ConstraintLayout(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth().padding(bottom = 10.dp)
        ) {

            val (tvTitle, ivBanner) = createRefs()

            Text(
                textAlign = TextAlign.Center,
                text = title.uppercase(),
                fontSize = 20.sp,
                color = Color.Red,
                modifier = Modifier
                    .padding(1.dp)
                    .constrainAs(tvTitle) {
                        start.linkTo(ivBanner.end)
                        top.linkTo(parent.top)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                        width = Dimension.fillToConstraints
                    }
            )

            Image(painter = painterResource(id = android.R.drawable.ic_menu_call),
                contentDescription = "image",
                modifier = Modifier
                    .constrainAs(ivBanner) {
                        start.linkTo(parent.start)
                        end.linkTo(tvTitle.start)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }.width(50.dp)
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable()
fun setUpUiPreview(

) {
    Card(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .height(120.dp),
        shape = RoundedCornerShape(18.dp),
        elevation = 10.dp
    ) {

        ConstraintLayout(
            modifier = Modifier.fillMaxSize().padding(10.dp)
        ) {

            val (tvTitle, ivBanner) = createRefs()

            Text(
                textAlign = TextAlign.Start,
                text = "vjkdvknkdsvn ",
                fontSize = 20.sp,
                color = Color.Red,
                modifier = Modifier
                    .padding(1.dp)
                    .constrainAs(tvTitle) {
                        start.linkTo(ivBanner.end)
                        top.linkTo(parent.top)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                        width = Dimension.fillToConstraints
                    }
            )

            Image(painter = painterResource(id = android.R.drawable.ic_menu_call),
                contentDescription = "image",
                modifier = Modifier
                    .constrainAs(ivBanner) {
                        start.linkTo(parent.start)
                        end.linkTo(tvTitle.start)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }.width(50.dp)
            )
        }
    }
}

