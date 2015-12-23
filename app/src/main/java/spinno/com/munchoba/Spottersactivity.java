package spinno.com.munchoba;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.ProgressBar;

import spinno.com.munchoba.Adapterclasses.Myspotteradapter;
import spinno.com.munchoba.parsingforapi.parsingforspotters;
import spinno.com.munchoba.settergetter.Spotterssettergetter;

public class Spottersactivity extends Activity {
        public static ListView lv;
    public static Myspotteradapter adp;
    public static ProgressBar pbsp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spottersactivity);
        lv = (ListView)findViewById(R.id.listviewspotters);
        pbsp = (ProgressBar)findViewById(R.id.pb);
        parsingforspotters.parsingspotters(Spottersactivity.this);
         adp = new Myspotteradapter(Spottersactivity.this,parsingforspotters.usernames,parsingforspotters.about,parsingforspotters.avatar);
        lv.setAdapter(adp);
    }
}
