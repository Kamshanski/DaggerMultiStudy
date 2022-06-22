package edu.kamshanski.market.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.Subcomponent
import dagger.multibindings.IntoMap
import edu.kamshanski.market.presentation.MarketViewModel
import edu.kamshanski.market.ui.MarketFragment
import edu.kamshanski.shared.di.FragmentScope
import edu.kamshanski.shared.di.ViewModelKey


@FragmentScope
@Subcomponent(modules = [MarketFragmentModule::class])
interface MarketFragmentComponent {

    fun inject(fragment: MarketFragment)

    @Subcomponent.Factory
    interface Factory {

        fun create(): MarketFragmentComponent
    }
}

@Module
interface MarketFragmentModule {

    @Binds
    @IntoMap
    @ViewModelKey(MarketViewModel::class)
    fun provideMarketViewModel(vm: MarketViewModel): ViewModel
}