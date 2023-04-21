package com.example.alokozapshopapplication.ui.bind

import android.annotation.SuppressLint
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.alokozapshopapplication.R
import java.io.File

class BindAdapters {
    companion object {

        @SuppressLint("CheckResult")
        @BindingAdapter(value = ["bind:imageSet", "bind:roundCornerRadius"], requireAll = false)
        @JvmStatic
        fun bindImageData(imageView: AppCompatImageView, url: String?, roundCornerRadius: String?) {
            if (url == null || url.isEmpty()) {
                imageView.setImageResource(R.color.teal_200)
                return
            } else {
                val requestOptions = RequestOptions()
                requestOptions.placeholder(R.color.teal_200)
                requestOptions.error(R.color.teal_200)
                requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL)
                if (roundCornerRadius != null) {
                    requestOptions.transforms(
                        CenterCrop(),
                        RoundedCorners(roundCornerRadius.toInt())
                    )
                }
                if ((url.contains("https://") || url.contains("http://"))) {
                    Glide.with(imageView.context).setDefaultRequestOptions(requestOptions).load(url)
                        .into(imageView)
                } else {
                    Glide.with(imageView.context).setDefaultRequestOptions(requestOptions)
                        .load(File(url)).into(imageView)
                }
            }
        }

        @BindingAdapter(value = ["bind:image"], requireAll = true)
        @JvmStatic
        fun bindImage(imageView: ImageView, url: String?) {
            if (url.isNullOrEmpty()) {
                //imageView.setImageResource(R.color.colorAccent)
                Glide.with(imageView.context)
                    .load(R.drawable.ic_alokozap_app_logo)
                    .into(imageView)
                return
            } else {
                val requestOptions = RequestOptions()
                requestOptions.placeholder(R.color.teal_200)
                requestOptions.error(R.color.teal_200)
                requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL)
                if ((url.contains("https://") || url.contains("http://"))) {
                    Glide.with(imageView.context).load(url)
                        .into(imageView)
                } else {
                    Glide.with(imageView.context).load(File(url)).into(imageView)
                }
            }
        }


        @BindingAdapter(value = ["bind:itemBg"], requireAll = true)
        @JvmStatic
        fun bindItemBg(constraintLayout: ConstraintLayout, isRead: String?) {
            if (isRead.isNullOrEmpty().not()) {
                if (isRead.equals("0")) {
                    constraintLayout.setBackgroundResource(R.color.white)
                } else {
                    constraintLayout.setBackgroundResource(R.color.white_gray)
                }
            } else {
                constraintLayout.setBackgroundResource(R.color.white_light_shadow)
            }
        }
    }

}