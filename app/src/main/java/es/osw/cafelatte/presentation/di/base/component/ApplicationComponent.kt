package es.osw.cafelatte.presentation.di.base.component

import dagger.Component
import es.osw.cafelatte.presentation.di.base.module.ActivityModule
import es.osw.cafelatte.presentation.di.base.module.ApplicationModule
import es.osw.cafelatte.presentation.di.base.module.DBModule
import es.osw.cafelatte.presentation.di.base.module.DataBindingModule
import es.osw.cafelatte.presentation.ui.CoffeeApp
import es.osw.cafelatte.presentation.ui.creditcard.AddCreditCardActivity
import es.osw.cafelatte.presentation.ui.login.LoginActivity
import es.osw.cafelatte.presentation.ui.main.HomeActivity
import es.osw.cafelatte.presentation.ui.profile.ProfileActivity
import es.osw.cafelatte.presentation.ui.splash.SplashActivity
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ApplicationModule::class, DataBindingModule::class, DBModule::class))
interface ApplicationComponent : ApplicationDataDependencies {
    fun inject(app: CoffeeApp)

    fun homeComponent(activityModule: ActivityModule): HomeActivity.HomeComponent

    fun loginComponent(activityModule: ActivityModule): LoginActivity.LoginComponent

    fun addCreditCardComponent(activityModule: ActivityModule): AddCreditCardActivity.AddCreditCardComponent

    fun splashComponent(activityModule: ActivityModule): SplashActivity.SplashComponent

    fun profileComponent(activityModule: ActivityModule): ProfileActivity.ProfileComponent
}
