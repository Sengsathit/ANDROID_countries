package kodizfun.countries.layer_data.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kodizfun.countries.layer_data.datasource.local.database.CountryDatabase
import kodizfun.countries.layer_data.datasource.local.database.dao.CountryDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideCountryDatabase(@ApplicationContext context: Context): CountryDatabase {
        return CountryDatabase.getInstance(context)
    }

    @Singleton
    @Provides
    fun provideLoginDao(countryDatabase: CountryDatabase): CountryDao {
        return countryDatabase.countryDao()
    }

}