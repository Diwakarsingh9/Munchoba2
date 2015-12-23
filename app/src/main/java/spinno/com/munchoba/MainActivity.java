package spinno.com.munchoba;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;

import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.login.widget.ProfilePictureView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

import spinno.com.munchoba.library.src.com.taig.pmc.PopupMenuCompat;
import spinno.com.munchoba.settergetter.loginsettergetter;
import spinno.com.munchoba.settergetter.loginsettergetter2;
import spinno.com.munchoba.singleton.VolleySingleton;
import spinno.com.munchoba.urlapi.Api_s;


public class MainActivity extends FragmentActivity {
        CallbackManager callbackManager;
        LoginButton loginButton;
    TextView signin2,needhelp;
LinearLayout signuplayout;
    String encodedImage;
    Bitmap profPict;
    RequestQueue queue;
    public static StringRequest sr1,sr22;
    public static EditText username2, password2;
    public static String str_id;
    public static MainActivity activity;
    String str_firstname,str_lastname;
    public static int intentcaled=0;
    public static int intentproceed=0;
    TextView forgotpw,signup,signin;
    public static Bitmap fbpicture;
    boolean previouslyStarted;
    public static int countfooditem =0;
    public static int croppedimage =0;
    public static int Smileyactivitystarted =0;
    public static int fromprofile =0;
    public static int create =0;
    Bitmap bitmap = null;
    ImageView trialdp;

 LinearLayout LoginBox;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            FacebookSdk.sdkInitialize(getApplicationContext());
            super.onCreate(savedInstanceState);
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
             previouslyStarted = prefs.getBoolean("pref_previously_started", false);
            setContentView(R.layout.newmain);


            activity=MainActivity.this;
            startnextactivity();

             LoginBox = (LinearLayout) findViewById(R.id.LoginBox);
            signin2=(TextView)findViewById(R.id.signin2);
            needhelp=(TextView)findViewById(R.id.needhelp);
            username2=(EditText)findViewById(R.id.username);
            password2=(EditText)findViewById(R.id.password);
            signuplayout=(LinearLayout)findViewById(R.id.signuplayout);
            needhelp.setVisibility(View.GONE);
            LoginBox.setVisibility(View.GONE);
            signuplayout.setVisibility(View.GONE);


            trialdp = (ImageView) findViewById(R.id.imageView2);

