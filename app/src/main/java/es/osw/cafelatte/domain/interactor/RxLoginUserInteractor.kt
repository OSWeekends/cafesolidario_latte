package es.osw.cafelatte.domain.interactor

import com.google.firebase.auth.FirebaseUser
import es.osw.cafelatte.domain.model.Profile
import es.osw.cafelatte.domain.repository.UserRepository
import es.osw.cafelatte.presentation.di.base.PerActivity
import es.osw.cafelatte.presentation.model.mapper.ProfileMapper
import io.reactivex.Completable
import javax.inject.Inject

@PerActivity class RxLoginUserInteractor @Inject constructor(val userRepository: UserRepository) {

    fun createObservable(param: FirebaseUser?): Completable {
        val profile = ProfileMapper.map(param)
        return userRepository.insert(profile)
    }

    private fun createFakeUser(): Profile {
        return Profile("name", "email", "image")
    }
}
