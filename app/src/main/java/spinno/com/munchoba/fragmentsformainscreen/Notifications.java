package spinno.com.munchoba.fragmentsformainscreen;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import spinno.com.munchoba.MainActivity;
import spinno.com.munchoba.R;


public class Notifications extends Fragment {
        LinearLayout chat,alerts;
    View chatview,alertview;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);

        View v = inflater.inflate(R.layout.activity_notifications, container, false);
        chat=(LinearLayout)v.findViewById(R.id.chat);
        alerts=(LinearLayout)v.findViewById(R.id.alerts);
        chatview=(View)v.findViewById(R.id.weightview);
        alertview=(View)v.findViewById(R.id.weightview2);

        if(MainActivity.croppedimage==31){
            changeFragment(new chatfrag(), "chat");
            viewvisibility(chatview);
        }
        else {
            changeFragment(new alertfrag(), "alert");
            viewvisibility(alertview);
        }
        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeFragment(new chatfrag(), "chat");
                viewvisibility(chatview);

            }
        });
        alerts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeFragment(new alertfrag(), "alert");
                viewvisibility(alertview);
            }
        });
        return v;
    }

    private void changeFragment(Fragment fm, String exercise) {

        getChildFragmentManager()
                .beginTransaction()
                .replace(R.id.main_container2, fm, exercise)
                .commit();
    }

    private void viewvisibility(View viewline) {

        chatview.setVisibility(View.INVISIBLE);
        alertview.setVisibility(View.INVISIBLE);
        viewline.setVisibility(View.VISIBLE);

    }
   /* public static Exercise_Diary newInstance(String text) {

        Exercise_Diary f = new Exercise_Diary();

        Bundle b = new Bundle();
        b.putString("msg", text);
        f.setArguments(b);
        return f;
    }*/

}
