package spinno.com.munchoba.Adapterclasses;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import spinno.com.munchoba.R;
import spinno.com.munchoba.imageloading.ImageLoader;


public class Videosadapter extends BaseAdapter {
    Context cont;
    ArrayList<String> d = new ArrayList<>();
    ArrayList<String> name22 = new ArrayList<>();
    ImageLoader il;
    LayoutInflater inflater;

    public Videosadapter(Context con, ArrayList<String> t, ArrayList<String> z){
        this.cont=con;
        d=t;
        name22=z;
        il=new ImageLoader(cont.getApplicationContext());
        inflater = LayoutInflater.from(this.cont);
    }

    @Override
    public int getCount() {
        return name22.size();
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
            convertView = inflater.inflate(R.layout.imges2, null);
            holder = new Holder();
            convertView.setTag(holder);

            //

        }

        else {
            holder = (Holder)convertView.getTag();
        }
       // holder.img = (ImageView)convertView.findViewById(R.id.imageVi12);
        holder.tv1 = (TextView)convertView.findViewById(R.id.txt22);
        holder.tv2 = (TextView)convertView.findViewById(R.id.tv22);


        if(name22.size()==0){
            //holder.tv1.setText("NA");
            holder.tv2.setText("NA");
            holder.tv1.setText("NA");
           // il.DisplayImage(d.get(position), holder.img);
        }
        else {

            // String image="http://wscubetech.org/paaza/gallery_images/".concat(images1.get(position));
            holder.tv1.setText("" + d.get(position));
            holder.tv2.setText(""+name22.get(position));
            //il.DisplayImage(d.get(position), holder.img);



        }

        return convertView;
    }

}
