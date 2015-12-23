package spinno.com.munchoba.library.src.com.taig.pmc;


        import android.content.Context;
        import android.graphics.Color;
        import android.graphics.drawable.Drawable;
        import android.os.Handler;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.AdapterView;
        import android.widget.BaseAdapter;
        import android.widget.Button;
        import android.widget.CheckBox;
        import android.widget.CompoundButton;
        import android.widget.Filter;
        import android.widget.Filterable;
        import android.widget.FrameLayout;
        import android.widget.ImageView;
        import android.widget.LinearLayout;
        import android.widget.TextView;
        import android.widget.Toast;

        import java.util.ArrayList;
        import java.util.HashMap;

        import spinno.com.munchoba.R;

        import static android.graphics.Color.*;

/**
 * Created by saifi45 on 7/13/2015.
 */
public class Tagactivityadapter extends BaseAdapter implements Filterable {
    String  foodeaten1,emotion,emotion2;
    String  cal,grams,cal2;
    int data2;
    boolean[] checkBoxState;
    ArrayList<Integer> data4;
    ArrayList<String> data5;
    private ArrayList<String> filteredData;
    ArrayList<Integer> clickable = new ArrayList<Integer>();
    int count ;
    public  static   ArrayList<String> FilteredArrayNames;
    public  static   ArrayList<String> tagfriendslist = new ArrayList<>();
    LayoutInflater inflater;
    Context ctc;






    public Tagactivityadapter(Context ctc, ArrayList<String> strings, ArrayList<Integer> a) {

        data5= strings;
        filteredData = strings;
        data4 =a;
        this.ctc=ctc;
        checkBoxState=new boolean[filteredData.size()];

        inflater = LayoutInflater.from(this.ctc);
    }


    @Override
    public int getCount() {
        return filteredData.size();
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
        CheckBox ch;
        FrameLayout fl ;
        LinearLayout layout;

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final Holder holder;
        //Toast.makeText(ctc, ""+filteredData.size(),Toast.LENGTH_SHORT).show();
        if(convertView== null) {
            convertView = inflater.inflate(R.layout.itemlayoutfortagfriends, null);
            holder = new Holder();

            convertView.setTag(holder);
        }

        else {
            holder = (Holder)convertView.getTag();
        }

        holder.tv1 = (TextView)convertView.findViewById(R.id.personname);
        holder.img = (ImageView)convertView.findViewById(R.id.imageView21);
       // holder.ch = (CheckBox)convertView.findViewById(R.id.checkBox17);
        holder.layout = (LinearLayout)convertView.findViewById(R.id.layout);



        final CheckBox checkbox=(CheckBox)convertView.findViewById(R.id.checkBox17);

        checkbox.setTag(new Integer(position));


        checkbox.setOnCheckedChangeListener(null);
        if(checkBoxState[position]) {
            checkbox.setChecked(true);
        }
        else {
            checkbox.setChecked(false);
        }


        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Integer pos = (Integer) buttonView.getTag();
                if (isChecked) {
                    checkBoxState[pos.intValue()] = true;
                    holder.tv1.getText();
                    tagfriendslist.add(holder.tv1.getText().toString());
                    final Toast toast = Toast.makeText(ctc, holder.tv1.getText()+" is tagged", Toast.LENGTH_SHORT);
                    toast.show();

                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            toast.cancel();
                        }
                    }, 200);
                } else {
                    checkBoxState[pos.intValue()] = false;
                    tagfriendslist.remove(holder.tv1.getText().toString());
                    final Toast toast = Toast.makeText(ctc, holder.tv1.getText()+" is untagged", Toast.LENGTH_SHORT);
                    toast.show();

                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            toast.cancel();
                        }
                    }, 200);


                }
            }
        });

        //holder.tv1.setText(data5[position]);
        if(filteredData.size()==0){
            holder.tv1.setText("NA");
        }
        else {
            holder.tv1.setText(filteredData.get(position));

        }
        return convertView;
    }

    @Override
    public Filter getFilter()
    {
        return new Filter()
        {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence)
            {
                FilterResults results = new FilterResults();
             FilteredArrayNames = new ArrayList<String>();
                FilteredArrayNames.clear();
                if(charSequence == null || charSequence.length() == 0)
                {
                    results.values = data5;
                    results.count = data5.size();
                }
                else
                {



                        charSequence = charSequence.toString().toLowerCase();
                        for(int i = 0; i < data5.size(); i++) {
                            String dataNames = data5.get(i);
                            if (dataNames.toLowerCase().startsWith(charSequence.toString())) {
                                FilteredArrayNames.add(dataNames);
                            }
                        }



                    results.count = FilteredArrayNames.size();
                    System.out.println(results.count);

                    results.values = FilteredArrayNames;
                    Log.e("VALUES", results.values.toString());

                }

                return results;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults)
            {
                filteredData = (ArrayList<String>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}
