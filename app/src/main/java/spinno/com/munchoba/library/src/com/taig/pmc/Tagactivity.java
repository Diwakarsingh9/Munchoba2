package spinno.com.munchoba.library.src.com.taig.pmc;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import spinno.com.munchoba.R;
import spinno.com.munchoba.fragmentsformainscreen.Smileyactivity;
import spinno.com.munchoba.fragmentsformainscreen.Status;

public class Tagactivity extends Activity {
        ListView lv;
    EditText searchedt;
    Tagactivityadapter adp;
    TextView next;

    String s[]={"Person1","Person2","Person3","Person4","Person5",
            "Person6","Person7","Person8","Person11","Person1111",
            "Person211","Person3111","Person1211","Person21112","Person31112","Person13321","Person2231","Person3121"};
    List<String> l = Arrays.<String>asList(s);
    ArrayList<String> al = new ArrayList<String>(l);
   ArrayList<Integer> a = new ArrayList<>();
    // if List<String> isnt specific enough:

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tagactivity);
        Tagactivityadapter.tagfriendslist.clear();
        for(int i=0;i<19;i++){
            a.add(i);
        }
        lv=(ListView)findViewById(R.id.lvtag);
        searchedt=(EditText)findViewById(R.id.edtedt);
        next=(TextView)findViewById(R.id.done);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String[] taglist = new String[Tagactivityadapter.tagfriendslist.size()];
                taglist = Tagactivityadapter.tagfriendslist.toArray(taglist);
                if(taglist.length!=(0)){
                for(int i =0; i<2;i++) {
                    if(taglist.length>2){
                    Status.tagfriends.setText("with " + taglist[0] + " & others");
                    }
                    else if(taglist.length==2 ){
                        Status.tagfriends.setText("with " + taglist[0] + " & other");
                    }
                    else {
                        Status.tagfriends.setText("with " + taglist[0]);
                    }
                }
                    Status.defaultlayout.setVisibility(View.VISIBLE);
                    Status.Statuslayout.setVisibility(View.VISIBLE);
                    Status.withlayout.setVisibility(View.VISIBLE);
                    ChangeVisibiltyoflayout();
                //Toast.makeText(Tagactivity.this, "" +Tagactivityadapter.tagfriendslist +" tagged now", Toast.LENGTH_SHORT).show();
                Tagactivity.this.finish();
                    InputMethodManager inputMethodManager = (InputMethodManager)Tagactivity.this.getSystemService(Activity.INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(Tagactivity.this.getCurrentFocus().getWindowToken(), 0);
                }
                else {
                    Tagactivity.this.finish();
                    InputMethodManager inputMethodManager = (InputMethodManager)Tagactivity.this.getSystemService(Activity.INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(Tagactivity.this.getCurrentFocus().getWindowToken(), 0);
                }

            }
        });
        adp = new Tagactivityadapter(getApplicationContext(), al, a);
        lv.setAdapter(adp);
        searchedt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //Tagactivityadapter.FilteredArrayNames.clear();
                Tagactivity.this.adp.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        }

    private void ChangeVisibiltyoflayout() {
        if(Status.emoticon.getText().toString().equals("Emotion")&&Status.place.getText().toString().equals("at")){
            Status.emotionlayout.setVisibility(View.INVISIBLE);
            Status.atlayout.setVisibility(View.INVISIBLE);
        }
       else if(!Status.emoticon.getText().toString().equals("Emotion")&&Status.place.getText().toString().equals("at")){
            Status.emotionlayout.setVisibility(View.VISIBLE);
            Status.atlayout.setVisibility(View.INVISIBLE);
        }
        else if(Status.emoticon.getText().toString().equals("Emotion")&&!Status.place.getText().toString().equals("at")){
            Status.emotionlayout.setVisibility(View.INVISIBLE);
            Status.atlayout.setVisibility(View.VISIBLE);
        }
        else{
            Status.emotionlayout.setVisibility(View.VISIBLE);
            Status.atlayout.setVisibility(View.VISIBLE);
        }
    }


}
