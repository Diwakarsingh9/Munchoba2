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
        import android.widget.CompoundButton;
        import android.widget.Filter;
        import android.widget.Filterable;
        import android.widget.FrameLayout;
        import android.widget.ImageView;
        import android.widget.LinearLayout;
        import android.widget.TextView;
        import android.widget.Toast;

        import java.util.ArrayList;

        import spinno.com.munchoba.MainActivity;
        import spinno.com.munchoba.R;


public class Locationactivityadapter extends BaseAdapter implements Filterable {
    String  foodeaten1,emotion,emotion2;
    String  cal,grams,cal2;
    int data2;
    public  static   ArrayList<String> Placedata = new ArrayList<>();
    boolean[] checkBoxState;
    ArrayList<Integer> data4;
    ArrayList<String> data5;
    private ArrayList<String> filteredData;
    ArrayList<Integer> clickable = new ArrayList<Integer>();
    int count ;
    public  static   ArrayList<String> FilteredArrayNames;
    LayoutInflater inflater;
    Context ctc;






    public Locationactivityadapter(Context ctc, ArrayList<String> strings, ArrayList<Integer> a) {
        Placedata.clear();
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
        Button Buttonview, unspot;
        ImageView img;
        CheckBox ch;
        FrameLayout fl ;
        LinearLayout layout, layout11;

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final Holder holder;
        //Toast.makeText(ctc, ""+filteredData.size(),Toast.LENGTH_SHORT).show();
        if(convertView== null) {
            convertView = inflater.inflate(R.layout.itemlayoutforlocation, null);
            holder = new Holder();

            convertView.setTag(holder);
        }

        else {
            holder = (Holder)convertView.getTag();
        }

        holder.tv1 = (TextView)convertView.findViewById(R.id.personname);
        holder.img = (ImageView)convertView.findViewById(R.id.imageView21);
        // holder.ch = (CheckBox)convertView.findViewById(R.id.checkBox17);
        holder.layout = (LinearLayout)convertView.findViewById(R.id.layout22);
        holder.layout11 = (LinearLayout)convertView.findViewById(R.id.layout11);
        holder.Buttonview = (Button)convertView.findViewById(R.id.buttonview);

        holder.Buttonview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Placedata.add("at " + holder.tv1.getText().toString());
                String[] placelist = new String[Locationactivityadapter.Placedata.size()];
                placelist = Locationactivityadapter.Placedata.toArray(placelist);
                if (placelist.length != (0)) {
                    for (int i = 0; i <= placelist.length; i++) {
                        Status.place.setText(placelist[0]);
                    }
                    Status.defaultlayout.setVisibility(View.VISIBLE);
                    Status.Statuslayout.setVisibility(View.VISIBLE);
                    Status.atlayout.setVisibility(View.VISIBLE);
                    ChangeVisibiltyoflayout();
                    Toast.makeText(ctc, "Place added", Toast.LENGTH_SHORT).show();
                    Locationactivity.locationactivity.finish();
                    InputMethodManager inputMethodManager = (InputMethodManager) Locationactivity.locationactivity.getSystemService(Activity.INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(Locationactivity.locationactivity.getCurrentFocus().getWindowToken(), 0);
                } else {
                    Locationactivity.locationactivity.finish();
                    InputMethodManager inputMethodManager = (InputMethodManager) Locationactivity.locationactivity.getSystemService(Activity.INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(Locationactivity.locationactivity.getCurrentFocus().getWindowToken(), 0);
                }

            }
        });

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Placedata.add("at " + holder.tv1.getText().toString());
                String[] placelist = new String[Locationactivityadapter.Placedata.size()];
                placelist = Locationactivityadapter.Placedata.toArray(placelist);
                if (placelist.length != (0)) {
                    for (int i = 0; i <= placelist.length; i++) {
                        Status.place.setText(placelist[0]);
                    }
                    Status.defaultlayout.setVisibility(View.VISIBLE);
                    Status.Statuslayout.setVisibility(View.VISIBLE);
                    Status.atlayout.setVisibility(View.VISIBLE);
                    ChangeVisibiltyoflayout();
                    Toast.makeText(ctc, "Place added", Toast.LENGTH_SHORT).show();
                    Locationactivity.locationactivity.finish();
                    InputMethodManager inputMethodManager = (InputMethodManager) Locationactivity.locationactivity.getSystemService(Activity.INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(Locationactivity.locationactivity.getCurrentFocus().getWindowToken(), 0);
                } else {
                    Locationactivity.locationactivity.finish();
                    InputMethodManager inputMethodManager = (InputMethodManager) Locationactivity.locationactivity.getSystemService(Activity.INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(Locationactivity.locationactivity.getCurrentFocus().getWindowToken(), 0);
                }
            }
        });

        holder.layout11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Placedata.add("at " + holder.tv1.getText().toString());
                String[] placelist = new String[Locationactivityadapter.Placedata.size()];
                placelist = Locationactivityadapter.Placedata.toArray(placelist);
                if (placelist.length != (0)) {
                    for (int i = 0; i <= placelist.length; i++) {
                        Status.place.setText(placelist[0]);
                    }
                    Status.defaultlayout.setVisibility(View.VISIBLE);
                    Status.Statuslayout.setVisibility(View.VISIBLE);
                    Status.atlayout.setVisibility(View.VISIBLE);
                    ChangeVisibiltyoflayout();
                    Toast.makeText(ctc, "Place added", Toast.LENGTH_SHORT).show();
                    Locationactivity.locationactivity.finish();
                    InputMethodManager inputMethodManager = (InputMethodManager) Locationactivity.locationactivity.getSystemService(Activity.INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(Locationactivity.locationactivity.getCurrentFocus().getWindowToken(), 0);
                } else {
                    Locationactivity.locationactivity.finish();
                    InputMethodManager inputMethodManager = (InputMethodManager) Locationactivity.locationactivity.getSystemService(Activity.INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(Locationactivity.locationactivity.getCurrentFocus().getWindowToken(), 0);
                }
            }
        });

        Locationactivity.next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Placedata.add("at " + Locationactivity.atvPlaces.getText().toString().trim());
                String[] placelist = new String[Locationactivityadapter.Placedata.size()];
                placelist = Locationactivityadapter.Placedata.toArray(placelist);
                if (placelist.length != (0)&&!Locationactivity.atvPlaces.equals("")) {
                    for (int i = 0; i <= placelist.length; i++) {
                        Status.place.setText(placelist[0]);
                    }

                    Status.defaultlayout.setVisibility(View.VISIBLE);
                    Status.Statuslayout.setVisibility(View.VISIBLE);
                    Status.atlayout.setVisibility(View.VISIBLE);
                    ChangeVisibiltyoflayout();
                    Toast.makeText(ctc, "Place added", Toast.LENGTH_SHORT).show();
                    Locationactivity.locationactivity.finish();
                    InputMethodManager inputMethodManager = (InputMethodManager) Locationactivity.locationactivity.getSystemService(Activity.INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(Locationactivity.locationactivity.getCurrentFocus().getWindowToken(), 0);
                } else {
                    Locationactivity.locationactivity.finish();
                    InputMethodManager inputMethodManager = (InputMethodManager) Locationactivity.locationactivity.getSystemService(Activity.INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(Locationactivity.locationactivity.getCurrentFocus().getWindowToken(), 0);
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
    private void ChangeVisibiltyoflayout() {
        if(Status.tagfriends.getText().toString().equals("with")&&Status.emoticon.getText().toString().equals("Emotion")){
            Status.withlayout.setVisibility(View.INVISIBLE);
            Status.emotionlayout.setVisibility(View.INVISIBLE);
        }
        else if(!Status.tagfriends.getText().toString().equals("with")&&Status.emoticon.getText().toString().equals("Emotion")){
            Status.withlayout.setVisibility(View.VISIBLE);
            Status.emotionlayout.setVisibility(View.INVISIBLE);
        }
        else if(Status.tagfriends.getText().toString().equals("with")&&!Status.emoticon.getText().toString().equals("Emotion")){
            Status.withlayout.setVisibility(View.INVISIBLE);
            Status.emotionlayout.setVisibility(View.VISIBLE);
        }
        else{
            Status.withlayout.setVisibility(View.VISIBLE);
            Status.emotionlayout.setVisibility(View.VISIBLE);
        }
    }
}
