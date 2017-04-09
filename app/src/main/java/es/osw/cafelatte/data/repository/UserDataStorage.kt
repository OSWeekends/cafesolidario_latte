package es.osw.cafelatte.data.repository

import com.squareup.sqlbrite.BriteDatabase
import es.osw.cafelatte.data.model.ProfileDtoModel
import es.osw.cafelatte.domain.repository.UserStorage
import javax.inject.Inject

class UserDataStorage @Inject constructor(val database: BriteDatabase) : UserStorage {
    override fun clear() {
        database.execute(ProfileDtoModel.CLEAR)
    }

}
