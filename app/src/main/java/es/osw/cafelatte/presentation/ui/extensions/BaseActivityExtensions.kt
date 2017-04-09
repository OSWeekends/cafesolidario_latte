package es.osw.cafelatte.presentation.ui.extensions

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import es.osw.cafelatte.presentation.di.base.component.ApplicationComponent
import es.osw.cafelatte.presentation.ui.BaseActivity
import es.osw.cafelatte.presentation.ui.CoffeeApp


val BaseActivity.applicationComponent: ApplicationComponent
    get() = (application as CoffeeApp).component

fun <T : ViewDataBinding> BaseActivity.setContentBinding(layoutId: Int): T {
    return DataBindingUtil.setContentView(this, layoutId)
}
