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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import spinno.com.munchoba.fragmentsformainscreen.Exercise_Diary;
import spinno.com.munchoba.settergetter.Exerciseacivitydata;
import spinno.com.munchoba.settergetter.Exercisegraph;
import spinno.com.munchoba.settergetter.Innerdata2;
import spinno.com.munchoba.settergetter.Innerdata4;
import spinno.com.munchoba.settergetter.Innerdata5;
import spinno.com.munchoba.settergetter.Innerdatafoodunits;
import spinno.com.munchoba.settergetter.Innerdataworktype;
import spinno.com.munchoba.settergetter.fooddiary;
import spinno.com.munchoba.singleton.VolleySingleton;
import spinno.com.munchoba.urlapi.Api_s;

/**
 * Created by saifi45 on 8/12/2015.
 */
public class parsingforexercisegraph {


    public static RequestQueue queue;
    public static int countcompleted=0;
    public static StringRequest sr1,sr2,sr3;
    public static List<Innerdata2> data_list1;
    public static List<Innerdata5> data_list2;
    public static List<Innerdataworktype> data_list3;
    public static List<Innerdatafoodunits> data_list4;
    public static ArrayList<String> date11 = new ArrayList<String>();
    public static ArrayList<String> date12 = new ArrayList<String>();
    public static ArrayList<String> date13 = new ArrayList<String>();
    public static ArrayList<String> idwork = new ArrayList<String>();
    public static ArrayList<String> titlework = new ArrayList<String>();
    public static ArrayList<String> idfood = new ArrayList<String>();
    public static ArrayList<String> titlefood = new ArrayList<String>();
    public static ArrayList<String> Calorieseat = new ArrayList<String>();
    public static ArrayList<String> Caloriesburn = new ArrayList<String>();
    public static ArrayList<String> Caloriesburn12 = new ArrayList<String>();
    public static ArrayList<String> time12 = new ArrayList<String>();
    public static ArrayList<String> activitytype12 = new ArrayList<String>();
    public static ArrayList<String> workouttype12 = new ArrayList<String>();
    public static ArrayList<String> measurement12 = new ArrayList<String>();
    public static ArrayList<String> calories12 = new ArrayList<String>();
    public static ArrayList<String> count12 = new ArrayList<String>();
    public static ArrayList<String> Caloriesfordownload = new ArrayList<String>();
    public static ArrayList<String> Datefordownload = new ArrayList<String>();

