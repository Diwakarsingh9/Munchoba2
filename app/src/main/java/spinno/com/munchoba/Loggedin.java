package spinno.com.munchoba;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.facebook.AccessToken;
import com.facebook.login.LoginManager;

import spinno.com.munchoba.fragmentsformainscreen.Community;
import spinno.com.munchoba.fragmentsformainscreen.Exercise_Diary;
import spinno.com.munchoba.fragmentsformainscreen.Fitnessfeed;
import spinno.com.munchoba.fragmentsformainscreen.Food_Diary;
import spinno.com.munchoba.fragmentsformainscreen.Menu2;
import spinno.com.munchoba.fragmentsformainscreen.Notifications;
import spinno.com.munchoba.fragmentsformainscreen.Status;
import spinno.com.munchoba.fragmentsformainscreen.Weight_Management;

public class Loggedin extends FragmentActivity {

    LinearLayout exercise,food,weight,status,feed,notifications,community,menu2;
    public static Loggedin loggedin;
    public  static int opencreate=0;
    public  static Bitmap bitmap2;


    View fitnessview,communityview,notiview,menuview,statusview,foodview,exerciseview,weightview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loggedin);
        loggedin=Loggedin.this;

        exercise=(LinearLayout)findViewById(R.id.exrcise);
        food=(LinearLayout)findViewById(R.id.food);
        weight=(LinearLayout)findViewById(R.id.weight);
        status=(LinearLayout)findViewById(R.id.Status);
        feed=(LinearLayout)findViewById(R.id.feed);
        notifications=(LinearLayout)findViewById(R.id.notifications);
        community=(LinearLayout)findViewById(R.id.community);
        menu2=(LinearLayout)findViewById(R.id.menu2);
        fitnessview=(View)findViewById(R.id.fitnessview);
        communityview=(View)findViewById(R.id.communityview);
        notiview=(View)findViewById(R.id.notiview);
        menuview=(View)findViewById(R.id.menuview);
        statusview=(View)findViewById(R.id.statusview);
        foodview=(View)findViewById(R.id.foodview);
        exerciseview=(View)findViewById(R.id.exerciseview);
        weightview=(View)findViewById(R.id.weightview);

        if(MainActivity.croppedimage==1){
            changeFragment(new Menu2(), "menu");
            viewvisibility(menuview);
           Intent intent = getIntent();
            bitmap2 = (Bitmap) intent.getParcelableExtra("imagedp");
            MainActivity.croppedimage=2;
        }
        else if(MainActivity.croppedimage==30){
            changeFragment(new Status(), "status");
            viewvisibility(statusview);
            MainActivity.croppedimage=3;
        }
        else if(MainActivity.croppedimage==31){
            changeFragment(new Notifications(), "notifications");
            viewvisibility(notiview);
           // MainActivity.croppedimage=3222;
        }
        else{
            changeFragment(new Fitnessfeed(), "feed");
            viewvisibility(fitnessview);
        }

        if(opencreate==1){
            loggedin.finish();
            opencreate=0;
        }




        exercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeFragment(new Exercise_Diary(), "exercise");
                viewvisibility(exerciseview);

            }
        });
        food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeFragment(new Food_Diary(), "food");
                viewvisibility(foodview);
            }
        });
        status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeFragment(new Status(), "status");
                viewvisibility(statusview);
            }
        });
        weight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeFragment(new Weight_Management(), "food");
                viewvisibility(weightview);
            }
        });
        feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeFragment(new Fitnessfeed(), "Fitness feed");
                viewvisibility(fitnessview);
            }
        });
        notifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeFragment(new Notifications(), "Notifications");
                viewvisibility(notiview);
            }
        });
        community.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeFragment(new Community(), "community");
                viewvisibility(communityview);
            }
        });
        menu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeFragment(new Menu2(), "menu");
                viewvisibility(menuview);
            }
        });

    }

    private void viewvisibility(View viewline) {
        notiview.setVisibility(View.INVISIBLE);
        fitnessview.setVisibility(View.INVISIBLE);
        communityview.setVisibility(View.INVISIBLE);
        menuview.setVisibility(View.INVISIBLE);
        statusview.setVisibility(View.INVISIBLE);
        foodview.setVisibility(View.INVISIBLE);
        exerciseview.setVisibility(View.INVISIBLE);
        weightview.setVisibility(View.INVISIBLE);
        viewline.setVisibility(View.VISIBLE);

    }

    private void changeFragment(Fragment fm, String exercise) {

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_container, fm, exercise)
                .commit();
    }

    @Override
    public void onBackPressed() {

        showExitDialouge();
    }
    private void showExitDialouge() {


        AlertDialog.Builder alertDialog = new AlertDialog.Builder(Loggedin.this);
        alertDialog.setTitle("Exit Munchoba");
        alertDialog.setMessage("Are you sure you want to Exit ?");
        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

                //super.onBackPressed();
                Loggedin.loggedin.finish();
                dialog.dismiss();
            }
        });
        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        alertDialog.show();
    }
}

