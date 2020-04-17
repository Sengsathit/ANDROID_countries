package kodizfun.countries.di

import dagger.Module
import kodizfun.countries.layer_presentation.di.MainComponent

// This module tells a Component which are its SubComponents
@Module(
    subcomponents = [MainComponent::class]
)
class SubComponentsModule
