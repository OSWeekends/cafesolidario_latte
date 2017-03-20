package es.osw.cafelatte.presentation.ui.splash;

import es.osw.cafelatte.domain.prefs.UserPreferences;
import es.osw.cafelatte.presentation.di.base.PerActivity;
import es.osw.cafelatte.presentation.ui.navigator.Navigator;
import io.reactivex.Completable;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import timber.log.Timber;

@PerActivity public class SplashViewModel {
  private final Navigator navigator;
  private final UserPreferences userPreferences;

  @Inject public SplashViewModel(final Navigator navigator, final UserPreferences preferences) {
    this.navigator = navigator;
    userPreferences = preferences;

    Completable.timer(2, TimeUnit.SECONDS)
        .doOnSubscribe(disposable -> Timber.d("Starting splash countdown"))
        .andThen(userPreferences.getUserName()
            .doOnSubscribe(disposable -> Timber.d("Checking if there is a user")))
        .isEmpty()
        .subscribe(noUserLoggedIn -> {
          if (noUserLoggedIn) {
            Timber.d("There is not user logged in, starting Login screen");
            navigator.startLoginScreen().subscribe(success -> {
              if (success) {
                Timber.d("User authenticated");
                navigator.startHomeScreen().andThen(navigator.finishScreen()).subscribe();
              } else {
                Timber.d("Shit");
              }
            }, error -> Timber.d(error, "Error trying to authenticate the user"));
          } else {
            Timber.d("There is a user logged in, starting home screen");
            navigator.startHomeScreen().andThen(navigator.finishScreen()).subscribe();
          }
        });
  }
}
