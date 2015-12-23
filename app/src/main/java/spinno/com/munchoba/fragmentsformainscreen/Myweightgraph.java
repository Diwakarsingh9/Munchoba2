package spinno.com.munchoba.fragmentsformainscreen;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
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
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import spinno.com.munchoba.R;
import spinno.com.munchoba.settergetter.Innerdata2;
import spinno.com.munchoba.settergetter.Innerdata3;
import spinno.com.munchoba.settergetter.fooddiary;
import spinno.com.munchoba.settergetter.weightgraph;
import spinno.com.munchoba.singleton.VolleySingleton;
import spinno.com.munchoba.urlapi.Api_s;

public class Myweightgraph extends Fragment {

    GraphView graph;
    SimpleDateFormat formatter ;
    Date dates ;
    RequestQueue queue;
    public static StringRequest sr2;
    public static List<Innerdata3> data_list1;
    ProgressBar pb;
    ArrayList<String> Data5=new ArrayList<String>();

    public static ArrayList<String> date11 = new ArrayList<String>();
    public static ArrayList<String> time = new ArrayList<String>();
    public static ArrayList<String> date12 = new ArrayList<String>();
    public static ArrayList<String> weight12 = new ArrayList<String>();
    public static ArrayList<String> weight = new ArrayList<String>();
    public static ArrayList<String> wt_unit = new ArrayList<String>();
    public static Myweightgraph newInstance(String param1, String param2) {
        Myweightgraph fragment = new Myweightgraph();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_myweightgraph, container, false);

     graph  = (GraphView) v.findViewById(R.id.graph);
        pb= (ProgressBar) v.findViewById(R.id.pb);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String id = prefs.getString("id", null);
        id=id.trim();
        Log.e("id", "id:" + id);
        if (id.equals("")) {
            Toast.makeText(getActivity(), "Please Check Your Internet Connection.", Toast.LENGTH_SHORT).show();
        }
        else {

            String foodidurl = Api_s.weightgraph.concat(id);
            Log.e("id", "" + foodidurl);
            queue = VolleySingleton.getInstance(getActivity()).getRequestQueue();
            sr2 = new StringRequest(Request.Method.GET, foodidurl, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    pb.setVisibility(View.GONE);
                    graph.setVisibility(View.VISIBLE);
                    date11.clear();
                   weight.clear();
                    time.clear();
                   wt_unit.clear();


                    try {
                        GsonBuilder gsonBuilder = new GsonBuilder();
                        final Gson gson = gsonBuilder.create();
                        weightgraph received2 = new weightgraph();
                        received2 = gson.fromJson(response, weightgraph.class);
                        String status = received2.message;
                        data_list1=received2.innerdataweightgraph;
                        for(int i=0;i<data_list1.size();i++){
                            date11.add(data_list1.get(i).dates11);
                            weight.add(data_list1.get(i).weight);
                            time.add(data_list1.get(i).time);
                            wt_unit.add(data_list1.get(i).wunit);

                        }
                        date12=date11;
                        weight12=weight;
                        //Collections.reverse(date11);

                        setgraph();

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
            pb.setVisibility(View.VISIBLE);
            graph.setVisibility(View.GONE);

        }



        return v;

    }

    private void setgraph() {
        LineGraphSeries<DataPoint>  series = new LineGraphSeries<DataPoint>(generateData());

        graph.addSeries(series);
        series.setColor(Color.parseColor("#FF991C"));
        // titles

        graph.setTitle("My Weight Graph");
        graph.setTitleColor(Color.parseColor("#a61c1e"));
        graph.getGridLabelRenderer().setVerticalAxisTitle("Calories");
        graph.getGridLabelRenderer().setHorizontalAxisTitle("Date");

        // optional styles
        graph.setTitleTextSize(25);
        graph.getViewport().setXAxisBoundsManual(true);
        graph.onDataChanged(false, false);

        // enable scrolling
        // graph.getViewport().setScrollable(true);

        try {
            graph.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(getActivity()));
        }catch (Exception e){}
        graph.getGridLabelRenderer().setNumHorizontalLabels(3); // only 4 because of the space
        graph.getGridLabelRenderer().setVerticalAxisTitleColor(Color.parseColor("#a61c1e"));
        graph.getGridLabelRenderer().setHorizontalAxisTitleColor(Color.parseColor("#a61c1e"));
        graph.getViewport().setXAxisBoundsManual(true);
    }

    private DataPoint[] generateData() {
        int count = weight.size();
        DataPoint[] values = new DataPoint[count];
        formatter = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        for (int i=0; i<count; i++) {
            try {
                Log.e("date"," "+date11.get(i));
                dates = (Date) formatter.parse(date11.get(i));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Calendar cal=Calendar.getInstance();
            cal.setTime(dates);
            Date x =  dates;
            //double f = mRand.nextDouble()*0.15+0.3;
            Float y = Float.parseFloat(weight.get(i));
            DataPoint v = new DataPoint(x, y);
            values[i] = v;
        }
        return values;
    }

}
