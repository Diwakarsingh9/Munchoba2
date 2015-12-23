package spinno.com.munchoba.Adapterclasses;


    import android.content.Context;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.BaseAdapter;
    import android.widget.ImageView;
    import android.widget.TextView;

    import java.util.ArrayList;
    import java.util.List;

    import spinno.com.munchoba.R;
    import spinno.com.munchoba.imageloading.ImageLoader;

    public class Myspottingadapter extends BaseAdapter {

        Context ctc;
        LayoutInflater inflater;
        List<String> ad;
        ArrayList<String> ad2;
        ArrayList<String> ad3;
        int c[];
        ImageLoader il;

        public Myspottingadapter(Context context, ArrayList<String> usernames, ArrayList<String> about, ArrayList<String> strings) {
            ctc=context;
            ad=usernames;
            ad2=about;
            ad3=strings;
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
                convertView = inflater.inflate(R.layout.itemlayoutforspotters, null);
                holder = new Holder();
                convertView.setTag(holder);
            }
            else {
                holder = (Holder)convertView.getTag();
            }

            holder.tv2 = (TextView)convertView.findViewById(R.id.about);
            holder.tv1 = (TextView)convertView.findViewById(R.id.personname);
            holder.f1 = (ImageView)convertView.findViewById(R.id.circle22);
            holder.tv1.setText(""+ad.get(position));
            holder.tv2.setText(""+ad2.get(position));
            il.DisplayImage(ad3.get(position), holder.f1);
            return convertView;
        }
    }
