package com.example.irvin.rubicon;

import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;
import org.greenrobot.eventbus.EventBus;

public class Main2Activity extends AppCompatActivity implements tvShowsFragment.OnFragmentInteractionListener, moviesFragment.OnFragmentInteractionListener, DetailsFragment.OnFragmentInteractionListener{

    private static final String TAG = "Main2Activity";

    Fragment fragmentMovies;
    Fragment fragmentTvShows;
    Toolbar mToolbar;

    private SectionsPageAdapter mSectionsPageAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mToolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());
        fragmentMovies = new moviesFragment();
        fragmentTvShows = new tvShowsFragment();

        mViewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(mViewPager, fragmentMovies, fragmentTvShows);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
    }

    private void setupViewPager(ViewPager viewPager, Fragment fragmentMovies, Fragment fragmentTvShows){
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(fragmentTvShows, "TV Shows");
        adapter.addFragment(fragmentMovies, "Movies");
        viewPager.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        MenuItem item = menu.findItem(R.id.search);
        SearchView searchView = (SearchView)item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                EventBus.getDefault().post(new EventUpdateFragment(newText));
                return false;
            }
        });
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onBackPressed() {

        int currentPage = mViewPager.getCurrentItem();

        // If the fragment exists and has some back-stack entry
        if(currentPage==0){
            if (fragmentTvShows != null && fragmentTvShows.getChildFragmentManager().getBackStackEntryCount() > 0){
                // Get the fragment fragment manager - and pop the backstack
                fragmentTvShows.getChildFragmentManager().popBackStack();
            }
            // Else, nothing in the direct fragment back stack
            else{
                // Let super handle the back press
                super.onBackPressed();
            }
        }
        if(currentPage==1){
            if (fragmentMovies != null && fragmentMovies.getChildFragmentManager().getBackStackEntryCount() > 0){
                // Get the fragment fragment manager - and pop the backstack
                fragmentMovies.getChildFragmentManager().popBackStack();
            }
            // Else, nothing in the direct fragment back stack
            else{
                // Let super handle the back press
                super.onBackPressed();
            }
        }

    }
}
