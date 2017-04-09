package es.osw.cafelatte.presentation.ui

import android.support.multidex.MultiDex
import android.support.multidex.MultiDexApplication
import com.crashlytics.android.Crashlytics
import com.crashlytics.android.core.CrashlyticsCore
import com.facebook.appevents.AppEventsLogger
import com.facebook.stetho.Stetho
import es.osw.cafelatte.BuildConfig
import es.osw.cafelatte.presentation.di.base.component.ApplicationComponent
import es.osw.cafelatte.presentation.di.base.component.DaggerApplicationComponent
import es.osw.cafelatte.presentation.di.base.module.ApplicationModule
import io.fabric.sdk.android.Fabric
import rx_activity_result2.RxActivityResult
import timber.log.Timber

class CoffeeApp : MultiDexApplication() {
    val component: ApplicationComponent by lazy {
        DaggerApplicationComponent.builder().applicationModule(ApplicationModule(this)).build()
    }

    override fun onCreate() {
        super.onCreate()
        MultiDex.install(this)
        RxActivityResult.register(this)

        initializeLogger()
        initializeFacebook()
        initializeStetho()
        initializeFrabric()
    }

    private fun initializeLogger() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    private fun initializeFacebook() {
        AppEventsLogger.activateApp(this)
    }

    private fun initializeFrabric() {
        if (!BuildConfig.FABRIC) {
            return
        }

        val crashlyticsKit = Crashlytics.Builder().core(
                CrashlyticsCore.Builder().disabled(BuildConfig.DEBUG).build()).build()
        Fabric.with(this, crashlyticsKit, Crashlytics())
    }

    private fun initializeStetho() {
        if (!BuildConfig.DEBUG) {
            return
        }

        Stetho.initializeWithDefaults(this)
    }
}
