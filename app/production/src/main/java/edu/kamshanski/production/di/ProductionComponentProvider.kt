package edu.kamshanski.production.di

interface ProductionComponentProvider {

    fun provideProductionComponentFactory(): ProductionFragmentComponent.Factory
}