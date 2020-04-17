package kodizfun.countries.layer_presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kodizfun.countries.layer_domain.usecase.GetCountriesUseCase
import kodizfun.countries.layer_presentation.di.ActivityScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@ActivityScope
class MainViewModel @Inject constructor(private var getCountriesUseCaseUseCase: GetCountriesUseCase) :
    ViewModel() {

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        println(throwable.message)
    }

    fun getCountries() {
        viewModelScope.launch(coroutineExceptionHandler) {
            val countries = getCountriesUseCaseUseCase.invoke()
        }
    }

    companion object {
        private val TAG = MainViewModel::class.java.name
    }
}