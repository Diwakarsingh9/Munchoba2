package spinno.com.munchoba.fragmentsformainscreen;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
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
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.login.LoginManager;
import com.viewpagerindicator.CirclePageIndicator;

import spinno.com.munchoba.AccountDetails;
import spinno.com.munchoba.Loggedin;
import spinno.com.munchoba.MainActivity;
import spinno.com.munchoba.One_Fragment;
import spinno.com.munchoba.R;
import spinno.com.munchoba.Three_FRAGMENT;
import spinno.com.munchoba.Two_FRAGMENT;

public class Editprofile extends FragmentActivity {
    ViewPager pager;
    public  static Editprofile activity;
        TextView deleteprofile;
    CirclePageIndicator titleIndicator;

    public  static FragmentManager fragmentManager;
    //  PagerSlidingTabStrip tabs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editprofile);
        fragmentManager=getSupportFragmentManager();
        pager = (ViewPager) findViewById(R.id.pager);
        deleteprofile = (TextView)findViewById(R.id.textView222);
        activity=Editprofile.this;
        pager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        titleIndicator= (CirclePageIndicator)findViewById(R.id.titles);

        deleteprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showExitDialouge();

            }
        });

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


    private void showExitDialouge() {


        AlertDialog.Builder alertDialog = new AlertDialog.Builder(Editprofile.this);
        alertDialog.setTitle("Delete Profile");
        alertDialog.setMessage("Are you sure you want to delete profile ?");
        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Intent in = new Intent(Editprofile.this, MainActivity.class);

                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(Editprofile.this);
                SharedPreferences.Editor edit2 = prefs.edit();
                edit2.putBoolean("pref_previously_started", Boolean.FALSE);
                edit2.commit();

                //MyApplication.getInstance();
                //MyApplication.getInstance().clearApplicationData();
                //clearApplicationData();
                AccessToken accessToken = AccessToken.getCurrentAccessToken();
                if(accessToken != null){
                    LoginManager.getInstance().logOut();
                }
                startActivity(in);
                Editprofile.this.finish();
                Loggedin.loggedin.finish();
                dialog.dismiss();
            }
        });
        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        alertDialog.show();
    }



    class MyPagerAdapter extends FragmentStatePagerAdapter {

        private final String[] TITLES = {"Account Details","My Fitness Details" };

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

                    return AccountDetails.newInstance("AccountDetails, Instance 1");
                case 1:

                    return Editfitnessdetails.newInstance("Editfitnessdetails, Instance 2");

                default:
                    return null;

            }

        }


    }


}
