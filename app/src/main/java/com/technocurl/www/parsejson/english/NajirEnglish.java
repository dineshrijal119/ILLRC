package com.technocurl.www.parsejson.english;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.technocurl.www.parsejson.MainPage;
import com.technocurl.www.parsejson.R;

/**
 * Created by dinesh on 8/16/16.
 */
public class NajirEnglish extends AppCompatActivity {
    Toolbar toolbar;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_nepali);
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(NajirEnglish.this, MainPage.class));
                finish();
            }
        });
        setTitle("नजीर(English)");
        TabLayout tabLayout =(TabLayout)findViewById(R.id.tabs);
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
        final ViewPager viewPager = (ViewPager)findViewById(R.id.viewpager);
        viewPager.setAdapter(new PagerAdapter(getSupportFragmentManager(),tabLayout.getTabCount()));
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);
        tabLayout.setSelectedTabIndicatorHeight(8);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }

   /* @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final   View view =  inflater.inflate(R.layout.activity_fragment_nepali,container,false);
        TabLayout tabLayout =(TabLayout)view.findViewById(R.id.tabs);
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
        final ViewPager viewPager = (ViewPager)view.findViewById(R.id.viewpager);
        viewPager.setAdapter(new PagerAdapter(getSupportFragmentManager(),tabLayout.getTabCount()));
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);
        tabLayout.setSelectedTabIndicatorHeight(8);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        return view;


    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }*/


    public class  PagerAdapter extends FragmentStatePagerAdapter {

        int nNumberTabs;
        private String tabTitles[] = new String[]{"General Search", "Specific Search"};

        public PagerAdapter(FragmentManager fm, int nNumberTabs) {
            super(fm);
            this.nNumberTabs=nNumberTabs;
        }
        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitles[position];
        }
        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    return new NajirEnglishGeneral();

                case 1:
                    return  new NajirEnglishGeneral();

                default:
                    return null;
            }

        }

        @Override
        public int getCount() {
            return nNumberTabs;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
