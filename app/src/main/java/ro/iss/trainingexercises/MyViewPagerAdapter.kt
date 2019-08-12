package ro.iss.trainingexercises

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class MyViewPagerAdapter(fragmentManager : FragmentManager, private val numberOfFragments : Int) : FragmentStatePagerAdapter(fragmentManager) {
    override fun getItem(position: Int): Fragment {
        return FirstFragment.newInstance()
    }

    override fun getCount(): Int {
        return numberOfFragments
    }


}