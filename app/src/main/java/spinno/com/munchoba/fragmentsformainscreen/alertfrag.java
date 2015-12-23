package spinno.com.munchoba.fragmentsformainscreen;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import spinno.com.munchoba.R;


public class alertfrag extends Fragment {
        public static ListView listviewforalert;
    String titles[]={"titles 1","title 2","titles 1","title 2","titles 1","title 2","titles 1","title 2","titles 1","title 2","titles 1","title 2","titles 1","title 2","titles 1","title 2"};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);

        View v = inflater.inflate(R.layout.alertfrag, container, false);
        listviewforalert=(ListView)v.findViewById(R.id.listViewalert);
        Alertnotificationadapter alertnotificationadapter = new Alertnotificationadapter(getActivity(),titles );
        Log.e("ffff", "adapter comm chla");
        listviewforalert.setAdapter(alertnotificationadapter);
        return v;
    }
    public static alertfrag newInstance(String text) {

        alertfrag f = new alertfrag();

        Bundle b = new Bundle();
        b.putString("msg", text);
        f.setArguments(b);
        return f;
    }

}


