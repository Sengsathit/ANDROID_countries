package kodizfun.countries.layer_presentation.view.listener

import kodizfun.countries.layer_domain.entity.Country

interface CountrySelectionListener {
    // Save selected country in the ViewModel
    fun onCountrySelected(country: Country)

    // Navigate to country details
    fun navigateToCountryDetails()
}