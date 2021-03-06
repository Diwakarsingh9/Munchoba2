package spinno.com.munchoba.parsingforapi;

import android.content.Context;
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
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import spinno.com.munchoba.Photoopenactivity;
import spinno.com.munchoba.fragmentsformainscreen.Imagesadapter;
import spinno.com.munchoba.fragmentsformainscreen.Photoactivity;
import spinno.com.munchoba.fragmentsformainscreen.Photoimagesadapter;
import spinno.com.munchoba.fragmentsformainscreen.Photosinneractivity;
import spinno.com.munchoba.settergetter.Photodeletesettergetter;
import spinno.com.munchoba.settergetter.deletealbumgettersetter;
import spinno.com.munchoba.singleton.VolleySingleton;
import spinno.com.munchoba.urlapi.Api_s;

/**
 * Created by saifi45 on 9/4/2015.
 */
public class parsingfordeletephoto {

    public static RequestQueue queue;
    public static int count =0;
    public static StringRequest sr9;









    public static void parsingfordeletephotos(final Context applicationContext, String s){

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(applicationContext);
        String id2 = prefs.getString("id", null);
        id2=id2.trim();

        String foodidurl = Api_s.deletephoto1.concat(id2).concat(Api_s.deletephoto2).concat(s);
        Log.e("id", "" + foodidurl);
        queue = VolleySingleton.getInstance(applicationContext).getRequestQueue();
        sr9 = new StringRequest(Request.Method.GET, foodidurl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                try {
                    GsonBuilder gsonBuilder = new GsonBuilder();
                    final Gson gson = gsonBuilder.create();
                    Photodeletesettergetter received2 = new Photodeletesettergetter();
                    received2 = gson.fromJson(response, Photodeletesettergetter.class);
                    String status = received2.status;
                    String result = received2.result;
                    Toast.makeText(applicationContext, "" + result, Toast.LENGTH_SHORT).show();
                    if(status.equals("success")){
                     Photosinneractivity.php2.finish();
                        parsingformyphotos.parsingforphotos(applicationContext);
                        count =1;
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

}
