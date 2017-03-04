package es.osw.cafelatte.presentation.analytics;

import android.content.Context;
import cafesolidario.osw.es.cafelatte.BuildConfig;
import com.segment.analytics.Analytics;
import com.segment.analytics.Properties;
import com.segment.analytics.android.integrations.amplitude.AmplitudeIntegration;
import com.segment.analytics.android.integrations.flurry.FlurryIntegration;
import com.segment.analytics.android.integrations.google.analytics.GoogleAnalyticsIntegration;
import com.segment.analytics.android.integrations.localytics.LocalyticsIntegration;
import es.osw.cafelatte.domain.analytics.EventMessenger;
import es.osw.cafelatte.domain.analytics.Transaction;
import java.math.BigDecimal;
import java.util.Map;
import javax.annotation.Nullable;

import static com.facebook.FacebookSdk.getApplicationContext;

public class SegmentEventTracker implements EventMessenger {

  public SegmentEventTracker(final Context context, boolean debug) {
    final Analytics.Builder builder = new Analytics.Builder(context, BuildConfig.SEGMENT_KEY).use(
        GoogleAnalyticsIntegration.FACTORY)
        .use(FlurryIntegration.FACTORY)
        .use(AmplitudeIntegration.FACTORY)
        .use(LocalyticsIntegration.FACTORY);

    if (debug) {
      builder.logLevel(Analytics.LogLevel.VERBOSE);
    }

    Analytics.setSingletonInstance(builder.build());
  }

  @Override public void send(final String eventName, @Nullable Map<String, String> properties) {
    final Properties segmentProperties = new Properties();

    if (properties != null) {
      for (Map.Entry<String, String> entry : properties.entrySet()) {
        segmentProperties.put(entry.getKey(), entry.getValue());
      }
    }

    Analytics.with(getApplicationContext()).track(eventName, segmentProperties);
  }

  @Override public void register(final String email) {
    Analytics.with(getApplicationContext()).identify(email);
  }

  @Override public void login(final String email) {
    Analytics.with(getApplicationContext()).identify(email);
  }

  @Override public void transaction(final Transaction transaction) {
    final Properties segmentProperties = new Properties();

    BigDecimal roundFinalPrice =
        new BigDecimal(transaction.getTotal()).setScale(2, BigDecimal.ROUND_HALF_UP);
    segmentProperties.putRevenue(roundFinalPrice.doubleValue());

    Analytics.with(getApplicationContext()).track("Transaction", segmentProperties);
  }

  @Override public void stopScreen(final Object payload) {
    //empty method
  }

  @Override public void startScreen(final Object payload) {
    //empty method
  }
}
