package edu.kamshanski.daggermultistudy.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import edu.kamshanski.daggermultistudy.R
import edu.kamshanski.daggermultistudy.app
import edu.kamshanski.daggermultistudy.databinding.ActivityMainBinding
import edu.kamshanski.daggermultistudy.di.ActivityComponent
import edu.kamshanski.market.di.MarketComponentProvider
import edu.kamshanski.market.di.MarketFragmentComponent
import edu.kamshanski.production.di.ProductionComponentProvider
import edu.kamshanski.production.di.ProductionFragmentComponent
import javax.inject.Inject
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity(), MarketComponentProvider, ProductionComponentProvider {

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    var activityComponent: ActivityComponent by Delegates.notNull()

    private val navigator by lazy { AppNavigator(this, R.id.fragmentContainer) }

    private var binding: ActivityMainBinding by Delegates.notNull()

    override fun onCreate(savedInstanceState: Bundle?) {
        val appComp = app.appComponent
        activityComponent = appComp.activityComponent().create()
        activityComponent.inject(this)

        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.fabric.setOnClickListener { router.navigateTo(Screens.Production()) }
        binding.market.setOnClickListener { router.navigateTo(Screens.Market()) }

        router.navigateTo(Screens.Market())
    }

    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }

    override fun provideMarketComponentFactory(): MarketFragmentComponent.Factory =
        activityComponent.marketFragmentComponent()

    override fun provideProductionComponentFactory(): ProductionFragmentComponent.Factory =
        activityComponent.productionFragmentComponent()
}