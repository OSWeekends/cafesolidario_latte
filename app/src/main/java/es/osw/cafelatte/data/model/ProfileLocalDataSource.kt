package es.osw.cafelatte.data.model

import com.squareup.sqlbrite.BriteDatabase
import hu.akarnokd.rxjava.interop.RxJavaInterop
import io.reactivex.Completable
import io.reactivex.Maybe
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class ProfileLocalDataSource @Inject constructor(val database: BriteDatabase) {
    fun selectByEmail(email: String): Maybe<ProfileDto> {
        val select_by_email = ProfileDto.FACTORY
                .select_by_email(email)

        return RxJavaInterop.toV2Observable(
                database.createQuery(ProfileDtoModel.TABLE_NAME, select_by_email
                        .statement, *select_by_email.args)
                        .mapToOne { ProfileDto.SELECT_ALL_MAPPER.map(it) })
                .firstElement()

    }

    fun insert(data: ProfileDto): Completable {
        return Completable.fromAction {
            val insert_row = ProfileDtoModel.Insert_row(database.writableDatabase)
            insert_row.bind(data.name, data.email, data.image)
            insert_row.program.executeInsert()
        }
    }
}
