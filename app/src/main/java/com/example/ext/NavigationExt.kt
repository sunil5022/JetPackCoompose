@file:Suppress("unused")

package com.example.ext

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.Navigator
import androidx.navigation.fragment.findNavController
import com.example.jetpackdemo.R

fun <T> Fragment.getNavigationResult(key: String = "result") =
    findNavController().currentBackStackEntry?.savedStateHandle?.get<T>(key)

fun <T> Fragment.getNavigationResultLiveData(key: String = "result") =
    findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<T>(key)

fun <T> Fragment.setNavigationResult(result: T, key: String = "result") {
    findNavController().previousBackStackEntry?.savedStateHandle?.set(key, result)
}

fun getNavOptions(): NavOptions {
    return NavOptions.Builder()
        .setEnterAnim(R.anim.slide_in_right)
        .setExitAnim(R.anim.slide_out_left)
        .setPopEnterAnim(R.anim.slide_in_left)
        .setPopExitAnim(R.anim.slide_out_right)
        .build()
}

fun Fragment.navigateWithAnimation(
    navController: NavController,
    navDirections: NavDirections
) {
    navController.navigate(navDirections, getNavOptions())
}

fun Fragment.navigateWithAnimation(
    navController: NavController,
    directions: NavDirections,
    navigatorExtras: Navigator.Extras
) {
    navigateWithAnimation(navController, directions.actionId, directions.arguments, navigatorExtras)
}

fun Fragment.navigateWithAnimationWithPopUpto(
    navController: NavController,
    idRes: Int,
    popUpto: Int,
    args: Bundle? = null,
    navigatorExtras: Navigator.Extras? = null,
) {
    val navOptions = NavOptions.Builder()
        .setEnterAnim(R.anim.slide_in_right)
        .setExitAnim(R.anim.slide_out_left)
        .setPopEnterAnim(R.anim.slide_in_left)
        .setPopExitAnim(R.anim.slide_out_right)
        .setPopUpTo(popUpto, true)
        .build()
    navController.navigate(idRes, args, navOptions, navigatorExtras)
}

fun Fragment.navigateWithAnimation(
    navController: NavController,
    idRes: Int,
    args: Bundle? = null,
    navigatorExtras: Navigator.Extras? = null,
) {
    val navOptions = NavOptions.Builder()
        .setEnterAnim(R.anim.slide_in_right)
        .setExitAnim(R.anim.slide_out_left)
        .setPopEnterAnim(R.anim.slide_in_left)
        .setPopExitAnim(R.anim.slide_out_right)
        .build()

    navController.navigate(idRes, args, navOptions, navigatorExtras)
}

fun Fragment.withActivity(navController: NavController, action: (activity: Activity) -> Unit) {
    this.activity?.let(action)
}
