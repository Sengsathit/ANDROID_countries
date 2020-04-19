package kodizfun.countries.layer_domain.usecase

import kodizfun.countries.di.AppScope
import kodizfun.countries.layer_domain.abstraction.CountryRepository
import kodizfun.countries.layer_domain.entity.Country
import javax.inject.Inject

@AppScope
class AddCountryToFavoritesUseCase @Inject constructor(private val countryRepository: CountryRepository) {

    suspend operator fun invoke(country: Country) = countryRepository.addCountryToFavorites(country)

}