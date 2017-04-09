package es.osw.cafelatte.presentation.ui

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import es.osw.cafelatte.presentation.di.base.component.ApplicationComponent
import timber.log.Timber
import kotlin.reflect.KClass

open class BaseActivity : AppCompatActivity() {
    protected val applicationComponent: ApplicationComponent
        get() = (application as CoffeeApp).component

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.d("onCreate()")
    }

    open fun <T : Any> getComponent(clazz: KClass<T>): T? {
        return null
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.d("onDestroy()")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)
        Timber.d("onActivityResult: [requestCode=%1\$d, resultCode=%2\$d, data=%3\$s]", requestCode,
                resultCode, data)
    }

    override fun onPause() {
        super.onPause()
        Timber.d("onPause()")
    }

    override fun onResume() {
        super.onResume()
        Timber.d("onResume()")
    }
}
