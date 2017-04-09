package es.osw.cafelatte.domain.repository

import es.osw.cafelatte.domain.model.Profile
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single

interface UserRepository {
    fun insert(profile: Profile): Completable

    fun getByEmail(email: String): Maybe<Profile>
    fun getLoggedUser(): Maybe<Profile>
}
