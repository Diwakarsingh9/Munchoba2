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

import spinno.com.munchoba.fragmentsformainscreen.Weight_Management;
import spinno.com.munchoba.settergetter.Bodyfatadd;
import spinno.com.munchoba.settergetter.Weightadd;
import spinno.com.munchoba.singleton.VolleySingleton;
import spinno.com.munchoba.urlapi.Api_s;

/**
 * Created by saifi45 on 9/3/2015.
 */
public class parsingforaddingweightandbodyfat {

    public static  RequestQueue queue;
    public static StringRequest sr1,sr2;
  public static void  parsingforaddingweight(final FragmentActivity activity) {

      SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(activity);
      String id = prefs.getString("id", null);
      id = id.trim();
      Log.e("id", "id:" + id);
      if (id.equals("")) {
          Toast.makeText(activity, "Please Check Your Internet Connection.", Toast.LENGTH_SHORT).show();
      } else {
          String foodidurl = Api_s.weightaddstart.concat("" + Weight_Management.datevalue).concat(Api_s.weightaddmid).
                  concat(Weight_Management.hourvalue + ":" + Weight_Management.minsvalue).concat(Api_s.weightaddmid2).
                  concat(Weight_Management.weight.getText() + "").concat(Api_s.weightaddmid3).concat(Weight_Management.wgtunitvalue)
                  .concat(Api_s.weightaddlast).concat(id);
          Log.e("id", "" + foodidurl);
          queue = VolleySingleton.getInstance(activity).getRequestQueue();
          sr2 = new StringRequest(Request.Method.GET, foodidurl, new Response.Listener<String>() {
              @Override
              public void onResponse(String response) {
                  Weight_Management.pb.setVisibility(View.GONE);
                  Weight_Management.save.setVisibility(View.VISIBLE);


                  try {
                      GsonBuilder gsonBuilder = new GsonBuilder();
                      final Gson gson = gsonBuilder.create();
                      Weightadd received2 = new Weightadd();
                      received2 = gson.fromJson(response, Weightadd.class);
                      String status = received2.message;
                      String message = received2.result;

                      if (status.equals("success")) {
                          Toast.makeText(activity, "" + message, Toast.LENGTH_SHORT).show();

                      } else {
                          Toast.makeText(activity, "" + message, Toast.LENGTH_SHORT).show();
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
          sr2.setRetryPolicy(new DefaultRetryPolicy(50000,
                  DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                  DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
          queue.add(sr2);
          Weight_Management.pb.setVisibility(View.VISIBLE);
          Weight_Management.save.setVisibility(View.GONE);

      }
  }

    public static void  parsingforaddingbodyfat(final FragmentActivity activity) {

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(activity);
        String id = prefs.getString("id", null);
        id = id.trim();
        Log.e("id", "id:" + id);
        if (id.equals("")) {
            Toast.makeText(activity, "Please Check Your Internet Connection.", Toast.LENGTH_SHORT).show();
        } else {
            String foodidurl = Api_s.addbodyfat1.concat("" + Weight_Management.datevalue).concat(Api_s.addbodyfat2).
                    concat(Weight_Management.bodyfat.getText().toString()).concat(Api_s.addbodyfat3)
                    .concat(Api_s.weightaddlast).concat(id);
            Log.e("id", "" + foodidurl);
            queue = VolleySingleton.getInstance(activity).getRequestQueue();
            sr1 = new StringRequest(Request.Method.GET, foodidurl, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Weight_Management.pb.setVisibility(View.GONE);
                    Weight_Management.save.setVisibility(View.VISIBLE);


                    try {
                        GsonBuilder gsonBuilder = new GsonBuilder();
                        final Gson gson = gsonBuilder.create();
                        Bodyfatadd received2 = new Bodyfatadd();
                        received2 = gson.fromJson(response, Bodyfatadd.class);
                        String status = received2.message;
                        String message = received2.result;

                        if (status.equals("success")) {
                            Toast.makeText(activity, "" + message, Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(activity, "" + message, Toast.LENGTH_SHORT).show();
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
            Weight_Management.pb.setVisibility(View.VISIBLE);
            Weight_Management.save.setVisibility(View.GONE);

        }
    }
}
