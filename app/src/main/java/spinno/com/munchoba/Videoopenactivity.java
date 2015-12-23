package spinno.com.munchoba;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import spinno.com.munchoba.fragmentsformainscreen.Photoimagesadapter;
import spinno.com.munchoba.fragmentsformainscreen.Photosinneractivity;
import spinno.com.munchoba.parsingforapi.parsingfordeletealbum;
import spinno.com.munchoba.parsingforapi.parsingfordeletevideo;
import spinno.com.munchoba.parsingforapi.parsingformyphotos;
import spinno.com.munchoba.parsingforapi.parsingformyvideos;

public class Videoopenactivity  extends YouTubeBaseActivity implements
        YouTubePlayer.OnInitializedListener{
    public static TextView albumname;
    int a;
    String idvideo;
   public static ImageView play,deletalbum;
    public static TextView Description;
    public static Photoimagesadapter adp;
    public static Videoopenactivity php;
    private static final int RECOVERY_DIALOG_REQUEST = 1;
    String b22;
    // YouTube player view
    private YouTubePlayerView youTubeView;
    ProgressDialog pDialog;
    VideoView videoview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videoopenactivity);
        php = this;
        Bundle b = getIntent().getExtras();
        String s =  b.getString("videoname");
        String s2 =  b.getString("videodescription");
        String s3 =  b.getString("Activity");
        if(s3.equals("Videos")){
        a =  b.getInt("position");
        }
        else {
         b22 = b.getString("urlid");
        }
        videoview = (VideoView) findViewById(R.id.myVideo);
        albumname = (TextView) findViewById(R.id.album);
       // play = (ImageView) findViewById(R.id.playvideo);
        Description = (TextView) findViewById(R.id.descptn);
       deletalbum = (ImageView) findViewById(R.id.deletealbum);
        albumname.setText(s);
        Description.setText(s2);

        youTubeView = (YouTubePlayerView) findViewById(R.id.youtube_view);

        if(s3.equals("Videos")) {
            idvideo = parsingformyvideos.video_id.get(a).trim();
            deletalbum.setVisibility(View.VISIBLE);
        }
        else {
            idvideo=b22;
            deletalbum.setVisibility(View.GONE);
        }
            youTubeView.initialize(Config.DEVELOPER_KEY, this);




        // Initializing video player with developer key

       deletalbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showdeletedialog();
            }
        });

       }



    private void showdeletedialog() {


        AlertDialog.Builder alertDialog = new AlertDialog.Builder(Videoopenactivity.this);
        alertDialog.setTitle("Delete Album");
        alertDialog.setMessage("Are you sure you want to delete this video ?");
        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
               parsingfordeletevideo.parsingfordeletevid(Videoopenactivity.this, parsingformyvideos.id.get(a));
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


    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider,
                                        YouTubeInitializationResult errorReason) {
        if (errorReason.isUserRecoverableError()) {
            errorReason.getErrorDialog(this, RECOVERY_DIALOG_REQUEST).show();
        } else {
            String errorMessage = String.format(
                    getString(R.string.error_player), errorReason.toString());
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                        YouTubePlayer player, boolean wasRestored) {
        if (!wasRestored) {



            player.loadVideo(idvideo);

            // Hiding player controls
            player.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RECOVERY_DIALOG_REQUEST) {
            // Retry initialization if user performed a recovery action
            getYouTubePlayerProvider().initialize(Config.DEVELOPER_KEY, this);
        }
    }

    private YouTubePlayer.Provider getYouTubePlayerProvider() {
        return (YouTubePlayerView) findViewById(R.id.youtube_view);
    }
}
