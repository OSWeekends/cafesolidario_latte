package es.osw.cafelatte.presentation.ui.profile

import android.databinding.ObservableField
import es.osw.cafelatte.domain.interactor.LogoutInteractor
import es.osw.cafelatte.domain.model.Profile
import es.osw.cafelatte.domain.observable.UserObservable
import es.osw.cafelatte.presentation.ui.navigator.Navigator
import javax.inject.Inject

class ProfileViewModel @Inject constructor(val userObservable: UserObservable,
                                           val logoutInteractor: LogoutInteractor,
                                           val navigator: Navigator) {
    val profile = ObservableField<Profile>()

    fun load() {
        userObservable.observe().subscribe(profile::set)
    }

    fun logout() {
        logoutInteractor.createObservable().subscribe({ navigator.startSplashScreen().subscribe() })
    }
}

