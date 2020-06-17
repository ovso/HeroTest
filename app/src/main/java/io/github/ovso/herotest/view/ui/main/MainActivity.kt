package io.github.ovso.herotest.view.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.github.ovso.herotest.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViewPagerAndTabs()
    }

    private fun setupViewPagerAndTabs() {
        view_pager.adapter = SectionsPagerAdapter(this, supportFragmentManager)
        tabs.setupWithViewPager(view_pager)
    }
}