package es.osw.cafelatte.domain.interactor

import es.osw.cafelatte.domain.model.Profile
import es.osw.cafelatte.domain.repository.UserRepository
import io.reactivex.Maybe
import javax.inject.Inject

class GetLoggedUserInteractor @Inject constructor(val userRepository: UserRepository) {

    fun createObservable(): Maybe<Profile> {
        return userRepository.getLoggedUser()
    }

}

