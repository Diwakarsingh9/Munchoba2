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

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import spinno.com.munchoba.fragmentsformainscreen.FitnessfeedAdapter;
import spinno.com.munchoba.fragmentsformainscreen.Viewprofileactivity;
import spinno.com.munchoba.settergetter.myactivitysettergetter;
import spinno.com.munchoba.settergetter.myactivitystart;
import spinno.com.munchoba.settergetter.photosactivitysettergetter;
import spinno.com.munchoba.singleton.VolleySingleton;
import spinno.com.munchoba.urlapi.Api_s;

/**
 * Created by saifi45 on 10/8/2015.
 */
public class parsingformyactivity {



    public static RequestQueue queue;

    public static StringRequest sr9;

    public static List<photosactivitysettergetter> photodatalist;
    public static ArrayList<List<photosactivitysettergetter>> photomain = new ArrayList<List<photosactivitysettergetter>>();
    public static ArrayList<ArrayList<String>> idphotomain = new ArrayList<>();
    public static ArrayList<List<String>> thumnailphotomain = new ArrayList<>();
    public static ArrayList<List<String>> captionmain = new ArrayList<>();
    public static ArrayList<List<String>> imagemain = new ArrayList<>();
    public static List<myactivitysettergetter> data_list3;
    public static String saa[]={" "," "};
    public static ArrayList<String> empty = new ArrayList<String>();
    public static ArrayList<String> id33 = new ArrayList<String>();
    public static ArrayList<String> commenttype = new ArrayList<String>();
    public static ArrayList<String> likes22 = new ArrayList<String>();
    public static ArrayList<String> foodtitle = new ArrayList<String>();
    public static ArrayList<String> title11 = new ArrayList<String>();
    public static ArrayList<String> title11profile = new ArrayList<String>();
    public static ArrayList<String> title11photos = new ArrayList<String>();
    public static ArrayList<String> created1 = new ArrayList<String>();
    public static ArrayList<String> calories11 = new ArrayList<String>();
    public static ArrayList<String> volume = new ArrayList<String>();
    public static ArrayList<String> volume_unit = new ArrayList<String>();
    public static ArrayList<String> food_id = new ArrayList<String>();
    public static ArrayList<String> total_cal = new ArrayList<String>();
    public static ArrayList<String> graph_image = new ArrayList<String>();
    public static ArrayList<String> graph_image2 = new ArrayList<String>();
    public static ArrayList<String> workout_time = new ArrayList<String>();
    public static ArrayList<String> workout_calories = new ArrayList<String>();
    public static ArrayList<String> mood = new ArrayList<String>();
    public static ArrayList<String> mood22 = new ArrayList<String>();
    public static ArrayList<String> typevideo = new ArrayList<String>();
    public static ArrayList<String> description11 = new ArrayList<String>();
    public static ArrayList<String> thumb = new ArrayList<String>();
    public static ArrayList<String> path = new ArrayList<String>();
    public static ArrayList<String> thumbnailphoto;
    public static ArrayList<String> idphoto ;
    public static ArrayList<String> captionphoto ;
    public static ArrayList<String> imagephoto;

