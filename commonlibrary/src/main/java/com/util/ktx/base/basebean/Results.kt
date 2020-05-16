package com.util.ktx.base.basebean

sealed class Results<out T : Any> {

    data class Success<out T : Any>(val data: T) : Results<T>()
    data class Error(val exception: Exception) : Results<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
        }
    }
}