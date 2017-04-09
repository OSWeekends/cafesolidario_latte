package es.osw.cafelatte.presentation.ui.main.profile

import android.databinding.ObservableField
import es.osw.cafelatte.domain.model.Profile
import es.osw.cafelatte.domain.observable.UserObservable
import es.osw.cafelatte.presentation.ui.navigator.Navigator
import javax.inject.Inject

class NavBarProfileViewModel @Inject constructor(userObservable: UserObservable,
                                                 val navigator: Navigator) {
    val profile: ObservableField<Profile> = ObservableField()

    init {
        userObservable.observe().subscribe(profile::set)
    }

    fun onClick() {
        navigator.startProfileActivity().subscribe()
    }
}
