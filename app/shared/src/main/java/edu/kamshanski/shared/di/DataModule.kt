package edu.kamshanski.shared.di

import dagger.Module
import dagger.Provides
import edu.kamshanski.shared.model.toy.ToyFabric
import edu.kamshanski.shared.model.wallet.Wallet
import edu.kamshanski.shared.model.workers.LabourMarket
import javax.inject.Singleton

@Module
interface DataModule {

    companion object {

        @Provides
        @Singleton
        fun provideToyFabric(): ToyFabric = ToyFabric()

        @Provides
        @Singleton
        fun provideWallet(): Wallet = Wallet()

        @Provides
        @Singleton
        fun provideLabourMarket(): LabourMarket = LabourMarket()
    }

}