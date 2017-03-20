package es.osw.cafelatte.presentation.di.base.module;

import android.content.Context;
import dagger.Module;
import dagger.Provides;
import es.osw.cafelatte.domain.prefs.UserPreferences;
import es.osw.cafelatte.presentation.UserAndroidPreferences;
import javax.inject.Singleton;

@Module public class DataModule {
  @Singleton @Provides public UserPreferences provideUserPreferences(Context context) {
    return new UserAndroidPreferences(
        context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE));
  }
}
