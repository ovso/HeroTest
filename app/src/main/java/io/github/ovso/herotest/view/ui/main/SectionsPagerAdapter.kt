package io.github.ovso.herotest.view.ui.main

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import io.github.ovso.herotest.R
import io.github.ovso.herotest.view.ui.screena.AFragment
import io.github.ovso.herotest.view.ui.screenb.BFragment

private val TAB_TITLES = arrayOf(
  R.string.tab_text_1,
  R.string.tab_text_2
)

class SectionsPagerAdapter(private val context: Context, fm: FragmentManager) :
  FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

  override fun getItem(position: Int): Fragment {
    return when (position == 0) {
      true -> AFragment.newInstance()
      false -> BFragment.newInstance()
    }
  }

  override fun getPageTitle(position: Int): CharSequence? {
    return context.resources.getString(TAB_TITLES[position])
  }

  override fun getCount(): Int {
    return TAB_TITLES.count()
  }
}
