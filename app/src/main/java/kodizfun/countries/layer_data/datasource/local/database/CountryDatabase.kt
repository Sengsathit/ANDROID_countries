package kodizfun.countries.layer_data.datasource.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kodizfun.countries.layer_data.datasource.local.database.dao.CountryDao
import kodizfun.countries.layer_data.datasource.local.model.LocalDataCountry

@Database(
    entities = [LocalDataCountry::class],
    version = 1
)
abstract class CountryDatabase : RoomDatabase() {
    abstract fun countryDao(): CountryDao

    companion object {

        private const val DATABASE_NAME = "country_db"

        // For Singleton instantiation
        @Volatile private var instance: CountryDatabase? = null

        fun getInstance(context: Context): CountryDatabase {
            return instance
                ?: synchronized(this) {
                instance
                    ?: buildDatabase(
                        context
                    )
                        .also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): CountryDatabase {
            return Room.databaseBuilder(
                context, CountryDatabase::class.java,
                DATABASE_NAME
            ).build()
        }
    }
}