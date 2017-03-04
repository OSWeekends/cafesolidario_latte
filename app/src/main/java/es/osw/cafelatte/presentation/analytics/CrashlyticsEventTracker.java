package es.osw.cafelatte.presentation.analytics;

import com.crashlytics.android.Crashlytics;
import es.osw.cafelatte.domain.analytics.EventMessenger;
import es.osw.cafelatte.domain.analytics.Transaction;
import io.fabric.sdk.android.Fabric;
import java.util.Map;
import javax.annotation.Nullable;

public class CrashlyticsEventTracker implements EventMessenger {
  @Override
  public void send(final String eventName, @Nullable final Map<String, String> properties) {
    if (Fabric.isInitialized()) {
      Crashlytics.log(eventName);
    }
  }

  @Override public void register(final String email) {
    if (Fabric.isInitialized()) {
      Crashlytics.setUserEmail(email);
    }
  }

  @Override public void login(final String email) {
    if (Fabric.isInitialized()) {
      Crashlytics.setUserEmail(email);
    }
  }

  @Override public void transaction(final Transaction transaction) {
    //empty method
  }

  @Override public void stopScreen(final Object payload) {
    //empty method
  }

  @Override public void startScreen(final Object payload) {
    //empty method
  }
}
