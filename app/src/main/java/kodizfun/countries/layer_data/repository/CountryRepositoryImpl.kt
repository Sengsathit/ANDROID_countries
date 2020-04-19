package kodizfun.countries.layer_data.repository

import kodizfun.countries.di.AppScope
import kodizfun.countries.layer_data.datasource.local.CountryLocalDataSource
import kodizfun.countries.layer_data.datasource.local.model.LocalDataCountry
import kodizfun.countries.layer_data.datasource.remote.CountryRemoteDataSource
import kodizfun.countries.layer_data.mapper.mapToDomain
import kodizfun.countries.layer_data.mapper.mapToLocalData
import kodizfun.countries.layer_domain.abstraction.CountryRepository
import kodizfun.countries.layer_domain.entity.Country
import java.lang.Exception
import javax.inject.Inject

@AppScope
class CountryRepositoryImpl @Inject constructor(
    private val localDataSource: CountryLocalDataSource,
    private val remoteDataSource: CountryRemoteDataSource
) : CountryRepository {

    override suspend fun getAllCountries(): ArrayList<Country> =
        remoteDataSource.getAllCountries().mapToDomain()

    override suspend fun addCountryToFavorites(country: Country) =
        localDataSource.addToFavorites(country.mapToLocalData())

    override suspend fun removeCountryFromFavorites(countryCode: String) =
        localDataSource.removeFromFavorites(countryCode)

    override suspend fun getCountryByCode(countryCode: String): Country? {
        return try {
            localDataSource.getCountryByCode(countryCode).mapToDomain()
        } catch(e: Exception) {
            null
        }
    }

}
