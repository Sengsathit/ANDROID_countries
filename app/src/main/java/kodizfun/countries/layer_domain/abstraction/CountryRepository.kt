package kodizfun.countries.layer_domain.abstraction

import kodizfun.countries.layer_domain.entity.Country

interface CountryRepository {
    suspend fun getAllCountries(): ArrayList<Country>
}