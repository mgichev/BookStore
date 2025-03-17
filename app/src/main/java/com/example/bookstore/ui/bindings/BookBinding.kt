package com.example.bookstore.ui.bindings

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

class BookBinding {

    companion object {

        @JvmStatic
        @BindingAdapter("app:image")
        fun loadImage(imageView: ImageView, url: String?) {
            Glide.with(imageView.context).load(url).into(imageView)
        }

        @JvmStatic
        @BindingAdapter("app:text")
        fun loadText(textView: TextView, text: String?) {
            textView.text = text
        }

        @JvmStatic
        @BindingAdapter("app:text")
        fun loadText(textView: TextView, list: List<String>?) {
            textView.text = list?.joinToString()
        }
    }

}