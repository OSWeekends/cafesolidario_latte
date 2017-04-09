package es.osw.cafelatte.presentation.di.base.module

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import es.osw.cafelatte.domain.AutoValueGsonFactory
import es.osw.cafelatte.presentation.ui.CoffeeApp
import javax.inject.Singleton

@Module class ApplicationModule(private val application: CoffeeApp) {

    @Provides @Singleton internal fun provideApplicationContext(): Context {
        return this.application
    }

    @Provides @Singleton internal fun provideGson(): Gson {
        return GsonBuilder().registerTypeAdapterFactory(AutoValueGsonFactory.create()).create()
    }
}
