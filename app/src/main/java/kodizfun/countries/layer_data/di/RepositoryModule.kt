package kodizfun.countries.layer_data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kodizfun.countries.layer_data.repository.CountryRepositoryImpl
import kodizfun.countries.layer_domain.abstraction.CountryRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindCountryRepository(countryRemoteDataSource: CountryRepositoryImpl): CountryRepository
}