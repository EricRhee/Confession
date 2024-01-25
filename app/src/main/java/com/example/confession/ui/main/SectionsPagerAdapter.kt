package com.example.confession.ui.main

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.confession.BookmarkFragment
import com.example.confession.MyPostFragment
import com.example.confession.PostsFragment
import com.example.confession.R

private val TAB_TITLES = arrayOf(
    R.string.tab_text_1,
    R.string.tab_text_2
    //R.string.tab_text_3
)


class SectionsPagerAdapter(private val context: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm) {

    // returns corresponding page depending on tab selected
    override fun getItem(position: Int): Fragment {
        var frag: Fragment
        frag = PostsFragment()
        when(position) {
            0 -> frag = PostsFragment()
            1 -> frag = MyPostFragment()
            // dead code below
           // else -> {
           //     frag = PlaceholderFragment.newInstance(position + 1)
           // }

            //2 -> frag = BookmarkFragment()

        }
        return frag


    }

    // gets the title of page for instance posts and my posts
    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }

    //returns amount of pages/tabs
    override fun getCount(): Int {
        // Show 2 total pages.
        return 2
    }
}