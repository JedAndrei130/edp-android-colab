package com.example.myapplication.core

sealed interface AppResult<out T> {
    data class Success<T>(val data: T) : AppResult<T>
    sealed interface Failure : AppResult<Nothing> {
        object NoInternet : Failure
        object Timeout : Failure
        data class Unknown(val message: String?) : Failure
    }
}
