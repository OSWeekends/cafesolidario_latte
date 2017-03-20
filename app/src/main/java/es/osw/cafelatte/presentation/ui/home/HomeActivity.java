package es.osw.cafelatte.presentation.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import es.osw.cafelatte.presentation.ui.BaseActivity;
import timber.log.Timber;

public class HomeActivity extends BaseActivity {
  public static Intent getCallingIntent(Context context) {
    return new Intent(context, HomeActivity.class);
  }

  @Override protected void onCreate(@Nullable
  final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Timber.d("Starting %1$s", HomeActivity.class.getSimpleName());
  }
}
