package edu.kamshanski.daggermultistudy.ui

import com.github.terrakok.cicerone.androidx.FragmentScreen
import edu.kamshanski.market.ui.MarketFragment
import edu.kamshanski.production.ui.ProductionFragment

object Screens {
    fun Market() = FragmentScreen { MarketFragment() }
    fun Production() = FragmentScreen { ProductionFragment() }
}