package com.bapidas.composesample.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.bapidas.composesample.BuildConfig
import com.bapidas.composesample.data.db.dao.NewsArticlesDao
import com.bapidas.composesample.data.db.model.ArticleEntity
import timber.log.Timber

@Database(
    entities = [
        ArticleEntity::class
    ],
    version = BuildConfig.DATABASE_VERSION
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun newsArticlesDao(): NewsArticlesDao

    companion object {
        private const val DB_NAME = "articles-db"
        private val lock = Any()
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            synchronized(lock) {
                Timber.d("getInstance")
                if (INSTANCE == null) {
                    INSTANCE = createDB(context)
                }
                return INSTANCE as AppDatabase
            }
        }

        private fun createDB(context: Context): AppDatabase {
            Timber.d("createDB")
            val database: Builder<AppDatabase> =
                Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME)

            return database
                .allowMainThreadQueries()
                .addCallback(object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        Timber.d("onCreate")
                    }
                })
                .build()
        }
    }
}