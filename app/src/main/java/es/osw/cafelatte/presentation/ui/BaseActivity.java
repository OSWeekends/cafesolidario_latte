package es.osw.cafelatte.presentation.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
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

  @Override protected void onPause() {
    super.onPause();
    Timber.d("onPause()");
  }

  @Override protected void onResume() {
    super.onResume();
    Timber.d("onResume()");
  }
}
