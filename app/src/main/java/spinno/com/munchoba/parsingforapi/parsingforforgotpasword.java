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

import spinno.com.munchoba.Forgotpassword;
import spinno.com.munchoba.fragmentsformainscreen.Imagesadapter;
import spinno.com.munchoba.fragmentsformainscreen.Photoactivity;
import spinno.com.munchoba.settergetter.createalbumsettergetter;
import spinno.com.munchoba.settergetter.forgotpasswordsettergetter;
import spinno.com.munchoba.singleton.VolleySingleton;
import spinno.com.munchoba.urlapi.Api_s;

/**
 * Created by saifi45 on 10/16/2015.
 */
public class parsingforforgotpasword {


    public static RequestQueue queue;

    public static StringRequest sr9;









    public static void parsingforfrgtpass(final Context applicationContext,String s){



        String foodidurl = Api_s.Forgotpassword.concat(s);
        Log.e("id", "" + foodidurl);
        foodidurl=foodidurl.replaceAll(" ", "%20");
        queue = VolleySingleton.getInstance(applicationContext).getRequestQueue();
        sr9 = new StringRequest(Request.Method.GET, foodidurl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                try {
                    GsonBuilder gsonBuilder = new GsonBuilder();
                    final Gson gson = gsonBuilder.create();
                    forgotpasswordsettergetter received2 = new forgotpasswordsettergetter();
                    received2 = gson.fromJson(response, forgotpasswordsettergetter.class);
                    String status = received2.status;
                    String result = received2.result;

                    if(status.equals("sucess")){

                        Forgotpassword.head.setText(""+result);
                        Forgotpassword.part1.setVisibility(View.GONE);
                        Forgotpassword.part2.setVisibility(View.VISIBLE);
                    }
                    else {
                        Toast.makeText(applicationContext, "" + result, Toast.LENGTH_SHORT).show();
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
