package com.tocaan.receiver.ui.utils

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes


object ViewUtils {

    fun Context?.toast(@StringRes textId: Int, duration: Int = Toast.LENGTH_LONG) =
        this?.let { Toast.makeText(it, textId, duration).show() }
}