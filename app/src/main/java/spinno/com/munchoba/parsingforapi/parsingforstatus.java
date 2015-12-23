package spinno.com.munchoba.parsingforapi;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.gms.common.api.Api;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import spinno.com.munchoba.Adapterclasses.Videosadapter;
import spinno.com.munchoba.Loggedin;
import spinno.com.munchoba.fragmentsformainscreen.Status;
import spinno.com.munchoba.fragmentsformainscreen.Videosactivity;
import spinno.com.munchoba.settergetter.Videosmy;
import spinno.com.munchoba.settergetter.myvideossettergetter;
import spinno.com.munchoba.settergetter.statussettergetter;
import spinno.com.munchoba.singleton.VolleySingleton;
import spinno.com.munchoba.urlapi.Api_s;

/**
 * Created by saifi45 on 9/11/2015.
 */
public class parsingforstatus {
    public static RequestQueue queue;
    public static StringRequest sr3;


    public static ArrayList<String> result = new ArrayList<String>();
    public static ArrayList<String> status = new ArrayList<String>();


    public static void parsingforstatus(final Context activity, String title, String smiley, ArrayList<String> frndstag, String place, String video, String photo, int minute, int hourofday, String s){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(activity);
        String id2 = prefs.getString("id", null);
        id2=id2.trim();
        if(frndstag.equals(null)){
            frndstag.add("null");
        }
        Log.e("nullvaluecheckerr"," "+frndstag+ " "+place+" ");
        String foodidurl = Api_s.status1.concat(id2).concat(Api_s.status2).concat(title).concat(Api_s.status3).concat(smiley).concat(Api_s.status4).
                concat(""+frndstag).concat(Api_s.status5).concat(place).concat(Api_s.status6).concat(video).concat(Api_s.status7).concat(photo).concat(Api_s.statusminutes).
                concat(""+minute).concat(Api_s.statushours).concat(""+hourofday).concat(Api_s.statuscaloriesss).concat(s);
        Log.e("id", "" + foodidurl);
        queue = VolleySingleton.getInstance(activity).getRequestQueue();
        sr3 = new StringRequest(Request.Method.GET, foodidurl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //  Toast.makeText(activity,""+response,Toast.LENGTH_SHORT).show();
                Status.pb.setVisibility(View.GONE);
                Status.post.setVisibility(View.VISIBLE);


                result.clear();
                status.clear();



                try {
                    GsonBuilder gsonBuilder = new GsonBuilder();
                    final Gson gson = gsonBuilder.create();
                    statussettergetter received2 = new statussettergetter();
                    received2 = gson.fromJson(response, statussettergetter.class);
                    String status = received2.status;
                    String result = received2.result;
                    Toast.makeText(activity, ""+result, Toast.LENGTH_SHORT).show();
                    if(status.equals("success")) {
                        Intent in = new Intent(activity, Loggedin.class);
                        Loggedin.loggedin.finish();
                        activity.startActivity(in);

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
        sr3.setRetryPolicy(new DefaultRetryPolicy(50000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(sr3);
        Status.pb.setVisibility(View.VISIBLE);
        Status.post.setVisibility(View.GONE);

    }

}
