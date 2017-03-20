package es.osw.cafelatte.presentation;

import android.content.SharedPreferences;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import es.osw.cafelatte.domain.prefs.UserPreferences;
import io.reactivex.Maybe;
import timber.log.Timber;

public class UserAndroidPreferences implements UserPreferences {
  private static final String USER_EMAIL = "user_email";
  private final SharedPreferences sharedPreferences;

  public UserAndroidPreferences(final SharedPreferences preferences) {
    sharedPreferences = preferences;
  }

  @Override public Maybe<String> getUserName() {
    return Maybe.create(emitter -> {
      final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
      if (user == null) {
        Timber.d("There is no user logged in");

        emitter.onComplete();
        return;
      }

      final String userEmail = user.getEmail();

      Timber.d("There is a user logged in: %1$s", user);
      emitter.onSuccess(userEmail);
    });
  }
}
