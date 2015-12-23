package spinno.com.munchoba.Adapterclasses;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

import spinno.com.munchoba.fragmentsformainscreen.Basefragmentforphotoopen;


public class MyAdapter extends FragmentPagerAdapter {
    String[] a,f;
        int b;


    public MyAdapter(FragmentManager fm, int position1, String[] b, String[] arrayB) {
        super(fm);
        this.b =position1;
        f=arrayB;
        a=b;

    }



    @Override
    public Fragment getItem(int position) {

        Fragment frag = null;

        frag= Basefragmentforphotoopen.newInstance(a[position],f[position]);

        return frag;
    }

    @Override
    public int getCount() {
        return a.length;
    }
}