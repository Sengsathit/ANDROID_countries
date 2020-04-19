package kodizfun.countries.layer_data.di

import dagger.Binds
import dagger.Module
import kodizfun.countries.di.AppScope
import kodizfun.countries.layer_data.datasource.local.CountryLocalDataSource
import kodizfun.countries.layer_data.datasource.local.CountryLocalDataSourceImpl
import kodizfun.countries.layer_data.datasource.remote.CountryRemoteDataSource
import kodizfun.countries.layer_data.datasource.remote.CountryRemoteDataSourceImpl

@Module
abstract class DataSourceModule {

    @AppScope
    @Binds
    abstract fun bindCountryRemoteDataSource(countryRemoteDataSource: CountryRemoteDataSourceImpl): CountryRemoteDataSource

    @AppScope
    @Binds
    abstract fun bindCountryLocalDataSource(countryLocalDataSource: CountryLocalDataSourceImpl): CountryLocalDataSource

}