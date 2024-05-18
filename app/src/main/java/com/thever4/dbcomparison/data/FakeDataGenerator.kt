package com.thever4.dbcomparison.data

import io.github.serpro69.kfaker.Faker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object FakeDataGenerator {
    private var faker: Faker? = null

    init {
        CoroutineScope(Dispatchers.Default).launch {
            faker = Faker()
        }
    }

    fun getFakeName(): String = faker?.name?.nameWithMiddle() ?: ""

}