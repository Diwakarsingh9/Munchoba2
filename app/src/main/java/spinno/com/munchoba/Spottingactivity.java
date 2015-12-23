package spinno.com.munchoba;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.ProgressBar;

import spinno.com.munchoba.Adapterclasses.Myspotteradapter;
import spinno.com.munchoba.Adapterclasses.Myspottingadapter;
import spinno.com.munchoba.parsingforapi.parsingforspotters;
import spinno.com.munchoba.parsingforapi.parsingforspotting;

public class Spottingactivity extends Activity {
    public static ListView lv;
    public static ProgressBar pbsp;
    public static Myspottingadapter adp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spottingactivity);
        lv = (ListView)findViewById(R.id.listviewspotters);
        pbsp = (ProgressBar)findViewById(R.id.pb);
        parsingforspotting.parsingspotting(Spottingactivity.this);
        adp = new Myspottingadapter(Spottingactivity.this,parsingforspotting.usernames,parsingforspotting.about,parsingforspotting.avatar);
        lv.setAdapter(adp);
    }
}
