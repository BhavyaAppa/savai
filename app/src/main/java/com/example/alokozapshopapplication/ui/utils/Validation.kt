package com.example.alokozapshopapplication.ui.utils

import android.util.Patterns
import java.util.regex.Matcher
import java.util.regex.Pattern


object Validation {

/*
    fun isEmailEmpty(email: String): Boolean {
        if (email.isEmpty()) {
            return true
        }
        return false
    }*/

/*    fun isPasswordEmpty(password: String): Boolean {
        if (password.isEmpty()) {
            return true
        }
        return false
    }

    fun isPasswordMinimumLength(password: String): Boolean {
        if (password.length < 6) {
            return true
        }
        return false
    }

    fun isPasswordUpperCase(password: String): Boolean {
        if (!password.matches(".*[A-Z].*".toRegex())) {
            return true
        }
        return false
    }

    fun isPasswordDigit(password: String): Boolean {
        if (!password.matches(".*[0-9].*".toRegex())) {
            return true
        }
        return false
    }

    fun isPasswordLowerCase(password: String): Boolean {
        if (!password.matches(".*[a-z].*".toRegex())) {
            return true
        }
        return false
    }

    fun isPasswordSpecialChar(password: String): Boolean {
        if (!password.matches(".*[@#\$%^&+=].*".toRegex())) {
            return true
        }
        return false
    }*/

/*    fun isMobileEmpty(mobileNo: String): Boolean {
        if (mobileNo.isEmpty()) {
            return true
        }
        return false
    }*/


    fun isEmailValid(email: String): Boolean {
        return when {
            !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                true
            }
            else -> false
        }
    }

    fun isMobileValidation(mobileNo: String): Boolean {
        return when {
            !Patterns.PHONE.matcher(mobileNo.trim()).matches() -> {
                true
            }
            else -> false
        }
    }

    fun isMobileValidate(mobileNo: String): Boolean {
        return when {
            mobileNo.length < 8 || mobileNo.length > 14 -> {
                true
            }
            else -> false
        }
    }

    fun isValidPassword(password: String): Boolean {
        val pattern: Pattern

        val passwordRegex ="^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{6,}$"


        pattern = Pattern.compile(passwordRegex)
        val matcher: Matcher = pattern.matcher(password)
        return matcher.matches()
    }

    fun isFieldEmpty(field: String): Boolean {
        return when {
            field.isEmpty() -> {
                true
            }
            field == " " -> {
                true
            }
            field == "null" -> {
                true
            }
            field.equals(null) -> {
                true
            }
            else -> false
        }


    }

    fun isNameValidate(name: String): Boolean {
        return when {
            name.length < 2 || name.length > 100 -> {
                true
            }
            else -> false
        }
    }

    fun isSwitchValidate(isChecked:Boolean): Boolean {
        return when {
            isChecked -> {
                true
            }
            else -> false
        }
    }




}