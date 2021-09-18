package com.ismailamassi.presentation.utils

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.appcompat.content.res.AppCompatResources
import androidx.databinding.BindingAdapter
import coil.ImageLoader
import coil.load
import coil.request.ImageRequest
import coil.request.SuccessResult
import coil.transform.CircleCropTransformation
import coil.transform.RoundedCornersTransformation
import com.ismailamassi.presentation.R
import java.io.File

object ImageUtil {


    @JvmStatic
    @BindingAdapter(value = ["app:normalImageUrl"])
    fun ImageView.loadNormalImageFromUrl(url: String?) {
        if (!url.isNullOrEmpty() && url.contains("http")) {
            this.load(url) {
                placeholder(R.drawable.image_placeholder)
                error(R.drawable.image_error)
            }
        } else {
            this.setImageResource(R.mipmap.ic_launcher)
        }
    }

    @JvmStatic
    @BindingAdapter(value = ["app:circleImageUrl"])
    fun ImageView.loadCircleImageFromUrl(url: String?) {
        if (!url.isNullOrEmpty() && url.contains("http")) {
            this.load(url) {
                crossfade(true)
                transformations(CircleCropTransformation())
                placeholder(R.drawable.image_placeholder)
                error(R.drawable.image_error)
            }
        } else {
            this.setImageResource(R.mipmap.ic_launcher)
        }
    }


    @JvmStatic
    @BindingAdapter(value = ["app:roundedImageUrl"])
    fun ImageView.loadRoundedImageFromUrl(url: String?) {
        if (!url.isNullOrEmpty() && url.contains("http")) {
            this.load(url) {
                crossfade(true)
                transformations(RoundedCornersTransformation(4f))
                placeholder(R.drawable.image_placeholder)
                error(R.drawable.image_error)
            }
        } else {
            this.setImageResource(R.mipmap.ic_launcher)
        }
    }

    suspend fun Context.getDrawableFromUrl(url: String?): Drawable? {
        return if (!url.isNullOrEmpty() && url.contains("http")) {
            val loader = ImageLoader(this)
            val request = ImageRequest.Builder(this)
                .data(url)
                .transformations(CircleCropTransformation())
                .build()
            (loader.execute(request) as SuccessResult).drawable
        } else {
            AppCompatResources.getDrawable(this, R.mipmap.ic_launcher)
        }
    }

    @JvmStatic
    @BindingAdapter(value = ["app:imageFilePath"])
    fun ImageView.loadImageFromFile(filePath: String?) {
        if (!filePath.isNullOrEmpty()) {
            this.load(File(filePath)) {
                crossfade(true)
                placeholder(R.drawable.image_placeholder)
                transformations(RoundedCornersTransformation(4f))
                error(R.drawable.image_error)
            }
        } else {
            this.setImageResource(R.mipmap.ic_launcher)
        }
    }
}