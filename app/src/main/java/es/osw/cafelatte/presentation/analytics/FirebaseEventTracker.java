package es.osw.cafelatte.presentation.analytics;

import android.content.Context;
import android.os.Bundle;
import com.google.firebase.analytics.FirebaseAnalytics;
import es.osw.cafelatte.domain.analytics.EventMessenger;
import es.osw.cafelatte.domain.analytics.Transaction;
import java.util.Map;
import javax.annotation.Nullable;

public class FirebaseEventTracker implements EventMessenger {
  private final FirebaseAnalytics firebaseAnalytics;

  public FirebaseEventTracker(Context context) {
    firebaseAnalytics = FirebaseAnalytics.getInstance(context);
  }

  @Override
  public void send(final String eventName, @Nullable final Map<String, String> properties) {
    final Bundle bundlerProperties = new Bundle();

    if (properties != null) {
      for (Map.Entry<String, String> entry : properties.entrySet()) {
        bundlerProperties.putString(entry.getKey(), entry.getValue());
      }
    }

    //we remove all spaces in the event name due to a firebase restriction
    firebaseAnalytics.logEvent(eventName.replaceAll("\\s", ""), bundlerProperties);
  }

  @Override public void register(final String email) {
    firebaseAnalytics.setUserId(email);
  }

  @Override public void login(final String email) {
    firebaseAnalytics.setUserId(email);
  }

  @Override public void transaction(final Transaction transaction) {

  }

  @Override public void stopScreen(final Object payload) {
    //empty method
  }

  @Override public void startScreen(final Object payload) {
    //empty method
  }
}
