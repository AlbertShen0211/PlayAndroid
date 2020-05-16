package com.android.playandroid.util

import java.util.regex.Pattern

object VerifyUtil {
    fun isEmail(email: String): Boolean {
        // val flag: Boolean
        //  val check = "[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\\\.[A-Za-z]{2,4}"
        val check = "^\\w+((-\\w+)|(\\.\\w+))*@\\w+(\\.\\w{2,3}){1,3}\$"
        val regex = Pattern.compile(check)
        val matcher = regex.matcher(email)
        /* flag = try {
            // val check = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$"
             //val check = "^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\\\\.([a-zA-Z0-9_-])+)+\$"
             val check = "[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\\\.[A-Za-z]{2,4}"
             val regex = Pattern.compile(check)
             val matcher = regex.matcher(email)
             return matcher.matches()
         } catch (e: Exception) {
             Timber.e("false")
             false
         }*/
        return matcher.matches()
    }

    fun isLengthLegal(str: Any?): Boolean {
        if (str == null) {
            return false
        }
        if (str is String) {
            if (8 <= str.length && str.length <= 16) {
                return true
            }
        }
        if (str is CharSequence) {
            if (8 <= str.length && str.length <= 16) {
                return true
            }
        }
        return false
    }

    fun isContainAll(str: String): Boolean {
        var isDigit = false //定义一个boolean值，用来表示是否包含数字
        var isLowerCase = false //定义一个boolean值，用来表示是否包含字母
        var isUpperCase = false
        for (i in 0 until str.length) {
            if (Character.isDigit(str[i])) {   //用char包装类中的判断数字的方法判断每一个字符
                isDigit = true
            } else if (Character.isLowerCase(str[i])) {  //用char包装类中的判断字母的方法判断每一个字符
                isLowerCase = true
            } else if (Character.isUpperCase(str[i])) {
                isUpperCase = true
            }
        }
        // val regex = "^[a-zA-Z0-9]+$"
        var regex = Regex("^[a-zA-Z0-9]+$")
        return isDigit && isLowerCase && isUpperCase && str.matches(regex)
    }

}