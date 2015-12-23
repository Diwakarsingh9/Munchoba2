package spinno.com.munchoba.parsingforapi;

import android.support.v4.app.FragmentActivity;
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

import spinno.com.munchoba.MainActivity;
import spinno.com.munchoba.R;
import spinno.com.munchoba.fragmentsformainscreen.Food_Diary;
import spinno.com.munchoba.settergetter.Exerciseacivitydata;
import spinno.com.munchoba.settergetter.Fooddiarydata;
import spinno.com.munchoba.settergetter.Innerdata2;
import spinno.com.munchoba.settergetter.Innerdata5;
import spinno.com.munchoba.settergetter.Innerdatafooditem;
import spinno.com.munchoba.settergetter.Innerdatafoodmeal;
import spinno.com.munchoba.settergetter.Innerdatafoodunits;
import spinno.com.munchoba.settergetter.Innerdataworktype;
import spinno.com.munchoba.singleton.VolleySingleton;
import spinno.com.munchoba.urlapi.Api_s;

/**
 * Created by saifi45 on 8/31/2015.
 */
public class parsingforfooddiarydetails {


    public static RequestQueue queue;

    public static StringRequest sr3;

    public static List<Innerdatafoodmeal> data_list3;
    public static List<Innerdatafooditem> data_list4;
    public static List<Innerdatafoodunits> data_list5;

    public static ArrayList<String> idmeal = new ArrayList<String>();
    public static ArrayList<String> titlemeal = new ArrayList<String>();
    public static ArrayList<String> idfooditem = new ArrayList<String>();
    public static ArrayList<String> titlefooditem = new ArrayList<String>();
    public static ArrayList<String> unitfooditem = new ArrayList<String>();
    public static ArrayList<String> idfoodunit = new ArrayList<String>();
    public static ArrayList<String> titlefoodunit = new ArrayList<String>();






    public static void parsingforfooddetails(final FragmentActivity activity){



        String foodidurl = Api_s.fooddiarydata;
        Log.e("id", "" + foodidurl);
        queue = VolleySingleton.getInstance(activity).getRequestQueue();
        sr3 = new StringRequest(Request.Method.GET, foodidurl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Food_Diary.pb2.setVisibility(View.GONE);
                Food_Diary.ll1.setVisibility(View.VISIBLE);
                idmeal.clear();
                titlemeal.clear();
                idfooditem.clear();
                titlefooditem.clear();
                unitfooditem.clear();
                idfoodunit.clear();
                titlefoodunit.clear();

                try {
                    GsonBuilder gsonBuilder = new GsonBuilder();
                    final Gson gson = gsonBuilder.create();
                    Fooddiarydata received2 = new Fooddiarydata();
                    received2 = gson.fromJson(response, Fooddiarydata.class);
                    String status = received2.status;
                    data_list3=received2.foodmeal;
                    data_list4=received2.food_items;
                    data_list5=received2.foodunits;
                    MainActivity.countfooditem=1;
                    for(int i=0;i<data_list3.size();i++){
                        idmeal.add(data_list3.get(i).id);
                        titlemeal.add(data_list3.get(i).title);

                    }
                  //  Log.e("titlewrk"," "+titlework);
                    for(int i=0;i<data_list4.size();i++){
                        idfooditem.add(data_list4.get(i).id);
                        titlefooditem.add(data_list4.get(i).title);
                        unitfooditem.add(data_list4.get(i).foodunit);

                    }
                    for(int i=0;i<data_list5.size();i++){
                        idfoodunit.add(data_list5.get(i).id);
                        titlefoodunit.add(data_list5.get(i).title);


                    }
                    Food_Diary.adp3= new ArrayAdapter(activity, R.layout.itemlayoutforfooddiary,R.id.kljjjjjjj,parsingforfooddiarydetails.titlemeal);
                    Food_Diary.mealtype.setAdapter(Food_Diary.adp3);

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
        Food_Diary.pb2.setVisibility(View.VISIBLE);
        Food_Diary.ll1.setVisibility(View.GONE);

    }
}
