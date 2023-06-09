package com.example.alokozapshopapplication.ui.utils

open class Event<out T>(private val content: T) {

    var hasBeenHandled = false

    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }

}