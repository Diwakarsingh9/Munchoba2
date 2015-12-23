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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import spinno.com.munchoba.R;
import spinno.com.munchoba.library.src.com.taig.pmc.Tagactivityadapter;

public class Linkactivity extends Activity {
    ListView lv;
    EditText searchedt;
    Linkactivityadapter adp;
    TextView next;
    public  static  Linkactivity linkactivity;
    String s[]={"Video1","Video1","Video3","Video1","Video1",
            "Video1","Video3","Video1","Video1","Video1",
            "Video1","Video2","Video2","Video2","Video3","Video1","Video3","Video2"};
    List<String> l = Arrays.<String>asList(s);
    ArrayList<String> al = new ArrayList<String>(l);
    ArrayList<Integer> a = new ArrayList<>();
    // if List<String> isnt specific enough:

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linkactivity);
        linkactivity=this;
        for(int i=0;i<19;i++){
            a.add(i);
        }
        lv=(ListView)findViewById(R.id.lvtag);
        searchedt=(EditText)findViewById(R.id.edtedt);
        next=(TextView)findViewById(R.id.done);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Linkactivity.this.finish();
                InputMethodManager inputMethodManager = (InputMethodManager) Linkactivity.linkactivity.getSystemService(Activity.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(Linkactivity.linkactivity.getCurrentFocus().getWindowToken(), 0);
            }
        });
        adp = new Linkactivityadapter(getApplicationContext(),al,a);
        lv.setAdapter(adp);
        searchedt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //Tagactivityadapter.FilteredArrayNames.clear();
                Linkactivity.this.adp.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

}
