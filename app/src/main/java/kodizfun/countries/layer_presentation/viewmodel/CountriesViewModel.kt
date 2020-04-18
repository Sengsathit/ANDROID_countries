package kodizfun.countries.layer_presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kodizfun.countries.layer_domain.entity.Country
import kodizfun.countries.layer_domain.usecase.GetCountriesUseCase
import kodizfun.countries.layer_presentation.di.ActivityScope
import kotlinx.coroutines.*
import javax.inject.Inject

@ActivityScope
class CountriesViewModel @Inject constructor(private var getCountriesUseCaseUseCase: GetCountriesUseCase) :
    ViewModel() {

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

    // Encapsulated selected country
    private lateinit var _selectedCountry: Country
    val selectedCountry: Country?
        get() = _selectedCountry

    // Exception handler for coroutines
    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        _loading.value = false
        _error.value = throwable.message
    }

    // Coroutines scope and job
    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    lateinit var countriesJob: Job

    fun getCountries() {
        countriesJob = coroutineScope.launch(coroutineExceptionHandler) {
            withContext(Dispatchers.Main) {
                _loading.value = true
                _countries.value = getCountriesUseCaseUseCase()
                _loading.value = false
            }
        }
    }

    fun setSelectedCountry(country: Country) {
        _selectedCountry = country
    }

    override fun onCleared() {
        super.onCleared()
        countriesJob.cancel()
    }

    companion object {
        private val TAG = CountriesViewModel::class.java.name
    }
}