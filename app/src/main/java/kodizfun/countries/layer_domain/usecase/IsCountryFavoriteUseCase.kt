package kodizfun.countries.layer_domain.usecase

import kodizfun.countries.di.AppScope
import kodizfun.countries.layer_domain.abstraction.CountryRepository
import javax.inject.Inject

@AppScope
class IsCountryFavoriteUseCase @Inject constructor(private val countryRepository: CountryRepository) {

    suspend operator fun invoke(countryCode: String): Boolean {
        val country = countryRepository.getCountryByCode(countryCode)
        return country != null
    }

}