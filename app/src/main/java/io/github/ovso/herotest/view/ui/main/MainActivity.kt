package io.github.ovso.herotest.view.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.viewpager.widget.ViewPager
import io.github.ovso.herotest.R
import io.github.ovso.herotest.databinding.ActivityMainBinding
import io.github.ovso.herotest.exts.getViewModelFactory
import io.github.ovso.herotest.view.base.DataBindingActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : DataBindingActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val viewModel: MainViewModel by viewModels { getViewModelFactory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding.viewModel = viewModel
        setupViewPagerAndTabs()
    }

    private fun setupViewPagerAndTabs() {
        view_pager.adapter = SectionsPagerAdapter(this, supportFragmentManager)
        view_pager.addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {
            override fun onPageSelected(position: Int) {
                Screen.selected(position)
            }
        })
        tabs.setupWithViewPager(view_pager)
    }
}