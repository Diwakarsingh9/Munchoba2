package spinno.com.munchoba.fragmentsformainscreen;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import spinno.com.munchoba.Adapterclasses.Videosadapter;
import spinno.com.munchoba.Photoopenactivity;
import spinno.com.munchoba.R;
import spinno.com.munchoba.Videoopenactivity;
import spinno.com.munchoba.Videoshowing;
import spinno.com.munchoba.library.src.com.taig.pmc.PopupMenuCompat;
import spinno.com.munchoba.parsingforapi.parsingformyphotos;
import spinno.com.munchoba.parsingforapi.parsingformyvideos;
import spinno.com.munchoba.parsingforapi.parsingforuploadvideofromlink;

public class Videosactivity extends Activity {
    int s[]=new int[]{R.drawable.video1,R.drawable.video1,R.drawable.video1,R.drawable.video1,
            R.drawable.video1,R.drawable.video1,R.drawable.video1,R.drawable.video1,
            R.drawable.video1,R.drawable.video1,R.drawable.video1,R.drawable.video1,R.drawable.video1};
    ImageView filter, filter2;
    TextView add;
    String videonametype = "youtube";
   public static Videosadapter adp;
    public static ListView gd;
    public static ProgressBar pb,pbdialog;
    public static int county=0;
    public static String spvalue[]={"Please select Category","General"};
    public static String videovalue[]={"Youtube","Vimeo"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videosactivity);

