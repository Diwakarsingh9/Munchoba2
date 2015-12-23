package spinno.com.munchoba;

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.Calendar;
import java.util.StringTokenizer;


public class Aboutmeactivity extends Activity {
TextView name, age, email, about, phoneno,gender, race, country;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutmeactivity);
        name=(TextView)findViewById(R.id.name2);
        age=(TextView)findViewById(R.id.age);
        email=(TextView)findViewById(R.id.email);
        about=(TextView)findViewById(R.id.about_me);
        phoneno=(TextView)findViewById(R.id.phone);
        gender=(TextView)findViewById(R.id.gender);
        race=(TextView)findViewById(R.id.race);
        country=(TextView)findViewById(R.id.country);





        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(Aboutmeactivity.this);


        name.setText(prefs.getString("name", null));


      String dob =prefs.getString("dob", null);


        StringTokenizer tokens = new StringTokenizer(dob, "-");
        String year = tokens.nextToken().trim();;
        String month = tokens.nextToken().trim();
        String day = tokens.nextToken().trim();

        Log.e("date", "year: " + year + "Month: " + month + "Day: " + day);

        int year2 = Integer.parseInt(year);
        int month2 = Integer.parseInt(month);
        int day2 = Integer.parseInt(day);



        age.setText(getAge(year2,month2,day2));

        email.setText(prefs.getString("email", null));
        about.setText(prefs.getString("about", null));
        phoneno.setText(prefs.getString("phoneno", null));
        gender.setText(prefs.getString("gender", null));
        race.setText(prefs.getString("race", null));
        country.setText(prefs.getString("country", null));
    }
    private String getAge(int year, int month, int day){
        Calendar dob = Calendar.getInstance();
        Calendar today = Calendar.getInstance();

        dob.set(year, month, day);

        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);

        if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)){
            age--;
        }

        Integer ageInt = new Integer(age);
        String ageS = ageInt.toString();

        return ageS;
    }
}
