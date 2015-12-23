package spinno.com.munchoba;

import android.app.Dialog;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Three_FRAGMENT extends Fragment {

        Button btnupload,btnskip,btnsubmit;


    private static int RESULT_LOAD_IMG2 = 0;
    ImageView img;
    Button cancel;
    Uri imageUri;
    private ImageView photoGalleryViewPager;
    ImageView bg;
    Bitmap bitmap;
    private String selectedImagePath=null;
    Bitmap bmScreen;
    ImageView back;
    private static int RESULT_LOAD_IMG = 1;
    public  static Bitmap bitmap1;

    private String mCurrentPhotoPath;
    private AlbumStorageDirFactory mAlbumStorageDirFactory = null;
    private static final String JPEG_FILE_PREFIX = "IMG_";
    private static final String JPEG_FILE_SUFFIX = ".jpg";
    public static String imgDecodableString;
    public  static  EditText aboutme;
    private static final int ACTION_TAKE_PHOTO_B = 1;
    private final static int RESULT_SELECT_IMAGE = 100;
    private final static int REQUEST_CROP_ICON = 101;
    public static final int MEDIA_TYPE_IMAGE = 1;
    private static final String TAG = "GalleryUtil";
    Uri selectedImageUri;

    File photoFile = null;
    View screen;
    String mTmpGalleryPicturePath;
    SharedPreferences prefs;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_three__fragment, container, false);
        btnupload=(Button)view.findViewById(R.id.buttonupload);
        prefs= PreferenceManager.getDefaultSharedPreferences(getActivity());

        btnskip=(Button)view.findViewById(R.id.buttonskip);
        btnsubmit=(Button)view.findViewById(R.id.buttonsubmit);
        img=(ImageView)view.findViewById(R.id.imageviewcamera);
        aboutme=(EditText)view.findViewById(R.id.edittextforabtme);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO) {
            mAlbumStorageDirFactory = new FroyoAlbumDirFactory();
        } else {
            mAlbumStorageDirFactory = new BaseAlbumDirFactory();
        }

        btnupload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showcamerdialog();
                /*Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                // Start the Intent
                startActivityForResult(galleryIntent, RESULT_LOAD_IMG);*/
            }
        });

        btnskip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Loggedin.class);
               // intent.putExtra("name", One_Fragment.Fname.getText().toString());
                SharedPreferences.Editor edit2 = prefs.edit();
                edit2.putBoolean("pref_previously_started", Boolean.TRUE);
                edit2.commit();
                SharedPreferences.Editor edit = prefs.edit();
                edit.putString("name", One_Fragment.Fname.getText().toString() + " " + One_Fragment.lname.getText().toString());
                edit.commit();
                startActivity(intent);
                MainActivity.activity.finish();
                getActivity().finish();
            }
        });

        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Loggedin.class);
               // intent.putExtra("name", One_Fragment.Fname.getText().toString());
               // intent.putExtra("aboutme", Three_FRAGMENT.aboutme.getText().toString());
                SharedPreferences.Editor edit2 = prefs.edit();
                edit2.putBoolean("pref_previously_started", Boolean.TRUE);
                edit2.commit();
                MainActivity.intentcaled = 2;
                startActivity(intent);
                SharedPreferences.Editor edit = prefs.edit();
                edit.putString("name", One_Fragment.Fname.getText().toString() + " " +  One_Fragment.lname.getText().toString());
                edit.commit();
                MainActivity.activity.finish();
                getActivity().finish();
            }
        });

        return view;
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
        Log.e("bitmap",""+bitmap1);
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






    public static Three_FRAGMENT newInstance(String text) {

        Three_FRAGMENT f = new Three_FRAGMENT();
        Bundle b = new Bundle();

        b.putString("msg", text);


        f.setArguments(b);

        return f;
    }


    @Override
    public void onResume() {
        super.onResume();

        //loaddata();
    }
}

