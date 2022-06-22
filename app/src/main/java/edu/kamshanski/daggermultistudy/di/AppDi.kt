package edu.kamshanski.daggermultistudy.di

import android.content.Context
import com.github.terrakok.cicerone.Cicerone
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import edu.kamshanski.daggermultistudy.App
import edu.kamshanski.shared.di.DataModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        DataModule::class,
    ],
)

interface AppComponent {

    fun inject(app: App)

    fun activityComponent(): ActivityComponent.Factory

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance context: Context,
        ): AppComponent
    }
}

@Module(subcomponents = [
    ActivityComponent::class
])
abstract class AppModule {

    companion object {

        @Provides
        @Singleton
        fun provideCicerone() = Cicerone.create()
    }
}