package kodizfun.countries.layer_data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kodizfun.countries.layer_data.datasource.local.CountryLocalDataSource
import kodizfun.countries.layer_data.datasource.local.CountryLocalDataSourceImpl
import kodizfun.countries.layer_data.datasource.remote.CountryRemoteDataSource
import kodizfun.countries.layer_data.datasource.remote.CountryRemoteDataSourceImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Singleton
    @Binds
    abstract fun bindCountryRemoteDataSource(countryRemoteDataSource: CountryRemoteDataSourceImpl): CountryRemoteDataSource

    @Singleton
    @Binds
    abstract fun bindCountryLocalDataSource(countryLocalDataSource: CountryLocalDataSourceImpl): CountryLocalDataSource

}