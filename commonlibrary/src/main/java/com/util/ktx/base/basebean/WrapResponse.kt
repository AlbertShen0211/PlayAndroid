package com.util.ktx.base.basebean
data class WrapResponse<out T>(val errorCode: Int, val errorMsg: String, val data: T)