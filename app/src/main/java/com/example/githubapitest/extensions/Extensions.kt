package com.example.punchandroidtest.extension

import android.content.Context
import android.net.Uri
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.RequestManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

private var mToast: Toast? = null

val LOG_TAG = "com.example.githubapitest"

val Any.TAG: String
    get() {
        val tag = javaClass.simpleName
        return if (tag.length <= 23) tag else tag.substring(0, 23)
    }

fun Context?.showToast(@StringRes resId: Int) = showToast(this?.getString(resId))

fun Context?.showToast(message: String?) {
    var toastMessage: String? = message
    if (message != null && message.isNumeric) {
        toastMessage = try {
            this?.getString(message.toInt())
        } catch (e: Exception) {
            message
        }
    }
    mToast?.cancel()
    if (this != null)
        mToast = Toast.makeText(this, toastMessage, Toast.LENGTH_SHORT).apply { show() }
}

inline val String.isNumeric
    get() = matches("[-+]?\\d*\\.?\\d+".toRegex())

inline val String.tagName: String
    get() = "$LOG_TAG.$this"

fun <T> T?.toJson(): String {
    return Gson().toJson(this, object : TypeToken<T>() {}.type)
}



