package es.osw.cafelatte.presentation.di.base.module;

import dagger.Module;
import dagger.Provides;
import es.osw.cafelatte.presentation.ui.BaseActivity;

@Module public class ActivityModule {
  private final BaseActivity mActivity;

  public ActivityModule(BaseActivity activity) {
    this.mActivity = activity;
  }

  @Provides BaseActivity provideBaseActivity() {
    return this.mActivity;
  }
}
