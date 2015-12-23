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

import spinno.com.munchoba.Adapterclasses.Videosadapter;
import spinno.com.munchoba.fragmentsformainscreen.Imagesadapter;
import spinno.com.munchoba.fragmentsformainscreen.Photoactivity;
import spinno.com.munchoba.fragmentsformainscreen.Videosactivity;
import spinno.com.munchoba.settergetter.Innerdataforphotosresult;
import spinno.com.munchoba.settergetter.Innerdataphotos;
import spinno.com.munchoba.settergetter.Videosmy;
import spinno.com.munchoba.settergetter.myvideossettergetter;
import spinno.com.munchoba.settergetter.photoalbum;
import spinno.com.munchoba.singleton.VolleySingleton;
import spinno.com.munchoba.urlapi.Api_s;

/**
 * Created by saifi45 on 9/2/2015.
 */
public class parsingformyvideos {

    public static RequestQueue queue;
    public static StringRequest sr3;

    public static List<myvideossettergetter> data_list3;

    public static ArrayList<String> id = new ArrayList<String>();
    public static ArrayList<String> user_id = new ArrayList<String>();
    public static ArrayList<String> title = new ArrayList<String>();
    public static ArrayList<String> description = new ArrayList<String>();
    public static ArrayList<String> video_id = new ArrayList<String>();
    public static ArrayList<String> created = new ArrayList<String>();
    public static ArrayList<String> path = new ArrayList<String>();
    public static ArrayList<String> type = new ArrayList<String>();








    public static void parsingforvideos(final Context activity){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(activity);
        String id2 = prefs.getString("id", null);
        id2=id2.trim();


        String foodidurl = Api_s.myvideos.concat(id2);
        Log.e("id", "" + foodidurl);
        queue = VolleySingleton.getInstance(activity).getRequestQueue();
        sr3 = new StringRequest(Request.Method.GET, foodidurl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //  Toast.makeText(activity,""+response,Toast.LENGTH_SHORT).show();
                Videosactivity.pb.setVisibility(View.GONE);
                Videosactivity.gd.setVisibility(View.VISIBLE);
                id.clear();
                user_id.clear();
                title.clear();
                description.clear();
                video_id.clear();
                created.clear();
                path.clear();
                type.clear();


                try {
                    GsonBuilder gsonBuilder = new GsonBuilder();
                    final Gson gson = gsonBuilder.create();
                    Videosmy received2 = new Videosmy();
                    received2 = gson.fromJson(response, Videosmy.class);
                    String status = received2.status;
                    data_list3=received2.result;


                    for(int i=0;i<data_list3.size();i++){
                        id.add(data_list3.get(i).id);
                        user_id.add(data_list3.get(i).user_id);
                        title.add(data_list3.get(i).title);
                        description.add(data_list3.get(i).description);
                        video_id.add(data_list3.get(i).video_id);
                        created.add(data_list3.get(i).created);
                        path.add(data_list3.get(i).path);
                        type.add(data_list3.get(i).type);
                        }




                    Videosactivity.adp = new Videosadapter(activity,title,description);
                    Videosactivity.gd.setAdapter(Videosactivity.adp);

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
        Videosactivity.pb.setVisibility(View.VISIBLE);
        Videosactivity.gd.setVisibility(View.GONE);

    }
}
