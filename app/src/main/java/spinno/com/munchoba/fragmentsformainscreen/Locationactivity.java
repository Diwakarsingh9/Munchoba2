package spinno.com.munchoba.fragmentsformainscreen;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import spinno.com.munchoba.R;
import spinno.com.munchoba.parsingforapi.parsingforplaces;

public class Locationactivity extends Activity {
    ListView lv;
    public static AutoCompleteTextView atvPlaces;
    EditText searchedt;
    Locationactivityadapter adp;
    public static  TextView next;
    public  static  Locationactivity locationactivity;
    String s[]={"Place1","Place2","Place1","Place1","Place1",
            "Place2","Place2","Place2","Place2","Place3",
            "Place1","Place3","Place3","Place1","Place2","Place3","Place3","Place2"};
    List<String> l = Arrays.<String>asList(s);
    ArrayList<String> al = new ArrayList<String>(l);
    ArrayList<Integer> a = new ArrayList<>();
    // if List<String> isnt specific enough:

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locationactivity);
        locationactivity=this;
        atvPlaces = (AutoCompleteTextView) findViewById(R.id.atv_places);
        for(int i=0;i<19;i++){
            a.add(i);
        }
        lv=(ListView)findViewById(R.id.lvtag);
        searchedt=(EditText)findViewById(R.id.edtedt);
        next=(TextView)findViewById(R.id.done);
        atvPlaces.setThreshold(1);

        atvPlaces.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                parsingforplaces.parsing(Locationactivity.this, atvPlaces.getText().toString().trim());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }
        });
       /* next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Locationactivity.this.finish();
                InputMethodManager inputMethodManager = (InputMethodManager) Locationactivity.locationactivity.getSystemService(Activity.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(Locationactivity.locationactivity.getCurrentFocus().getWindowToken(), 0);
            }
        });*/
        adp = new Locationactivityadapter(getApplicationContext(),al,a);
        lv.setAdapter(adp);

    }

}
