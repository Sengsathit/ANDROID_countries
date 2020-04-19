package kodizfun.countries.layer_data.datasource.local

import kodizfun.countries.layer_data.datasource.local.model.LocalDataCountry

interface CountryLocalDataSource {
    suspend fun addToFavorites(country: LocalDataCountry)
    suspend fun removeFromFavorites(countryCode: String)
    suspend fun getCountryByCode(countryCode: String): LocalDataCountry
}

