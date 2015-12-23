package spinno.com.munchoba.Adapterclasses;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.StringTokenizer;

import spinno.com.munchoba.R;
import spinno.com.munchoba.fragmentsformainscreen.Allpages;
import spinno.com.munchoba.fragmentsformainscreen.Detailsactivity;
import spinno.com.munchoba.library.src.com.taig.pmc.Pagesactivity;

/**
 * Created by saifi45 on 8/13/2015.
 */
public class Myallpagesadapter extends BaseAdapter {

public static ArrayList<String> name = new ArrayList<String>();
public static ArrayList<String> created = new ArrayList<String>();
public static ArrayList<String> descp = new ArrayList<String>();

LayoutInflater inflater;
Context ctc;







    public Myallpagesadapter(FragmentActivity pagesactivity, ArrayList<String> datedet, ArrayList<String> timedet, ArrayList<String> mealtypedet) {

        this.ctc=pagesactivity;
        this.name=datedet;
        this.created=timedet;
        this.descp=mealtypedet;


        inflater = LayoutInflater.from(this.ctc);
    }


    @Override
    public int getCount() {
        return name.size();
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
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.itemlayoutforpagesactiviy, null);
            holder = new Holder();

            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        holder.tv1 = (TextView) convertView.findViewById(R.id.name);
        holder.tv2 = (TextView) convertView.findViewById(R.id.crt);
        holder.tv3 = (TextView) convertView.findViewById(R.id.descp);


        StringTokenizer tokens = new StringTokenizer(descp.get(position), ">");
        String first = tokens.nextToken();// this will contain "Fruit"
        String second = tokens.nextToken();
        Log.e("stttttt"," "+second);

       StringTokenizer tokens1 = new StringTokenizer(second, "<");
        String first1 = tokens1.nextToken();// this will contain "Fruit"
        String second1 = tokens1.nextToken();
        Log.e("stttttt22", " " + first1);

        if(name.size()==0){
           Allpages.pagenot.setVisibility(View.VISIBLE);
            Allpages.list.setVisibility(View.GONE);
        }
        else {
            Allpages.pagenot.setVisibility(View.GONE);
            Allpages.list.setVisibility(View.VISIBLE);
            holder.tv1.setText(name.get(position));
            holder.tv2.setText(created.get(position));
            holder.tv3.setText(first1);
        }


        //holder.tv1.setText(data5[position]);

        return convertView;

    }
}