    public static void parsingdataforcalorieseat(FragmentActivity activity){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(activity);
        String id = prefs.getString("id", null);
        id=id.trim();
        Log.e("id", "id:" + id);
        if (id.equals("")) {
            Toast.makeText(activity, "Please Check Your Internet Connection.", Toast.LENGTH_SHORT).show();
        }
        else {

            String foodidurl = Api_s.fooddiarygraph.concat(id);
            Log.e("id", "" + foodidurl);
            queue = VolleySingleton.getInstance(activity).getRequestQueue();
            sr2 = new StringRequest(Request.Method.GET, foodidurl, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Exercise_Diary.pb.setVisibility(View.GONE);
                    Exercise_Diary.graph.setVisibility(View.VISIBLE);
                    date11.clear();
                    Calorieseat.clear();

                    try {
                        GsonBuilder gsonBuilder = new GsonBuilder();
                        final Gson gson = gsonBuilder.create();
                        fooddiary received2 = new fooddiary();
                        received2 = gson.fromJson(response, fooddiary.class);
                        String status = received2.message;
                        data_list1=received2.innerdataafooddiary;
                        for(int i=0;i<data_list1.size();i++){
                            date11.add(data_list1.get(i).dates11);
                            Calorieseat.add(data_list1.get(i).calories);

                        }
                      //  date12=date11;
                        Log.e("dateburn11", "" + date11.size());
                        Collections.reverse(date11);
                        Collections.reverse(Calorieseat);
                        countcompleted++;
                       // Exercise_Diary.callgraph();
           Exercise_Diary.setgraph();
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
            Exercise_Diary.pb.setVisibility(View.VISIBLE);
            Exercise_Diary.graph.setVisibility(View.GONE);


        }

    }
    public static void parsingdataforcaloriesburn(FragmentActivity activity){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(activity);
        String id = prefs.getString("id", null);
        id=id.trim();
        Log.e("id", "id:" + id);
        if (id.equals("")) {
            Toast.makeText(activity, "Please Check Your Internet Connection.", Toast.LENGTH_SHORT).show();
        }
        else {

            String foodidurl = Api_s.exercisediarygraph.concat(id);
            Log.e("id", "" + foodidurl);
            queue = VolleySingleton.getInstance(activity).getRequestQueue();
            sr1 = new StringRequest(Request.Method.GET, foodidurl, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Exercise_Diary.pb.setVisibility(View.GONE);
                    Exercise_Diary.graph.setVisibility(View.VISIBLE);
                    date12.clear();
                    Caloriesburn.clear();

                    try {
                        GsonBuilder gsonBuilder = new GsonBuilder();
                        final Gson gson = gsonBuilder.create();
                        Exercisegraph received2 = new Exercisegraph();
                        received2 = gson.fromJson(response, Exercisegraph.class);
                        String status = received2.message;
                        data_list2=received2.innerexrcise;
                        for(int i=0;i<data_list2.size();i++){
                            date12.add(data_list2.get(i).dates11);
                            time12.add(data_list2.get(i).time);
                            activitytype12.add(data_list2.get(i).activity_type);
                            workouttype12.add(data_list2.get(i).workout_type);
                            measurement12.add(data_list2.get(i).measurement);
                            calories12.add(data_list2.get(i).calories);
                            count12.add(data_list2.get(i).counts);

                            Caloriesburn.add(data_list2.get(i).calories);

                        }
                        Log.e("dateburn", "" + date12.size());
                        date13=date12;
                        Caloriesburn12 = Caloriesburn;
                        Collections.reverse(date12);
                        Collections.reverse(Caloriesburn);
                        Caloriesfordownload=Caloriesburn;
                        Datefordownload=date12;

                        countcompleted++;
                       // Exercise_Diary.callgraph();

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
            Exercise_Diary.pb.setVisibility(View.VISIBLE);
            Exercise_Diary.graph.setVisibility(View.GONE);

        }

    }
public  static  void parsingcompleted(){
    if(parsingforexercisegraph.countcompleted==2){
        Exercise_Diary.setgraph();
    }

}

    public static void parsingdataforactivitydata(FragmentActivity activity){



            String foodidurl = Api_s.exerciseactivitydata;
            Log.e("id", "" + foodidurl);
            queue = VolleySingleton.getInstance(activity).getRequestQueue();
            sr3 = new StringRequest(Request.Method.GET, foodidurl, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                   // Exercise_Diary.pb.setVisibility(View.GONE);
                    titlework.clear();
                    idwork.clear();
                    titlefood.clear();
                    idfood.clear();

                    try {
                        GsonBuilder gsonBuilder = new GsonBuilder();
                        final Gson gson = gsonBuilder.create();
                        Exerciseacivitydata received2 = new Exerciseacivitydata();
                        received2 = gson.fromJson(response, Exerciseacivitydata.class);
                        String status = received2.status;
                        data_list3=received2.workout_type;
                        data_list4=received2.food_units;
                        for(int i=0;i<data_list3.size();i++){
                            idwork.add(data_list3.get(i).id);
                            titlework.add(data_list3.get(i).title);

                        }
                        Log.e("titlewrk"," "+titlework);
                        for(int i=0;i<data_list4.size();i++){
                            idfood.add(data_list4.get(i).id);
                            titlefood.add(data_list4.get(i).title);

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
        sr3.setRetryPolicy(new DefaultRetryPolicy(50000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            queue.add(sr3);


        }

    }


