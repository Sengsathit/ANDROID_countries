package kodizfun.countries.layer_data.di

import dagger.Binds
import dagger.Module
import kodizfun.countries.di.AppScope
import kodizfun.countries.layer_data.datasource.local.CountryLocalDataSource
import kodizfun.countries.layer_data.datasource.local.CountryLocalDataSourceImpl
import kodizfun.countries.layer_data.datasource.remote.CountryRemoteDataSource
import kodizfun.countries.layer_data.datasource.remote.CountryRemoteDataSourceImpl
import kodizfun.countries.layer_data.repository.CountryRepositoryImpl
import kodizfun.countries.layer_domain.abstraction.CountryRepository

@Module
abstract class DataModule {

    @AppScope
    @Binds
    abstract fun provideCountryRepository(countryRemoteDataSource: CountryRepositoryImpl): CountryRepository

    @AppScope
    @Binds
    abstract fun provideCountryRemoteDataSource(countryRemoteDataSource: CountryRemoteDataSourceImpl): CountryRemoteDataSource

    @AppScope
    @Binds
    abstract fun provideCountryLocalDataSource(countryLocalDataSource: CountryLocalDataSourceImpl): CountryLocalDataSource

}