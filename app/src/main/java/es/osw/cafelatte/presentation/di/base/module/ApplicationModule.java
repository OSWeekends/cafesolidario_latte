package es.osw.cafelatte.presentation.di.base.module;

import android.content.Context;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dagger.Module;
import dagger.Provides;
import es.osw.cafelatte.domain.AutoValueGsonFactory;
import es.osw.cafelatte.presentation.ui.CoffeeApp;
import javax.inject.Singleton;

@Module public class ApplicationModule {
  private final CoffeeApp application;

  public ApplicationModule(CoffeeApp application) {
    this.application = application;
  }

  @Provides @Singleton Context provideApplicationContext() {
    return this.application;
  }

  @Provides @Singleton Gson provideGson() {
    return new GsonBuilder().registerTypeAdapterFactory(AutoValueGsonFactory.create()).create();
  }
}
