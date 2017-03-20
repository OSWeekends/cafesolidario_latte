package es.osw.cafelatte.presentation.ui.splash;

import android.os.Bundle;
import android.support.annotation.Nullable;
import dagger.Component;
import es.osw.cafelatte.presentation.di.base.PerActivity;
import es.osw.cafelatte.presentation.di.base.component.ApplicationComponent;
import es.osw.cafelatte.presentation.di.base.module.ActivityModule;
import es.osw.cafelatte.presentation.ui.BaseActivity;
import javax.inject.Inject;

public class SplashActivity extends BaseActivity {
  @Inject SplashViewModel splashViewModel;

  @Override protected void onCreate(@Nullable final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    inject();
  }

  private void inject() {
    DaggerSplashActivity_SplashComponent.builder()
        .applicationComponent(getApplicationComponent())
        .activityModule(new ActivityModule(this))
        .build()
        .inject(this);
  }

  @PerActivity
  @Component(dependencies = ApplicationComponent.class, modules = { ActivityModule.class })
  public interface SplashComponent {
    void inject(SplashActivity activity);
  }
}
