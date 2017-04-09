package es.osw.cafelatte.presentation.ui.login

import android.app.Activity
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import es.osw.cafelatte.R
import es.osw.cafelatte.domain.model.LoginUser
import es.osw.cafelatte.presentation.di.base.PerActivity
import es.osw.cafelatte.presentation.model.mapper.LoginUserMapper
import es.osw.cafelatte.presentation.ui.BaseActivity
import io.reactivex.Observable
import rx_activity_result2.Result
import rx_activity_result2.RxActivityResult
import timber.log.Timber
import java.util.*
import javax.inject.Inject

@PerActivity class LoginNavigator @Inject constructor(activity: BaseActivity) {
    private val activity: LoginActivity = activity as LoginActivity

    fun startLoginFlow(): Observable<LoginUser> {
        return RxActivityResult.on(activity)
                .startIntent(AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setTheme(R.style.Login)
                        .setProviders(
                                Arrays.asList(AuthUI.IdpConfig.Builder(AuthUI.GOOGLE_PROVIDER).build(),
                                        AuthUI.IdpConfig.Builder(AuthUI.EMAIL_PROVIDER).build(),
                                        AuthUI.IdpConfig.Builder(AuthUI.FACEBOOK_PROVIDER).build()))
                        .build())
                .doOnSubscribe {
                    Timber.d("Starting login flow")
                }
                .doOnNext({ Timber.d("Received result from login $it") }).flatMap {
            if (it.resultCode() == Activity.RESULT_CANCELED) {
                return@flatMap Observable.error<Result<LoginActivity>>(Throwable())
            }
            Observable.just(it)

        }.map { LoginUserMapper.map(IdpResponse.fromResultIntent(it.data())!!) }
    }

    fun finishSuccess() {
        activity.setResult(Activity.RESULT_OK)
        activity.finish()
    }

    fun finish() {
        activity.finish()
    }
}
