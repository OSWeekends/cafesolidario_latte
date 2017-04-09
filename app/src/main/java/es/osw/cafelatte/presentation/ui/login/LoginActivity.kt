package es.osw.cafelatte.presentation.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import dagger.Subcomponent
import es.osw.cafelatte.presentation.di.base.PerActivity
import es.osw.cafelatte.presentation.di.base.component.ActivityComponent
import es.osw.cafelatte.presentation.di.base.module.ActivityModule
import es.osw.cafelatte.presentation.ui.BaseActivity
import javax.inject.Inject

class LoginActivity : BaseActivity() {
    val component: LoginComponent by lazy {
        applicationComponent.loginComponent(ActivityModule(this))
    }

    @Inject lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        component.inject(this)
    }

    @PerActivity
    @Subcomponent(modules = arrayOf(ActivityModule::class))
    interface LoginComponent : ActivityComponent {
        fun inject(activity: LoginActivity)
    }

    companion object {
        fun getCallingIntent(context: Context): Intent = Intent(context, LoginActivity::class.java)
    }
}
