package spinno.com.munchoba;

import android.app.Activity;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;


import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.astuetz.PagerSlidingTabStrip;
import com.viewpagerindicator.CirclePageIndicator;
import com.viewpagerindicator.TitlePageIndicator;


public class Registration extends FragmentActivity {
    ViewPager pager;
    CirclePageIndicator titleIndicator;
    public  static FragmentManager fragmentManager;
  //  PagerSlidingTabStrip tabs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        fragmentManager=getSupportFragmentManager();
        pager = (ViewPager) findViewById(R.id.pager);

        pager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        titleIndicator= (CirclePageIndicator)findViewById(R.id.titles);



       /* tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        tabs.setTextColor(Color.BLACK);
        tabs.setTabPaddingLeftRight(15);
        tabs.setTextSize(20);
        tabs.setShouldExpand(true);
        tabs.setUnderlineColor(0x7f1101);
        tabs.setDividerColor(Color.BLACK);
     tabs.setFitsSystemWindows(true);
        //tabs.setIndicatorHeight(2);
       // tabs.setIndicatorColor(Color.BLACK);
        tabs.setViewPager(pager);*/
        titleIndicator.setViewPager(pager);

        titleIndicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }






    class MyPagerAdapter extends FragmentStatePagerAdapter {

        private final String[] TITLES = {"Account Details","My Fitness Details","About Me" };

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return TITLES[position];
        }

        @Override
        public int getCount() {
            return TITLES.length;
        }
        @Override
        public Fragment getItem(int position) {
            switch (position) {

                case 0:

                    return One_Fragment.newInstance("FirstFragment, Instance 1");
                case 1:

                    return Two_FRAGMENT.newInstance("SecondFragment, Instance 1");
                case 2:

                    return Three_FRAGMENT.newInstance("ThirdFragment, Instance 1");


                default:
                    return null;

            }

        }


    }


}


/*
 <com.astuetz.PagerSlidingTabStrip
            android:id="@+id/tabs"
            android:layout_width="match_parent"
            android:layout_height="50dip"
            android:paddingLeft="1dp"
            android:paddingRight="1dp"
            android:paddingBottom="2dp"
            />
 */