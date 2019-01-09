package com.nyinyi.nw.themovie.activity

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuItemCompat
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.nyinyi.nw.themovie.R
import com.nyinyi.nw.themovie.adapter.ViewPagerAdapter
import com.nyinyi.nw.themovie.fragment.NowplayingFragment
import com.nyinyi.nw.themovie.fragment.PopularFragment
import com.nyinyi.nw.themovie.fragment.UpcomingFragment
import com.nyinyi.nw.themovie.util.NetworkUtils

class HomeActivity : AppCompatActivity() {

    lateinit var searchView: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        setupApplication()

    }

    private fun setupApplication() {
        val tabs = findViewById<TabLayout>(R.id.tabs)
        val viewPager = findViewById<ViewPager>(R.id.viewpager)

        if (NetworkUtils.isOnline(applicationContext)) {
            setupViewPager(viewPager)
            tabs.setupWithViewPager(viewPager)
            viewPager.offscreenPageLimit = 4

        } else {

            Toast.makeText(applicationContext, "No Internet connection !!!,Please open internet access.", Toast.LENGTH_LONG).show()
            startActivityForResult(Intent(android.provider.Settings.ACTION_SETTINGS), 0)
        }
    }

    // Add Fragments to Tabs
    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(UpcomingFragment(), "Upcoming")
        adapter.addFragment(NowplayingFragment(), "Now Playing")
        adapter.addFragment(PopularFragment(), "Popular")
        viewPager.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {


        menuInflater.inflate(R.menu.menu_movie_detail, menu)
        // Retrieve the SearchView and plug it into SearchManager
        searchView = MenuItemCompat.getActionView(menu.findItem(R.id.action_search)) as SearchView
        searchmovie()

        return true
    }

    fun searchmovie() {
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                val intent = Intent(applicationContext, SearchresultActivity::class.java)
                intent.putExtra("search_name", query)
                startActivity(intent)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val id = item.itemId

        if (id == R.id.action_search) {

            searchmovie()

        } else if (id == R.id.about) {
            val intent = Intent(this, AboutActivity::class.java)
            startActivity(intent)
        }

        return super.onOptionsItemSelected(item)
    }
}
