package edu.kamshanski.production.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import edu.kamshanski.production.databinding.ProductionFragmentBinding
import edu.kamshanski.production.di.ProductionComponentProvider
import edu.kamshanski.production.di.ProductionFragmentComponent
import edu.kamshanski.production.presentation.ProductionViewModel
import edu.kamshanski.shared.ui.base.BaseFragment
import edu.kamshanski.shared.util.repeatOnStarted
import kotlinx.coroutines.flow.collect
import kotlin.properties.Delegates

class ProductionFragment : BaseFragment() {

    private val vm: ProductionViewModel by vm()
    lateinit var fragComponent: ProductionFragmentComponent
    private var viewBinding: ProductionFragmentBinding by Delegates.notNull()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        ProductionFragmentBinding.inflate(inflater, container, false)
            .also { viewBinding = it }
            .root

    override fun onAttach(context: Context) {
        super.onAttach(context)
        fragComponent = (requireActivity() as ProductionComponentProvider)
            .provideProductionComponentFactory().create()
        fragComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.hire.setOnClickListener { vm.hireNewWorker() }

        repeatOnStarted {
            vm.freeWorkersCount.collect { viewBinding.hire.text = "Нанять (Доступно $it)" }
        }
        repeatOnStarted {
            vm.occupiedWorkers.collect {
                viewBinding.apply {
                    occupiedWorkersNumber.text = it.toString()

                    workersList.removeAllViews()

                    for (worker in it) {
                        workersList.addView(
                            TextView(context).apply {
                                text = worker.name
                                setOnClickListener { vm.fireWorker(worker) }
                            }
                        )
                    }
                }
            }
        }
        repeatOnStarted {
            vm.producedToys.collect {
                viewBinding.toysAmount.text =
                    it.toList().joinToString(separator = "\n") { (type, count) -> "$type\t$count" }
            }
        }
        repeatOnStarted {
            vm.message.collect { viewBinding.msg.text = it }
        }
    }
}