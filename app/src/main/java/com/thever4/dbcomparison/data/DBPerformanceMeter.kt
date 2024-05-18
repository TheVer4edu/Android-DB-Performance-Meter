package com.thever4.dbcomparison.data

import com.thever4.dbcomparison.R
import com.thever4.dbcomparison.data.model.Experiment
import com.thever4.dbcomparison.data.model.SampleItem
import java.util.Date
import javax.inject.Inject
import kotlin.random.Random
import kotlin.system.measureTimeMillis

class DBPerformanceMeter @Inject constructor(
    private val repository: DatabaseRepository,
) {

    var rowsCount = DEFAULT_DIMENSION

    private suspend fun generateData2K() {
        val sampleData = createSampleData(rowsCount)
        repository.addItems(sampleData)
    }

    private fun createSampleData(rows: Int) = mutableListOf<SampleItem>().apply {
        (1..rows).forEach { id ->
            add(
                SampleItem(
                    id = id,
                    name = FakeDataGenerator.getFakeName(),
                    date = Date(Random.nextLong(199329780000, 230865780000)),
                    active = Random.nextBoolean(),
                    doze = Random.nextDouble(1.0),
                )
            )
        }
    }

    private suspend fun clearTable() {
        repository.clearTable()
    }

    private suspend fun runExperiment(action: suspend (item: SampleItem) -> Unit): Long {
        clearTable()
        generateData2K()
        val randomItem = repository.getItemById(Random.nextInt(1, rowsCount + 1))
        return measureTimeMillis {
            action(randomItem)
        }
    }

    private suspend fun openConnection() = measureTimeMillis {
        repository.emptyRequest()
    }

    private suspend fun measureSelectAll() = runExperiment {
        repository.getAllItems()
    }

    private suspend fun measureConditionalSelectByInt() = runExperiment {
        repository.selectWhere(SampleItem.Field.ID, it.id)
    }

    private suspend fun measureConditionalSelectByString() = runExperiment {
        repository.selectWhere(SampleItem.Field.NAME, it.name)
    }

    private suspend fun measureConditionalSelectByBoolean() = runExperiment {
        repository.selectWhere(SampleItem.Field.ACTIVE, it.active)
    }

    private suspend fun measureConditionalSelectByDate() = runExperiment {
        repository.selectWhere(SampleItem.Field.DATE, it.date)
    }

    private suspend fun measureConditionalSelectByFloat() = runExperiment {
        repository.selectWhere(SampleItem.Field.DOZE, it.doze)
    }

    private suspend fun measureSortingSelectByInt() = runExperiment {
        repository.selectOrderingBy(SampleItem.Field.ID, false)
    }

    private suspend fun measureSortingSelectByString() = runExperiment {
        repository.selectOrderingBy(SampleItem.Field.NAME, false)
    }

    private suspend fun measureSortingSelectByBoolean() = runExperiment {
        repository.selectOrderingBy(SampleItem.Field.ACTIVE, false)
    }

    private suspend fun measureSortingSelectByDate() = runExperiment {
        repository.selectOrderingBy(SampleItem.Field.DATE, false)
    }

    private suspend fun measureSortingSelectByFloat() = runExperiment {
        repository.selectOrderingBy(SampleItem.Field.DOZE, false)
    }

    private suspend fun measureSortingSelectByIntDesc() = runExperiment {
        repository.selectOrderingBy(SampleItem.Field.ID, true)
    }

    private suspend fun measureSortingSelectByStringDesc() = runExperiment {
        repository.selectOrderingBy(SampleItem.Field.NAME, true)
    }

    private suspend fun measureSortingSelectByBooleanDesc() = runExperiment {
        repository.selectOrderingBy(SampleItem.Field.ACTIVE, true)
    }

    private suspend fun measureSortingSelectByDateDesc() = runExperiment {
        repository.selectOrderingBy(SampleItem.Field.DATE, true)
    }

    private suspend fun measureSortingSelectByFloatDesc() = runExperiment {
        repository.selectOrderingBy(SampleItem.Field.DOZE, true)
    }

    private suspend fun measureClear() = runExperiment {
        clearTable()
    }

    private suspend fun measureInsertRandomData(): Long {
        clearTable()
        val sampleData = createSampleData(rowsCount)
        return measureTimeMillis {
            repository.addItems(sampleData)
        }
    }

    val experiments
        get() = listOf(
            Experiment(::openConnection, R.string.measure_open_connection),
            Experiment(::measureInsertRandomData, R.plurals.measure_insert_all, listOf(rowsCount)),
            Experiment(::measureClear, R.string.measure_clearing),
            Experiment(::measureSelectAll, R.plurals.measure_select_all, listOf(rowsCount)),
            Experiment(::measureConditionalSelectByInt, R.string.measure_select_by_int),
            Experiment(::measureConditionalSelectByString, R.string.measure_select_by_string),
            Experiment(::measureConditionalSelectByBoolean, R.string.measure_select_by_boolean),
            Experiment(::measureConditionalSelectByDate, R.string.measure_select_by_date),
            Experiment(::measureConditionalSelectByFloat, R.string.measure_select_by_float),
            Experiment(::measureSortingSelectByInt, R.string.measure_sorting_by_int),
            Experiment(::measureSortingSelectByString, R.string.measure_sorting_by_string),
            Experiment(::measureSortingSelectByBoolean, R.string.measure_sorting_by_boolean),
            Experiment(::measureSortingSelectByDate, R.string.measure_sorting_by_date),
            Experiment(::measureSortingSelectByFloat, R.string.measure_sorting_by_float),
            Experiment(::measureSortingSelectByIntDesc, R.string.measure_sorting_by_int_desc),
            Experiment(::measureSortingSelectByStringDesc, R.string.measure_sorting_by_string_desc),
            Experiment(
                ::measureSortingSelectByBooleanDesc,
                R.string.measure_sorting_by_boolean_desc
            ),
            Experiment(::measureSortingSelectByDateDesc, R.string.measure_sorting_by_date_desc),
            Experiment(::measureSortingSelectByFloatDesc, R.string.measure_sorting_by_float_desc),
        )

    companion object {
        const val DEFAULT_DIMENSION = 2000
    }
}