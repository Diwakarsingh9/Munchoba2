package spinno.com.munchoba.fragmentsformainscreen;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import spinno.com.munchoba.R;

public class Communityadapter extends BaseAdapter {

    boolean[] checkBoxState;
    int s=0;
    ArrayList<String> data4;
    ArrayList<String> data5;
    ArrayList<String> age1;
    LayoutInflater inflater;
    Context ctc;






    public Communityadapter(Context ctc, ArrayList<String> strings, ArrayList<String> s, ArrayList<String> age) {
        data4=s;
        data5= strings;
        age1 =age;
        this.ctc=ctc;
        checkBoxState=new boolean[data5.size()];
        inflater = LayoutInflater.from(this.ctc);
    }


    @Override
    public int getCount() {
        return data4.size();
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

        TextView tv1,tv2,tv3,tv4,tv5;
        Button spot, unspot;
        ImageView img;
        FrameLayout fl ;

    }
    @Override
    public View getView( int position, View convertView, ViewGroup parent) {

      Holder holder;

        if(convertView== null) {
                    convertView = inflater.inflate(R.layout.listitemforcommunity, null);
                    holder = new Holder();
            convertView.setTag(holder);

           //

        }

        else {
            holder = (Holder)convertView.getTag();
        }


        holder.tv1 = (TextView)convertView.findViewById(R.id.personname);
        holder.tv2 = (TextView)convertView.findViewById(R.id.gender);
        holder.tv3 = (TextView)convertView.findViewById(R.id.age);

        holder.fl = (FrameLayout) convertView.findViewById(R.id.frame_in_list);


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

                } else {
                    checkBoxState[pos.intValue()] = false;


                }
            }
        });

        holder.tv1.setText(data4.get(position));
        if(data4.size()==0){
            holder.tv1.setText("NA");
        }
        else {
            holder.tv1.setText(data5.get(position));
            holder.tv2.setText(data4.get(position));
            holder.tv3.setText(age1.get(position));
        }

        return convertView;
    }
}
