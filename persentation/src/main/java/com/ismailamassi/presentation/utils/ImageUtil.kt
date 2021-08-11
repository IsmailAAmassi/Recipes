package com.ismailamassi.presentation.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import com.ismailamassi.presentation.R
import java.io.File

object ImageUtil {

    @JvmStatic
    @BindingAdapter(value = ["app:imageUrl"])
    fun ImageView.loadImageFromUrl(url: String?) {
        if (!url.isNullOrEmpty() && url.contains("http")) {
            this.load(url) {
                crossfade(true)
                placeholder(R.mipmap.ic_launcher)
                error(R.mipmap.ic_launcher)
//                    transformations(CircleCropTransformation())
            }
        } else {
            this.setImageResource(R.mipmap.ic_launcher)
        }
    }

    @JvmStatic
    @BindingAdapter(value = ["app:imageFilePath"])
    fun ImageView.loadImageFromFile(filePath: String?) {
        if (!filePath.isNullOrEmpty()) {
            this.load(File(filePath)) {
                crossfade(true)
                placeholder(R.mipmap.ic_launcher)
                error(R.mipmap.ic_launcher)
            }
        } else {
            this.setImageResource(R.mipmap.ic_launcher)
        }
    }
}