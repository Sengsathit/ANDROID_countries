package kodizfun.countries.layer_data.di

import dagger.Binds
import dagger.Module
import kodizfun.countries.di.AppScope
import kodizfun.countries.layer_data.repository.CountryRepositoryImpl
import kodizfun.countries.layer_domain.abstraction.CountryRepository

@Module
abstract class RepositoryModule {

    @AppScope
    @Binds
    abstract fun bindCountryRepository(countryRemoteDataSource: CountryRepositoryImpl): CountryRepository
}