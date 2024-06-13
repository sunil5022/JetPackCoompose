package com.example.retrofit

import com.example.model.Data
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("posts")
    suspend fun getUsers() : Response<Any>
}