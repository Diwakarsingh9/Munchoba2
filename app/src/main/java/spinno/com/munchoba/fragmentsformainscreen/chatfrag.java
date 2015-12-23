package spinno.com.munchoba.fragmentsformainscreen;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import spinno.com.munchoba.MainActivity;
import spinno.com.munchoba.R;

/**
 * Created by saifi45 on 7/13/2015.
 */
public class chatfrag extends Fragment {

    LinearLayout inbox,sent,create;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                                                   Bundle savedInstanceState)  {
    super.onCreate(savedInstanceState);

    View v = inflater.inflate(R.layout.chatfrag, container, false);
        inbox= (LinearLayout) v.findViewById(R.id.inbox);
        sent= (LinearLayout) v.findViewById(R.id.sent);
        create= (LinearLayout) v.findViewById(R.id.create);

        if(MainActivity.croppedimage==31){
            changeFragment(new createfrag(), "create");
        }
        else {
        changeFragment(new inboxfrag(), "inbox");
        }

        inbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeFragment(new inboxfrag(), "inbox");


            }
        });
        sent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeFragment(new sentfrag(), "sent");

            }
        });
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeFragment(new createfrag(), "create");

            }
        });
    return v;
}
    private void changeFragment(Fragment fm, String exercise) {

        getChildFragmentManager()
                .beginTransaction()
                .replace(R.id.main_container3, fm, exercise)
                .commit();
    }
    public static chatfrag newInstance(String text) {

        chatfrag f = new chatfrag();

        Bundle b = new Bundle();
        b.putString("msg", text);
        f.setArguments(b);
        return f;
    }
}
