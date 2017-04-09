package es.osw.cafelatte.presentation.di.base.module

import android.content.Context
import com.squareup.sqlbrite.BriteDatabase
import com.squareup.sqlbrite.BuildConfig
import com.squareup.sqlbrite.SqlBrite
import dagger.Module
import dagger.Provides
import es.osw.cafelatte.data.local.db.CafeLatteOpenHelper
import rx.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Singleton


@Singleton @Module
class DBModule {
    @Provides fun provideBriteDatabase(context: Context): BriteDatabase {
        val sqlBrite = SqlBrite.Builder().logger { Timber.i("LocalDB: $it") }.build()
        return sqlBrite.wrapDatabaseHelper(CafeLatteOpenHelper(context), Schedulers.io()).apply {
            setLoggingEnabled(BuildConfig.DEBUG)
        }
    }

}
