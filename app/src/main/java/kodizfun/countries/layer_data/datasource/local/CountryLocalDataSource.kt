package kodizfun.countries.layer_data.datasource.local

import kodizfun.countries.layer_data.datasource.local.model.LocalDataCountry

interface CountryLocalDataSource {
    suspend fun get(): List<LocalDataCountry>
    suspend fun set(item: LocalDataCountry): LocalDataCountry
    suspend fun get(userId: String): LocalDataCountry
    suspend fun set(list: List<LocalDataCountry>): List<LocalDataCountry>
}

