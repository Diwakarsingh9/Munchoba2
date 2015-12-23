package spinno.com.munchoba.fragmentsformainscreen;

import android.app.Activity;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import spinno.com.munchoba.R;

public class Mybmigraph extends Fragment {
    LinearLayout graph;

    public static Mybmigraph newInstance(String param1, String param2) {
        Mybmigraph fragment = new Mybmigraph();
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
        View v = inflater.inflate(R.layout.fragment_mybmigraph, container, false);
        graph=(LinearLayout)v.findViewById(R.id.layoutgraph);

        GraphView graph = (GraphView) v.findViewById(R.id.graph);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[] {
                new DataPoint(0, 0),
                new DataPoint(1, 1),
                new DataPoint(2, 2),
                new DataPoint(3, 3),
                new DataPoint(4, 4),
                new DataPoint(5, 5)
        });
        graph.addSeries(series);
        series.setColor(Color.parseColor("#FF991C"));
        // titles
        graph.setTitle("My Bmi Graph --> (Under Development)  ");
        graph.setTitleColor(Color.parseColor("#a61c1e"));
        graph.setTitleTextSize(25);
        graph.getGridLabelRenderer().setVerticalAxisTitle("BMI");
        graph.getGridLabelRenderer().setHorizontalAxisTitle("Date");

        // optional styles


        //graph.setTitleColor(Color.BLUE);
        //graph.getGridLabelRenderer().setVerticalAxisTitleTextSize(40);
        graph.getGridLabelRenderer().setVerticalAxisTitleColor(Color.parseColor("#a61c1e"));
        //graph.getGridLabelRenderer().setHorizontalAxisTitleTextSize(40);
        graph.getGridLabelRenderer().setHorizontalAxisTitleColor(Color.parseColor("#a61c1e"));

        return v;
    }


}
