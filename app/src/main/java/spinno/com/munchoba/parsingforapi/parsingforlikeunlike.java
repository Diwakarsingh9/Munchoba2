package spinno.com.munchoba.parsingforapi;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import spinno.com.munchoba.fragmentsformainscreen.Photosinneractivity;
import spinno.com.munchoba.settergetter.Photodeletesettergetter;
import spinno.com.munchoba.settergetter.likesettergetter;
import spinno.com.munchoba.settergetter.unlikesettergetter;
import spinno.com.munchoba.singleton.VolleySingleton;
import spinno.com.munchoba.urlapi.Api_s;

/**
 * Created by saifi45 on 10/19/2015.
 */
public class parsingforlikeunlike {

    public static RequestQueue queue;
    public static int count =0;
    public static StringRequest sr9;









    public static void parsingforlike(final Context applicationContext, String s, String s2){

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(applicationContext);
        String id2 = prefs.getString("id", null);
        id2=id2.trim();

        String foodidurl = Api_s.Likestatus.concat(id2).concat(Api_s.Likestatus2).concat(s).concat(Api_s.Likestatus3).concat(s2);
        Log.e("id", "" + foodidurl);
        queue = VolleySingleton.getInstance(applicationContext).getRequestQueue();
        sr9 = new StringRequest(Request.Method.GET, foodidurl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                try {
                    GsonBuilder gsonBuilder = new GsonBuilder();
                    final Gson gson = gsonBuilder.create();
                    likesettergetter received2 = new likesettergetter();
                    received2 = gson.fromJson(response, likesettergetter.class);
                    String status = received2.status;
                    String result = received2.result;

                    if(status.equals("success")){
                        final Toast toast = Toast.makeText(applicationContext, ""+result, Toast.LENGTH_SHORT);
                        toast.show();

                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                toast.cancel();
                            }
                        }, 200);

                       /* pars.parsingforphotos(applicationContext);
                        Photoactivity.adp = new Imagesadapter(applicationContext,parsingformyphotos.thumbnail22,parsingformyphotos.name);
                        Photoactivity.gd.setAdapter(Photoactivity.adp);*/
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
        sr9.setRetryPolicy(new DefaultRetryPolicy(50000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(sr9);


    }


    public static void parsingforunlike(final Context applicationContext, String s,String s2){

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(applicationContext);
        String id2 = prefs.getString("id", null);
        id2=id2.trim();

        String foodidurl = Api_s.UnLikestatus.concat(id2).concat(Api_s.UnLikestatus2).concat(s).concat(Api_s.UnLikestatus3).concat(s2);
        Log.e("id", "" + foodidurl);
        queue = VolleySingleton.getInstance(applicationContext).getRequestQueue();
        sr9 = new StringRequest(Request.Method.GET, foodidurl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                try {
                    GsonBuilder gsonBuilder = new GsonBuilder();
                    final Gson gson = gsonBuilder.create();
                    unlikesettergetter received2 = new unlikesettergetter();
                    received2 = gson.fromJson(response, unlikesettergetter.class);
                    String status = received2.status;
                    String result = received2.result;
                    if(status.equals("success")){
                        final Toast toast = Toast.makeText(applicationContext, ""+result, Toast.LENGTH_SHORT);
                        toast.show();

                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                toast.cancel();
                            }
                        }, 200);


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
        sr9.setRetryPolicy(new DefaultRetryPolicy(50000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(sr9);


    }
}
