package es.osw.cafelatte.presentation.ui.image

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.DrawableTypeRequest
import com.bumptech.glide.Glide
import java.io.File

object ImageLoader {
    fun load(context: Context, url: String?): Request {
        return Request(context, url)
    }

    fun load(context: Context, resId: Int): Request {
        return Request(context, resId)
    }

    fun load(context: Context, file: File): Request {
        return Request(context, file)
    }

    class Request {
        private val context: Context
        internal var requestManager: DrawableTypeRequest<*>
        private var placeHolder: Int = 0

        constructor(context: Context, url: String?) {
            this.context = context
            requestManager = Glide.with(context).load(url)
        }

        constructor(context: Context, file: File) {
            this.context = context
            requestManager = Glide.with(context).load(file)
        }

        constructor(context: Context, resId: Int) {
            this.context = context
            requestManager = Glide.with(context).load(resId)
        }

        fun centerCrop(): Request {
            requestManager.centerCrop()
            return this
        }

        fun withPlaceHolder(`val`: Int): Request {
            placeHolder = `val`
            requestManager.placeholder(`val`)
            return this
        }

        fun withPlaceHolder(`val`: Drawable): Request {
            requestManager.placeholder(`val`)
            return this
        }

        fun into(imageView: ImageView) {
            requestManager.into(imageView)
        }
    }
}
