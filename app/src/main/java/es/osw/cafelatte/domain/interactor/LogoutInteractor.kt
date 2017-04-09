package es.osw.cafelatte.domain.interactor

import com.firebase.ui.auth.AuthUI
import es.osw.cafelatte.domain.repository.UserStorage
import es.osw.cafelatte.presentation.ui.BaseActivity
import io.reactivex.Completable
import javax.inject.Inject

class LogoutInteractor @Inject constructor(val activity: BaseActivity, val userStorage: UserStorage) {

    fun createObservable(): Completable {
        return Completable.create { emitter ->
            AuthUI.getInstance().signOut(activity).addOnCompleteListener {
                emitter.onComplete()
            }
        }.doOnComplete { userStorage.clear() }
    }
}
