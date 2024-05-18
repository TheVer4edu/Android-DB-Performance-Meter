package com.thever4.dbcomparison.data.model

import androidx.annotation.PluralsRes
import androidx.annotation.StringRes

data class Experiment(
    val action: suspend () -> Long,
    @[StringRes PluralsRes]
    val description: Int,
    val params: List<Any> = emptyList()
)