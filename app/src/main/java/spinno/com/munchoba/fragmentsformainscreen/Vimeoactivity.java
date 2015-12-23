package spinno.com.munchoba.fragmentsformainscreen;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import spinno.com.munchoba.R;
import spinno.com.munchoba.parsingforapi.parsingfordeletevideo;
import spinno.com.munchoba.parsingforapi.parsingformyvideos;

/**
 * Created by saifi45 on 9/14/2015.
 */
public class Vimeoactivity extends Activity {

    public static TextView albumname;
    int a;
    public static LinearLayout llforweb;
    String idvideo;
    public static ImageView deletalbum;
    public static TextView Description;
    public static Photoimagesadapter adp;
    public static Vimeoactivity php;
    private static final int RECOVERY_DIALOG_REQUEST = 1;
    WebView webView1;
    HTML5WebView mWebView;

    ProgressDialog pDialog;
    VideoView videoview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the layout from video_main.xml
        setContentView(R.layout.vimeovideoplayer);
        php = this;
        Bundle b = getIntent().getExtras();
        String s =  b.getString("videoname");
        String s2 =  b.getString("videodescription");
        a =  b.getInt("position");
        deletalbum = (ImageView) findViewById(R.id.deletealbum);
        albumname = (TextView) findViewById(R.id.album);
        // play = (ImageView) findViewById(R.id.playvideo);
        Description = (TextView) findViewById(R.id.descptn);
        llforweb = (LinearLayout) findViewById(R.id.llforweb);
        // deletalbum = (ImageView) findViewById(R.id.deletealbum);
        albumname.setText(s);
        Description.setText(s2);
        // Find your VideoView in your video_main.xml layout

        // Execute StreamVideo AsyncTask
        deletalbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showdeletedialog();
            }
        });
        // Create a progressbar

        // webView1.loadData("<iframe src=\"http://player.vimeo.com/video/" + 138174318 + "\" width=\"100%\" height=\"95%\" frameborder=\"0\" webkitallowfullscreen mozallowfullscreen allowfullscreen></iframe>", "text/html", "utf-8");







        mWebView = new HTML5WebView(this);

        if (savedInstanceState != null) {
            mWebView.restoreState(savedInstanceState);
        } else {
            idvideo  = parsingformyvideos.video_id.get(a).trim();
            mWebView.loadUrl("http://player.vimeo.com/video/"+idvideo);
        }
        mWebView.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        llforweb.addView(mWebView.getLayout());
          // setContentView(mWebView.getLayout());

    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mWebView.saveState(outState);
    }

    @Override
    public void onStop() {
        super.onStop();
        mWebView.stopLoading();
    }
    @Override
    public void onPause() {
        super.onPause();
        mWebView.stopLoading();
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        mWebView.destroy();
    }
    private void showdeletedialog() {


        AlertDialog.Builder alertDialog = new AlertDialog.Builder(Vimeoactivity.this);
        alertDialog.setTitle("Delete Album");
        alertDialog.setMessage("Are you sure you want to delete this video ?");
        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                parsingfordeletevideo.parsingfordeletevid(Vimeoactivity.this, parsingformyvideos.id.get(a));
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
