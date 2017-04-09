package es.osw.cafelatte.domain.observable

import es.osw.cafelatte.domain.model.Profile
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserObservable @Inject constructor() {
    val behaviorSubject: BehaviorSubject<Profile> = BehaviorSubject.create()

    fun post(data: Profile) = behaviorSubject.onNext(data)

    fun observe(): Observable<Profile> = behaviorSubject
}
