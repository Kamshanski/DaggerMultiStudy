package edu.kamshanski.market.di

interface MarketComponentProvider {

    fun provideMarketComponentFactory(): MarketFragmentComponent.Factory
}