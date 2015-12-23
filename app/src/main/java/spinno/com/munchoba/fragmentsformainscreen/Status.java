package spinno.com.munchoba.fragmentsformainscreen;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import spinno.com.munchoba.Adapterclasses.Removefriendsadapter;
import spinno.com.munchoba.AlbumStorageDirFactory;
import spinno.com.munchoba.BaseAlbumDirFactory;
import spinno.com.munchoba.FroyoAlbumDirFactory;
import spinno.com.munchoba.Loggedin;
import spinno.com.munchoba.MainActivity;
import spinno.com.munchoba.R;
import spinno.com.munchoba.library.src.com.taig.pmc.Tagactivity;
import spinno.com.munchoba.library.src.com.taig.pmc.Tagactivityadapter;
import spinno.com.munchoba.parsingforapi.parsingforstatus;


public class Status extends Fragment {
    public  static   Button post;
    public static TextView emoticon, tagfriends, place;
   public static LinearLayout defaultlayout, cameralayout, Statuslayout,emotionlayout,atlayout,withlayout;
    private static int RESULT_LOAD_IMG = 1;
    Button cancel;
    public static ListView lvr;
    public static  Removefriendsadapter adpr;
    public static ProgressBar pb;
    public  static ArrayList<Integer> smileyimagecopy = new ArrayList<>();
    SharedPreferences prefs;
    public  static LinearLayout posttostatus;
    public  static Bitmap bitmap1;
    public static EditText title;
    public static  ImageView smileyimage;
    private String mCurrentPhotoPath;
    public AlbumStorageDirFactory mAlbumStorageDirFactory = null;
    private static final String JPEG_FILE_PREFIX = "IMG_";
    private static final String JPEG_FILE_SUFFIX = ".jpg";
    public static String imgDecodableString;
    public static ImageView camera, tag, link, smiley, location,imgperson,img;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);

        View v = inflater.inflate(R.layout.activity_status, container, false);
        prefs= PreferenceManager.getDefaultSharedPreferences(getActivity());
        pb =(ProgressBar) v.findViewById(R.id.pb);

        smileyimagecopy.add(R.drawable.wwhite);
        camera=(ImageView)v.findViewById(R.id.camera);
        title=(EditText)v.findViewById(R.id.eidttextforstatus);
        smileyimage=(ImageView)v.findViewById(R.id.smileyimage);
        tag=(ImageView)v.findViewById(R.id.tag);
        link=(ImageView)v.findViewById(R.id.link);
        emoticon=(TextView)v.findViewById(R.id.emotion2);
        tagfriends=(TextView)v.findViewById(R.id.with2);
        place=(TextView)v.findViewById(R.id.atplace2);
        posttostatus=(LinearLayout)v.findViewById(R.id.layoutforshow);
        defaultlayout=(LinearLayout)v.findViewById(R.id.defaultlayout);
        Statuslayout=(LinearLayout)v.findViewById(R.id.statuslayout);
        emotionlayout=(LinearLayout)v.findViewById(R.id.emotion);
        atlayout=(LinearLayout)v.findViewById(R.id.atplace);
        withlayout=(LinearLayout)v.findViewById(R.id.with);
        cameralayout=(LinearLayout)v.findViewById(R.id.cameralayout);
        img=(ImageView)v.findViewById(R.id.img);
        smiley=(ImageView)v.findViewById(R.id.emoticon);
        location=(ImageView)v.findViewById(R.id.location);
        imgperson=(ImageView)v.findViewById(R.id.imgperson);
        post=(Button)v.findViewById(R.id.postbtn);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO) {
            mAlbumStorageDirFactory = new FroyoAlbumDirFactory();
        } else {
            mAlbumStorageDirFactory = new BaseAlbumDirFactory();
        }
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showcamerdialog();
            }
        });
        withlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showremovefriends();
            }
        });
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                defaultlayout.setVisibility(View.VISIBLE);
                cameralayout.setVisibility(View.GONE);
                emotionlayout.setVisibility(View.GONE);
                withlayout.setVisibility(View.GONE);
                atlayout.setVisibility(View.GONE);
                Calendar cal = Calendar.getInstance();
                int minute = cal.get(Calendar.MINUTE);
                //24 hour format
                int hourofday = cal.get(Calendar.HOUR_OF_DAY);
              parsingforstatus.parsingforstatus(getActivity(), title.getText().toString(), emoticon.getText().toString(), Tagactivityadapter.tagfriendslist, place.getText().toString(),"null","null",minute,hourofday,"0");

            }
        });

        tag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent in = new Intent(getActivity(),Tagactivity.class);
                startActivity(in);
            }
        });
        link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in = new Intent(getActivity(),Linkactivity.class);
                startActivity(in);
            }
        });
        smiley.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(),Smileyactivity.class);
                startActivity(in);
            }
        });
        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), Locationactivity.class);
                startActivity(in);
            }
        });

        return v;
    }

    public  void showcamerdialog() {

        final Dialog dialog = new Dialog(getActivity(),android.R.style.Theme_Translucent_NoTitleBar);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window=dialog.getWindow();
        dialog.setCancelable(false);
        window.setGravity(Gravity.CENTER);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.camerdialog);
        cancel = (Button)dialog.findViewById(R.id.button1);
        LinearLayout button = (LinearLayout)dialog.findViewById(R.id.layout12);
        LinearLayout button1 = (LinearLayout)dialog.findViewById(R.id.layout13);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                dispatchTakePictureIntent(11);


            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                // Start the Intent
                startActivityForResult(galleryIntent, RESULT_LOAD_IMG);

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
    public  void showremovefriends() {

        final Dialog dialog = new Dialog(getActivity(),android.R.style.Theme_Translucent_NoTitleBar);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window=dialog.getWindow();
        dialog.setCancelable(false);
        window.setGravity(Gravity.CENTER);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.dialogforremovetagfriends);
        ImageView close = (ImageView)dialog.findViewById(R.id.closeoopr);
        TextView done=(TextView)dialog.findViewById(R.id.done);
         lvr=(ListView)dialog.findViewById(R.id.lsst);

        adpr = new Removefriendsadapter(getActivity(),Tagactivityadapter.tagfriendslist);
        lvr.setAdapter(adpr);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] taglist = new String[Removefriendsadapter.name.size()];
                taglist = Removefriendsadapter.name.toArray(taglist);
                if(taglist.length!=(0)) {
                    for (int i = 0; i < 2; i++) {
                        if (taglist.length > 2) {
                            Status.tagfriends.setText("with " + taglist[0] + " & others");
                        } else if (taglist.length == 2) {
                            Status.tagfriends.setText("with " + taglist[0] + " & other");
                        } else {
                            Status.tagfriends.setText("with " + taglist[0]);
                        }
                    }
                    Status.defaultlayout.setVisibility(View.VISIBLE);
                    Status.Statuslayout.setVisibility(View.VISIBLE);
                    Status.withlayout.setVisibility(View.VISIBLE);
                    ChangeVisibiltyoflayout();
                    dialog.dismiss();
                }
                else{

                    Status.tagfriends.setText("with");
                    Status.withlayout.setVisibility(View.INVISIBLE);
                    dialog.dismiss();
                }
            }
        });

        close.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                dialog.dismiss();
            }
        });

        dialog.show();
    }
    private void ChangeVisibiltyoflayout() {
        if(Status.emoticon.getText().toString().equals("Emotion")&&Status.place.getText().toString().equals("at")){
            Status.emotionlayout.setVisibility(View.INVISIBLE);
            Status.atlayout.setVisibility(View.INVISIBLE);
        }
        else if(!Status.emoticon.getText().toString().equals("Emotion")&&Status.place.getText().toString().equals("at")){
            Status.emotionlayout.setVisibility(View.VISIBLE);
            Status.atlayout.setVisibility(View.INVISIBLE);
        }
        else if(Status.emoticon.getText().toString().equals("Emotion")&&!Status.place.getText().toString().equals("at")){
            Status.emotionlayout.setVisibility(View.INVISIBLE);
            Status.atlayout.setVisibility(View.VISIBLE);
        }
        else{
            Status.emotionlayout.setVisibility(View.VISIBLE);
            Status.atlayout.setVisibility(View.VISIBLE);
        }
    }
    private void dispatchTakePictureIntent(int actionCode) {

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        switch(actionCode) {
            case 11:
                File f = null;

                try {
                    f = setUpPhotoFile();
                    mCurrentPhotoPath = f.getAbsolutePath();
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
                } catch (IOException e) {
                    e.printStackTrace();
                    f = null;
                    mCurrentPhotoPath = null;
                }
                break;

            default:
                break;
        } // switch

        startActivityForResult(takePictureIntent, 11);
    }
    private void galleryAddPic() {
        Intent mediaScanIntent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
        File f = new File(mCurrentPhotoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        getActivity().sendBroadcast(mediaScanIntent);
    }
    private String getAlbumName() {
        return getString(R.string.album_name);
    }
    private void handleBigCameraPhoto() {

        if (mCurrentPhotoPath != null) {
            galleryAddPic();
            setPic();

            mCurrentPhotoPath = null;
        }

    }

    private void setPic() {

		/* There isn't enough memory to open up more than a couple camera photos */
		/* So pre-scale the target bitmap into which the file is decoded */

		/* Get the size of the ImageView */
        int targetW =200;
        int targetH = 300;


		/* Get the size of the image */
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

		/* Figure out which way needs to be reduced less */
        int scaleFactor = 1;
        if ((targetW > 0) || (targetH > 0)) {
            scaleFactor = Math.min(photoW/targetW, photoH/targetH);
        }

		/* Set bitmap options to scale the image decode target */
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

		/* Decode the JPEG file into a Bitmap */
        bitmap1 = BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
        Log.e("bitmap", "" + bitmap1);
        defaultlayout.setVisibility(View.VISIBLE);
        cameralayout.setVisibility(View.VISIBLE);
        img.setImageBitmap(bitmap1);

       		/* Associate the Bitmap to the ImageView */

    }


    private File getAlbumDir() {
        File storageDir = null;

        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {

            storageDir = mAlbumStorageDirFactory.getAlbumStorageDir(getAlbumName());

            if (storageDir != null) {
                if (! storageDir.mkdirs()) {
                    if (! storageDir.exists()){
                        Log.d("CameraSample", "failed to create directory");
                        return null;
                    }
                }
            }

        } else {
            Log.v(getString(R.string.app_name), "External storage is not mounted READ/WRITE.");
        }

        return storageDir;
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = JPEG_FILE_PREFIX + timeStamp + "_";
        File albumF = getAlbumDir();
        File imageF = File.createTempFile(imageFileName, JPEG_FILE_SUFFIX, albumF);
        return imageF;
    }

    private File setUpPhotoFile() throws IOException {

        File f = createImageFile();
        mCurrentPhotoPath = f.getAbsolutePath();

        return f;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            //Toast.makeText(getActivity(),""+requestCode+" "+resultCode,Toast.LENGTH_SHORT).show();
            switch(requestCode){
                case 11:
                    if(resultCode!=0){

                        MainActivity.intentproceed=11;
                        handleBigCameraPhoto();
                    }
                case 1:
                    if (requestCode == RESULT_LOAD_IMG && resultCode != 0
                            && null != data) {
                        // Get the Image from data
                        MainActivity.intentproceed=1;
                        Uri selectedImage = data.getData();
                        String[] filePathColumn = {MediaStore.Images.Media.DATA};

                        // Get the cursor
                        Cursor cursor = getActivity().getContentResolver().query(selectedImage,
                                filePathColumn, null, null, null);
                        // Move to first row
                        cursor.moveToFirst();

                        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                        imgDecodableString = cursor.getString(columnIndex);
                        cursor.close();
                        //img.setImageResource(android.R.color.transparent);
                        // Set the Image in ImageView after decoding the String

                        defaultlayout.setVisibility(View.VISIBLE);
                        cameralayout.setVisibility(View.VISIBLE);
                        img.setImageBitmap(BitmapFactory
                                .decodeFile(imgDecodableString));
                        img.setScaleType(ImageView.ScaleType.FIT_XY);

                    }
                default:{
                    // Toast.makeText(getActivity(), "You haven't picked Image",Toast.LENGTH_LONG).show();
                }
            }

            // When an Image is picked
            // Toast.makeText(getActivity(),""+requestCode+" "+resultCode,Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_LONG)
                    .show();
        }

    }
    public static Status newInstance(String text) {

        Status f = new Status();
        Bundle b = new Bundle();
        b.putString("msg", text);
        f.setArguments(b);
        return f;
    }
    @Override
    public void onResume() {
        super.onResume();

            Status.smileyimage.setImageResource(Status.smileyimagecopy.get(0));


        //loaddata();
    }

    @Override
    public void onPause() {
        super.onPause();


       /*smileyimage.buildDrawingCache();
       Bitmap bm = smileyimage.getDrawingCache();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] b = baos.toByteArray();
        String encodedImage = Base64.encodeToString(b, Base64.DEFAULT);
        SharedPreferences.Editor edit=prefs.edit();
        edit.putString("image_data",encodedImage);
        edit.commit();*/


    }
}
