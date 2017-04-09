package es.osw.cafelatte.presentation.di.base.module

import dagger.Binds
import dagger.Module
import es.osw.cafelatte.data.repository.UserDataRepository
import es.osw.cafelatte.data.repository.UserDataStorage
import es.osw.cafelatte.domain.prefs.UserPreferences
import es.osw.cafelatte.domain.repository.UserRepository
import es.osw.cafelatte.domain.repository.UserStorage
import es.osw.cafelatte.presentation.UserAndroidPreferences

@Module abstract class DataBindingModule {
    @Binds abstract fun userPreferences(userPreferences: UserAndroidPreferences): UserPreferences

    @Binds abstract fun userRepository(userRepository: UserDataRepository): UserRepository

    @Binds abstract fun userStorage(userStorage: UserDataStorage): UserStorage
}
