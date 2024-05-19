package com.thever4.dbcomparison.presenter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thever4.dbcomparison.data.DBPerformanceMeter
import com.thever4.dbcomparison.data.model.ExperimentItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val meter: DBPerformanceMeter,
) : ViewModel() {

    private val _results = MutableLiveData<ExperimentState>(ExperimentState.NotLaunched)
    val results: LiveData<ExperimentState>
        get() = _results

    fun measureAll() = viewModelScope.launch {
        val resultList = mutableListOf<ExperimentItem>()
        _results.postValue(ExperimentState.Preparing)
        meter.experiments.map {
            async(Dispatchers.IO) {
                ExperimentItem(
                    description = it.description,
                    arguments = it.params,
                    elapsed = it.action(),
                    unit = it.measurementUnit.stringId
                )
            }.await().also {
                resultList.add(it)
                _results.postValue(ExperimentState.Running(resultList))
            }
        }
        _results.postValue(ExperimentState.Success(resultList))
    }

    fun setRowsCount(count: Int) {
        meter.rowsCount = count
    }

}