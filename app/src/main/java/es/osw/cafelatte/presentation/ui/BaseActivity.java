package es.osw.cafelatte.presentation.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import es.osw.cafelatte.presentation.di.base.component.ApplicationComponent;
import timber.log.Timber;

public class BaseActivity extends AppCompatActivity {
  @Override protected void onCreate(@Nullable final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Timber.d("onCreate()");
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    Timber.d("onDestroy()");
  }

  protected ApplicationComponent getApplicationComponent() {
    return ((CoffeeApp) getApplication()).getComponent();
  }

  @Override
  protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    Timber.d("onActivityResult: [requestCode=%1$d, resultCode=%2$d, data=%3$s]", requestCode,
        resultCode, data);
  }

  @Override protected void onPause() {
    super.onPause();
    Timber.d("onPause()");
  }

  @Override protected void onResume() {
    super.onResume();
    Timber.d("onResume()");
  }
}
