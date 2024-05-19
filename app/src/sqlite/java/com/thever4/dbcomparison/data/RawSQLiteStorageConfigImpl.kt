package com.thever4.dbcomparison.data

import android.content.Context
import java.nio.file.Paths
import javax.inject.Inject
import kotlin.io.path.absolutePathString

class RawSQLiteStorageConfigImpl @Inject constructor(
    private val context: Context,
): StorageConfig {

    override val isFileStorage: Boolean
        get() = true
    override val fileStorageLocation: String?
        get() = Paths.get(context.filesDir.absolutePath.replace("files", "databases"), "/sample.sqlite.db").absolutePathString()

}