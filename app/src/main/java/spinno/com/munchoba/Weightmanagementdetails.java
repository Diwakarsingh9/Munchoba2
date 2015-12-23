package spinno.com.munchoba;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import spinno.com.munchoba.fragmentsformainscreen.Detailsadapter;


public class Weightmanagementdetails extends Activity {
    public static ArrayList<String> titlesdet = new ArrayList<String>();
    public static ArrayList<String> datedet = new ArrayList<String>();
    String heading,subtitle;
    TextView heading1, title1;

    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weightmanagementdetails);
        lv= (ListView)findViewById(R.id.lvdet);
        heading1= (TextView)findViewById(R.id.heading);
        title1= (TextView)findViewById(R.id.subtitle);

        Bundle bundle = getIntent().getExtras();
        datedet= bundle.getStringArrayList("date");
        titlesdet= bundle.getStringArrayList("titles");
        heading= bundle.getString("Heading");
        subtitle= bundle.getString("subtitles");

        heading1.setText(heading);
        title1.setText(subtitle);

        Weightdetailsadapter adp = new Weightdetailsadapter(Weightmanagementdetails.this,datedet,titlesdet);
        lv.setAdapter(adp);

    }

}
