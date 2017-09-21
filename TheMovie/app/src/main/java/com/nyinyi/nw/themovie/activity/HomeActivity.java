package com.nyinyi.nw.themovie.activity;

import android.app.SearchManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.Toast;

import com.nyinyi.nw.themovie.R;
import com.nyinyi.nw.themovie.adapter.ViewPagerAdapter;
import com.nyinyi.nw.themovie.fragment.NowplayingFragment;
import com.nyinyi.nw.themovie.fragment.PopularFragment;
import com.nyinyi.nw.themovie.fragment.UpcomingFragment;
import com.nyinyi.nw.themovie.network.RetrofitDataAgentImpl;
import com.nyinyi.nw.themovie.util.NetworkUtils;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        setupApplication();

    }

    private void setupApplication() {
        TabLayout tabs = (TabLayout) findViewById(R.id.tabs);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);

        if (NetworkUtils.isOnline(getApplicationContext()))
        {
            setupViewPager(viewPager);
            tabs.setupWithViewPager(viewPager);
            viewPager.setOffscreenPageLimit(4);

        }else {

            Toast.makeText(getApplicationContext(),"No Internet connection !!!,Please open internet access.",Toast.LENGTH_LONG).show();
            startActivityForResult(new Intent(android.provider.Settings.ACTION_SETTINGS), 0);
        }
    }



    // Add Fragments to Tabs
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new UpcomingFragment(), "Upcoming");
        adapter.addFragment(new NowplayingFragment(), "Now Playing");
        adapter.addFragment(new PopularFragment(),"Popular");
//        adapter.addFragment(new );
        viewPager.setAdapter(adapter);
    }

    public SearchView searchView;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


        getMenuInflater().inflate(R.menu.menu_movie_detail, menu);
        // Retrieve the SearchView and plug it into SearchManager
        searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));
        searchmovie();

        return true;
    }

    public void searchmovie()
    {
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                String search_str = query;
                //doFilterAsync(mSearchString);
//                Toast.makeText(getApplicationContext(),search_str, Toast.LENGTH_LONG).show();

                Intent intent = new Intent(getApplicationContext(),SearchresultActivity.class);
                intent.putExtra("search_name",search_str);
                startActivity(intent);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_search) {

            searchmovie();

        }else if(id==R.id.about){
            Intent intent = new Intent(this, AboutActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
