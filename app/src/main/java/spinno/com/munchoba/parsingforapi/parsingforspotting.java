package spinno.com.munchoba.parsingforapi;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import java.util.ArrayList;
import java.util.List;

import spinno.com.munchoba.Adapterclasses.Myspotteradapter;
import spinno.com.munchoba.Spottersactivity;
import spinno.com.munchoba.Spottingactivity;
import spinno.com.munchoba.settergetter.Innerdataspotters;
import spinno.com.munchoba.settergetter.Innerdataspotting;
import spinno.com.munchoba.settergetter.Spotterssettergetter;
import spinno.com.munchoba.settergetter.spottingsettergetter;
import spinno.com.munchoba.singleton.VolleySingleton;
import spinno.com.munchoba.urlapi.Api_s;

/**
 * Created by saifi45 on 10/17/2015.
 */
public class parsingforspotting {

    public static RequestQueue queue;
    public static StringRequest sr3;

    public static List<Innerdataspotting> data_list3;


    public static ArrayList<String> userid = new ArrayList<String>();
    public static ArrayList<String> avatar = new ArrayList<String>();
    public static ArrayList<String> about = new ArrayList<String>();
    public static ArrayList<String> usernames = new ArrayList<String>();








    public static void parsingspotting(final Context activity){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(activity);
        String id2 = prefs.getString("id", null);
        id2=id2.trim();


        String foodidurl = Api_s.Spotting.concat(id2);
        Log.e("id", "" + foodidurl);
        queue = VolleySingleton.getInstance(activity).getRequestQueue();
        sr3 = new StringRequest(Request.Method.GET, foodidurl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //  Toast.makeText(activity,""+response,Toast.LENGTH_SHORT).show();

                userid.clear();
                avatar.clear();
                about.clear();
                usernames.clear();
                Spottingactivity.lv.setVisibility(View.VISIBLE);
                Spottingactivity.pbsp.setVisibility(View.GONE);

                try {
                    GsonBuilder gsonBuilder = new GsonBuilder();
                    final Gson gson = gsonBuilder.create();
                    spottingsettergetter received2 = new spottingsettergetter();
                    received2 = gson.fromJson(response, spottingsettergetter.class);
                    String status = received2.status;
                    data_list3=received2.innerspotting;


                    for(int i=0;i<data_list3.size();i++){

                        userid.add(data_list3.get(i).user_id);
                        avatar.add(data_list3.get(i).avatar);
                        about.add(data_list3.get(i).about);
                        usernames.add(data_list3.get(i).user_name);

                    }
                    Spottersactivity.adp = new Myspotteradapter(activity,parsingforspotting.usernames,parsingforspotting.about,parsingforspotting.avatar);
                    Spottersactivity.lv.setAdapter(Spottersactivity.adp);






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

        Spottingactivity.lv.setVisibility(View.GONE);
        Spottingactivity.pbsp.setVisibility(View.VISIBLE);
    }
}
