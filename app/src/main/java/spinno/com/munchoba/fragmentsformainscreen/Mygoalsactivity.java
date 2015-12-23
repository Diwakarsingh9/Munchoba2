package spinno.com.munchoba.fragmentsformainscreen;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import spinno.com.munchoba.AccountDetails;
import spinno.com.munchoba.Loggedin;
import spinno.com.munchoba.MainActivity;
import spinno.com.munchoba.R;
import spinno.com.munchoba.parsingforapi.parsingformygoals;

public class Mygoalsactivity extends Activity {
        public  static FrameLayout framelayout2;
    public  static LinearLayout Endurance , flexi , Generalmain, layoutforgoals, llforcb;
    public static TextView Currentheight, weight2, bodyfat, bmi,musclegroup;
    public static Spinner mygoal, intensity;
    public  static Button save;
    public  static ProgressBar pb,pb2;
    public  static ArrayAdapter adp1,adp2;
    String mygoaldata[]={"Select Goal Type","Endurance Improvement",
            "Fat Loss","Flexibility / Balance Improvement",
            "General Maintenance","Muscle Building","Weight Loss"};
    String intensitydata[]={"Beginner","Intermediate","Advanced"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mygoalsactivity);
        //framelayout2 = (FrameLayout)findViewById(R.id.framelayout);
       // Endurance = (LinearLayout)findViewById(R.id.endnmuscle);
        layoutforgoals = (LinearLayout)findViewById(R.id.layoutforgoal);
        llforcb = (LinearLayout)findViewById(R.id.llforcheckbox);
        //flexi = (LinearLayout)findViewById(R.id.flexi);
       // Generalmain = (LinearLayout)findViewById(R.id.general);
        Currentheight = (TextView)findViewById(R.id.height22);
        weight2 = (TextView)findViewById(R.id.weight2);
        pb = (ProgressBar)findViewById(R.id.pb);
        pb2 = (ProgressBar)findViewById(R.id.pb22);
        bodyfat = (TextView)findViewById(R.id.bodyfat);
        musclegroup = (TextView)findViewById(R.id.musclegroup);
        bmi = (TextView)findViewById(R.id.BMi);
        mygoal = (Spinner)findViewById(R.id.spgoal);
        intensity = (Spinner)findViewById(R.id.spintensity);
        save = (Button)findViewById(R.id.save);
        musclegroup.setVisibility(View.GONE);

        parsingformygoals.parsingallpagesdata(getApplicationContext());
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(Mygoalsactivity.this);
        Currentheight.setText(prefs.getString("height", null)+" "+prefs.getString("heightut", null));
        weight2.setText(prefs.getString("weight", null) + " " + prefs.getString("weightut", null));
        parsingformygoals.title.add(0, "Select Goal");
        parsingformygoals.id.add(0, "0");
        adp1= new ArrayAdapter(Mygoalsactivity.this,R.layout.itemlayoutforfooddiary,R.id.kljjjjjjj, parsingformygoals.title);
        mygoal.setAdapter(adp1);
         adp2= new ArrayAdapter(Mygoalsactivity.this,R.layout.itemlayoutforfooddiary,R.id.kljjjjjjj,intensitydata);
        intensity.setAdapter(adp2);
        mygoal.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                    parsingformygoals.parsingallpagesdata22(getApplicationContext(), parsingformygoals.id.get(position));

                // Toast.makeText(getActivity(),""+position,Toast.LENGTH_SHORT).show();
                /*if (position == 1 || position == 5) {
                    framelayout2.setVisibility(View.VISIBLE);
                    Endurance.setVisibility(View.VISIBLE);
                    flexi.setVisibility(View.GONE);
                    Generalmain.setVisibility(View.GONE);
                } else if (position == 3) {
                    framelayout2.setVisibility(View.VISIBLE);
                    flexi.setVisibility(View.VISIBLE);
                    Endurance.setVisibility(View.GONE);
                    Generalmain.setVisibility(View.GONE);
                } else if (position == 4) {
                    framelayout2.setVisibility(View.VISIBLE);
                    Generalmain.setVisibility(View.VISIBLE);
                    Endurance.setVisibility(View.GONE);
                    flexi.setVisibility(View.GONE);
                } else {
                    framelayout2.setVisibility(View.GONE);
                    Endurance.setVisibility(View.GONE);
                    flexi.setVisibility(View.GONE);
                    Generalmain.setVisibility(View.GONE);

                }*/

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                framelayout2.setVisibility(View.GONE);
                Endurance.setVisibility(View.GONE);
                flexi.setVisibility(View.GONE);
                Generalmain.setVisibility(View.GONE);
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(), "Best Plans will be shown", Toast.LENGTH_SHORT).show();
            }
        });

    }


}
