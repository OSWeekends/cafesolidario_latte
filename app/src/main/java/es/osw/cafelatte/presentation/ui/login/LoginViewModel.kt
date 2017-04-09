package es.osw.cafelatte.presentation.ui.login

import com.google.firebase.auth.FirebaseAuth
import es.osw.cafelatte.domain.interactor.RxLoginUserInteractor
import es.osw.cafelatte.presentation.di.base.PerActivity
import timber.log.Timber
import javax.inject.Inject

@PerActivity class LoginViewModel @Inject
constructor(val navigator: LoginNavigator, val loginUserInteractor: RxLoginUserInteractor) {

    init {

        navigator.startLoginFlow()
                .doOnNext { user -> Timber.d("Authenticated: %1\$s", user) }
                .flatMapCompletable {
                    loginUserInteractor.createObservable(FirebaseAuth.getInstance()
                            .currentUser)
                }
                .subscribe({ navigator.finishSuccess() }) { error ->
                    Timber.d(error, "Error trying to authenticate the user")
                    navigator.finish()
                }
    }
}
