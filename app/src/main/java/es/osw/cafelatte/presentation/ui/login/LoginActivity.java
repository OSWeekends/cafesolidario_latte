package es.osw.cafelatte.presentation.ui.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import dagger.Component;
import es.osw.cafelatte.presentation.di.base.PerActivity;
import es.osw.cafelatte.presentation.di.base.component.ApplicationComponent;
import es.osw.cafelatte.presentation.di.base.module.ActivityModule;
import es.osw.cafelatte.presentation.ui.BaseActivity;
import javax.inject.Inject;

public class LoginActivity extends BaseActivity {
  @Inject LoginViewModel viewModel;

  public static Intent getCallingIntent(Context context) {
    return new Intent(context, LoginActivity.class);
  }

  @Override protected void onCreate(@Nullable final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    inject();
  }

  private void inject() {
    DaggerLoginActivity_LoginComponent.builder()
        .applicationComponent(getApplicationComponent())
        .activityModule(new ActivityModule(this))
        .build()
        .inject(this);
  }

  @PerActivity
  @Component(dependencies = ApplicationComponent.class, modules = { ActivityModule.class })
  public interface LoginComponent {
    void inject(LoginActivity activity);
  }
}
