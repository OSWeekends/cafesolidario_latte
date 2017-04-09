package es.osw.cafelatte.domain.prefs

import io.reactivex.Maybe

interface UserPreferences {
    fun getUserName(): Maybe<String>
}
