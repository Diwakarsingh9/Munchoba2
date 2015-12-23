package spinno.com.munchoba.parsingforapi;

import android.content.Context;
import android.content.SharedPreferences;
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

import spinno.com.munchoba.Adapterclasses.Videosadapter;
import spinno.com.munchoba.Photoopenactivity;
import spinno.com.munchoba.Videoopenactivity;
import spinno.com.munchoba.fragmentsformainscreen.Imagesadapter;
import spinno.com.munchoba.fragmentsformainscreen.Photoactivity;
import spinno.com.munchoba.fragmentsformainscreen.Videosactivity;
import spinno.com.munchoba.settergetter.deletealbumgettersetter;
import spinno.com.munchoba.settergetter.deletevideosettergetter;
import spinno.com.munchoba.singleton.VolleySingleton;
import spinno.com.munchoba.urlapi.Api_s;

/**
 * Created by saifi45 on 9/2/2015.
 */
public class parsingfordeletevideo {

    public static RequestQueue queue;

    public static StringRequest sr9;









    public static void parsingfordeletevid(final Context applicationContext, String s){

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(applicationContext);
        String id2 = prefs.getString("id", null);
        id2=id2.trim();

        String foodidurl = Api_s.deletevideo1.concat(id2).concat(Api_s.deletevideo2).concat(s);
        Log.e("id", "" + foodidurl);
        queue = VolleySingleton.getInstance(applicationContext).getRequestQueue();
        sr9 = new StringRequest(Request.Method.GET, foodidurl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                try {
                    GsonBuilder gsonBuilder = new GsonBuilder();
                    final Gson gson = gsonBuilder.create();
                    deletevideosettergetter received2 = new deletevideosettergetter();
                    received2 = gson.fromJson(response, deletevideosettergetter.class);
                    String status = received2.status;
                    String result = received2.result;
                    Toast.makeText(applicationContext, "" + result, Toast.LENGTH_SHORT).show();
                    if(status.equals("success")){
                        Videoopenactivity.php.finish();
                        parsingformyvideos.parsingforvideos(applicationContext);
                        Videosactivity.adp = new Videosadapter(applicationContext,parsingformyvideos.title,parsingformyvideos.description);
                        Videosactivity.gd.setAdapter(Videosactivity.adp);
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
