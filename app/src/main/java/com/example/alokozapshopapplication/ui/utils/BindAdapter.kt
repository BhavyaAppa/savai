package com.example.alokozapshopapplication.ui.utils


import android.text.method.LinkMovementMethod
import android.widget.RatingBar
import androidx.core.text.HtmlCompat

import androidx.databinding.BindingAdapter
import com.google.android.material.textview.MaterialTextView


class BindAdapter {

    companion object {

        @JvmStatic
        @BindingAdapter("android:ratingbar")
        fun setRating(view: RatingBar?, rating: String) {
            if (view != null) {
                val rate = rating.toFloat()
                view.rating = rate
            }
        }
        @BindingAdapter("renderHtml")
        @JvmStatic
        fun setText(view: MaterialTextView, description: String?) {
            if (description != null) {
                view.text = HtmlCompat.fromHtml(description, HtmlCompat.FROM_HTML_MODE_COMPACT)
                view.movementMethod = LinkMovementMethod.getInstance()
            } else {
                view.text = ""
            }
        }
    }
}