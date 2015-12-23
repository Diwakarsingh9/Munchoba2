package spinno.com.munchoba.parsingforapi;

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

import java.util.ArrayList;
import java.util.List;

import spinno.com.munchoba.Adapterclasses.Mypagesadapter;
import spinno.com.munchoba.fragmentsformainscreen.Allpages;
import spinno.com.munchoba.fragmentsformainscreen.Mybodyfatpercentagegraph;
import spinno.com.munchoba.fragmentsformainscreen.Mypages;
import spinno.com.munchoba.settergetter.Innerdata4;
import spinno.com.munchoba.settergetter.Innerdataallpages;
import spinno.com.munchoba.settergetter.Innerdatamypages;
import spinno.com.munchoba.settergetter.bodygraph;
import spinno.com.munchoba.settergetter.mypagesettergetter2;
import spinno.com.munchoba.settergetter.mypagessettergetter;
import spinno.com.munchoba.settergetter.pagesallsettergetter;
import spinno.com.munchoba.singleton.VolleySingleton;
import spinno.com.munchoba.urlapi.Api_s;

public class parsingpages {
    public static RequestQueue queue;
public  static  String status;
    public static StringRequest sr1,sr2;
    public static List<Innerdataallpages> data_list1;
    public static ArrayList<String> name = new ArrayList<String>();
    public static ArrayList<String> created = new ArrayList<String>();
    public static ArrayList<String> descp = new ArrayList<String>();
    public static List<Innerdatamypages> data_list2;
    public static ArrayList<String> myname = new ArrayList<String>();
    public static ArrayList<String> mycreated = new ArrayList<String>();
    public static ArrayList<String> mydescp = new ArrayList<String>();

    public static void parsingallpagesdata(FragmentActivity activity){

            String bodyurl = Api_s.allpages;
            Log.e("id", "" + bodyurl);
            queue = VolleySingleton.getInstance(activity).getRequestQueue();
            sr2 = new StringRequest(Request.Method.GET, bodyurl, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    name.clear();
                    created.clear();
                    descp.clear();


                    try {
                        GsonBuilder gsonBuilder = new GsonBuilder();
                        final Gson gson = gsonBuilder.create();
                        pagesallsettergetter received2 = new pagesallsettergetter();
                        received2 = gson.fromJson(response, pagesallsettergetter.class);
                        String status = received2.message;
                        data_list1=received2.innerdatabodyfatgraph;
                        for(int i=0;i<data_list1.size();i++){
                            name.add(data_list1.get(i).name);
                            created.add(data_list1.get(i).created);
                            descp.add(data_list1.get(i).description);

                        }
                        Allpages.list.setAdapter(Allpages.adp);

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

    }

    public static void parsingmypagesdata(FragmentActivity activity){

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(activity);
        String id = prefs.getString("id", null);
        id=id.trim();
        Log.e("id", "id:" + id);
        if (id.equals("")) {
            Toast.makeText(activity, "Please Check Your Internet Connection.", Toast.LENGTH_SHORT).show();
        }
        else {

            String bodyurl = Api_s.mypages.concat(id);
            Log.e("id", "" + bodyurl);
            queue = VolleySingleton.getInstance(activity).getRequestQueue();
            sr1 = new StringRequest(Request.Method.GET, bodyurl, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    myname.clear();
                    mycreated.clear();
                    mydescp.clear();


                    try {
                        GsonBuilder gsonBuilder = new GsonBuilder();
                        final Gson gson = gsonBuilder.create();
                        mypagessettergetter received2 = new mypagessettergetter();
                        received2 = gson.fromJson(response, mypagessettergetter.class);
                        status = received2.message;
                        if(parsingpages.status.equals("fail")){
                                    Mypages.pagenot.setVisibility(View.VISIBLE);
                                    Mypages.list.setVisibility(View.GONE);
                                    Log.e("challa"," "+status);
                        }
                        else {
                                    mypagesettergetter2 received3 = new mypagesettergetter2();
                                    received3 = gson.fromJson(response, mypagesettergetter2.class);
                                data_list2 = received3.innerdatamypages;
                                for (int i = 0; i < data_list2.size(); i++) {
                                    myname.add(data_list2.get(i).name);
                                    mycreated.add(data_list2.get(i).created);
                                    mydescp.add(data_list2.get(i).description);

                                }
                                    Log.e("challa22"," "+status);
                                    Mypages.pagenot.setVisibility(View.GONE);
                                    Mypages.list.setVisibility(View.VISIBLE);
                                    Mypages.list.setAdapter(Mypages.adp);
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
        }
    }
}