    public static void parsingactivity(final Context applicationContext) {


        for(int y=0;y<saa.length;y++){
            empty.add(saa[y]);
        }
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(applicationContext);
        String id2 = prefs.getString("id", null);
        id2 = id2.trim();

        String urlact = Api_s.myactivitypost.concat(id2);
        Log.e("id", "" + urlact);
        queue = VolleySingleton.getInstance(applicationContext).getRequestQueue();
        sr9 = new StringRequest(Request.Method.GET, urlact, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                id33.clear();
                commenttype.clear();
                foodtitle.clear();
                title11.clear();
                title11profile.clear();
                title11photos.clear();
                created1.clear();
                calories11.clear();
                volume.clear();
                volume_unit.clear();
                food_id.clear();
                total_cal.clear();
                graph_image.clear();
                graph_image2.clear();
                workout_time.clear();
                workout_calories.clear();
                mood.clear();
                mood22.clear();
                typevideo.clear();
                description11.clear();
                thumb.clear();
                path.clear();



                try {
                    GsonBuilder gsonBuilder = new GsonBuilder();
                    final Gson gson = gsonBuilder.create();
                    myactivitystart received2 = new myactivitystart();
                    received2 = gson.fromJson(response, myactivitystart.class);
                    data_list3 = received2.myact;

                    for (int i = 0; i < data_list3.size(); i++) {
                        id33.add(data_list3.get(i).id);
                        commenttype.add(data_list3.get(i).comment_type);
                        created1.add(data_list3.get(i).created);
                        likes22.add("0");
                        if (data_list3.get(i).comment_type.equals("food")) {
                            foodtitle.add(data_list3.get(i).food_title);
                            calories11.add(data_list3.get(i).calories);
                            volume.add(data_list3.get(i).volume);
                            volume_unit.add(data_list3.get(i).volume_unit);
                            food_id.add(data_list3.get(i).food_id);
                            total_cal.add(data_list3.get(i).total_cal);
                            title11.add("");
                            workout_time.add("");
                            workout_calories.add("");
                            mood.add("");
                            title11photos.add("");
                            title11profile.add("");
                            mood22.add("");
                            graph_image.add("");
                            graph_image2.add("");
                            typevideo.add("");
                            description11.add("");
                            thumb.add("");
                            path.add("");
                            photomain.add(photodatalist);
                            idphotomain.add(empty);
                            captionmain.add(empty);
                            imagemain.add(empty);
                            thumnailphotomain.add(empty);


                        } else if (data_list3.get(i).comment_type.equals("profile.status.workout")) {
                            foodtitle.add("");
                            calories11.add("");
                            volume.add("");
                            volume_unit.add("");
                            food_id.add("");
                            total_cal.add("");
                            title11.add(data_list3.get(i).title1);
                            workout_time.add(data_list3.get(i).workout_time);
                            workout_calories.add(data_list3.get(i).workout_calories);

                            if (data_list3.get(i).mood==JSONObject.NULL) {

                                mood.add("");
                            } else {

                                mood.add(data_list3.get(i).mood);
                            }
                            Log.e("mood", " moodw" + mood);
                            title11profile.add("");
                            mood22.add("");
                            title11photos.add("");
                            graph_image.add("");
                            graph_image2.add("");
                            typevideo.add("");
                            description11.add("");
                            thumb.add("");
                            path.add("");
                            photomain.add(photodatalist);
                            idphotomain.add(empty);
                            captionmain.add(empty);
                            imagemain.add(empty);
                            thumnailphotomain.add(empty);


                        } else if (data_list3.get(i).comment_type.equals("profile.status")) {
                            foodtitle.add("");
                            calories11.add("");
                            volume.add("");
                            volume_unit.add("");
                            food_id.add("");
                            total_cal.add("");
                            title11.add("");
                            workout_time.add("");
                            workout_calories.add("");
                            mood.add("");
                            title11profile.add(data_list3.get(i).title1);

                            if (data_list3.get(i).mood==JSONObject.NULL) {

                                mood22.add("");
                            } else {

                                mood22.add(data_list3.get(i).mood);
                            }

                            graph_image.add("");
                            graph_image2.add("");
                            typevideo.add("");
                            description11.add("");
                            thumb.add("");
                            path.add("");
                            photomain.add(photodatalist);
                            title11photos.add("");
                            idphotomain.add(empty);
                            captionmain.add(empty);
                            imagemain.add(empty);
                            thumnailphotomain.add(empty);


                        } else if (data_list3.get(i).comment_type.equals("food_graph")) {
                            foodtitle.add("");
                            calories11.add("");
                            volume.add("");
                            volume_unit.add("");
                            food_id.add("");
                            total_cal.add("");
                            title11.add("");
                            workout_time.add("");
                            workout_calories.add("");
                            mood.add("");
                            title11profile.add("");
                            mood22.add("");
                            title11photos.add("");
                            graph_image2.add("");
                            typevideo.add("");
                            description11.add("");
                            thumb.add("");
                            path.add("");
                            photomain.add(photodatalist);
                            graph_image.add(data_list3.get(i).graph_image);
                            idphotomain.add(empty);
                            captionmain.add(empty);
                            imagemain.add(empty);
                            thumnailphotomain.add(empty);


                        } else if (data_list3.get(i).comment_type.equals("exercise_graph")) {
                            foodtitle.add("");
                            calories11.add("");
                            volume.add("");
                            volume_unit.add("");
                            food_id.add("");
                            total_cal.add("");
                            title11.add("");
                            workout_time.add("");
                            workout_calories.add("");
                            mood.add("");
                            title11profile.add("");
                            mood22.add("");
                            graph_image.add("");
                            title11photos.add("");
                            typevideo.add("");
                            description11.add("");
                            thumb.add("");
                            path.add("");
                            photomain.add(photodatalist);
                            graph_image2.add(data_list3.get(i).graph_image);
                            idphotomain.add(empty);
                            captionmain.add(empty);
                            imagemain.add(empty);
                            thumnailphotomain.add(empty);


                        } else if (data_list3.get(i).comment_type.equals("videos.linking")) {
                            foodtitle.add("");
                            calories11.add("");
                            volume.add("");
                            volume_unit.add("");
                            food_id.add("");
                            total_cal.add("");
                            title11.add("");
                            workout_time.add("");
                            workout_calories.add("");
                            mood.add("");
                            title11profile.add("");
                            mood22.add("");
                            graph_image.add("");
                            graph_image2.add("");
                            title11photos.add("");
                            photomain.add(photodatalist);
                            typevideo.add(data_list3.get(i).type);
                            description11.add(data_list3.get(i).description);
                            thumb.add(data_list3.get(i).thumb);
                            path.add(data_list3.get(i).path);
                            idphotomain.add(empty);
                            captionmain.add(empty);
                            imagemain.add(empty);
                            thumnailphotomain.add(empty);

                        } else if (data_list3.get(i).comment_type.equals("photos")) {

                            foodtitle.add("");
                            calories11.add("");
                            volume.add("");
                            volume_unit.add("");
                            food_id.add("");
                            total_cal.add("");
                            title11.add("");
                            workout_time.add("");
                            workout_calories.add("");
                            mood.add("");
                            mood22.add("");
                            title11profile.add("");
                            graph_image.add("");
                            graph_image2.add("");
                            title11photos.add("");
                            typevideo.add(data_list3.get(i).type);
                            description11.add(data_list3.get(i).description);
                            thumb.add(data_list3.get(i).thumb);
                            path.add(data_list3.get(i).path);
                            title11photos.add(data_list3.get(i).title1);
                            photodatalist = data_list3.get(i).photos11;
                            photomain.add(photodatalist);
                            Log.e("phtomain", "" + i + "  " + photodatalist.size());
                            Log.e("size", "" + i + "  " + photomain.get(i).size());
                            idphoto = new ArrayList<String>();
                            captionphoto = new ArrayList<String>();
                            imagephoto = new ArrayList<String>();
                            thumbnailphoto = new ArrayList<String>();
                            for (int j = 0; j <photomain.get(i).size(); j++) {
                                Log.e("value", "" + j + "  " + photomain.get(i).get(j).thumbnail);
                                idphoto.add(photomain.get(i).get(j).id11);
                                captionphoto.add(photomain.get(i).get(j).caption);
                                imagephoto.add(photomain.get(i).get(j).image11);
                                thumbnailphoto.add(photomain.get(i).get(j).thumbnail);

                            }

                            idphotomain.add(idphoto);
                            captionmain.add(captionphoto);
                            imagemain.add(imagephoto);
                            thumnailphotomain.add(thumbnailphoto);
                            Log.e("idphotomain", "  " + thumnailphotomain);


                        } else {
                            //
                        }


                    }

                    Viewprofileactivity.fitnessfeedAdapter = new FitnessfeedAdapter(applicationContext, parsingformyactivity.id33, parsingformyactivity.commenttype, parsingformyactivity.created1,
                            parsingformyactivity.foodtitle, parsingformyactivity.calories11, parsingformyactivity.volume, parsingformyactivity.volume_unit, parsingformyactivity.food_id, parsingformyactivity.total_cal,
                            parsingformyactivity.title11, parsingformyactivity.workout_time, parsingformyactivity.workout_calories, parsingformyactivity.mood,
                            parsingformyactivity.title11profile, parsingformyactivity.mood22, parsingformyactivity.graph_image, parsingformyactivity.graph_image2, parsingformyactivity.typevideo, parsingformyactivity.description11,
                            parsingformyactivity.thumb, parsingformyactivity.path, parsingformyactivity.title11photos, parsingformyactivity.idphotomain, parsingformyactivity.captionmain,
                            parsingformyactivity.thumnailphotomain, parsingformyactivity.imagemain,likes22);
                    Viewprofileactivity.listviewfeed.setAdapter(Viewprofileactivity.fitnessfeedAdapter);
                   // Viewprofileactivity.setListViewHeightBasedOnChildren(Viewprofileactivity.listviewfeed);
                    Viewprofileactivity.listviewfeed.setFocusable(false);
                    Viewprofileactivity.pb33.setVisibility(View.GONE);
                    Viewprofileactivity.listviewfeed.setVisibility(View.VISIBLE);
                   //Log.e("parsingdata", foodtitle + " " + calories11 + "  " + volume);

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
        sr9.setRetryPolicy(new DefaultRetryPolicy(50000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(sr9);
        Viewprofileactivity.pb33.setVisibility(View.VISIBLE);
        Viewprofileactivity.listviewfeed.setVisibility(View.GONE);

    }

}
