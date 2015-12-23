package spinno.com.munchoba;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.google.android.youtube.player.YouTubePlayerView;

import spinno.com.munchoba.fragmentsformainscreen.Photoimagesadapter;
import spinno.com.munchoba.parsingforapi.parsingfordeletevideo;
import spinno.com.munchoba.parsingforapi.parsingformyvideos;

public class Videoshowing extends Activity {

    public static TextView albumname;
    int a;
    String idvideo;
      public static ImageView deletalbum;
    public static TextView Description;
    public static Photoimagesadapter adp;
    public static Videoshowing php;
    private static final int RECOVERY_DIALOG_REQUEST = 1;


    ProgressDialog pDialog;
    VideoView videoview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the layout from video_main.xml
        setContentView(R.layout.activity_videoshowing);
        php = this;
        Bundle b = getIntent().getExtras();
        String s =  b.getString("videoname");
        String s2 =  b.getString("videodescription");
        a =  b.getInt("position");
        deletalbum = (ImageView) findViewById(R.id.deletealbum);
        albumname = (TextView) findViewById(R.id.album);
        // play = (ImageView) findViewById(R.id.playvideo);
        Description = (TextView) findViewById(R.id.descptn);
        // deletalbum = (ImageView) findViewById(R.id.deletealbum);
        albumname.setText(s);
        Description.setText(s2);
        // Find your VideoView in your video_main.xml layout
        videoview = (VideoView) findViewById(R.id.myVideo);
        // Execute StreamVideo AsyncTask
        deletalbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showdeletedialog();
            }
        });
        // Create a progressbar
        pDialog = new ProgressDialog(Videoshowing.this);
        // Set progressbar title
        pDialog.setTitle("Munchoba Video Streaming");
        // Set progressbar message
        pDialog.setMessage("Buffering...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        // Show progressbar
        pDialog.show();

        try {
            // Start the MediaController
            MediaController mediacontroller = new MediaController(
                    Videoshowing.this);
            mediacontroller.setAnchorView(videoview);
            String VideoURL = "http://munchoba.com/".concat(parsingformyvideos.path.get(a).trim());
            // Get the URL from String VideoURL
            Uri video = Uri.parse(VideoURL);
            videoview.setMediaController(mediacontroller);
            videoview.setVideoURI(video);

        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }

        videoview.requestFocus();
        videoview.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            // Close the progress bar and play the video
            public void onPrepared(MediaPlayer mp) {
                pDialog.dismiss();
                videoview.start();
            }
        });

    }
    private void showdeletedialog() {


        AlertDialog.Builder alertDialog = new AlertDialog.Builder(Videoshowing.this);
        alertDialog.setTitle("Delete Album");
        alertDialog.setMessage("Are you sure you want to delete this video ?");
        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                parsingfordeletevideo.parsingfordeletevid(Videoshowing.this, parsingformyvideos.id.get(a));
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
