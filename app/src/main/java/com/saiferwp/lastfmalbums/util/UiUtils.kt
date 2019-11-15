package com.saiferwp.lastfmalbums.util

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

fun convertDurationToString(duration: Long): String {
    val sec: Long = duration % 60
    val min: Long = (duration % 3600) / 60

    return String.format("%02d:%02d", min, sec)
}

fun hideKeyboard(view: View) {
    val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
}