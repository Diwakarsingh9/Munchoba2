package spinno.com.munchoba;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.login.LoginManager;

import spinno.com.munchoba.fragmentsformainscreen.Imagesadapter;
import spinno.com.munchoba.fragmentsformainscreen.Photoimagesadapter;
import spinno.com.munchoba.fragmentsformainscreen.Photosinneractivity;
import spinno.com.munchoba.parsingforapi.parsingfordeletealbum;
import spinno.com.munchoba.parsingforapi.parsingformyphotos;

public class Photoopenactivity extends Activity {
    public static TextView albumname,deletalbum;
    public static int a;
    public static GridView gd;
    public static Photoimagesadapter adp;
    public static Photoopenactivity php;
    SharedPreferences prefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photoopenactivity);
        php = this;
        prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        String s = prefs.getString("albumname",null);
        a= prefs.getInt("position", 0);
      //  Toast.makeText(getApplicationContext(), "" + s + " " + a, Toast.LENGTH_SHORT).show();


        gd = (GridView) findViewById(R.id.grid);
        albumname = (TextView) findViewById(R.id.album);
        deletalbum = (TextView) findViewById(R.id.deletealbum);
        albumname.setText(s);
        deletalbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showdeletedialog();
            }
        });
        if(parsingformyphotos.captionphoto2.get(a)[0].equals("abc")){
            gd.setVisibility(View.GONE);
            Log.e("dewsss", ""+parsingformyphotos.captionphoto2.get(a)[0]);
        }
        else {
            gd.setVisibility(View.VISIBLE);
            adp = new Photoimagesadapter(Photoopenactivity.this, parsingformyphotos.thumbnailphoto22.get(a), parsingformyphotos.captionphoto2.get(a));
           // Toast.makeText(getApplicationContext(), "" + parsingformyphotos.thumbnailphoto22.size() + " " + parsingformyphotos.captionphoto2.size(), Toast.LENGTH_SHORT).show();
            gd.setAdapter(adp);
        }
        gd.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent in = new Intent(Photoopenactivity.this, Photosinneractivity.class);
                // in.putExtra("images", s[position]);
                in.putExtra("position", position);
                in.putExtra("imagesdata", parsingformyphotos.fullphoto2.get(a));
                in.putExtra("imagescap", parsingformyphotos.captionphoto2.get(a));
                in.putExtra("imagesid",parsingformyphotos.idphoto2.get(a));
                startActivity(in);
            }
        });


    }
    private void showdeletedialog() {


        AlertDialog.Builder alertDialog = new AlertDialog.Builder(Photoopenactivity.this);
        alertDialog.setTitle("Delete Album");
        alertDialog.setMessage("Are you sure you want to delete this album ?");
        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                parsingfordeletealbum.parsingfordeletealbums(Photoopenactivity.this,parsingformyphotos.id.get(a));
                dialog.dismiss();
            }
        });
        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        alertDialog.show();
    }
}
