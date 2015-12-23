package spinno.com.munchoba.fragmentsformainscreen;

        import android.app.Activity;
        import android.app.Service;
        import android.content.Context;
        import android.content.SharedPreferences;
        import android.graphics.Bitmap;
        import android.preference.PreferenceManager;
        import android.util.Base64;
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

        import java.io.ByteArrayOutputStream;
        import java.util.ArrayList;

        import spinno.com.munchoba.MainActivity;
        import spinno.com.munchoba.R;


public class Smileyactivityadapter extends BaseAdapter implements Filterable {
    String  foodeaten1,emotion,emotion2;
    String  cal,grams,cal2;
    //int data2[];
    public  static Bitmap bm;
    boolean[] checkBoxState;
    ArrayList<Integer> data4;
    ArrayList<String> data5;
    ArrayList<Integer> data2;
    public  static   ArrayList<String> smileydata = new ArrayList<>();
    public  static   ArrayList<Bitmap> smileyimage = new ArrayList<>();

    private ArrayList<String> filteredData;
    private ArrayList<Integer> filterImage;
    ArrayList<Integer> clickable = new ArrayList<Integer>();
    int count ;
    public  static   ArrayList<String> FilteredArrayNames;
    public  static   ArrayList<Integer> FilteredImages;
    LayoutInflater inflater;
    Context ctc;






    public Smileyactivityadapter(Context ctc, ArrayList<String> strings, ArrayList<Integer> a, ArrayList<Integer> smiley) {

        data5= strings;
        filteredData = strings;
        filterImage = smiley;
        data4 =a;
        data2=smiley;
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
    public View getView(final int position, View convertView, ViewGroup parent) {

        final Holder holder;
        //Toast.makeText(ctc, ""+filteredData.size(),Toast.LENGTH_SHORT).show();
        if(convertView== null) {
            convertView = inflater.inflate(R.layout.itemforsmiley, null);
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
                Status.smileyimagecopy.clear();
                Status.smileyimagecopy.add(filterImage.get(position));
                MainActivity.Smileyactivitystarted=1;
                smileydata.add("Feeling " + holder.tv1.getText().toString());
                holder.img.buildDrawingCache();
                bm = holder.img.getDrawingCache();

                smileyimage.add(bm);
                String[] smileylist = new String[Smileyactivityadapter.smileydata.size()];
                smileylist = Smileyactivityadapter.smileydata.toArray(smileylist);


                if (smileylist.length != (0)) {
                    for (int i = 0; i <= smileylist.length; i++) {
                      //  Status.smileyimage.setImageBitmap(Smileyactivityadapter.smileyimage.get(0));
                          Status.smileyimage.setImageResource(filterImage.get(position));
                        Status.emoticon.setText(smileylist[0]);
                    }

                    Status.defaultlayout.setVisibility(View.VISIBLE);
                    Status.Statuslayout.setVisibility(View.VISIBLE);
                    Status.emotionlayout.setVisibility(View.VISIBLE);
                    ChangeVisibiltyoflayout();
                    Toast.makeText(ctc, "Smiley added", Toast.LENGTH_SHORT).show();
                    Smileyactivity.smileyactivity.finish();
                    InputMethodManager inputMethodManager = (InputMethodManager) Smileyactivity.smileyactivity.getSystemService(Activity.INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(Smileyactivity.smileyactivity.getCurrentFocus().getWindowToken(), 0);
                } else {
                    Smileyactivity.smileyactivity.finish();
                    InputMethodManager inputMethodManager = (InputMethodManager) Smileyactivity.smileyactivity.getSystemService(Activity.INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(Smileyactivity.smileyactivity.getCurrentFocus().getWindowToken(), 0);
                }
            }
        });

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Status.smileyimagecopy.clear();
                Status.smileyimagecopy.add(filterImage.get(position));
                MainActivity.Smileyactivitystarted=1;
                smileydata.add("Feeling " + holder.tv1.getText().toString());
                holder.img.buildDrawingCache();
                bm = holder.img.getDrawingCache();

                smileyimage.add(bm);
                String[] smileylist = new String[Smileyactivityadapter.smileydata.size()];
                smileylist = Smileyactivityadapter.smileydata.toArray(smileylist);


                if (smileylist.length != (0)) {
                    for (int i = 0; i <= smileylist.length; i++) {
                        //  Status.smileyimage.setImageBitmap(Smileyactivityadapter.smileyimage.get(0));
                        Status.smileyimage.setImageResource(filterImage.get(position));
                        Status.emoticon.setText(smileylist[0]);
                    }

                    Status.defaultlayout.setVisibility(View.VISIBLE);
                    Status.Statuslayout.setVisibility(View.VISIBLE);
                    Status.emotionlayout.setVisibility(View.VISIBLE);
                    ChangeVisibiltyoflayout();
                    Toast.makeText(ctc, "Smiley added", Toast.LENGTH_SHORT).show();
                    Smileyactivity.smileyactivity.finish();
                    InputMethodManager inputMethodManager = (InputMethodManager) Smileyactivity.smileyactivity.getSystemService(Activity.INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(Smileyactivity.smileyactivity.getCurrentFocus().getWindowToken(), 0);
                } else {
                    Smileyactivity.smileyactivity.finish();
                    InputMethodManager inputMethodManager = (InputMethodManager) Smileyactivity.smileyactivity.getSystemService(Activity.INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(Smileyactivity.smileyactivity.getCurrentFocus().getWindowToken(), 0);
                }
            }
        });

        holder.layout11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Status.smileyimagecopy.clear();
                Status.smileyimagecopy.add(filterImage.get(position));
                MainActivity.Smileyactivitystarted=1;
                smileydata.add("Feeling " + holder.tv1.getText().toString());
                holder.img.buildDrawingCache();
                bm = holder.img.getDrawingCache();

                smileyimage.add(bm);
                String[] smileylist = new String[Smileyactivityadapter.smileydata.size()];
                smileylist = Smileyactivityadapter.smileydata.toArray(smileylist);


                if (smileylist.length != (0)) {
                    for (int i = 0; i <= smileylist.length; i++) {
                        //  Status.smileyimage.setImageBitmap(Smileyactivityadapter.smileyimage.get(0));
                        Status.smileyimage.setImageResource(filterImage.get(position));
                        Status.emoticon.setText(smileylist[0]);
                    }

                    Status.defaultlayout.setVisibility(View.VISIBLE);
                    Status.Statuslayout.setVisibility(View.VISIBLE);
                    Status.emotionlayout.setVisibility(View.VISIBLE);
                    ChangeVisibiltyoflayout();
                    Toast.makeText(ctc, "Smiley added", Toast.LENGTH_SHORT).show();
                    Smileyactivity.smileyactivity.finish();
                    InputMethodManager inputMethodManager = (InputMethodManager) Smileyactivity.smileyactivity.getSystemService(Activity.INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(Smileyactivity.smileyactivity.getCurrentFocus().getWindowToken(), 0);
                } else {
                    Smileyactivity.smileyactivity.finish();
                    InputMethodManager inputMethodManager = (InputMethodManager) Smileyactivity.smileyactivity.getSystemService(Activity.INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(Smileyactivity.smileyactivity.getCurrentFocus().getWindowToken(), 0);
                }
            }
        });



