package spinno.com.munchoba.Adapterclasses;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import spinno.com.munchoba.R;
import spinno.com.munchoba.imageloading.ImageLoader;

public class Myadaptericon extends BaseAdapter {

    Context ctc;
    LayoutInflater inflater;
    List<String> ad;
    String[] ad2;
    String[] ad3;
    int c[];
     ImageLoader il;

    public Myadaptericon(Context context, List<String> strings) {
        ctc=context;
        ad=strings;

        inflater = LayoutInflater.from(this.ctc);
          il=new ImageLoader(ctc.getApplicationContext());
    }

    @Override
    public int getCount() {
        return ad.size();
    }

    @Override
    public Object getItem(int position) {
        //Log.e("position", "Dish" + position);
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public  class Holder{

        TextView tv1,tv2;
        ImageView f1;
        View v1;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final Holder holder;
        if(convertView== null) {
            convertView = inflater.inflate(R.layout.layoutforhori, null);
            holder = new Holder();
            convertView.setTag(holder);
        }
        else {
            holder = (Holder)convertView.getTag();
        }



        holder.f1 = (ImageView)convertView.findViewById(R.id.circleView);
        il.DisplayImage(ad.get(position), holder.f1);
        return convertView;
    }
}