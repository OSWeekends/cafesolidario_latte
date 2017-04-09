package es.osw.cafelatte.data.local.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import es.osw.cafelatte.data.model.ProfileDtoModel

class CafeLatteOpenHelper(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {
    companion object {
        val DB_NAME = "cafe_late"
        private val DB_VERSION = 1
    }

    override fun onCreate(database: SQLiteDatabase) {
        database.execSQL(ProfileDtoModel.CREATE_TABLE)
    }

    override fun onUpgrade(database: SQLiteDatabase, i: Int, i1: Int) {

    }
}
