package kodizfun.countries.layer_presentation.di

import dagger.Subcomponent
import kodizfun.countries.layer_presentation.view.MainActivity
import kodizfun.countries.layer_presentation.view.CountriesFragment
import kodizfun.countries.layer_presentation.view.CountryDetailsFragment

@ActivityScope
@Subcomponent(modules = [ViewModelModule::class])
interface MainComponent {

    // Factory to create instances of PresentationComponent
    @Subcomponent.Factory
    interface Factory {
        fun create(): MainComponent
    }

    // Classes that can be injected by this Component
    fun inject(countriesFragment: CountriesFragment)
    fun inject(countryDetailsFragment: CountryDetailsFragment)
    fun inject(mainActivity: MainActivity)
}