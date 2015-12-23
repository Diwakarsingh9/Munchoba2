package spinno.com.munchoba.parsingforapi;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;

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

import spinno.com.munchoba.R;
import spinno.com.munchoba.fragmentsformainscreen.Community;
import spinno.com.munchoba.fragmentsformainscreen.Communityadapter;
import spinno.com.munchoba.fragmentsformainscreen.Mygoalsactivity;
import spinno.com.munchoba.settergetter.Innerdatacommu;
import spinno.com.munchoba.settergetter.Innerdatamygoals;
import spinno.com.munchoba.settergetter.Innerdatamygoals2;
import spinno.com.munchoba.settergetter.Mygoalssettergetter;
import spinno.com.munchoba.settergetter.communitysettergetter;
import spinno.com.munchoba.singleton.VolleySingleton;
import spinno.com.munchoba.urlapi.Api_s;

/**
 * Created by saifi45 on 10/26/2015.
 */
public class parsingforcommunity {
    public static RequestQueue queue;
    public  static  String status;
    public static StringRequest sr1,sr2;
    public static List<Innerdatacommu> data_list1;
    public static List<Innerdatamygoals2> data_list2;
    public static ArrayList<String> user_id11 = new ArrayList<String>();
    public static ArrayList<String> name11 = new ArrayList<String>();
    public static ArrayList<String> gender11 = new ArrayList<String>();
    public static ArrayList<String> weight11 = new ArrayList<String>();
    public static ArrayList<String> age = new ArrayList<String>();
    public static ArrayList<String> BMI = new ArrayList<String>();
    public static ArrayList<String> height11 = new ArrayList<String>();
    public static ArrayList<String> weight_change = new ArrayList<String>();

    public static void parsingforcomm(final Context activity,String s){

        String bodyurl2 = Api_s.communitysearch.concat(s);
       String bodyurl=bodyurl2.trim();
        Log.e("id", "" + bodyurl);
        queue = VolleySingleton.getInstance(activity).getRequestQueue();
        sr2 = new StringRequest(Request.Method.GET, bodyurl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Community.pb.setVisibility(View.GONE);
                Community.listviewcommunity.setVisibility(View.VISIBLE);
                user_id11.clear();
                name11.clear();
                gender11.clear();
                weight11.clear();
                age.clear();
                BMI.clear();
                height11.clear();
                weight_change.clear();



                try {
                    GsonBuilder gsonBuilder = new GsonBuilder();
                    final Gson gson = gsonBuilder.create();
                    communitysettergetter received2 = new communitysettergetter();
                    received2 = gson.fromJson(response, communitysettergetter.class);
                    String status = received2.status;
                    data_list1=received2.innerdatacommunity;
                    for(int i=0;i<data_list1.size();i++){
                        user_id11.add(data_list1.get(i).user_id);
                        name11.add(data_list1.get(i).name);
                        gender11.add(data_list1.get(i).gender);
                        weight11.add(data_list1.get(i).weight);
                        age.add(data_list1.get(i).age);
                        BMI.add(data_list1.get(i).BMI);
                        height11.add(data_list1.get(i).height);
                        weight_change.add(data_list1.get(i).weight_change);

                    }

                    Community.communityadapter= new Communityadapter(activity, parsingforcommunity.name11,parsingforcommunity.gender11,parsingforcommunity.age);
                    Community.listviewcommunity.setAdapter(Community.communityadapter);

                    Community.setListViewHeightBasedOnChildren(Community.listviewcommunity);
                    Community.listviewcommunity.setFocusable(false);

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
        Community.pb.setVisibility(View.VISIBLE);
        Community.listviewcommunity.setVisibility(View.GONE);
    }
}
