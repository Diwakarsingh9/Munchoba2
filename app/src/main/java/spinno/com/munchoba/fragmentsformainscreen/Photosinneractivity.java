package spinno.com.munchoba.fragmentsformainscreen;

import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import spinno.com.munchoba.Adapterclasses.MyAdapter;
import spinno.com.munchoba.Photoopenactivity;
import spinno.com.munchoba.R;
import spinno.com.munchoba.parsingforapi.parsingfordeletephoto;

public class Photosinneractivity extends FragmentActivity {
    ImageView img,back,delete;
    public static Photosinneractivity php2;
    int position2;
    String  caption,data;
    int seconds;
    ViewPager viewPager;
    String  []arrayB;
    String  []arrayc;
    String  []arrayd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photosinneractivity);
        php2=this;
        back= (ImageView)findViewById(R.id.close);
        delete= (ImageView)findViewById(R.id.deletephoto);
        Bundle bundle2= getIntent().getExtras();
        int position1= bundle2.getInt("position", 0);
        arrayB = bundle2.getStringArray("imagesdata");
        arrayc = bundle2.getStringArray("imagescap");
        arrayd = bundle2.getStringArray("imagesid");
        viewPager=(ViewPager)findViewById(R.id.view);
        viewPager.setAdapter(new MyAdapter(getSupportFragmentManager(),position1,arrayB,arrayc));
        viewPager.setCurrentItem(position1);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Photosinneractivity.this.finish();
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              int pos= viewPager.getCurrentItem();
                parsingfordeletephoto.parsingfordeletephotos(Photosinneractivity.this,arrayd[pos]);
               // Toast.makeText(getApplicationContext(),""+pos,Toast.LENGTH_SHORT).show();
            }
        });
    }


}
