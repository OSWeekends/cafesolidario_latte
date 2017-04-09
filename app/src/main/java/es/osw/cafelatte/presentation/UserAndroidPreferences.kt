package es.osw.cafelatte.presentation

import com.google.firebase.auth.FirebaseAuth
import es.osw.cafelatte.domain.prefs.UserPreferences
import io.reactivex.Maybe
import timber.log.Timber
import javax.inject.Inject

class UserAndroidPreferences @Inject constructor() : UserPreferences {

    override fun getUserName(): Maybe<String> {
        return Maybe.create<String> { emitter ->
            val user = FirebaseAuth.getInstance().currentUser
            if (user == null) {
                Timber.d("There is no user logged in")

                emitter.onComplete()
                return@create
            }

            val userEmail = user.email

            Timber.d("There is a user logged")
            emitter.onSuccess(userEmail)
        }
    }
}
