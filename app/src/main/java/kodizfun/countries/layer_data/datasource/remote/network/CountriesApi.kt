package kodizfun.countries.layer_data.datasource.remote.network

import kodizfun.countries.layer_data.datasource.remote.model.RemoteDataCountry
import retrofit2.http.GET

interface CountriesApi {
    @GET("/Sengsathit/DataMock/master/countries/countries.json")
    suspend fun getAllCountries(): List<RemoteDataCountry>
}