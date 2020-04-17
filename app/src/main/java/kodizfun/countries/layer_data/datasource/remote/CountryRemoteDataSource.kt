package kodizfun.countries.layer_data.datasource.remote

import kodizfun.countries.layer_data.datasource.remote.model.RemoteDataCountry

interface CountryRemoteDataSource {
    suspend fun getAllCountries(): List<RemoteDataCountry>
}