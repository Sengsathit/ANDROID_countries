package kodizfun.countries.di

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.Module
import dagger.Provides
import kodizfun.countries.layer_presentation.di.MainComponent

// This module tells a Component which are its SubComponents
@Module(
    subcomponents = [MainComponent::class]
)
class SubComponentsModule
