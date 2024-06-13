package com.example.jetpackdemo.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.model.User
import com.example.retrofit.ApiService
import com.example.retrofit.ResponseHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class BottomScreenViewModel @Inject constructor(val apiService: ApiService) : ViewModel() {

    private val _counter = mutableStateOf(0)

    val counter: State<Int> get() = _counter


    private val _users = MutableLiveData<ResponseHandler<Any>>()
    val users: LiveData<ResponseHandler<Any>> = _users


//    private val _users = mutableStateOf<ResponseHandler<List<User>>>(ResponseHandler.loading("loading","100"))
//    val users: State<ResponseHandler<List<User>>> = _users

    fun incrementCounter()
    {
        _counter.value++
    }
    fun decrementCounter()
    {
        _counter.value--
    }


    fun fetchUsers() {
        CoroutineScope(Dispatchers.IO).launch {

            withContext(Dispatchers.Main) {
                try {
                     _users.value = ResponseHandler.loading("loading","100")
                     val response = apiService.getUsers()
                    _users.value = ResponseHandler.onSuccess(response.body(), "success", "100")
                } catch (e: Exception) {
                    _users.value = ResponseHandler.onError("Failed to fetch data", "1001")
                }
            }
        }
    }

    fun getData(): Any? {
        return _users.value!!.data
    }

}