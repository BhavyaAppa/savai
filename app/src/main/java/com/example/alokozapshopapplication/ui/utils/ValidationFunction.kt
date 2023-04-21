package com.example.alokozapshopapplication.ui.utils

import android.util.Patterns

object ValidationFunction {


    fun passwordValidation(password: String): Boolean {
        return when {
            !password.matches(".{8,}".toRegex()) -> false
            !password.matches("(?=.*[0-9])".toRegex()) -> false
            !password.matches("(?=.*[a-z])".toRegex()) -> false
            !password.matches("(?=.*[A-Z])".toRegex()) -> false
            !password.matches("(?=.*[@#$%^&+=])".toRegex()) -> false
            !password.matches("(?=\\S+$)".toRegex()) -> false
            else -> true

        }
    }

    fun isPasswordValidate(password: String): Boolean {
        return when {
            password.length < 6 -> return false
            !password.contains(Regex(".[0-9].")) -> false
            !password.contains(Regex(".[A-Z].")) -> false
            !password.contains(Regex(".[a-z].")) -> false
            !password.contains(Regex(".[~!@#\$%^&*()\\-_=+|\\[{\\]};:'\",<.>/?].")) -> false
            else -> true
        }
    }

    fun isFieldEmpty(validField: String): Boolean {
        return when {
            validField == null -> true
            validField == "null" -> true
            validField == " " -> true
            validField.isEmpty() -> true
            else -> false
        }
    }

    fun isEmailEmpty(email: String): Boolean {
        return when {
            email.equals(null) -> true
            email == "null" -> true
            email == " " -> true
            email.isEmpty() -> true
            else -> false
        }
    }

    fun isEmailValidate(email: String): Boolean {
        return when {
            Patterns.EMAIL_ADDRESS.matcher(email).matches() -> true
            else -> false
        }

    }

    fun isMobileValidateData(mobileNo: String): Boolean {
        return when {
            !Patterns.PHONE.matcher(mobileNo).matches() -> return true
            else -> false
        }
    }

    fun isMobileNoValid(mobileNo: String): Boolean {
        return when {
            mobileNo.length != 10-> return true
            else -> false
        }
    }

    fun isMobileNoEmpty(mobileNo: String): Boolean {
        return when {
            mobileNo.equals(null) -> true
            mobileNo == "null" -> true
            mobileNo == " " -> true
            mobileNo.isEmpty() -> true
            else -> false
        }
    }


}