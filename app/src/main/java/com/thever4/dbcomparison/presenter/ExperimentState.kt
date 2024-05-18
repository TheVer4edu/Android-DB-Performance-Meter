package com.thever4.dbcomparison.presenter

import com.thever4.dbcomparison.data.model.ExperimentItem

sealed class ExperimentState {
    data object NotLaunched: ExperimentState()
    data object Preparing: ExperimentState()
    data class Running(val list: List<ExperimentItem>): ExperimentState()
    data class Success(val result: List<ExperimentItem>): ExperimentState()
}