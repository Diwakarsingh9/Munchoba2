package spinno.com.munchoba.Adapterclasses;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.StringTokenizer;

import spinno.com.munchoba.R;
import spinno.com.munchoba.fragmentsformainscreen.Allpages;
import spinno.com.munchoba.fragmentsformainscreen.Status;
import spinno.com.munchoba.library.src.com.taig.pmc.Tagactivity;
import spinno.com.munchoba.library.src.com.taig.pmc.Tagactivityadapter;


public class Removefriendsadapter extends BaseAdapter {

    public static ArrayList<String> name = new ArrayList<String>();


    LayoutInflater inflater;
    Context ctc;







    public Removefriendsadapter(FragmentActivity pagesactivity, ArrayList<String> datedet) {

        this.ctc=pagesactivity;
        this.name=datedet;



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

        TextView tv1;
        ImageView img;

    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final Holder holder;
        //Toast.makeText(ctc, ""+filteredData.size(),Toast.LENGTH_SHORT).show();
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.itemlayoutforremovedialog, null);
            holder = new Holder();

            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        holder.tv1 = (TextView) convertView.findViewById(R.id.personname);
        holder.img = (ImageView) convertView.findViewById(R.id.imgclose);

        holder.img .setTag(position);
        if(name.size()==0){
            holder.tv1.setText("No friends get tagged");
        }
        else {

            holder.tv1.setText(name.get(position));

        }
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer index = (Integer) view.getTag();
                //items.remove(index.intValue());
                name.remove(position);
                notifyDataSetChanged();
                //Tagactivityadapter.tagfriendslist.remove(position);



            }
        });

        //holder.tv1.setText(data5[position]);

        return convertView;

    }


}