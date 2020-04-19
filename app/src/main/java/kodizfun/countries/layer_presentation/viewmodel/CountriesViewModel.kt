package kodizfun.countries.layer_presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kodizfun.countries.layer_domain.entity.Country
import kodizfun.countries.layer_domain.usecase.*
import kodizfun.countries.layer_presentation.di.ActivityScope
import kotlinx.coroutines.*
import javax.inject.Inject

@ActivityScope
class CountriesViewModel @Inject constructor(
    private val getCountriesUseCase: GetCountriesUseCase,
    private val getCountryFromFavoritesUseCase: GetCountryFromFavoritesUseCase,
    private val addCountryToFavoritesUseCase: AddCountryToFavoritesUseCase,
    private val removeCountryFromFavoritesUseCase: RemoveCountryFromFavoritesUseCase,
    private val isCountryFavoriteUseCase: IsCountryFavoriteUseCase
) : ViewModel() {

    //region PROPERTIES REGION

    // Encapsulated LiveData for countries
    private val _countries = MutableLiveData<ArrayList<Country>>()
    val countries: LiveData<ArrayList<Country>>
        get() = _countries

    // Encapsulated LiveData for progress loader
    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _loading

    // Encapsulated LiveData for error
    private val _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error

    // Encapsulated LiveData for selected country
    private val _selectedCountry = MutableLiveData<Country>()
    val selectedCountry: LiveData<Country>
        get() = _selectedCountry

    // Exception handler for coroutines
    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        _loading.value = false
        _error.value = throwable.message
    }

    // Coroutines scope and job
    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    private lateinit var countriesJob: Job

    //endregion

    fun getCountries() {
        countriesJob = coroutineScope.launch(coroutineExceptionHandler) {
            withContext(Dispatchers.Main) {
                _loading.value = true
                _countries.value = getCountriesUseCase()
                _loading.value = false
            }
        }
    }

    /**
     * Sets or removes the country from favorites
     *
     * - if country is in favorites, removes it from DB
     * - if not adds it to DB
     */
    fun toggleCountryFavorite() {
        _selectedCountry.value?.let { country ->

            country.code?.let { countryCode ->
                countriesJob = coroutineScope.launch(coroutineExceptionHandler) {
                    // Check if country is in favorites
                    val isFavorite = isCountryFavoriteUseCase(countryCode)
                    if(isFavorite) {
                        // Remove country from favorites
                        removeCountryFromFavoritesUseCase(countryCode)
                        country.isFavorite = false
                    } else {
                        // Add country to favorites
                        addCountryToFavoritesUseCase(country)
                        country.isFavorite = true
                    }
                    _selectedCountry.postValue(country)
                }
            }
        }
    }

    /**
     * Sets the selected country
     *
     * - if country is in favorites, use the object get from DB
     * - if not use the object from de countries list
     */
    fun setSelectedCountry(country: Country) {
        country?.code?.let {countryCode ->
            countriesJob = coroutineScope.launch(coroutineExceptionHandler) {
                val selectedCountryFromUseCase = getCountryFromFavoritesUseCase(countryCode)
                withContext(Dispatchers.Main) {
                    _selectedCountry.value = selectedCountryFromUseCase?.let {
                        it
                    }  ?: country
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        countriesJob.cancel()
    }

    companion object {
        private val TAG = CountriesViewModel::class.java.name
    }
}