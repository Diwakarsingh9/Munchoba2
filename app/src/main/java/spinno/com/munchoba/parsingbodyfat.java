package spinno.com.munchoba;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
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
import com.jjoe64.graphview.GraphView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import spinno.com.munchoba.fragmentsformainscreen.Mybodyfatpercentagegraph;
import spinno.com.munchoba.settergetter.Innerdata4;
import spinno.com.munchoba.settergetter.bodygraph;
import spinno.com.munchoba.singleton.VolleySingleton;
import spinno.com.munchoba.urlapi.Api_s;

/**
 * Created by saifi45 on 8/12/2015.
 */
public class parsingbodyfat {


    public static RequestQueue queue;

    public static StringRequest sr1,sr2;
    public static List<Innerdata4> data_list1;
    public static ArrayList<String> date11 = new ArrayList<String>();
    public static ArrayList<String> fat11 = new ArrayList<String>();
    public static ArrayList<String> date12 = new ArrayList<String>();
    public static ArrayList<String> fat12 = new ArrayList<String>();

    public static void parsingdata(FragmentActivity activity){
        date11.clear();
        fat11.clear();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(activity);
        String id = prefs.getString("id", null);
        id=id.trim();
        Log.e("id", "id:" + id);
        if (id.equals("")) {
            Toast.makeText(activity, "Please Check Your Internet Connection.", Toast.LENGTH_SHORT).show();
        }
        else {

            String bodyurl = Api_s.bodyfatgraph.concat(id);
            Log.e("id", "" + bodyurl);
            queue = VolleySingleton.getInstance(activity).getRequestQueue();
            sr2 = new StringRequest(Request.Method.GET, bodyurl, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Mybodyfatpercentagegraph.pb.setVisibility(View.GONE);
                   // Mybodyfatpercentagegraph.graph.setVisibility(View.VISIBLE);
                    date11.clear();
                    fat11.clear();


                    try {
                        GsonBuilder gsonBuilder = new GsonBuilder();
                        final Gson gson = gsonBuilder.create();
                        bodygraph received2 = new bodygraph();
                        received2 = gson.fromJson(response, bodygraph.class);
                        String status = received2.message;
                        data_list1=received2.innerdatabodyfatgraph;
                        for(int i=0;i<data_list1.size();i++){
                            date11.add(data_list1.get(i).dates11);
                            fat11.add(data_list1.get(i).fat11);

                        }
                        date12=date11;
                        fat12=fat11;
                        //Collections.reverse(date11);
                       // Log.e("data11", "datebody:" + date11+"fat11 "+fat11);
                        Mybodyfatpercentagegraph.setgraph();
                       // Mybodyfatpercentagegraph.setgraph();

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
            sr2.setRetryPolicy(new DefaultRetryPolicy(50000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            queue.add(sr2);
            Mybodyfatpercentagegraph.pb.setVisibility(View.VISIBLE);
            //Mybodyfatpercentagegraph.graph.setVisibility(View.GONE);

        }
    }
}
