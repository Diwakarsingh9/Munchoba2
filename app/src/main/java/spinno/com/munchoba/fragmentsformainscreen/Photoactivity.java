package spinno.com.munchoba.fragmentsformainscreen;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
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
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

import spinno.com.munchoba.MainActivity;
import spinno.com.munchoba.Photoopenactivity;
import spinno.com.munchoba.R;
import spinno.com.munchoba.parsingforapi.parsingforcreatealbum;
import spinno.com.munchoba.parsingforapi.parsingformyphotos;

public class Photoactivity extends Activity {
    int s[]=new int[]{R.drawable.frgt,R.drawable.frgt2,R.drawable.frgt,R.drawable.frgt2,
            R.drawable.frgt,R.drawable.frgt2,R.drawable.frgt,R.drawable.frgt2,
            R.drawable.frgt,R.drawable.frgt,R.drawable.frgt2,R.drawable.frgt,R.drawable.frgt2};
    public static TextView create, upload;
    public static ProgressBar pb;
    public static GridView gd;
    public static  Imagesadapter adp;
    SharedPreferences prefs;
    public static int count =0;
    public static LinearLayout linealltoadd;
    public static Spinner spforexisting;
    public static String imgDecodableString;
    public static EditText namealbum,locationalbum,descpalbum;
    private static int RESULT_LOAD_IMG = 1;
    private static int spposition = 0;
    private static int sppositionfordialog = 0;
    private String selectedImagePath = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photoactivity);
        prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
         gd =(GridView)findViewById(R.id.grid);
        create =(TextView)findViewById(R.id.create);
        upload =(TextView)findViewById(R.id.upload);
        pb =(ProgressBar)findViewById(R.id.pb);
        parsingformyphotos.parsingforphotos(Photoactivity.this);
       // adp = new Imagesadapter(Photoactivity.this,parsingformyphotos.thumbnailphoto, parsingformyphotos.name);
       // gd.setAdapter(adp);

        gd.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent in = new Intent(Photoactivity.this, Photoopenactivity.class);
                SharedPreferences.Editor edit2 = prefs.edit();
                edit2.putString("albumname", parsingformyphotos.name.get(position));
                edit2.putInt("position", position);
                edit2.commit();
                // Toast.makeText(getApplicationContext(),""+parsingformyphotos.name.get(position)+" "+position,Toast.LENGTH_SHORT).show();
                // in.putExtra("albumname",parsingformyphotos.name.get(position));
                // in.putExtra("position",position);
                startActivity(in);
            }
        });

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showdialogcreate();
            }
        });
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showdialogupload();
            }
        });

    }

    public  void showdialogcreate() {

        final Dialog dialog = new Dialog(Photoactivity.this,android.R.style.Theme_Translucent_NoTitleBar);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window=dialog.getWindow();
        dialog.setCancelable(false);
        window.setGravity(Gravity.CENTER);
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.dialogcreateofphotoalbum);
        ImageView cancel = (ImageView)dialog.findViewById(R.id.cancel);
        Button save = (Button)dialog.findViewById(R.id.save);
        namealbum = (EditText)dialog.findViewById(R.id.namealbum);
        descpalbum = (EditText)dialog.findViewById(R.id.descriptionalbum);
        locationalbum = (EditText)dialog.findViewById(R.id.locationalbum);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parsingforcreatealbum.parsingforcreatealbums(getApplicationContext(), namealbum.getText().toString().trim(), descpalbum.getText().toString());

                //Toast.makeText(getApplicationContext(),"Saved",Toast.LENGTH_SHORT).show();
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

    public  void showdialogupload() {

        final Dialog dialog = new Dialog(Photoactivity.this,android.R.style.Theme_Translucent_NoTitleBar);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window=dialog.getWindow();
        dialog.setCancelable(false);
        window.setGravity(Gravity.CENTER);
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.dialogforuploadphoto);
        ImageView cancel = (ImageView)dialog.findViewById(R.id.cancel);

       Button buttonaddfiles = (Button)dialog.findViewById(R.id.addfiles);
        final EditText edittextforname = (EditText)dialog.findViewById(R.id.edittextforalbumname);
        final TextView addtoexisting = (TextView)dialog.findViewById(R.id.addto);
        spforexisting=(Spinner)dialog.findViewById(R.id.spinnerforexisting);
        ArrayAdapter adp22 = new ArrayAdapter(getApplicationContext(),R.layout.layoutforspinnerforalbumnames,R.id.kljjjjjjj,parsingformyphotos.name);
        spforexisting.setAdapter(adp22);
        spforexisting.setVisibility(View.GONE);

        edittextforname.setVisibility(View.VISIBLE);
        addtoexisting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count == 0) {
                    count = 1;

                    addtoexisting.setText("Create new album");
                    spforexisting.setVisibility(View.VISIBLE);
                    edittextforname.setVisibility(View.GONE);
                } else {
                    count = 0;
                    //sppositionfordialog=0;
                    addtoexisting.setText("Add to existing album");
                    spforexisting.setVisibility(View.GONE);
                    edittextforname.setVisibility(View.VISIBLE);
                }

            }
        });
        spforexisting.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sppositionfordialog=position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                sppositionfordialog=0;
            }
        });

        buttonaddfiles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (count == 0) {
                    if (edittextforname.getText().toString().trim().equals("")) {
                        Toast.makeText(getApplicationContext(), "Please Fill required fields", Toast.LENGTH_SHORT).show();
                    } else {
                        parsingforcreatealbum.parsingforcreatealbums(getApplicationContext(), edittextforname.getText().toString(), " ");
                        //showdialogforfiles();
                        sppositionfordialog=0;
                        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        // Start the Intent
                        startActivityForResult(galleryIntent, RESULT_LOAD_IMG);
                        dialog.dismiss();
                    }
                }
                else{
                    Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    // Start the Intent
                    startActivityForResult(galleryIntent, RESULT_LOAD_IMG);

                    count=0;
                    addtoexisting.setText("Add to existing album");
                    spforexisting.setVisibility(View.GONE);
                    edittextforname.setVisibility(View.VISIBLE);
                    dialog.dismiss();
                }


            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                count=0;
                sppositionfordialog=0;
                addtoexisting.setText("Add to existing album");
                spforexisting.setVisibility(View.GONE);
                edittextforname.setVisibility(View.VISIBLE);
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void showdialogforfiles(String imgDecodableString) {
        //Toast.makeText(Photoactivity.this, "Yet to be done",Toast.LENGTH_LONG).show();

        final Dialog dialog = new Dialog(Photoactivity.this,android.R.style.Theme_Translucent_NoTitleBar);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window=dialog.getWindow();
        dialog.setCancelable(false);
        window.setGravity(Gravity.CENTER);
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.dialogforuploadingphotos);
        ImageView cancel = (ImageView)dialog.findViewById(R.id.cancel);
        ImageView imgtoupload = (ImageView)dialog.findViewById(R.id.imgtoupload);
        Button upload = (Button)dialog.findViewById(R.id.upload);
        Spinner spforalbum=(Spinner)dialog.findViewById(R.id.spforselectingalbum);
        ArrayAdapter adp22 = new ArrayAdapter(getApplicationContext(),R.layout.layoutforspinnerforalbumnames,R.id.kljjjjjjj,parsingformyphotos.name);
        spforalbum.setAdapter(adp22);
        imgtoupload.setImageBitmap(BitmapFactory
                .decodeFile(imgDecodableString));
        imgtoupload.setScaleType(ImageView.ScaleType.FIT_XY);
        spforalbum.setSelection(sppositionfordialog);
        spforalbum.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spposition=position;

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                spposition=sppositionfordialog;
            }
        });
        upload.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(Photoactivity.this);
                String id2 = prefs.getString("id", null);
                id2=id2.trim();
                String albumiddd= parsingformyphotos.id.get(spposition);
                AsyncAddPhotoupload(id2,albumiddd);
                dialog.dismiss();

            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                sppositionfordialog=0;
                spposition=0;
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            //Toast.makeText(getActivity(),""+requestCode+" "+resultCode,Toast.LENGTH_SHORT).show();
            switch(requestCode){

                case 1:
                    if (requestCode == RESULT_LOAD_IMG && resultCode != 0
                            && null != data) {
                        // Get the Image from data

                        Uri selectedImageUri = data.getData();
                        selectedImagePath = getPath(selectedImageUri);
                        String[] filePathColumn = {MediaStore.Images.Media.DATA};

                        // Get the cursor
                        Cursor cursor = Photoactivity.this.getContentResolver().query(selectedImageUri,
                                filePathColumn, null, null, null);
                        // Move to first row
                        cursor.moveToFirst();

                        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                   imgDecodableString = cursor.getString(columnIndex);
                        cursor.close();
                        //img.setImageResource(android.R.color.transparent);
                        // Set the Image in ImageView after decoding the String
                       /* img.setImageBitmap(BitmapFactory
                                .decodeFile(imgDecodableString));
                        img.setScaleType(ImageView.ScaleType.FIT_XY);*/
                        showdialogforfiles(imgDecodableString);

                    }
                default:{
                    //Toast.makeText(getActivity(), "You haven't picked Image",Toast.LENGTH_LONG).show();
                }
            }

            // When an Image is picked
            // Toast.makeText(getActivity(),""+requestCode+" "+resultCode,Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            Toast.makeText(Photoactivity.this, "Something went wrong"+e, Toast.LENGTH_LONG)
                    .show();
        }

    }
    public String getPath(Uri uri) {

        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = cursor
                .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);

    }
  protected void AsyncAddPhotoupload(String id2, String albumiddd) {


        final ProgressDialog pDialog = new ProgressDialog(Photoactivity.this);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
        pDialog.show();

        RequestParams params = new RequestParams();



        if (!selectedImagePath.equalsIgnoreCase("")) {

            try {
                Toast.makeText(Photoactivity.this,""+selectedImagePath,Toast.LENGTH_SHORT).show();
                params.put("user_id", "535");
                params.put(" albumid","60");
                params.put("image", new File(selectedImagePath));
                Toast.makeText(getApplicationContext(), ""+albumiddd, Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                // TODO: handle exception
                Toast.makeText(getApplicationContext(), "file not found", Toast.LENGTH_SHORT).show();

            }
        }


        AsyncHttpClient client = new AsyncHttpClient();

        client.post("http://munchoba.com/API/upload_photo.php", params,
                new AsyncHttpResponseHandler() {

                    @Override
                    public void onSuccess(int statusCode,
                                          org.apache.http.Header[] headers,
                                          byte[] responseBody) {
                        // TODO Auto-generated method stub
                        // Toast.makeText(getApplicationContext(),
                        // new String(responseBody), 1).show();
                        if (pDialog.isShowing()) {
                            pDialog.dismiss();
                        }

                        String response = new String(responseBody);
                        Log.d("Response", response);

                        //Toast.makeText(getApplicationContext(), ""+response, Toast.LENGTH_SHORT).show();


                        try {



                            JSONObject jsonObj = new JSONObject(response);

                            String result = jsonObj.getString("result");
                            String status = jsonObj.getString("status");
                            Toast.makeText(getApplicationContext(), ""+result, Toast.LENGTH_SHORT).show();
                            if(status.equals("success")){
                                parsingformyphotos.parsingforphotos(Photoactivity.this);
                            }

                            // Getting JSON Array node
                            Log.i("result ki ", "" + status);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }




                    }

                    @Override
                    public void onFailure(int arg0,
                                          org.apache.http.Header[] arg1, byte[] arg2,
                                          Throwable arg3) {
                        // TODO Auto-generated method stub
                        if (pDialog.isShowing()) {
                            pDialog.dismiss();
                        }

                        Log.d("Status", "Failure");
                    }

                });
    }

}
