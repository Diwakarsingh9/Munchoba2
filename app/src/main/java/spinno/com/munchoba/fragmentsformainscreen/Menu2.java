package spinno.com.munchoba.fragmentsformainscreen;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;



import com.facebook.AccessToken;
import com.facebook.login.LoginManager;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import spinno.com.munchoba.AlbumStorageDirFactory;
import spinno.com.munchoba.BaseAlbumDirFactory;
import spinno.com.munchoba.FroyoAlbumDirFactory;
import spinno.com.munchoba.Loggedin;
import spinno.com.munchoba.MainActivity;
import spinno.com.munchoba.R;
import spinno.com.munchoba.Three_FRAGMENT;
import spinno.com.munchoba.library.src.com.taig.pmc.Pagesactivity;


public class Menu2 extends Fragment {
    LinearLayout mygoals,fitnessdetails,photos,videos,pages,logout,viewprofilell;
    ImageView dp;
    TextView tvname;
    Button cancel, takephoto, deletephoto, choosephoto;
    private static int RESULT_LOAD_IMG = 1;
    TextView editprofile, viewprofile;
    public  static Bitmap bitmap1;

    private String mCurrentPhotoPath;
    public AlbumStorageDirFactory mAlbumStorageDirFactory = null;
    private static final String JPEG_FILE_PREFIX = "IMG_";
    private static final String JPEG_FILE_SUFFIX = ".jpg";
    public static String imgDecodableString;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);

        View v = inflater.inflate(R.layout.activity_menu2, container, false);
        mygoals=(LinearLayout)v.findViewById(R.id.mygoals);
        fitnessdetails=(LinearLayout)v.findViewById(R.id.fitnessdetails);
        photos=(LinearLayout)v.findViewById(R.id.photoslayout);
        viewprofilell=(LinearLayout)v.findViewById(R.id.layout223322);
        videos=(LinearLayout)v.findViewById(R.id.videosla);
        tvname=(TextView)v.findViewById(R.id.nametv);
        pages=(LinearLayout)v.findViewById(R.id.pagesla);
        editprofile=(TextView)v.findViewById(R.id.editprofile);
        viewprofile=(TextView)v.findViewById(R.id.viewprof);
        logout=(LinearLayout)v.findViewById(R.id.logout);
        dp=(ImageView)v.findViewById(R.id.profilepic);
       // SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());

        if(MainActivity.croppedimage==2){

            dp.setImageBitmap(Loggedin.bitmap2);

                    }

        if(MainActivity.intentcaled==2){



            if(MainActivity.intentproceed==1) {
                dp.setImageBitmap(BitmapFactory
                        .decodeFile(Three_FRAGMENT.imgDecodableString));
               // dp.setScaleType(ImageView.ScaleType.FIT_CENTER);
            }
            else{
                dp.setImageBitmap(Three_FRAGMENT.bitmap1);
                //dp.setScaleType(ImageView.ScaleType.FIT_CENTER);
            }
        }
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String profilename = prefs.getString("name", null);
        String profimg = prefs.getString("imagePreferance", "a");
        if(profimg.equals("a")){
            dp.setImageResource(R.drawable.userdefault);
        }
        else{
            dp.setImageBitmap(decodeBase64(profimg));
        }
        Log.e("", "prof name" + profilename);
        //Toast.makeText(getActivity(),""+profilename,Toast.LENGTH_SHORT).show();
        tvname.setText("" + profilename);

        editprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), Editprofile.class);
                startActivity(in);
            }
        });
        viewprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), Viewprofileactivity.class);
                startActivity(in);
            }
        });
        viewprofilell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), Viewprofileactivity.class);
                startActivity(in);
            }
        });
        dp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showEditpicdialog();
            }
        });
        mygoals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), Mygoalsactivity.class);
                startActivity(in);
            }
        });
        photos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), Photoactivity.class);
                startActivity(in);
            }
        });
        videos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), Videosactivity.class);
                startActivity(in);
            }
        });
        pages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), Pagesactivity.class);
                startActivity(in);
            }
        });

        fitnessdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), Fitnessdetailsactivity.class);
                startActivity(in);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showExitDialouge();
            }
        });
        return v;
    }
    public static Bitmap decodeBase64(String input) {
        byte[] decodedByte = Base64.decode(input, 0);
        return BitmapFactory
                .decodeByteArray(decodedByte, 0, decodedByte.length);
    }
    private void showExitDialouge() {


        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
        alertDialog.setTitle("Log Out");
        alertDialog.setMessage("Are you sure you want to logout ?");
        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
                SharedPreferences.Editor edit2 = prefs.edit();
                edit2.putBoolean("pref_previously_started", Boolean.FALSE);
                edit2.commit();

                //MyApplication.getInstance();
                //MyApplication.getInstance().clearApplicationData();
                //clearApplicationData();
                AccessToken accessToken = AccessToken.getCurrentAccessToken();
                if(accessToken != null){
                    LoginManager.getInstance().logOut();
                }
                Intent in = new Intent(getActivity(),MainActivity.class);
                startActivity(in);
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

    public  void showEditpicdialog() {

        final Dialog dialog = new Dialog(getActivity(),android.R.style.Theme_Translucent_NoTitleBar);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window=dialog.getWindow();
        dialog.setCancelable(false);
        window.setGravity(Gravity.CENTER);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.editpicdialog);
        cancel = (Button)dialog.findViewById(R.id.button1);
        takephoto = (Button)dialog.findViewById(R.id.take);
        choosephoto = (Button)dialog.findViewById(R.id.choose);
        deletephoto = (Button)dialog.findViewById(R.id.delete);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO) {
            mAlbumStorageDirFactory = new FroyoAlbumDirFactory();
        } else {
            mAlbumStorageDirFactory = new BaseAlbumDirFactory();
        }
        takephoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                dispatchTakePictureIntent(11);


            }
        });

        deletephoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dp.setImageResource(R.drawable.userdefault);
                SharedPreferences prefs2 = PreferenceManager.getDefaultSharedPreferences(getActivity());
                SharedPreferences.Editor edit2 = prefs2.edit();
                Bitmap largeIcon = BitmapFactory.decodeResource(getResources(), R.drawable.userdefault);

                // edit2.putBitmap("imagedp", profPict);
                edit2.putString("imagePreferance", encodeTobase64(largeIcon));
                edit2.commit();
                dialog.dismiss();

            }
        });
        choosephoto.setOnClickListener(new View.OnClickListener() {
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

       dp.setImageBitmap(bitmap1);
        SharedPreferences prefs2 = PreferenceManager.getDefaultSharedPreferences(getActivity());
        SharedPreferences.Editor edit2 = prefs2.edit();

        // edit2.putBitmap("imagedp", profPict);
        edit2.putString("imagePreferance", encodeTobase64(bitmap1));
        edit2.commit();
       // dp.setScaleType(ImageView.ScaleType.FIT_CENTER);

       		/* Associate the Bitmap to the ImageView */

    }
    public static String encodeTobase64(Bitmap image) {
        Bitmap immage = image;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        immage.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();
        String imageEncoded = Base64.encodeToString(b, Base64.DEFAULT);

        Log.d("Image Log:", imageEncoded);
        return imageEncoded;
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



                        dp.setImageBitmap(BitmapFactory
                                .decodeFile(imgDecodableString));
                        SharedPreferences prefs2 = PreferenceManager.getDefaultSharedPreferences(getActivity());
                        SharedPreferences.Editor edit2 = prefs2.edit();

                        // edit2.putBitmap("imagedp", profPict);
                        edit2.putString("imagePreferance", encodeTobase64(BitmapFactory
                                .decodeFile(imgDecodableString)));
                        edit2.commit();
                       // dp.setScaleType(ImageView.ScaleType.FIT_CENTER);

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



}
