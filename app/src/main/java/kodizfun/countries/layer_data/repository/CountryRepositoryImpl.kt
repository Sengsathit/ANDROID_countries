package kodizfun.countries.layer_data.repository

import kodizfun.countries.di.AppScope
import kodizfun.countries.layer_data.datasource.local.CountryLocalDataSource
import kodizfun.countries.layer_data.datasource.remote.CountryRemoteDataSource
import kodizfun.countries.layer_data.mapper.mapToDomain
import kodizfun.countries.layer_domain.abstraction.CountryRepository
import kodizfun.countries.layer_domain.entity.Country
import javax.inject.Inject

@AppScope
class CountryRepositoryImpl @Inject constructor(
    private val localDataSource: CountryLocalDataSource,
    private val remoteDataSource: CountryRemoteDataSource
) : CountryRepository {

    override suspend fun getAllCountries(): List<Country> =
        remoteDataSource.getAllCountries().mapToDomain()

}
