package es.osw.cafelatte.presentation.ui.navigator;

import android.app.Activity;
import es.osw.cafelatte.presentation.di.base.PerActivity;
import es.osw.cafelatte.presentation.ui.BaseActivity;
import es.osw.cafelatte.presentation.ui.home.HomeActivity;
import es.osw.cafelatte.presentation.ui.login.LoginActivity;
import io.reactivex.Completable;
import io.reactivex.Single;
import javax.inject.Inject;
import rx_activity_result2.RxActivityResult;

@PerActivity public class Navigator {
  private final BaseActivity activity;

  @Inject public Navigator(final BaseActivity activity) {
    this.activity = activity;
  }

  public Completable startHomeScreen() {
    return Completable.fromAction(
        () -> activity.startActivity(HomeActivity.getCallingIntent(activity)));
  }

  public Single<Boolean> startLoginScreen() {
    return RxActivityResult.on(activity)
        .startIntent(LoginActivity.getCallingIntent(activity))
        .flatMapSingle(result -> Single.just(result.resultCode() == Activity.RESULT_OK))
        .singleOrError();
  }

  public Completable finishScreen() {
    return Completable.fromAction(activity::finish);
  }
}
