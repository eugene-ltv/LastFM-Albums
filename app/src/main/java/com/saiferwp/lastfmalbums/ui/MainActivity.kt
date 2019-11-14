package com.saiferwp.lastfmalbums.ui

import android.os.Bundle
import com.saiferwp.lastfmalbums.R
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity() {

    private lateinit var containerFragment: ContainerFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        containerFragment = ContainerFragment.newInstance()
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, containerFragment)
                .commitNow()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return containerFragment.onSupportNavigateUp()
    }

    override fun onBackPressed() {
        if (containerFragment.onSupportNavigateUp()) return
        super.onBackPressed()
    }
}
