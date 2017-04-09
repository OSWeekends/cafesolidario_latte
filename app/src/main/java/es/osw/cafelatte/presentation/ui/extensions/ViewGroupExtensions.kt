package es.osw.cafelatte.presentation.ui.extensions

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

fun <T : ViewDataBinding> ViewGroup.inflateBinding(layoutId: Int, attachToParent: Boolean = false): T {
    return DataBindingUtil.inflate(LayoutInflater.from(context), layoutId, this, attachToParent)
}

fun ViewGroup.inflate(layoutId: Int, attachToParent: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutId, this, attachToParent)
}