       gd =(ListView)findViewById(R.id.grid2);
      filter=(ImageView)findViewById(R.id.filter);
        filter2=(ImageView)findViewById(R.id.filter2);
        add=(TextView)findViewById(R.id.add);
        pb =(ProgressBar)findViewById(R.id.pb);
        parsingformyvideos.parsingforvideos(Videosactivity.this);
        //Imagesadapter adp = new Imagesadapter(Videosactivity.this, parsingformyphotos.thumbnail22, parsingformyphotos.name);
       // gd.setAdapter(adp);

        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenuCompat menu = PopupMenuCompat.newInstance(Videosactivity.this, v);
                menu.inflate(R.menu.menu3);
                menu.setOnMenuItemClickListener(new PopupMenuCompat.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        Toast.makeText(Videosactivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();

                        return false;
                    }
                });
                menu.show();

            }
        });
        filter2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenuCompat menu = PopupMenuCompat.newInstance(Videosactivity.this, v);
                menu.inflate(R.menu.menu3);
                menu.setOnMenuItemClickListener(new PopupMenuCompat.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        Toast.makeText(Videosactivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();

                        return false;
                    }
                });
                menu.show();

            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showdialogadd();
            }
        });
        gd.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (parsingformyvideos.video_id.get(position) != null && !parsingformyvideos.video_id.get(position).isEmpty() && parsingformyvideos.type.get(position).equals("youtube")){
                    Intent in = new Intent(Videosactivity.this, Videoopenactivity.class);
                    in.putExtra("videoname", parsingformyvideos.title.get(position));
                    in.putExtra("videodescription", parsingformyvideos.description.get(position));
                    in.putExtra("position", position);
                    in.putExtra("Activity","Videos");
                    startActivity(in);
                }
                else  if (parsingformyvideos.video_id.get(position) != null && !parsingformyvideos.video_id.get(position).isEmpty() && parsingformyvideos.type.get(position).equals("vimeo") )
                {
                    Intent in = new Intent(Videosactivity.this, Vimeoactivity.class);
                    in.putExtra("videoname", parsingformyvideos.title.get(position));
                    in.putExtra("videodescription", parsingformyvideos.description.get(position));
                    in.putExtra("position", position);
                    startActivity(in);

                }
                else
                {
                    Intent in = new Intent(Videosactivity.this, Videoshowing.class);
                    in.putExtra("videoname", parsingformyvideos.title.get(position));
                    in.putExtra("videodescription", parsingformyvideos.description.get(position));
                    in.putExtra("position", position);
                    startActivity(in);

                }

            }
        });


    }
    public  void showdialogadd() {

        final Dialog dialog = new Dialog(Videosactivity.this,android.R.style.Theme_Translucent_NoTitleBar);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window=dialog.getWindow();
        dialog.setCancelable(false);
        window.setGravity(Gravity.CENTER);
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.dialogforuploadvideo);
        ImageView cancel = (ImageView)dialog.findViewById(R.id.cancel);
        ImageView global = (ImageView)dialog.findViewById(R.id.global);
        ImageView drop = (ImageView)dialog.findViewById(R.id.drop);
        Button upload = (Button)dialog.findViewById(R.id.upload);
        pbdialog = (ProgressBar)dialog.findViewById(R.id.pbdialog);
        final TextView link =(TextView)dialog.findViewById(R.id.textView44);
        final TextView uploadvideo =(TextView)dialog.findViewById(R.id.textView46);
        final TextView linkgrey =(TextView)dialog.findViewById(R.id.textView442);
        final TextView uploadvideogrey =(TextView)dialog.findViewById(R.id.textView478);
        final EditText videourl = (EditText)dialog.findViewById(R.id.editText1011);
        final EditText title = (EditText)dialog.findViewById(R.id.editTexttitle);
        final EditText descp = (EditText)dialog.findViewById(R.id.editTextDescp);
        Spinner category = (Spinner)dialog.findViewById(R.id.spinner6);
        final Spinner videoname = (Spinner)dialog.findViewById(R.id.spinner62);
        ArrayAdapter adp22 = new ArrayAdapter(getApplicationContext(),R.layout.layoutforspinnerforalbumnames,R.id.kljjjjjjj,spvalue);
        category.setAdapter(adp22);
        ArrayAdapter adp223 = new ArrayAdapter(getApplicationContext(),R.layout.layoutforspinnerforalbumnames,R.id.kljjjjjjj,videovalue);
        videoname.setAdapter(adp223);
        final LinearLayout linkll =(LinearLayout)dialog.findViewById(R.id.linkll);
        final LinearLayout uploadll =(LinearLayout)dialog.findViewById(R.id.uploadll);
        county =0;
        videoname.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position==1){
                    videonametype="vimeo";
                }
                else{
                    videonametype="youtube";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                videonametype="youtube";
            }
        });
        linkll.setVisibility(View.VISIBLE);
        uploadll.setVisibility(View.GONE);
        uploadvideogrey.setVisibility(View.VISIBLE);
        uploadvideo.setVisibility(View.GONE);
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (county == 0) {

                    Toast.makeText(getApplicationContext(), "Uploading Video from url!!!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Uploading Video from phone!!!", Toast.LENGTH_SHORT).show();
                }

                dialog.dismiss();
            }
        });
        linkgrey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                county =0;
               linkll.setVisibility(View.VISIBLE);
                uploadll.setVisibility(View.GONE);
                uploadvideogrey.setVisibility(View.VISIBLE);
                link.setVisibility(View.VISIBLE);
                uploadvideo.setVisibility(View.GONE);
            }
        });
        uploadvideogrey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                county =1;
                linkll.setVisibility(View.GONE);
                uploadll.setVisibility(View.VISIBLE);
                linkgrey.setVisibility(View.VISIBLE);
                uploadvideo.setVisibility(View.VISIBLE);
                link.setVisibility(View.GONE);
            }
        });
        global.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenuCompat menu = PopupMenuCompat.newInstance(Videosactivity.this, v);
                menu.inflate(R.menu.menu4);
                menu.setOnMenuItemClickListener(new PopupMenuCompat.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        Toast.makeText(Videosactivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();

                        return false;
                    }
                });
                menu.show();

            }
        });
        drop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenuCompat menu = PopupMenuCompat.newInstance(Videosactivity.this, v);
                menu.inflate(R.menu.menu4);
                menu.setOnMenuItemClickListener(new PopupMenuCompat.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        Toast.makeText(Videosactivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();

                        return false;
                    }
                });
                menu.show();

            }
        });
        upload.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
            parsingforuploadvideofromlink.parsingforuploadvideo(Videosactivity.this,videourl.getText().toString().trim(),videonametype,title.getText().toString().trim(),descp.getText().toString().trim());
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

        dialog.show();
    }

}
