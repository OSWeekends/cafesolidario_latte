package es.osw.cafelatte.presentation.di.base.component;

import dagger.Component;
import es.osw.cafelatte.presentation.di.base.PerActivity;
import es.osw.cafelatte.presentation.di.base.module.ActivityModule;
import es.osw.cafelatte.presentation.ui.BaseActivity;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = { ActivityModule.class })
public interface ActivityComponent {
  BaseActivity activity();
}
