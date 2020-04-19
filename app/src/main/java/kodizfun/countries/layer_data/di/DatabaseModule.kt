package kodizfun.countries.layer_data.di

import android.content.Context
import dagger.Module
import dagger.Provides
import kodizfun.countries.di.AppScope
import kodizfun.countries.layer_data.datasource.local.database.CountryDatabase
import kodizfun.countries.layer_data.datasource.local.database.dao.CountryDao

@Module
class DatabaseModule {

    @AppScope
    @Provides
    fun provideCountryDatabase(context: Context): CountryDatabase {
        return CountryDatabase.getInstance(context)
    }

    @AppScope
    @Provides
    fun provideLoginDao(countryDatabase: CountryDatabase): CountryDao {
        return countryDatabase.countryDao()
    }

}