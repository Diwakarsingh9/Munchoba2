package spinno.com.munchoba.parsingforapi;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

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
import spinno.com.munchoba.fragmentsformainscreen.Allpages;
import spinno.com.munchoba.fragmentsformainscreen.Mygoalsactivity;
import spinno.com.munchoba.settergetter.Innerdataallpages;
import spinno.com.munchoba.settergetter.Innerdatamygoals;
import spinno.com.munchoba.settergetter.Innerdatamygoals2;
import spinno.com.munchoba.settergetter.Innerdatamypages;
import spinno.com.munchoba.settergetter.Mygoalsforcb;
import spinno.com.munchoba.settergetter.Mygoalssettergetter;
import spinno.com.munchoba.settergetter.Mygoalssettergetter2;
import spinno.com.munchoba.settergetter.pagesallsettergetter;
import spinno.com.munchoba.singleton.VolleySingleton;
import spinno.com.munchoba.urlapi.Api_s;

/**
 * Created by saifi45 on 8/19/2015.
 */
public class parsingformygoals {
    public static RequestQueue queue;
    public  static  String status;
    public static StringRequest sr1,sr2;
    public static List<Innerdatamygoals> data_list1;
    public static List<Innerdatamygoals2> data_list2;
    public static ArrayList<String> id = new ArrayList<String>();
    public static ArrayList<String> title = new ArrayList<String>();
    public static ArrayList<String> Muscleid = new ArrayList<String>();
    public static ArrayList<String> Muscletitle = new ArrayList<String>();


    public static void parsingallpagesdata(final Context activity){

        String bodyurl = Api_s.mygoals;
        Log.e("id", "" + bodyurl);
        queue = VolleySingleton.getInstance(activity).getRequestQueue();
        sr2 = new StringRequest(Request.Method.GET, bodyurl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Mygoalsactivity.pb.setVisibility(View.GONE);
                Mygoalsactivity.layoutforgoals.setVisibility(View.VISIBLE);
                id.clear();
                title.clear();


                try {
                    GsonBuilder gsonBuilder = new GsonBuilder();
                    final Gson gson = gsonBuilder.create();
                    Mygoalssettergetter received2 = new Mygoalssettergetter();
                    received2 = gson.fromJson(response, Mygoalssettergetter.class);
                    String status = received2.status;
                    data_list1=received2.result;
                    for(int i=0;i<data_list1.size();i++){
                        id.add(data_list1.get(i).id);
                        title.add(data_list1.get(i).title);

                    }
                    parsingformygoals.title.add(0, "Select Goal");
                    parsingformygoals.id.add(0, "0");
                    Mygoalsactivity.adp1= new ArrayAdapter(activity, R.layout.itemlayoutforfooddiary,R.id.kljjjjjjj, parsingformygoals.title);
                    Mygoalsactivity.mygoal.setAdapter(Mygoalsactivity.adp1);

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
        Mygoalsactivity.pb.setVisibility(View.VISIBLE);
        Mygoalsactivity.layoutforgoals.setVisibility(View.GONE);
    }

    public static void parsingallpagesdata22(final Context activity, String s){

        String bodyurl = Api_s.mygoals2.concat(s);
        Log.e("id", "" + bodyurl);
        queue = VolleySingleton.getInstance(activity).getRequestQueue();
        sr1 = new StringRequest(Request.Method.GET, bodyurl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Mygoalsactivity.pb2.setVisibility(View.GONE);
                Mygoalsactivity.llforcb.setVisibility(View.VISIBLE);
                Muscleid.clear();
                Muscletitle.clear();


                try {
                    GsonBuilder gsonBuilder = new GsonBuilder();
                    final Gson gson = gsonBuilder.create();
                    Mygoalssettergetter2 received23 = new Mygoalssettergetter2();
                    received23 = gson.fromJson(response, Mygoalssettergetter2.class);
                    String status = received23.status;
                    if(status.equals("success")){

                        Mygoalsforcb received33 = new Mygoalsforcb();
                        received33 = gson.fromJson(response, Mygoalsforcb.class);
                        data_list2=received33.result;
                        for(int i=0;i<data_list2.size();i++){
                            Muscleid.add(data_list2.get(i).id);
                            Muscletitle.add(data_list2.get(i).title);

                        }
                        Log.e("mus", "" + Muscletitle);
                            Mygoalsactivity.llforcb.removeAllViews();

                        Mygoalsactivity.musclegroup.setVisibility(View.VISIBLE);
                        for(int i = 0; i < Muscletitle.size(); i++) {

                            CheckBox cb = new CheckBox(activity);
                            cb.setText(Muscletitle.get(i));
                            cb.setTextColor(Color.parseColor("#000000"));
                            cb.setButtonDrawable(R.drawable.cbselectorcheck);





                            Mygoalsactivity.llforcb.addView(cb);

                        }
                        Mygoalsactivity.llforcb.setVisibility(View.VISIBLE);
                    }
                    else
                    {
                        Mygoalsactivity.musclegroup.setVisibility(View.GONE);
                        Mygoalsactivity.llforcb.setVisibility(View.GONE);
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
        sr1.setRetryPolicy(new DefaultRetryPolicy(50000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(sr1);
        Mygoalsactivity.pb2.setVisibility(View.VISIBLE);
        Mygoalsactivity.llforcb.setVisibility(View.GONE);
    }
}
