package com.thever4.dbcomparison.data

interface StorageConfig {
    val isFileStorage: Boolean
    val fileStorageLocation: String?
}