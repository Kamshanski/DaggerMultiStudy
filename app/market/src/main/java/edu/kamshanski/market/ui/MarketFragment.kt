package edu.kamshanski.market.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import edu.kamshanski.market.databinding.MarketFragmentBinding
import edu.kamshanski.market.di.MarketComponentProvider
import edu.kamshanski.market.di.MarketFragmentComponent
import edu.kamshanski.market.presentation.MarketViewModel
import edu.kamshanski.shared.ui.base.BaseFragment
import edu.kamshanski.shared.util.repeatOnStarted
import kotlinx.coroutines.flow.collect
import kotlin.properties.Delegates

class MarketFragment : BaseFragment() {

    private val vm: MarketViewModel by vm()
    lateinit var fragComponent: MarketFragmentComponent
    private var viewBinding: MarketFragmentBinding by Delegates.notNull()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        MarketFragmentBinding.inflate(inflater, container, false)
            .also { viewBinding = it }
            .root

    override fun onAttach(context: Context) {
        super.onAttach(context)
        fragComponent = (requireActivity() as MarketComponentProvider)
            .provideMarketComponentFactory().create()
        fragComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.sellToys.setOnClickListener { vm.sellToys() }

        repeatOnStarted {
            vm.toysCount.collect { viewBinding.toysAmount.text = it.toString() }
        }
        repeatOnStarted {
            vm.money.collect { viewBinding.money.text = it.toString() }
        }
        repeatOnStarted {
            vm.message.collect { viewBinding.msg.text = it }
        }
    }
}