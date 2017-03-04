package es.osw.cafelatte.presentation.ui;

import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;
import cafesolidario.osw.es.cafelatte.BuildConfig;
import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.core.CrashlyticsCore;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.stetho.Stetho;
import com.uphyca.stetho_realm.RealmInspectorModulesProvider;
import es.osw.cafelatte.domain.analytics.EventsTracker;
import es.osw.cafelatte.presentation.analytics.CrashlyticsEventTracker;
import es.osw.cafelatte.presentation.analytics.FirebaseEventTracker;
import es.osw.cafelatte.presentation.analytics.SegmentEventTracker;
import es.osw.cafelatte.presentation.di.base.component.ApplicationComponent;
import es.osw.cafelatte.presentation.di.base.component.DaggerApplicationComponent;
import es.osw.cafelatte.presentation.di.base.module.ApplicationModule;
import io.fabric.sdk.android.Fabric;
import timber.log.Timber;

public class CoffeeApp extends MultiDexApplication {
  private ApplicationComponent mComponent;

  @Override public void onCreate() {
    super.onCreate();
    MultiDex.install(this);

    initializeInjector();
    initializeLogger();
    initializeFacebook();
    initializeStetho();
    initializeFrabric();
    initializeAnalytics();
  }

  private void initializeLogger() {
    if (BuildConfig.DEBUG) {
      Timber.plant(new Timber.DebugTree());
    }
  }

  public ApplicationComponent getComponent() {
    return mComponent;
  }

  private void initializeAnalytics() {
    EventsTracker.INSTANCE.add(new SegmentEventTracker(this, BuildConfig.DEBUG))
        .add(new FirebaseEventTracker(this))
        .add(new CrashlyticsEventTracker());
  }

  private void initializeFacebook() {
    AppEventsLogger.activateApp(this);
  }

  @SuppressWarnings("PointlessBooleanExpression") private void initializeFrabric() {
    if (!BuildConfig.FABRIC) {
      return;
    }

    Crashlytics crashlyticsKit = new Crashlytics.Builder().core(
        new CrashlyticsCore.Builder().disabled(BuildConfig.DEBUG).build()).build();
    Fabric.with(this, crashlyticsKit, new Crashlytics());
  }

  private void initializeInjector() {
    mComponent =
        DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this)).build();
  }

  private void initializeStetho() {
    if (!BuildConfig.DEBUG) {
      return;
    }

    Stetho.initialize(Stetho.newInitializerBuilder(this)
        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
        .enableWebKitInspector(RealmInspectorModulesProvider.builder(this).build())
        .build());
  }
}
