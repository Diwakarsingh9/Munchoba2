package spinno.com.munchoba.fragmentsformainscreen;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import spinno.com.munchoba.Photoopenactivity;
import spinno.com.munchoba.R;
import spinno.com.munchoba.imageloading.ImageLoader;
import spinno.com.munchoba.parsingforapi.parsingformyphotos;


public class Photoimagesadapter extends BaseAdapter {
    Context cont;
    String[] d;
    String[] name22;
    ImageLoader il;
    LayoutInflater inflater;



    public Photoimagesadapter(Photoopenactivity photoopenactivity, String[] s, String[] name) {
        this.cont=photoopenactivity;
        d=s;
        name22=name;
        il=new ImageLoader(cont.getApplicationContext());
        inflater = LayoutInflater.from(this.cont);
    }

    @Override
    public int getCount() {
        return d.length;
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

        TextView tv1,tv2;

        ImageView img;


    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Holder holder;
        if(convertView== null) {
            convertView = inflater.inflate(R.layout.photoopen, null);
            holder = new Holder();
            convertView.setTag(holder);

            //

        }

        else {
            holder = (Holder)convertView.getTag();
        }
        holder.img = (ImageView)convertView.findViewById(R.id.imageVi12);
        //holder.tv1 = (TextView)convertView.findViewById(R.id.txt22);


        if(d.length==0){
            //holder.tv1.setText("NA");
            // holder.tv2.setText("NA");
            //Log.e("dewsss22", "" + d[position]);
            il.DisplayImage(d[position], holder.img);
        }
        else {
          //  Log.e("dewsss22", "" + d[position]);

            il.DisplayImage(d[position], holder.img);



        }

        return convertView;
    }

}
