package spinno.com.munchoba.fragmentsformainscreen;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import spinno.com.munchoba.R;

import static android.graphics.Color.*;

/**
 * Created by saifi45 on 7/13/2015.
 */
public class Sentnotificationadapter extends BaseAdapter {
    String  foodeaten1,emotion,emotion2;
    String  cal,grams,cal2;
    int data2;

    String []data4;
    String []data5;

    ArrayList<Integer> clickable = new ArrayList<Integer>();
    int count ;
    LayoutInflater inflater;
    Context ctc;






    public Sentnotificationadapter(Context ctc, String[] strings ) {

        data5= strings;
        this.ctc=ctc;

        inflater = LayoutInflater.from(this.ctc);
    }


    @Override
    public int getCount() {
        return data5.length;
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

        TextView tv1,tv2,tv3,tv4;
        Button spot, unspot;
        ImageView img;
        FrameLayout fl ;

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Holder holder;

        if(convertView== null) {
            convertView = inflater.inflate(R.layout.sentitemlayout, null);
            holder = new Holder();

            convertView.setTag(holder);
        }

        else {
            holder = (Holder)convertView.getTag();
        }

        holder.tv1 = (TextView)convertView.findViewById(R.id.title);
        holder.img=(ImageView)convertView.findViewById(R.id.indicator);



        //holder.tv1.setText(data5[position]);
        if(data5.length==0){
            holder.tv1.setText("NA");
        }
        else {
            holder.tv1.setText(data5[position]);

        }

        if(data5[position].equals("title 2")){
            holder.img.setImageResource(android.R.color.transparent);
        }
        else{
            holder.img.setImageResource(R.drawable.attach);
        }

        //holder.txt_itemPrice.setText(itemDetailsrrayList.get(position).getItemCost());

        return convertView;
    }
}
