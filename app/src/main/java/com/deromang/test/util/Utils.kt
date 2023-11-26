package com.deromang.test.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.deromang.test.R

fun ImageView.setImageUrl(context: Context, url: String) {
    Glide.with(context).load(url).diskCacheStrategy(DiskCacheStrategy.AUTOMATIC).placeholder(R.drawable.img_default).centerCrop().into(this)
    this.scaleType = ImageView.ScaleType.FIT_XY
    this.adjustViewBounds = true
}

fun share(context: Context, url: String) {
    context.startActivity(Intent.createChooser(Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, url)
        putExtra(Intent.EXTRA_TITLE, context.getString(R.string.label_share_url))
        type = "text/plain"
    }, null))
}

fun Fragment.hideKeyboard() {
    view?.let { activity?.hideKeyboard(it) }
}

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}