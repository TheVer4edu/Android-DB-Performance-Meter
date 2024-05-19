package com.thever4.dbcomparison.data.model

import androidx.annotation.PluralsRes
import androidx.annotation.StringRes
import com.thever4.dbcomparison.R

data class Experiment(
    val action: suspend () -> Long,
    @[StringRes PluralsRes]
    val description: Int,
    val measurementUnit: MeasurementUnit = MeasurementUnit.MS,
    val params: List<Any> = emptyList()
) {
    enum class MeasurementUnit(@StringRes val stringId: Int) {
        MS(R.string.elapsed_ms), BYTES(R.string.size_bytes),
    }
}