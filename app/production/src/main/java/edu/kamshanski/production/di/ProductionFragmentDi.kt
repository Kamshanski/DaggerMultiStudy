package edu.kamshanski.production.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.Subcomponent
import dagger.multibindings.IntoMap
import edu.kamshanski.production.presentation.ProductionViewModel
import edu.kamshanski.production.ui.ProductionFragment
import edu.kamshanski.shared.di.FragmentScope
import edu.kamshanski.shared.di.ViewModelKey

@FragmentScope
@Subcomponent(modules = [ProductionFragmentModule::class])
interface ProductionFragmentComponent {

    fun inject(fragment: ProductionFragment)

    @Subcomponent.Factory
    interface Factory {

        fun create(): ProductionFragmentComponent
    }
}

@Module
interface ProductionFragmentModule {

    @Binds
    @IntoMap
    @ViewModelKey(ProductionViewModel::class)
    fun provideProductionViewModel(vm: ProductionViewModel): ViewModel
}