            needhelp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PopupMenuCompat menu = PopupMenuCompat.newInstance(MainActivity.this, v);
                    menu.inflate(R.menu.menu2);
                    menu.setOnMenuItemClickListener(new PopupMenuCompat.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                           // Toast.makeText( MainActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();
                            if(item.getTitle().equals("Forgot Password"))
                            {
                                Intent in = new Intent(MainActivity.this,Forgotpassword.class);
                                startActivity(in);
                            }
                            else if(item.getTitle().equals("Terms & Policies"))
                            {
                                Intent in = new Intent(MainActivity.this,Termsandpolicy.class);
                                startActivity(in);
                            }
                            return false;
                        }
                    });
                        menu.show();

                    }
                });
            signin2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String username = (username2.getText() + "").trim();
                    String password = (password2.getText() + "").trim();
                    Log.e("userpass", "user:" + username+"pass: "+password);
                    if (username.equals("") || password.equals("")) {
                        Toast.makeText(getApplicationContext(), "Please fill required fields.", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Processing ....", Toast.LENGTH_SHORT).show();
                        String locationurl2 = Api_s.loginusername + username + Api_s.loginpassword + password;
                        locationurl2= locationurl2.replace(" ","%20");
                        Log.e("url", "" + locationurl2);
                        queue = VolleySingleton.getInstance(MainActivity.this).getRequestQueue();
                        sr22 = new StringRequest(Request.Method.GET, locationurl2, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                //pb2.setVisibility(View.GONE);

                                try {
                                    GsonBuilder gsonBuilder = new GsonBuilder();
                                    final Gson gson = gsonBuilder.create();
                                    loginsettergetter received2 = new loginsettergetter();
                                    received2 = gson.fromJson(response, loginsettergetter.class);

                                    String status = received2.message;
                                   /* */

                                    // String resulttoshow = received2.result;
                                   // Log.e("name", "" + name);
                                    if (status.equals("success")) {
                                        loginsettergetter2 received3 = new loginsettergetter2();
                                        received3 = gson.fromJson(response, loginsettergetter2.class);
                                        String id =received3.inner_signin.id;
                                        String name = received3.inner_signin.fullname;
                                        String Dob = received3.inner_signin.dob;
                                        String email = received3.inner_signin.email;
                                        String gender = received3.inner_signin.gender;
                                        String phone_no = received3.inner_signin.phone;
                                        String weight = received3.inner_signin.weight;
                                        String weightunit = received3.inner_signin.wunit;
                                        String height = received3.inner_signin.height;
                                        String heightunit = received3.inner_signin.hunit;
                                        String city = received3.inner_signin.city;
                                        String country = received3.inner_signin.country;
                                        String state = received3.inner_signin.state;
                                        String Race = received3.inner_signin.race;
                                        String Activity_level = received3.inner_signin.activitylevel;
                                        String Dietary = received3.inner_signin.dietarypreference;
                                        String Myinterests = received3.inner_signin.myinterests;
                                        String wearabletype = received3.inner_signin.wearablettype;
                                        String about = received3.inner_signin.about;
                                        Toast.makeText(getApplicationContext(), "Signed In ....", Toast.LENGTH_SHORT).show();
                                        signingin(id,name,Dob,email,gender,phone_no,weight,weightunit,height,heightunit,city,country,state,Race,Activity_level,Dietary,Myinterests,wearabletype,about);
                                        MainActivity.this.finish();
                                    } else {
                                        Toast.makeText(getApplicationContext(), "Username & Password do not match or you do not have an account yet.", Toast.LENGTH_SHORT).show();
                                    }

                                } catch (JsonSyntaxException e) {
                                    e.printStackTrace();
                                    Log.e("exception", "" + e);
                                }

                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                            }
                        });
                        sr22.setRetryPolicy(new DefaultRetryPolicy(50000,
                                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                        queue.add(sr22);

                    }
                }
            });

            signup=(TextView)findViewById(R.id.signup);



            signup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, Registration.class);

                    startActivity(intent);
                }
            });

             LoginButton loginButton = (LoginButton) findViewById(R.id.login_button);

            loginButton.setReadPermissions("user_friends");
            loginButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onFblogin();


                }
            });
        }
    //http://munchoba.com/API/add_weight.php?date=2015-08-04&time=03:33&weight=47&wunit=kg&user_id=535
    private void signingin(String id, String name, String dob, String email, String gender, String phone_no,
                           String weight, String weightunit, String height, String heightunit,
                           String city, String country, String state, String race,
                           String activity_level, String dietary, String myinterests,
                           String wearabletype, String about) {
        Intent intent = new Intent(MainActivity.this, Loggedin.class);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        SharedPreferences.Editor edit2 = prefs.edit();
        edit2.putBoolean("pref_previously_started", Boolean.TRUE);
        edit2.putString("name", name);
        edit2.putString("id", id);
        edit2.putString("dob", dob);
        edit2.putString("email", email);
        edit2.putString("gender", gender);
        edit2.putString("phoneno", phone_no);
        edit2.putString("weight", weight);
        edit2.putString("weightut", weightunit);
        edit2.putString("height", height);
        edit2.putString("heightut", heightunit);
        edit2.putString("city", city);
        edit2.putString("country", country);
        edit2.putString("state", state);
        edit2.putString("race", race);
        edit2.putString("activity_level", activity_level);
        edit2.putString("dietary", dietary);
        edit2.putString("myinterests", myinterests);
        edit2.putString("wearabletype", wearabletype);
        edit2.putString("about", about);

        edit2.commit();
        startActivity(intent);
    }


    private void onFblogin()
    {
        callbackManager = CallbackManager.Factory.create();

        // Set permissions
        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("email", "user_photos", "public_profile"));

        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {

                        System.out.println("Success");
                        GraphRequest.newMeRequest(
                                loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {


                                    @Override
                                    public void onCompleted(JSONObject json, GraphResponse response) {
                                        if (response.getError() != null) {
                                            // handle error
                                            System.out.println("ERROR");
                                        } else {
                                            System.out.println("Success");
                                            try {

                                                String jsonresult = String.valueOf(json);
                                                System.out.println("JSON Result" + jsonresult);

                                                String str_email = json.getString("email");
                                               str_id = json.getString("id");
                                                str_firstname = json.getString("first_name");
                                                str_lastname = json.getString("last_name");
                                                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());

                                                SharedPreferences.Editor edit = prefs.edit();
                                                edit.putString("name", str_firstname + " " + str_lastname);
                                                edit.commit();
                                               // Toast.makeText(getApplicationContext(),""+str_firstname+" "+str_lastname,Toast.LENGTH_SHORT).show();

                                                //Toast.makeText(getApplicationContext(),""+str_id,Toast.LENGTH_SHORT).show();

                                               /* imageURL = "http://graph.facebook.com/"+str_id+"/picture?type=small";
                                                try {
                                                    bitmap = BitmapFactory.decodeStream((InputStream)new URL(imageURL).getContent());
                                                } catch (Exception e) {
                                                    Log.d("TAG", "Loading Picture FAILED");
                                                    e.printStackTrace();
                                                }

                                                         String user_ID = user.getId();
                                                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                                                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                                                byte[] b = baos.toByteArray();
                                                encodedImage = Base64.encodeToString(b, Base64.DEFAULT);
                        Toast.makeText(getApplicationContext(),""+str_id,Toast.LENGTH_SHORT).show();
                                                //int img=json.getInt("pro")*/





                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }

                                             catch (Exception e) {
                                                e.printStackTrace();
                                            }


                                        }
                                    }

                                }).executeAsync();


                    }

                    @Override
                    public void onCancel() {
                        //Log.d(TAG_CANCEL, "On cancel");
                        //Toast.makeText(getApplicationContext(), "cancel", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(FacebookException error) {
                        // Log.d(TAG_ERROR, error.toString());
                    }
                });
    }
    private void startnextactivity() {



        if (!previouslyStarted) {
            Animation animTranslate  = AnimationUtils.loadAnimation(MainActivity.this, R.anim.anim);
            animTranslate.setAnimationListener(new Animation.AnimationListener() {

                @Override
                public void onAnimationStart(Animation arg0) { }

                @Override
                public void onAnimationRepeat(Animation arg0) { }

                @Override
                public void onAnimationEnd(Animation arg0) {
                    LoginBox.setVisibility(View.VISIBLE);
                    needhelp.setVisibility(View.VISIBLE);
                    signuplayout.setVisibility(View.VISIBLE);
                    Animation animFade  = AnimationUtils.loadAnimation(MainActivity.this, R.anim.fade);
                    LoginBox.startAnimation(animFade);


                }
            });
            ImageView imgLogo = (ImageView) findViewById(R.id.imageView1);
            imgLogo.startAnimation(animTranslate);


        } else if (previouslyStarted) {
            Animation animTranslate  = AnimationUtils.loadAnimation(MainActivity.this, R.anim.anim);
            animTranslate.setAnimationListener(new Animation.AnimationListener() {

                @Override
                public void onAnimationStart(Animation arg0) {
                }

                @Override
                public void onAnimationRepeat(Animation arg0) {
                }

                @Override
                public void onAnimationEnd(Animation arg0) {

                    Intent i = new Intent(MainActivity.this, Loggedin.class);
                    startActivity(i);
                    finish();
                }
            });
            ImageView imgLogo = (ImageView) findViewById(R.id.imageView1);
            imgLogo.startAnimation(animTranslate);


        }
    }
   @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Toast.makeText(getApplicationContext(), ""+requestCode + " " + resultCode + " " + data, Toast.LENGTH_SHORT).show();
        //Log.e("fbans",""+requestCode + " " + resultCode + " " + data);
        if (resultCode == Activity.RESULT_OK) {
            callbackManager.onActivityResult(requestCode, resultCode, data);
            startactivityforloggedin();
        }
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
   public void startactivityforloggedin(){
       if (intentcaled==0) {
            Intent intent = new Intent(MainActivity.this, Loggedin.class);



            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());

            SharedPreferences.Editor edit2 = prefs.edit();
            edit2.putBoolean("pref_previously_started", Boolean.TRUE);
           // edit2.putBitmap("imagedp", profPict);
           edit2.putString("imagePreferance", encodeTobase64(profPict));
            edit2.commit();
            startActivity(intent);
            activity.finish();
            intentcaled = 1;
        }
    }

        @Override
        protected void onResume() {
            super.onResume();

            // Logs 'install' and 'app activate' App Events.
            //AppEventsLogger.activateApp(this);
        }
        @Override
        protected void onPause() {
            super.onPause();

            // Logs 'app deactivate' App Event.
            //AppEventsLogger.deactivateApp(this);
        }
}

/*
/* URL url = null;
                                                try {
                                                    url = new URL("https://graph.facebook.com/"+str_id+"/picture?type=large");

                                                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                                                HttpURLConnection.setFollowRedirects(true);
                                                conn.setInstanceFollowRedirects(true);
                                                fbpicture = BitmapFactory.decodeStream(conn.getInputStream());
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }
                                               // Toast.makeText(getApplicationContext(), "" + str_firstname, Toast.LENGTH_SHORT).show();

 */