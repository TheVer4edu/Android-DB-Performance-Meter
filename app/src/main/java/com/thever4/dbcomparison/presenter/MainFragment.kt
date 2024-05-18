package com.thever4.dbcomparison.presenter

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.thever4.dbcomparison.R
import com.thever4.dbcomparison.RootActivity
import com.thever4.dbcomparison.data.DBPerformanceMeter
import com.thever4.dbcomparison.databinding.FragmentMainBinding
import com.thever4.dbcomparison.di.ViewModelFactory
import com.thever4.dbcomparison.di.appComponent
import javax.inject.Inject

class MainFragment : Fragment(R.layout.fragment_main) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: MainViewModel by viewModels { viewModelFactory }

    private val binding: FragmentMainBinding by viewBinding()

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }

    private val adapter = ExperimentAdapter()

    private val experimentObserver = Observer<ExperimentState> {
        when (it) {
            is ExperimentState.Running -> {
                adapter.submitList(it.list.toList())
            }

            is ExperimentState.Success -> {
                enableControls(true)
                showLoad(false)
                adapter.submitList(it.result)
                adapter.notifyDataSetChanged()
            }

            is ExperimentState.Preparing -> {
                adapter.submitList(emptyList())
                enableControls(false)
                showLoad(true)
            }

            else -> {
                showLoad(false)
            }
        }
    }

    private fun showLoad(isLoading: Boolean) {
        if (isLoading)
            binding.experimentProgress.show()
        else
            binding.experimentProgress.hide()
    }

    private fun enableControls(enabled: Boolean) {
        binding.experimentRunButton.isEnabled = enabled
        binding.experimentDimension.isEnabled = enabled
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.textElapsed.text = getString(
            R.string.measure_app_launch_time,
            (requireActivity() as? RootActivity)?.elapsedOnStart
        )

        initObservers()
        initRecycler()
        initDropdown()
        initClickListeners()

        super.onViewCreated(view, savedInstanceState)
    }

    private fun initRecycler() = with(binding.experimentRecycler) {
        this@with.adapter = this@MainFragment.adapter
        layoutManager = LinearLayoutManager(requireContext())
        addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )
    }

    private fun initDropdown() = with(binding.experimentDimensionTextView) {
        val array = resources.getStringArray(R.array.possible_rows_count)
        setAdapter(
            ArrayAdapter(
                requireContext(),
                android.R.layout.simple_spinner_item,
                array
            )
        )
        setText(DBPerformanceMeter.DEFAULT_DIMENSION.toString(), false)
        setOnItemClickListener { parent, _, position, _ ->
            val selected = parent.getItemAtPosition(position) as String
            viewModel.setRowsCount(selected.toInt())
        }
    }

    private fun initClickListeners() {
        binding.experimentRunButton.setOnClickListener {
            viewModel.measureAll()
        }
    }

    private fun initObservers() {
        viewModel.results.observe(viewLifecycleOwner, experimentObserver)
    }

}