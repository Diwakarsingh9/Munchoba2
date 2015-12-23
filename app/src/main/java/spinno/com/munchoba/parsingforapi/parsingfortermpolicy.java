package spinno.com.munchoba.parsingforapi;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
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

import java.util.ArrayList;
import java.util.List;

import spinno.com.munchoba.Adapterclasses.Videosadapter;
import spinno.com.munchoba.Termsandpolicy;
import spinno.com.munchoba.fragmentsformainscreen.Videosactivity;
import spinno.com.munchoba.settergetter.Innerdataterm;
import spinno.com.munchoba.settergetter.Videosmy;
import spinno.com.munchoba.settergetter.myvideossettergetter;
import spinno.com.munchoba.settergetter.termpolicysettergetter;
import spinno.com.munchoba.singleton.VolleySingleton;
import spinno.com.munchoba.urlapi.Api_s;

/**
 * Created by saifi45 on 10/16/2015.
 */
public class parsingfortermpolicy {

    public static RequestQueue queue;
    public static StringRequest sr3;

    public static List<Innerdataterm> data_list3;


    public static ArrayList<String> title = new ArrayList<String>();
    public static ArrayList<String> description = new ArrayList<String>();









    public static void parsingpolicy(final Context activity){



        String foodidurl = Api_s.Termpolicy;
        Log.e("id", "" + foodidurl);
        queue = VolleySingleton.getInstance(activity).getRequestQueue();
        sr3 = new StringRequest(Request.Method.GET, foodidurl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //  Toast.makeText(activity,""+response,Toast.LENGTH_SHORT).show();

                title.clear();
                description.clear();



                try {
                    GsonBuilder gsonBuilder = new GsonBuilder();
                    final Gson gson = gsonBuilder.create();
                    termpolicysettergetter received2 = new termpolicysettergetter();
                    received2 = gson.fromJson(response, termpolicysettergetter.class);
                    String status = received2.status;
                    data_list3=received2.result;


                    for(int i=0;i<data_list3.size();i++){

                        title.add(data_list3.get(i).title);
                        description.add(data_list3.get(i).description);

                    }
                    Log.e("descp", "" + Html.fromHtml(Html.fromHtml(description.get(0)).toString()));
                    String descp11 =""+Html.fromHtml(Html.fromHtml(description.get(0)).toString());
                    Spanned htmlAsSpanned = Html.fromHtml(description.get(0).toString());
                    Toast.makeText(activity, ""+descp11, Toast.LENGTH_SHORT).show();
                    Termsandpolicy.subtitle.setText("" + descp11);
                    Termsandpolicy.subtitle.setText(""+htmlAsSpanned);
                    Termsandpolicy.subtitle2.setText(""+(description.get(1)));






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


    }
}
