package spinno.com.munchoba.fragmentsformainscreen;

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import spinno.com.munchoba.R;

public class Fitnessdetailsactivity extends Activity {
        TextView height, weight, bodyfat, bmi, wearable,interests,activitylevel, dietary;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fitnessdetailsactivity);
        height= (TextView)findViewById(R.id.height22);
        weight= (TextView)findViewById(R.id.weight2);
        bodyfat= (TextView)findViewById(R.id.bodyfat);
        bmi= (TextView)findViewById(R.id.BMi);
        wearable= (TextView)findViewById(R.id.wearable);
        interests= (TextView)findViewById(R.id.intr);
        activitylevel= (TextView)findViewById(R.id.lvl);
        dietary= (TextView)findViewById(R.id.diet);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(Fitnessdetailsactivity.this);


        height.setText(prefs.getString("height", null)+" "+prefs.getString("heightut", null));
        weight.setText(prefs.getString("weight", null)+" "+prefs.getString("weightut", null));
        wearable.setText(prefs.getString("wearabletype", null));
        interests.setText(prefs.getString("myinterests", null));
        activitylevel.setText(prefs.getString("activity_level", null));
        dietary.setText(prefs.getString("dietary", null));

    }


}
