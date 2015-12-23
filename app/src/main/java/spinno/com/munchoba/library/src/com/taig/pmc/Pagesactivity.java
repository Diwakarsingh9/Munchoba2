package spinno.com.munchoba.library.src.com.taig.pmc;

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
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.astuetz.PagerSlidingTabStrip;

import spinno.com.munchoba.R;
import spinno.com.munchoba.fragmentsformainscreen.Allpages;
import spinno.com.munchoba.fragmentsformainscreen.Mypages;
import spinno.com.munchoba.fragmentsformainscreen.Pendinginvitations;
import spinno.com.munchoba.fragmentsformainscreen.Search;
import spinno.com.munchoba.parsingforapi.parsingpages;

public class Pagesactivity extends FragmentActivity {
    ViewPager pager;
    public  static FragmentManager fragmentManager;
    ImageView filter, filter2;
    TextView create;

    PagerSlidingTabStrip tabs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagesactivity);
        filter=(ImageView)findViewById(R.id.filter);
        filter2=(ImageView)findViewById(R.id.filter2);
        create=(TextView)findViewById(R.id.add);
        fragmentManager=getSupportFragmentManager();
        parsingpages.parsingallpagesdata(Pagesactivity.this);
        parsingpages.parsingmypagesdata(Pagesactivity.this);

        pager = (ViewPager) findViewById(R.id.pager);

        pager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));

        // Bind the tabs to the ViewPager
        tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        tabs.setIndicatorHeight(2);
        tabs.setTextColor(Color.parseColor("#a61c1e"));
        tabs.setTabPaddingLeftRight(15);
        tabs.setShouldExpand(true);
        //tabs.setUnderlineColor(Color.parseColor("#a61c1e"));
        tabs.setIndicatorColor(Color.parseColor("#a61c1e"));

        tabs.setDividerColor(Color.parseColor("#a61c1e"));
        tabs.setViewPager(pager);
        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenuCompat menu = PopupMenuCompat.newInstance(Pagesactivity.this, v);
                menu.inflate(R.menu.menu3);
                menu.setOnMenuItemClickListener(new PopupMenuCompat.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        Toast.makeText(Pagesactivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();

                        return false;
                    }
                });
                menu.show();

            }
        });
        filter2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenuCompat menu = PopupMenuCompat.newInstance(Pagesactivity.this, v);
                menu.inflate(R.menu.menu3);
                menu.setOnMenuItemClickListener(new PopupMenuCompat.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        Toast.makeText(Pagesactivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();

                        return false;
                    }
                });
                menu.show();

            }
        });
    }

    class MyPagerAdapter extends FragmentStatePagerAdapter {

        private final String[] TITLES = {"All Pages","My Pages","Pending Invitations","Search" };

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

                    return Allpages.newInstance("FirstFragment, Instance 1");
                case 1:

                    return Mypages.newInstance("SecondFragment, Instance 1");

                case 2:

                    return Pendinginvitations.newInstance("ThirdFragment, Instance 1");
                case 3:

                    return Search.newInstance("FourFragment, Instance 1");


                default:
                    return null;

            }

        }


    }
}
