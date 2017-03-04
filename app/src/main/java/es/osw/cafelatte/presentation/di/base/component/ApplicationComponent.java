package es.osw.cafelatte.presentation.di.base.component;

import android.content.Context;
import com.google.gson.Gson;
import dagger.Component;
import es.osw.cafelatte.presentation.di.base.module.ApplicationModule;
import es.osw.cafelatte.presentation.ui.CoffeeApp;
import javax.inject.Singleton;

@Singleton // Constraints this component to one-per-application or unscoped bindings.
@Component(modules = {
    ApplicationModule.class
}) public interface ApplicationComponent {
  void inject(CoffeeApp app);

  Context context();

  Gson gson();
}
