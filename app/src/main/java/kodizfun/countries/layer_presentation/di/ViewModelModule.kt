package kodizfun.countries.layer_presentation.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import kodizfun.countries.layer_presentation.viewmodel.CountriesViewModel

/**
 * Module that binds :
 * - ViewModel factory
 * - ViewModels used in the application
 */
@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(CountriesViewModel::class)
    internal abstract fun bindCountriesViewModel(countriesViewModel: CountriesViewModel): ViewModel
}