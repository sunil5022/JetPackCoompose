package com.example.retrofit

import com.example.model.Data
import com.example.model.User

class ResponseHandler<out Any>(val status: Status, val data: kotlin.Any?, val message: String, val requestCode : String) {
    companion object {

        fun <Any> onSuccess(data: Any?, msg: String, requestCode : String): ResponseHandler<Any> {
            return ResponseHandler(Status.SUCCESS, data, msg, requestCode)
        }

        fun <Any> onError(msg: String, requestCode : String): ResponseHandler<Any> {
            return ResponseHandler(Status.ERROR,null, msg, requestCode)
        }

        fun <Any> loading(msg: String, requestCode : String): ResponseHandler<Any> {
            return ResponseHandler(Status.LOADING,0, msg, requestCode)
        }
    }

    enum class Status {
        SUCCESS, ERROR, LOADING
    }
}