        //holder.tv1.setText(data5[position]);
        if(filteredData.size()==0){
            holder.tv1.setText("NA");
        }
        else {
            holder.tv1.setText(filteredData.get(position));
            holder.img.setImageResource(filterImage.get(position));

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
                FilteredImages = new ArrayList<Integer>();
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
                        int dataimages = data2.get(i);
                        if (dataNames.toLowerCase().startsWith(charSequence.toString())) {
                            FilteredArrayNames.add(dataNames);
                            FilteredImages.add(dataimages);
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

        if(Status.tagfriends.getText().toString().equals("with")&&Status.place.getText().toString().equals("at")){
            Status.withlayout.setVisibility(View.INVISIBLE);
            Status.atlayout.setVisibility(View.INVISIBLE);
        }
        else if(!Status.tagfriends.getText().toString().equals("with")&&Status.place.getText().toString().equals("at")){
            Status.withlayout.setVisibility(View.VISIBLE);
            Status.atlayout.setVisibility(View.INVISIBLE);
        }
        else if(Status.tagfriends.getText().toString().equals("with")&&!Status.place.getText().toString().equals("at")){
            Status.withlayout.setVisibility(View.INVISIBLE);
            Status.atlayout.setVisibility(View.VISIBLE);
        }
        else{
            Status.withlayout.setVisibility(View.VISIBLE);
            Status.atlayout.setVisibility(View.VISIBLE);
        }
    }

}
