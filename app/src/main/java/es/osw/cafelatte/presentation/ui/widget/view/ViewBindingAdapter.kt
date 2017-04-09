package es.osw.cafelatte.presentation.ui.widget.view

import android.databinding.BindingAdapter
import android.databinding.BindingConversion
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import es.osw.cafelatte.presentation.ui.image.ImageLoader

@BindingConversion fun setVisibility(state: Boolean?): Int {
    return if (state != null && state) View.VISIBLE else View.GONE
}

@BindingAdapter(value = *arrayOf("imageUrl", "placeHolder"), requireAll = false)
fun setImageResource(imageView: ImageView, url: String?, placeHolder: Drawable?) {
    val request = ImageLoader.load(imageView.context, url)
            .centerCrop()

    if (placeHolder != null) {
        request.withPlaceHolder(placeHolder)

    }

    request.into(imageView)
}
