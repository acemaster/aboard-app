package com.vivekapp.android.aboard;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.SherlockFragmentActivity;

import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.os.Bundle;

public class Tabview extends SherlockFragmentActivity {
	 private ActionBar actionBar;
	    private ViewPager viewPager;

	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.main_fragment);

	        viewPager = (ViewPager) findViewById(R.id.pager);
	        viewPager.setOnPageChangeListener(onPageChangeListener);
	        viewPager.setAdapter(new TabsviewAdapter(getSupportFragmentManager()));
	        addActionBarTabs();
	    }

	    private ViewPager.SimpleOnPageChangeListener onPageChangeListener = new ViewPager.SimpleOnPageChangeListener() {
	        @Override
	        public void onPageSelected(int position) {
	            super.onPageSelected(position);
	            actionBar.setSelectedNavigationItem(position);
	        }
	    };

	    private void addActionBarTabs() {
	        actionBar = getSupportActionBar();
	        String[] tabs = { "About", "Location","Get Ride" };
	        for (String tabTitle : tabs) {
	            ActionBar.Tab tab = actionBar.newTab().setText(tabTitle)
	                    .setTabListener(tabListener);
	            actionBar.addTab(tab);
	        }
	        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
	    }

	    private ActionBar.TabListener tabListener = new ActionBar.TabListener() {
	        @Override
	        public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
	            viewPager.setCurrentItem(tab.getPosition());
	        }

	        @Override
	        public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
	        }

	        @Override
	        public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {
	        }

			
	    };
	}
