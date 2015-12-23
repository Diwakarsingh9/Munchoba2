package spinno.com.munchoba.fragmentsformainscreen;

import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import spinno.com.munchoba.R;


public class Fitnessfeed extends Fragment {
    public  static ListView listviewfeed;

    int a=R.drawable.imageforlist;
    int a2 = R.drawable.frgt2;
    String b = "awesome";
    String b2="good";
    String foodeaten = "Milk, Banana";
    int w[]={12,25,30};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);

        View v = inflater.inflate(R.layout.activity_fitnessfeed, container, false);
        listviewfeed=(ListView)v.findViewById(R.id.listView);

       // FitnessfeedAdapter fitnessfeedAdapter = new FitnessfeedAdapter(getActivity(),a,a2,b,b2,foodeaten,w);
        Log.e("ffff","adapter chla");
        //listviewfeed.setAdapter(fitnessfeedAdapter);


        return v;
    }
   /* public static Exercise_Diary newInstance(String text) {

        Exercise_Diary f = new Exercise_Diary();

        Bundle b = new Bundle();
        b.putString("msg", text);
        f.setArguments(b);
        return f;
    }*/

}
