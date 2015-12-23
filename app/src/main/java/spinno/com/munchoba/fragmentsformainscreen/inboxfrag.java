package spinno.com.munchoba.fragmentsformainscreen;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import spinno.com.munchoba.Loggedin;
import spinno.com.munchoba.MainActivity;
import spinno.com.munchoba.R;

/**
 * Created by saifi45 on 7/13/2015.
 */
public class inboxfrag extends Fragment {

    ListView lv;
    String titles[]={"titles 1","title 2","titles 1","title 2","titles 1","title 2","titles 1","title 2","titles 1","title 2","titles 1","title 2","titles 1","title 2","titles 1","title 2"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);

        View v = inflater.inflate(R.layout.inboxfrag, container, false);
        lv=(ListView)v.findViewById(R.id.listViewalert);
        Inboxnotificationadapter innotificationadapter = new Inboxnotificationadapter(getActivity(),titles );
        Log.e("ffff", "adapter comm chla");
        lv.setAdapter(innotificationadapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getActivity(),""+position, Toast.LENGTH_SHORT).show();

                showlayoutforsingle(position);
            }
        });
        return v;
    }
    public static inboxfrag newInstance(String text) {

        inboxfrag f = new inboxfrag();

        Bundle b = new Bundle();
        b.putString("msg", text);
        f.setArguments(b);
        return f;
    }

    public  void showlayoutforsingle(final int position) {

        final Dialog dialog = new Dialog(getActivity(),android.R.style.Theme_Translucent_NoTitleBar);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window=dialog.getWindow();
        dialog.setCancelable(false);
        window.setGravity(Gravity.CENTER);
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.singlelayoutforinbox);
        ImageView cancel = (ImageView)dialog.findViewById(R.id.cancel);
        TextView delete = (TextView)dialog.findViewById(R.id.delete);
        TextView reply = (TextView)dialog.findViewById(R.id.reply);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Deleted !!!", Toast.LENGTH_SHORT).show();

                dialog.dismiss();

            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                dialog.dismiss();
            }
        });
        reply.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                MainActivity.croppedimage=31;
                Intent in = new Intent(getActivity(), Loggedin.class);
                startActivity(in);
                Loggedin.loggedin.finish();

                dialog.dismiss();
            }
        });

        dialog.show();
    }
}
