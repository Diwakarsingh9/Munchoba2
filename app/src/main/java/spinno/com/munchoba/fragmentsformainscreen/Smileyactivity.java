package spinno.com.munchoba.fragmentsformainscreen;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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

public class Smileyactivity extends Activity {
    ListView lv;
    EditText searchedt;
    public  static  Smileyactivity smileyactivity;
    Smileyactivityadapter adp;
    TextView next;
    String s[]={"Happy","Sad","Angry","Pretty","Shocked",
            "Relieved","Annoyed","Tired","Laugh","Loved",
            "Bored","Sick","Irritated","Blessed","Excited","Meh","Normal","Sleepless"};

    int smiley[]={R.drawable.happy,R.drawable.sad,R.drawable.anger,R.drawable.happy,
            R.drawable.shocked,R.drawable.neutral,
            R.drawable.annoyed,R.drawable.speechless, R.drawable.laugh,R.drawable.loved,
            R.drawable.neutral,R.drawable.speechless, R.drawable.annoyed,R.drawable.blessed,
            R.drawable.laugh,R.drawable.coolll, R.drawable.neutral,R.drawable.sad, R.drawable.annoyed};
    List<String> l = Arrays.<String>asList(s);
    ArrayList<String> al = new ArrayList<String>(l);
    ArrayList<Integer> a = new ArrayList<>();

    ArrayList<Integer> al2 = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smileyactivity);
        Smileyactivityadapter.smileydata.clear();
        Smileyactivityadapter.smileyimage.clear();
        smileyactivity=this;
        for(int i=0;i<19;i++){
            a.add(i);
        }
        for(int i=0;i<smiley.length;i++){
            al2.add(smiley[i]);
        }

        lv=(ListView)findViewById(R.id.lvtag);
        searchedt=(EditText)findViewById(R.id.edtedt);
        next=(TextView)findViewById(R.id.done);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Smileyactivity.this.finish();
                    InputMethodManager inputMethodManager = (InputMethodManager) Smileyactivity.smileyactivity.getSystemService(Activity.INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(Smileyactivity.smileyactivity.getCurrentFocus().getWindowToken(), 0);

            }
            }

            );
            adp=new

            Smileyactivityadapter(getApplicationContext(),al,a,al2

            );
            lv.setAdapter(adp);
            searchedt.addTextChangedListener(new TextWatcher() {

                                  @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                                                         }

                           @Override
                  public void onTextChanged(CharSequence s, int start, int before, int count) {
                                                             //Tagactivityadapter.FilteredArrayNames.clear();
                            Smileyactivity.this.adp.getFilter().filter(s);
                                                         }

           @Override
                       public void afterTextChanged(Editable s) {

                                                         }
                                                     }

            );
        }


    }
