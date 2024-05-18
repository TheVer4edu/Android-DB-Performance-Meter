package com.thever4.dbcomparison

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.thever4.dbcomparison.presenter.MainFragment

class RootActivity : AppCompatActivity(R.layout.activity_main) {

    var elapsedOnStart: Long = -1
        private set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportFragmentManager.beginTransaction()
            .replace(R.id.root_fragment_host, MainFragment())
            .commit()

        if (application is MainApplication)
            elapsedOnStart =
                System.currentTimeMillis() - (application as MainApplication).launchAtTime
    }

}