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

import spinno.com.munchoba.Adapterclasses.Myallpagesadapter;
import spinno.com.munchoba.Adapterclasses.Mypagesadapter;
import spinno.com.munchoba.R;
import spinno.com.munchoba.parsingforapi.parsingpages;


public class Mypages extends Fragment {
    public  static ListView list;
    public  static Mypagesadapter adp;
    public  static TextView pagenot;
    public static Mypages newInstance(String param1) {
        Mypages fragment = new Mypages();
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
        View v = inflater.inflate(R.layout.fragment_mypages, container, false);
        list=(ListView)v.findViewById(R.id.list22);
        pagenot=(TextView)v.findViewById(R.id.pagesnot2);
        parsingpages.parsingmypagesdata(getActivity());

            adp = new Mypagesadapter(getActivity(), parsingpages.myname, parsingpages.mycreated, parsingpages.mydescp);
            list.setAdapter(adp);

        return v;
    }

}
