package spinno.com.munchoba.fragmentsformainscreen;

import android.app.Activity;
import android.content.Context;
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
import spinno.com.munchoba.parsingbodyfat;
import spinno.com.munchoba.settergetter.Innerdata2;
import spinno.com.munchoba.settergetter.Innerdata4;
import spinno.com.munchoba.settergetter.bodygraph;
import spinno.com.munchoba.settergetter.fooddiary;
import spinno.com.munchoba.singleton.VolleySingleton;
import spinno.com.munchoba.urlapi.Api_s;

public class Mybodyfatpercentagegraph extends Fragment {
    //LinearLayout graph;
    public static Context ctc;
    public static SimpleDateFormat formatter ;
    public static Date dates ;

    public static GraphView graph;

    public static  ProgressBar pb;


    public static Mybodyfatpercentagegraph newInstance(String param1, String param2) {
        Mybodyfatpercentagegraph fragment = new Mybodyfatpercentagegraph();
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
        View v = inflater.inflate(R.layout.fragment_mybodyfatpercentagegraph, container, false);
        //graph=(LinearLayout)v.findViewById(R.id.layoutgraph);

        graph = (GraphView) v.findViewById(R.id.graph);
        pb= (ProgressBar) v.findViewById(R.id.pb);
        ctc = getActivity();
        parsingbodyfat.parsingdata(getActivity());

        //setgraph();
        return v;

    }

    private void parsing() {


    }
    public static void setgraph() {
        LineGraphSeries<DataPoint>  series = new LineGraphSeries<DataPoint>(generateData());

        graph.addSeries(series);
        series.setColor(Color.parseColor("#FF991C"));
        // titles

        graph.setTitle("My Body Fat % Graph");
        graph.setTitleColor(Color.parseColor("#a61c1e"));
        graph.getGridLabelRenderer().setVerticalAxisTitle("Body Fat Percentage");
        graph.getGridLabelRenderer().setHorizontalAxisTitle("Date");

        // optional styles
        graph.setTitleTextSize(25);
        graph.getViewport().setXAxisBoundsManual(true);
        graph.onDataChanged(false, false);

        // enable scrolling
        // graph.getViewport().setScrollable(true);

        try {
            graph.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(ctc));
        }catch (Exception e){}
        graph.getGridLabelRenderer().setNumHorizontalLabels(3); // only 4 because of the space
        graph.getGridLabelRenderer().setVerticalAxisTitleColor(Color.parseColor("#a61c1e"));
        graph.getGridLabelRenderer().setHorizontalAxisTitleColor(Color.parseColor("#a61c1e"));
        graph.getViewport().setXAxisBoundsManual(true);
    }

    public static DataPoint[] generateData() {
        int count = parsingbodyfat.fat11.size();
        DataPoint[] values = new DataPoint[count];
        formatter = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        for (int i=0; i<count; i++) {
            try {
               // Log.e("date;;;;;;"," "+parsingbodyfat.date11.get(i));
                dates = (Date) formatter.parse(parsingbodyfat.date11.get(i));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Calendar cal=Calendar.getInstance();
            cal.setTime(dates);
            Date x =  dates;
            //double f = mRand.nextDouble()*0.15+0.3;
            int y = Integer.parseInt(parsingbodyfat.fat11.get(i));
            DataPoint v = new DataPoint(x, y);
            values[i] = v;
        }
        return values;
    }

}
