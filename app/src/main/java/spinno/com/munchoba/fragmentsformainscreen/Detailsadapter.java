package spinno.com.munchoba.fragmentsformainscreen;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import spinno.com.munchoba.Exercisediarydetails;
import spinno.com.munchoba.R;



public class Detailsadapter extends BaseAdapter  {


    public static ArrayList<String> datedet2 = new ArrayList<String>();
    public static ArrayList<String> timedet2 = new ArrayList<String>();
    public static ArrayList<String> mealtypedet2 = new ArrayList<String>();
    public static ArrayList<String> measurementdet2 = new ArrayList<String>();
    public static ArrayList<String> caloriesdet2 = new ArrayList<String>();
    public static ArrayList<String> countdet2 = new ArrayList<String>();
    public static ArrayList<String> fooddet2 = new ArrayList<String>();
    LayoutInflater inflater;
    Context ctc;







    public Detailsadapter(Detailsactivity detailsactivity, ArrayList<String> datedet, ArrayList<String> timedet, ArrayList<String> mealtypedet, ArrayList<String> measurementdet, ArrayList<String> caloriesdet, ArrayList<String> countdet, ArrayList<String> fooddet) {

        this.ctc=detailsactivity;
      this.datedet2=datedet;
        this.timedet2=timedet;
        this.mealtypedet2=mealtypedet;
        this.measurementdet2=measurementdet;
        this.caloriesdet2=caloriesdet;
        this.countdet2=countdet;
        this.fooddet2=fooddet;

        inflater = LayoutInflater.from(this.ctc);
    }

    public Detailsadapter(Exercisediarydetails exercisediarydetails, ArrayList<String> datedet, ArrayList<String> timedet, ArrayList<String> activitytypedet, ArrayList<String> workouttype, ArrayList<String> measurementdet, ArrayList<String> caloriesdet, ArrayList<String> countdet) {
        this.ctc=exercisediarydetails;
        this.datedet2=datedet;
        this.timedet2=timedet;
        this.mealtypedet2=workouttype;
        this.measurementdet2=measurementdet;
        this.caloriesdet2=caloriesdet;
        this.countdet2=countdet;
        this.fooddet2=activitytypedet;

        inflater = LayoutInflater.from(this.ctc);
    }


    @Override
    public int getCount() {
        return datedet2.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public  class Holder{

        TextView tv1,tv2,tv3,tv4,tv5,tv6,tv7;


    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final Holder holder;
        //Toast.makeText(ctc, ""+filteredData.size(),Toast.LENGTH_SHORT).show();
        if(convertView== null) {
            convertView = inflater.inflate(R.layout.itemlayoutfordetailsact, null);
            holder = new Holder();

            convertView.setTag(holder);
        }

        else {
            holder = (Holder)convertView.getTag();
        }

        holder.tv1 = (TextView)convertView.findViewById(R.id.datedetails);
        holder.tv2 = (TextView)convertView.findViewById(R.id.timedetails);
        holder.tv3 = (TextView)convertView.findViewById(R.id.mealtype);
        holder.tv4 = (TextView)convertView.findViewById(R.id.food);
        holder.tv5 = (TextView)convertView.findViewById(R.id.count);
        holder.tv6 = (TextView)convertView.findViewById(R.id.ut);
        holder.tv7 = (TextView)convertView.findViewById(R.id.cal);

        holder.tv1.setText(datedet2.get(position));
        holder.tv2.setText(timedet2.get(position));
        holder.tv3.setText(mealtypedet2.get(position));
        holder.tv4.setText(fooddet2.get(position));
        holder.tv5.setText(countdet2.get(position));
        holder.tv6.setText(measurementdet2.get(position));
        holder.tv7.setText(caloriesdet2.get(position));




        //holder.tv1.setText(data5[position]);

        return convertView;
    }


}
