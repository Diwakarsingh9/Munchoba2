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

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import spinno.com.munchoba.Adapterclasses.Videosadapter;
import spinno.com.munchoba.fragmentsformainscreen.Videosactivity;
import spinno.com.munchoba.settergetter.Videosmy;
import spinno.com.munchoba.settergetter.myvideossettergetter;
import spinno.com.munchoba.settergetter.uploadvideosettergetter;
import spinno.com.munchoba.singleton.VolleySingleton;
import spinno.com.munchoba.urlapi.Api_s;

/**
 * Created by saifi45 on 9/18/2015.
 */
public class parsingforuploadvideofromlink {

    public static RequestQueue queue;
    public static StringRequest sr3;

    public static String YouTubeVideoID;


    public static void parsingforuploadvideo(final Context activity, String link, String videonametype, String trim, String s) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(activity);
        String id2 = prefs.getString("id", null);
        id2 = id2.trim();


            if(videonametype.equals("youtube")) {
                 YouTubeVideoID = getYoutubeVideoId(link);
            }
        else{
              String Rvimeolink =  new StringBuilder(link).reverse().toString();
                String rvimeo[]=Rvimeolink.split("/");
                 YouTubeVideoID = new StringBuilder(rvimeo[0]).reverse().toString();
            }


        String foodidurl = Api_s.videolinking.concat(id2).concat(Api_s.videolinking2).concat(videonametype).concat(Api_s.videolinking3).concat(YouTubeVideoID).concat(Api_s.videolinking4).concat(link).concat("&title").concat(trim).concat("&description").concat(s);
        Log.e("id", "" + foodidurl);
        queue = VolleySingleton.getInstance(activity).getRequestQueue();
        sr3 = new StringRequest(Request.Method.GET, foodidurl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //  Toast.makeText(activity,""+response,Toast.LENGTH_SHORT).show();


                try {
                    GsonBuilder gsonBuilder = new GsonBuilder();
                    final Gson gson = gsonBuilder.create();
                    uploadvideosettergetter received2 = new uploadvideosettergetter();
                    received2 = gson.fromJson(response, uploadvideosettergetter.class);

                    String results = received2.result;

                    String messages = received2.message;
                    Toast.makeText(activity, "" + results, Toast.LENGTH_SHORT).show();

                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(activity,"Something went wrong !!!",Toast.LENGTH_SHORT).show();
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

    public static String getYoutubeVideoId(String youtubeUrl)
    {
        String video_id="";
        if (youtubeUrl != null && youtubeUrl.trim().length() > 0 && youtubeUrl.startsWith("http"))
        {

            String expression = "^.*((youtu.be"+ "\\/)" + "|(v\\/)|(\\/u\\/w\\/)|(embed\\/)|(watch\\?))\\??v?=?([^#\\&\\?]*).*"; // var regExp = /^.*((youtu.be\/)|(v\/)|(\/u\/\w\/)|(embed\/)|(watch\?))\??v?=?([^#\&\?]*).*/;
            CharSequence input = youtubeUrl;
            Pattern pattern = Pattern.compile(expression,Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(input);
            if (matcher.matches())
            {
                String groupIndex1 = matcher.group(7);
                if(groupIndex1!=null && groupIndex1.length()==11)
                    video_id = groupIndex1;
            }
        }
        return video_id;
    }
}
