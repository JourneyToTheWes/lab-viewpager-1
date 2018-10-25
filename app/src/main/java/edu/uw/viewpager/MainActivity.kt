package edu.uw.viewpager

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.app.FragmentTransaction
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.EditText

class MainActivity : AppCompatActivity(), MovieListFragment.OnMovieSelectedListener, SearchFragment.OnSearchListener {
    private var searchFragment: SearchFragment? = null
    private var listFragment: MovieListFragment? = null
    private var detailFragment: DetailFragment? = null
    private var viewPager: ViewPager? = null
    private var pagerAdapter: PagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        searchFragment = SearchFragment.newInstance()

        viewPager = findViewById<ViewPager>(R.id.pager)
        pagerAdapter = MoviePagerAdapter(supportFragmentManager)
        viewPager!!.adapter = pagerAdapter
    }

    override fun onSearchSubmitted(searchTerm: String) {
        listFragment = MovieListFragment.newInstance(searchTerm)
        pagerAdapter!!.notifyDataSetChanged()
        viewPager!!.currentItem = 1
    }

    override fun onMovieSelected(movie: Movie) {
        detailFragment = DetailFragment.newInstance(movie)
        pagerAdapter!!.notifyDataSetChanged()
        viewPager!!.currentItem = 2
    }

    companion object {

        private val TAG = "MainActivity"
        val MOVIE_LIST_FRAGMENT_TAG = "MoviesListFragment"
        val MOVIE_DETAIL_FRAGMENT_TAG = "DetailFragment"
    }

    private inner class MoviePagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
        override fun getItem(position: Int): Fragment? {
            return if (position == 0) {
                searchFragment
            } else if (position == 1) {
                listFragment
            } else if (position == 2) {
                detailFragment
            } else {
                null
            }
        }

        override fun getCount(): Int {
            return if (listFragment == null) {
                1
            } else if (detailFragment == null) {
                2
            } else {
                3
            }
        }

        override fun getItemPosition(`object`: Any?): Int {
            return PagerAdapter.POSITION_NONE
        }
    }
}
