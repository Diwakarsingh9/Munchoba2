package spinno.com.munchoba;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

import spinno.com.munchoba.fragmentsformainscreen.Detailsactivity;
import spinno.com.munchoba.fragmentsformainscreen.Detailsadapter;


public class Exercisediarydetails extends Activity {
    public static ArrayList<String> datedet = new ArrayList<String>();
    public static ArrayList<String> timedet = new ArrayList<String>();
    public static ArrayList<String> activitytypedet = new ArrayList<String>();
    public static ArrayList<String> workouttype = new ArrayList<String>();
    public static ArrayList<String> measurementdet = new ArrayList<String>();
    public static ArrayList<String> caloriesdet = new ArrayList<String>();
    public static ArrayList<String> countdet = new ArrayList<String>();

    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercisediarydetails);
        lv= (ListView)findViewById(R.id.lvdet);

        Bundle bundle = getIntent().getExtras();
        datedet= bundle.getStringArrayList("date");
        timedet= bundle.getStringArrayList("time");
        activitytypedet= bundle.getStringArrayList("activitytype");
        workouttype=bundle.getStringArrayList("workouttype");
        measurementdet= bundle.getStringArrayList("unit");
        caloriesdet= bundle.getStringArrayList("calories");
        countdet= bundle.getStringArrayList("count");

        Detailsadapter adp = new Detailsadapter(Exercisediarydetails.this,datedet,timedet,activitytypedet,workouttype,measurementdet,caloriesdet,countdet);
        lv.setAdapter(adp);
    }


}
