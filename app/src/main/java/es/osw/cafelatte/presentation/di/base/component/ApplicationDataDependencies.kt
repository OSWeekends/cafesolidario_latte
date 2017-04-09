package es.osw.cafelatte.presentation.di.base.component

import es.osw.cafelatte.domain.prefs.UserPreferences

interface ApplicationDataDependencies {
    fun userPreferences(): UserPreferences
}
