package es.osw.cafelatte.data.repository

import es.osw.cafelatte.data.model.ProfileLocalDataSource
import es.osw.cafelatte.data.model.mapper.ProfileDtoMapper
import es.osw.cafelatte.data.model.mapper.domain.ProfileMapper
import es.osw.cafelatte.domain.model.Profile
import es.osw.cafelatte.domain.observable.UserObservable
import es.osw.cafelatte.domain.prefs.UserPreferences
import es.osw.cafelatte.domain.repository.UserRepository
import io.reactivex.Completable
import io.reactivex.Maybe
import javax.inject.Inject

class UserDataRepository @Inject constructor(val profileLocalDataSource: ProfileLocalDataSource,
                                             val userObservable: UserObservable,
                                             val userPreferences: UserPreferences) :
        UserRepository {
    override fun insert(profile: Profile): Completable {
        return profileLocalDataSource.insert(ProfileDtoMapper.map(profile)).doOnComplete({
            userObservable.post(profile)
        })
    }

    override fun getByEmail(email: String): Maybe<Profile> {
        return profileLocalDataSource.selectByEmail(email).map { ProfileMapper.map(it) }
    }

    override fun getLoggedUser(): Maybe<Profile> {
        return userPreferences.getUserName()
                .flatMap { profileLocalDataSource.selectByEmail(it) }
                .map { ProfileMapper.map(it) }.doOnSuccess { userObservable.post(it) }
    }

}
