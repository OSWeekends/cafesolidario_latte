package es.osw.cafelatte.presentation.di.base.module

import dagger.Module
import dagger.Provides
import es.osw.cafelatte.presentation.di.base.PerActivity
import es.osw.cafelatte.presentation.ui.BaseActivity

@PerActivity @Module class ActivityModule(private val activity: BaseActivity) {

    @Provides internal fun provideBaseActivity(): BaseActivity {
        return activity
    }
}
