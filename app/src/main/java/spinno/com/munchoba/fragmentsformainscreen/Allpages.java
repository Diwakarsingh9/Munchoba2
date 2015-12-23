package spinno.com.munchoba.fragmentsformainscreen;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import spinno.com.munchoba.Adapterclasses.Myallpagesadapter;
import spinno.com.munchoba.R;
import spinno.com.munchoba.parsingforapi.parsingpages;


public class Allpages extends Fragment {

    public  static  ListView list;
public  static   Myallpagesadapter adp;
    public  static TextView pagenot;
    public static Allpages newInstance(String param1) {
        Allpages fragment = new Allpages();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_allpages, container, false);
        list=(ListView)v.findViewById(R.id.list22);
        pagenot=(TextView)v.findViewById(R.id.pagesnot);
     adp = new Myallpagesadapter(getActivity(), parsingpages.name,parsingpages.created,parsingpages.descp);
        list.setAdapter(adp);

        return v;
    }

}
