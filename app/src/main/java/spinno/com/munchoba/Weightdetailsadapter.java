package spinno.com.munchoba;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import spinno.com.munchoba.fragmentsformainscreen.Detailsactivity;

public class Weightdetailsadapter extends BaseAdapter {


    public static ArrayList<String> datedet2 = new ArrayList<String>();
    public static ArrayList<String> titlesdet2 = new ArrayList<String>();

    LayoutInflater inflater;
    Context ctc;







    public Weightdetailsadapter(Weightmanagementdetails detailsactivity, ArrayList<String> datedet, ArrayList<String> titlesdet) {

        this.ctc=detailsactivity;
        this.datedet2=datedet;
        this.titlesdet2=titlesdet;


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
            convertView = inflater.inflate(R.layout.itemlayoutfordetailsinweightmgmt, null);
            holder = new Holder();

            convertView.setTag(holder);
        }

        else {
            holder = (Holder)convertView.getTag();
        }

        holder.tv1 = (TextView)convertView.findViewById(R.id.datedetails2222);
        holder.tv2 = (TextView)convertView.findViewById(R.id.title222222);


        holder.tv1.setText(datedet2.get(position));
        holder.tv2.setText(titlesdet2.get(position));





        //holder.tv1.setText(data5[position]);

        return convertView;
    }


}
