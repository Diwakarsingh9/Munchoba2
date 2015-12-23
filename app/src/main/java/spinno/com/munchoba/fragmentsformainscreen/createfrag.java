package spinno.com.munchoba.fragmentsformainscreen;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import spinno.com.munchoba.R;

/**
 * Created by saifi45 on 7/13/2015.
 */
public class createfrag extends Fragment {

    TextView recpt;
    Button send;
    EditText tose, receiptant,subject, message;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);

        View v = inflater.inflate(R.layout.createfrag, container, false);
        recpt=(TextView)v.findViewById(R.id.torec);
        send=(Button)v.findViewById(R.id.send);
        recpt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showrecipientdialog();
            }
        });
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Sent", Toast.LENGTH_SHORT).show();
               // dialog.dismiss();
            }
        });
        return v;
    }
    public static createfrag newInstance(String text) {

        createfrag f = new createfrag();

        Bundle b = new Bundle();
        b.putString("msg", text);
        f.setArguments(b);
        return f;
    }

    public  void showrecipientdialog() {

        final Dialog dialog = new Dialog(getActivity(),android.R.style.Theme_Translucent_NoTitleBar);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window=dialog.getWindow();
        dialog.setCancelable(false);
        window.setGravity(Gravity.CENTER);
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.dialogforaddfriends);
        ImageView cancel = (ImageView)dialog.findViewById(R.id.cancel);
        Button select = (Button)dialog.findViewById(R.id.select);

        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Selected", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                dialog.dismiss();
            }
        });

        dialog.show();
    }
}
