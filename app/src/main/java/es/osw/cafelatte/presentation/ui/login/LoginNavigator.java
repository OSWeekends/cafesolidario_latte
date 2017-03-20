package es.osw.cafelatte.presentation.ui.login;

import android.app.Activity;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import es.osw.cafelatte.R;
import es.osw.cafelatte.domain.model.LoginUser;
import es.osw.cafelatte.presentation.di.base.PerActivity;
import es.osw.cafelatte.presentation.model.mapper.LoginUserMapper;
import es.osw.cafelatte.presentation.ui.BaseActivity;
import io.reactivex.Observable;
import java.util.Arrays;
import javax.inject.Inject;
import rx_activity_result2.RxActivityResult;
import timber.log.Timber;

@PerActivity public class LoginNavigator {
  private final LoginActivity activity;

  @Inject public LoginNavigator(final BaseActivity activity) {
    this.activity = (LoginActivity) activity;
  }

  public Observable<LoginUser> startLoginFlow() {
    return RxActivityResult.on(activity)
        .startIntent(AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setTheme(R.style.Login)
            .setProviders(
                Arrays.asList(new AuthUI.IdpConfig.Builder(AuthUI.GOOGLE_PROVIDER).build(),
                    new AuthUI.IdpConfig.Builder(AuthUI.EMAIL_PROVIDER).build(),
                    new AuthUI.IdpConfig.Builder(AuthUI.FACEBOOK_PROVIDER).build()))
            .build())
        .doOnSubscribe(disposable -> Timber.d("Starting login flow"))
        .doOnNext(result -> Timber.d("Received result from login %1$s", result))
        .flatMap(result -> {
          if (result.resultCode() != Activity.RESULT_OK) {
            return Observable.error(new Throwable("Canceled by the user"));
          }

          return Observable.just(result);
        })
        .map(result -> IdpResponse.fromResultIntent(result.data()))
        .map(LoginUserMapper::map);
  }

  public void finishSuccess() {
    activity.setResult(Activity.RESULT_OK);
    activity.finish();
  }

  public void finish() {
    activity.finish();
  }
}
