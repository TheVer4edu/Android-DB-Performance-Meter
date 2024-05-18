package com.thever4.dbcomparison.data.model

import androidx.annotation.PluralsRes
import androidx.annotation.StringRes

data class ExperimentItem(
    @[StringRes PluralsRes]
    val description: Int,
    val arguments: List<Any>,
    val elapsed: Long,
) // recyclerview item