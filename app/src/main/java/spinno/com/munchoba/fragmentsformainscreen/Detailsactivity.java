package spinno.com.munchoba.fragmentsformainscreen;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

import spinno.com.munchoba.R;

public class Detailsactivity extends Activity {

    public static ArrayList<String> datedet = new ArrayList<String>();
    public static ArrayList<String> timedet = new ArrayList<String>();
    public static ArrayList<String> mealtypedet = new ArrayList<String>();
    public static ArrayList<String> measurementdet = new ArrayList<String>();
    public static ArrayList<String> caloriesdet = new ArrayList<String>();
    public static ArrayList<String> countdet = new ArrayList<String>();
    public static ArrayList<String> fooddet = new ArrayList<String>();

    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailsactivity);
        lv= (ListView)findViewById(R.id.lvdet);

        Bundle bundle = getIntent().getExtras();
        datedet= bundle.getStringArrayList("date");
        timedet= bundle.getStringArrayList("time");
        mealtypedet= bundle.getStringArrayList("mealtype");
        measurementdet= bundle.getStringArrayList("unit");
        caloriesdet= bundle.getStringArrayList("calories");
        countdet= bundle.getStringArrayList("count");
        fooddet= bundle.getStringArrayList("meal");

        Detailsadapter adp = new Detailsadapter(Detailsactivity.this,datedet,timedet,mealtypedet,measurementdet,caloriesdet,countdet,fooddet);
        lv.setAdapter(adp);


    }

}
