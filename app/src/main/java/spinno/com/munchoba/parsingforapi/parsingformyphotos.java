package spinno.com.munchoba.parsingforapi;

import android.content.Context;
import android.content.Intent;
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

import spinno.com.munchoba.Photoopenactivity;
import spinno.com.munchoba.fragmentsformainscreen.Imagesadapter;
import spinno.com.munchoba.fragmentsformainscreen.Photoactivity;
import spinno.com.munchoba.fragmentsformainscreen.Photoimagesadapter;
import spinno.com.munchoba.settergetter.Innerdataforphotosresult;
import spinno.com.munchoba.settergetter.Innerdataphotos;
import spinno.com.munchoba.settergetter.photoalbum;
import spinno.com.munchoba.singleton.VolleySingleton;
import spinno.com.munchoba.urlapi.Api_s;


public class parsingformyphotos {


    public static RequestQueue queue;
    public static StringRequest sr3;
    public static String abc = "hhhh";
    public static List<Innerdataforphotosresult> data_list3;
    public static List<List<Innerdataphotos>> data_list4;
   public static ArrayList<String> data_list5 =new ArrayList<>();
    public static ArrayList<String> id = new ArrayList<String>();
    public static ArrayList<String> user_id = new ArrayList<String>();
    public static ArrayList<String> name = new ArrayList<String>();
    public static ArrayList<String> description = new ArrayList<String>();
    public static ArrayList<String> location = new ArrayList<String>();
    public static ArrayList<String> created = new ArrayList<String>();
    public static ArrayList<String> idphoto = new ArrayList<String>();
    public static ArrayList<String[]> idphoto2 = new ArrayList<>();
    public static ArrayList<String> captionphoto = new ArrayList<String>();
    public static ArrayList<String[]> captionphoto2 = new ArrayList<>();
    public static ArrayList<String> thumbnailphoto = new ArrayList<String>();
    public static ArrayList<List<String>> thumbnailphoto2 = new ArrayList<List<String>>();
    public static ArrayList<String[]> thumbnailphoto22 = new ArrayList<>();
    public static ArrayList<String> fullphoto = new ArrayList<String>();
    public static ArrayList<String[]> fullphoto2 = new ArrayList<>();
    public static ArrayList<String> thumbnail22 = new ArrayList<String>();








    public static void parsingforphotos(final Context activity){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(activity);
        String id2 = prefs.getString("id", null);
        id2=id2.trim();


        String foodidurl = Api_s.myphotoalbums.concat(id2);
        Log.e("id", "" + foodidurl);
        queue = VolleySingleton.getInstance(activity).getRequestQueue();
        sr3 = new StringRequest(Request.Method.GET, foodidurl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
              //  Toast.makeText(activity,""+response,Toast.LENGTH_SHORT).show();
                Photoactivity.pb.setVisibility(View.GONE);
                Photoactivity.gd.setVisibility(View.VISIBLE);
                id.clear();
                user_id.clear();
                name.clear();
                description.clear();
                location.clear();
                created.clear();
                idphoto2.clear();
                fullphoto2.clear();
                captionphoto2.clear();
                thumbnailphoto2.clear();
                thumbnail22.clear();
                thumbnailphoto22.clear();


                try {
                    GsonBuilder gsonBuilder = new GsonBuilder();
                    final Gson gson = gsonBuilder.create();
                    photoalbum received2 = new photoalbum();
                    received2 = gson.fromJson(response, photoalbum.class);
                    String status = received2.status;
                    data_list3=received2.result;


                    for(int i=0;i<data_list3.size();i++){
                        id.add(data_list3.get(i).id);
                        user_id.add(data_list3.get(i).user_id);
                        name.add(data_list3.get(i).name);
                        description.add(data_list3.get(i).description);
                        location.add(data_list3.get(i).location);
                        created.add(data_list3.get(i).created);
                        if(data_list3.get(i).photos.size()==0){
                            idphoto.add("0");
                            captionphoto.add("abc");
                            thumbnailphoto.add(abc);
                            fullphoto.add(abc);
                        }
                        else {
                        for(int j=0;j<data_list3.get(i).photos.size();j++) {

                                idphoto.add(data_list3.get(i).photos.get(j).id);
                                captionphoto.add(data_list3.get(i).photos.get(j).caption);
                                thumbnailphoto.add(data_list3.get(i).photos.get(j).thumbnail);
                                fullphoto.add(data_list3.get(i).photos.get(j).image);
                            }

                        }
                        String[] stockArr = new String[idphoto.size()];
                        stockArr = idphoto.toArray(stockArr);
                        idphoto2.add(stockArr);
                        String[] stockArr2 = new String[captionphoto.size()];
                        stockArr2 = captionphoto.toArray(stockArr2);
                        captionphoto2.add(stockArr2);
                        String[] stockArr3 = new String[thumbnailphoto.size()];
                        stockArr3 = thumbnailphoto.toArray(stockArr3);
                        thumbnailphoto22.add(stockArr3);
                        thumbnailphoto2.add(thumbnailphoto);
                        Log.e("exception2222222222", "" + thumbnailphoto);
                       thumbnail22.add(thumbnailphoto2.get(0).get(0));
                        String[] stockArr4= new String[fullphoto.size()];
                        stockArr4 = fullphoto.toArray(stockArr4);
                        fullphoto2.add(stockArr4);
                        idphoto.clear();
                        captionphoto.clear();
                        thumbnailphoto.clear();
                        fullphoto.clear();
                    if(parsingfordeletephoto.count==1) {

                        Intent in = new Intent(activity,Photoopenactivity.class);
                        Photoopenactivity.php.finish();
                        activity.startActivity(in);
                        parsingfordeletephoto.count=0;
                    }
                    }



                    Photoactivity.adp = new Imagesadapter(activity,thumbnail22,name);
                    Photoactivity.gd.setAdapter(Photoactivity.adp);

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
        Photoactivity.pb.setVisibility(View.VISIBLE);
        Photoactivity.gd.setVisibility(View.GONE);

    }
}
