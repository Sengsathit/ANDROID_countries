package kodizfun.countries.layer_domain.usecase

import kodizfun.countries.di.AppScope
import kodizfun.countries.layer_domain.abstraction.CountryRepository
import kodizfun.countries.layer_domain.entity.Country
import javax.inject.Inject

@AppScope
class GetCountryFromFavoritesUseCase @Inject constructor(private val countryRepository: CountryRepository) {

    suspend operator fun invoke(countryCode: String): Country? {
        val country = countryRepository.getCountryByCode(countryCode)
        country?.let {
            it.isFavorite = true
            return it
        }

        return null
    }

}