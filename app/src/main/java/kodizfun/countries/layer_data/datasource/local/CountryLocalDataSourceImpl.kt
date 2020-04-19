package kodizfun.countries.layer_data.datasource.local

import kodizfun.countries.layer_data.datasource.local.database.dao.CountryDao
import kodizfun.countries.layer_data.datasource.local.model.LocalDataCountry
import kodizfun.countries.layer_domain.entity.Country
import javax.inject.Inject

class CountryLocalDataSourceImpl @Inject constructor(private val countryDao: CountryDao): CountryLocalDataSource {

    override suspend fun addToFavorites(country: LocalDataCountry) {
        countryDao.insertCountry(country)
    }

    override suspend fun removeFromFavorites(countryCode: String) {
        countryDao.deleteCountry(countryCode)
    }

    override suspend fun getCountryByCode(countryCode: String): LocalDataCountry {
        return countryDao.getCountryByCode(countryCode)
    }

}