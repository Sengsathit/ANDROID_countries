package kodizfun.countries.layer_data.datasource.local

import kodizfun.countries.layer_data.datasource.local.model.LocalDataCountry
import javax.inject.Inject

class CountryLocalDataSourceImpl @Inject constructor():
    CountryLocalDataSource {
    override suspend fun get(): List<LocalDataCountry> {
        TODO("Not yet implemented")
    }

    override suspend fun get(userId: String): LocalDataCountry {
        TODO("Not yet implemented")
    }

    override suspend fun set(item: LocalDataCountry): LocalDataCountry {
        TODO("Not yet implemented")
    }

    override suspend fun set(list: List<LocalDataCountry>): List<LocalDataCountry> {
        TODO("Not yet implemented")
    }
}