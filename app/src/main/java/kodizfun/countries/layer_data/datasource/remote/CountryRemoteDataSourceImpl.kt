package kodizfun.countries.layer_data.datasource.remote

import kodizfun.countries.di.AppScope
import kodizfun.countries.layer_data.datasource.remote.model.RemoteDataCountry
import kodizfun.countries.layer_data.datasource.remote.network.CountriesApi
import javax.inject.Inject

@AppScope
class CountryRemoteDataSourceImpl @Inject constructor(private val countriesService: CountriesApi) :
    CountryRemoteDataSource {

    override suspend fun getAllCountries(): List<RemoteDataCountry> {
        return countriesService.getAllCountries()
    }
}