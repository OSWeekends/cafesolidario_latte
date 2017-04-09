package es.osw.cafelatte.presentation.ui.splash

import android.content.Context
import android.content.Intent
import android.os.Bundle
import dagger.Subcomponent
import es.osw.cafelatte.presentation.di.base.PerActivity
import es.osw.cafelatte.presentation.di.base.component.ActivityComponent
import es.osw.cafelatte.presentation.di.base.module.ActivityModule
import es.osw.cafelatte.presentation.ui.BaseActivity
import javax.inject.Inject

class SplashActivity : BaseActivity() {
    val splashComponent: SplashComponent by lazy {
        applicationComponent.splashComponent(ActivityModule(this))
    }
    @Inject internal lateinit var splashViewModel: SplashViewModel

    companion object {
        fun getCallingIntent(context: Context) = Intent(context, SplashActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        splashComponent.inject(this)
    }

    @PerActivity
    @Subcomponent(modules = arrayOf(ActivityModule::class))
    interface SplashComponent : ActivityComponent {
        fun inject(activity: SplashActivity)
    }
}
