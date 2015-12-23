package spinno.com.munchoba.fragmentsformainscreen;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.nirhart.parallaxscroll.views.ParallaxScrollView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import spinno.com.munchoba.Aboutmeactivity;
import spinno.com.munchoba.Loggedin;
import spinno.com.munchoba.MainActivity;
import spinno.com.munchoba.R;
import spinno.com.munchoba.Spottersactivity;
import spinno.com.munchoba.Spottingactivity;
import spinno.com.munchoba.Videoopenactivity;
import spinno.com.munchoba.parsingforapi.parsingformyactivity;
import spinno.com.munchoba.parsingforapi.parsingformyvideos;

public class Viewprofileactivity extends Activity {
    public  static ListView listviewfeed;
    public  static ProgressBar pb33;
        ImageView dp;
    int a=R.drawable.imageforlist;
    int a2 = R.drawable.frgt2;
    String b = "awesome";
    String b2="good";
    TextView name,spotters,spotting;
    public  static FitnessfeedAdapter fitnessfeedAdapter;
    ParallaxScrollView parallex;
    String foodeaten = "Milk, Banana";
    int w[]={12,25,30};
    TextView status, videos, photos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewprofileactivity);

        listviewfeed=(ListView)findViewById(R.id.listView);
        status=(TextView)findViewById(R.id.stat);
        name=(TextView)findViewById(R.id.nameofuser);
        spotters=(TextView)findViewById(R.id.spottersuser);
        spotting=(TextView)findViewById(R.id.spottinguser);
        videos=(TextView)findViewById(R.id.vid);
        photos=(TextView)findViewById(R.id.phot);
        dp=(ImageView)findViewById(R.id.dppic);
        pb33=(ProgressBar)findViewById(R.id.pb333);

        parsingformyactivity.parsingactivity(Viewprofileactivity.this);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(Viewprofileactivity.this);
        String username = prefs.getString("name", null);
        String profimg = prefs.getString("imagePreferance", "a");
        if(profimg.equals("a")){
            dp.setImageResource(R.drawable.userdefault);
        }
        else{
            dp.setImageBitmap(decodeBase64(profimg));
        }


        name.setText(username + "");

        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Viewprofileactivity.this, Aboutmeactivity.class);
                startActivity(in);
            }
        });
        spotters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Viewprofileactivity.this, Spottersactivity.class);
                startActivity(in);
            }
        });
        spotting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Viewprofileactivity.this, Spottingactivity.class);
                startActivity(in);
            }
        });
        dp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Viewprofileactivity.this, Aboutmeactivity.class);
                startActivity(in);
            }
        });

     // fitnessfeedAdapter = new FitnessfeedAdapter(Viewprofileactivity.this,);
        Log.e("ffff", "adapter chla");
      //  listviewfeed.setAdapter(fitnessfeedAdapter);
       // setListViewHeightBasedOnChildren(listviewfeed);
        listviewfeed.setFocusable(false);
        listviewfeed.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               // Toast.makeText(Viewprofileactivity.this,""+)
                if(parsingformyactivity.commenttype.get(i).equals("videos.linking"))
                    {
                        if(parsingformyactivity.typevideo.get(i).equals("youtube")){
                            Intent in = new Intent(Viewprofileactivity.this, Videoopenactivity.class);
                            in.putExtra("videoname", "Shared Video");
                            in.putExtra("videodescription", parsingformyactivity.description11.get(i));
                            in.putExtra("Activity","Viewprofile");
                            in.putExtra("urlid", getYoutubeVideoId(parsingformyactivity.path.get(i)));
                            startActivity(in);
                        }
                       else if(parsingformyactivity.typevideo.get(i).equals("vimeo")){
                            Intent in = new Intent(Viewprofileactivity.this, Vimeoactivity.class);
                            in.putExtra("videoname", "Shared Video");
                            in.putExtra("videodescription", parsingformyactivity.description11.get(i));
                            in.putExtra("Activity","Viewprofile");
                            in.putExtra("urlid", getYoutubeVideoId(parsingformyactivity.path.get(i)));
                            startActivity(in);
                        }
                        else {
                            //
                        }
                    }


                else{
                    //
                }
            }
        });
        //Parallax.fullScroll(ScrollView.FOCUS_UP);
        status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Viewprofileactivity.this, Loggedin.class);
                startActivity(in);
                MainActivity.croppedimage = 30;
                Loggedin.loggedin.finish();
                Viewprofileactivity.this.finish();
            }
        });
        videos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Viewprofileactivity.this, Videosactivity.class);
                startActivity(in);

            }
        });
        photos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Viewprofileactivity.this, Photoactivity.class);
                startActivity(in);

            }
        });

    }
    public static Bitmap decodeBase64(String input) {
        byte[] decodedByte = Base64.decode(input, 0);
        return BitmapFactory
                .decodeByteArray(decodedByte, 0, decodedByte.length);
    }
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null)
            return;

        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.UNSPECIFIED);
        int totalHeight = 0;
        View view = null;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            view = listAdapter.getView(i, view, listView);
            if (i == 0)
                view.setLayoutParams(new ViewGroup.LayoutParams(desiredWidth, ViewGroup.LayoutParams.WRAP_CONTENT));

            view.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += view.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount()-1))+listAdapter.getCount()*15;
        listView.setLayoutParams(params);
        listView.requestLayout();
    }

    public static String getYoutubeVideoId(String youtubeUrl)
    {
        String video_id="";
        if (youtubeUrl != null && youtubeUrl.trim().length() > 0 && youtubeUrl.startsWith("http"))
        {

            String expression = "^.*((youtu.be"+ "\\/)" + "|(v\\/)|(\\/u\\/w\\/)|(embed\\/)|(watch\\?))\\??v?=?([^#\\&\\?]*).*"; // var regExp = /^.*((youtu.be\/)|(v\/)|(\/u\/\w\/)|(embed\/)|(watch\?))\??v?=?([^#\&\?]*).*/;
            CharSequence input = youtubeUrl;
            Pattern pattern = Pattern.compile(expression,Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(input);
            if (matcher.matches())
            {
                String groupIndex1 = matcher.group(7);
                if(groupIndex1!=null && groupIndex1.length()==11)
                    video_id = groupIndex1;
            }
        }
        return video_id;
    }

}
