package kodizfun.countries.layer_domain.usecase

import kodizfun.countries.di.AppScope
import kodizfun.countries.layer_domain.abstraction.CountryRepository
import javax.inject.Inject

@AppScope
class GetCountriesUseCase @Inject constructor(private val countryRepository: CountryRepository) {

    suspend operator fun invoke() = countryRepository.getAllCountries()

}