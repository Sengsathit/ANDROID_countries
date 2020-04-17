package kodizfun.countries.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import kodizfun.countries.layer_data.di.DataModule
import kodizfun.countries.layer_data.di.NetworkModule
import kodizfun.countries.layer_presentation.di.MainComponent

@AppScope
@Component(
    modules = [
        SubComponentsModule::class,
        DataModule::class,
        NetworkModule::class
    ]
)
interface AppComponent {

    // Factory to create instances of the AppComponent
    @Component.Factory
    interface Factory {
        // With @BindsInstance, the Context passed in will be available in the graph
        fun create(@BindsInstance context: Context): AppComponent
    }

    // Types that can be retrieved from the graph
    fun mainComponentFactory(): MainComponent.Factory

}