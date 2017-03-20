package es.osw.cafelatte.presentation.ui.login;

import es.osw.cafelatte.domain.interactor.RxLoginUserInteractor;
import es.osw.cafelatte.presentation.di.base.PerActivity;
import javax.inject.Inject;
import timber.log.Timber;

@PerActivity public class LoginViewModel {
  private final LoginNavigator navigator;
  private final RxLoginUserInteractor loginUserInteractor;

  @Inject
  public LoginViewModel(final LoginNavigator navigator, final RxLoginUserInteractor interactor) {
    this.navigator = navigator;
    loginUserInteractor = interactor;

    navigator.startLoginFlow()
        .doOnNext(user -> Timber.d("Authenticated: %1$s", user))
        .flatMap(loginUserInteractor::createObservable)
        .doOnNext(user -> Timber.d("Authenticated in our servers: %1$s", user))
        .subscribe(loginUser -> navigator.finishSuccess(), error -> {
          Timber.d(error, "Error trying to authenticate the user");
          navigator.finish();
        });
  }
}
