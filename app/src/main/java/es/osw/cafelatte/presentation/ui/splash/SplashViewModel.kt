package es.osw.cafelatte.presentation.ui.splash

import es.osw.cafelatte.domain.interactor.GetLoggedUserInteractor
import es.osw.cafelatte.presentation.di.base.PerActivity
import es.osw.cafelatte.presentation.ui.navigator.Navigator
import io.reactivex.Completable
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@PerActivity class SplashViewModel @Inject constructor(navigator: Navigator,
                                                       getLoggedUserInteractor: GetLoggedUserInteractor) {

    init {

        Completable.timer(2, TimeUnit.SECONDS)
                .doOnSubscribe { Timber.d("Starting splash countdown") }
                .andThen(getLoggedUserInteractor.createObservable()
                        .doOnSubscribe { Timber.d("Checking if there is a user") })
                .isEmpty
                .subscribe { noUserLoggedIn ->
                    if (noUserLoggedIn) {
                        Timber.d("There is not user logged in, starting Login screen")
                        navigator.startLoginScreen().subscribe({
                            if (it) {
                                Timber.d("User authenticated")
                                navigator.startHomeScreen().andThen(navigator.finishScreen()).subscribe()
                            } else {
                                Timber.d("User cancelled authentication")
                                navigator.finishScreen().subscribe()
                            }
                        }, { error -> Timber.d(error, "Error trying to authenticate the user") })
                    } else {
                        Timber.d("There is a user logged in, starting home screen")
                        navigator.startHomeScreen().andThen(navigator.finishScreen()).subscribe()
                    }
                }
    }
}
