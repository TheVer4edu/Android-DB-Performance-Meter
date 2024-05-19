package com.thever4.dbcomparison.data

import javax.inject.Inject

class NoDBStorageConfigImpl @Inject constructor(

): StorageConfig {
    override val isFileStorage: Boolean
        get() = false
    override val fileStorageLocation: String?
        get() = null
}