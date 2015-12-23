package spinno.com.munchoba.fragmentsformainscreen;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;

import spinno.com.munchoba.AccountDetails;
import spinno.com.munchoba.Loggedin;
import spinno.com.munchoba.MainActivity;
import spinno.com.munchoba.One_Fragment;
import spinno.com.munchoba.R;

public class Editfitnessdetails extends Fragment {
    Spinner spwearable,spActivity ,spDietary ,spWeightunit ,spheightnew,spheightunit,spheightnew2;
    String Data[]={"Select Below","Fitbit One","Fitbit Zip","Fitbit Flex","Fitbit Charge","Fitbit Charge HR","Fitbit Surge","Runtastic Orbit","Garmin Vivoactive","Garmin Vivofit 2","Garmin Vivofit","Garmin Vivosmart","Garmin Swim","Basis Peak","Withings Activite","Withings Activite Pop","Withings Pulse Ox","Sony Smartband (Roxy) SWR10","Sony Smartband SWR10","Sony Smartband Talk SWR30","Jawbone UP Move","Jawbone UP 2","Jawbone UP 3","Jawbone UP 24","Apple Watch","Nike+ Fuelband","Nike+ Fuelband SE","Pebble Smartwarch","Samsung Gear S","Samsung Gear 2","Samsung Gear 2 Neo","Samsung Gear Circle","Samsung Gear Fit","Other","None"};
    String Data2[]={"Select Below","0","1-3","4-6","7+"};
    String Data3[]={"Select Below","Fruitarian","Lacto vegetarian","Lacto-ovo vegetarian","Vegan","Pescatarian","Non-vegetarian","Other"};
    String Data4[]={"Kgs","Lbs"};
    ArrayList<String> Data5=new ArrayList<String>();
    String Data7[]={"4","5","6","7"};
    String Data6[]={"cms","Feets & Inches"};
    String Data8[]={"1","2","3","4","5","6","7","8","9","10","11"};
    ArrayAdapter adp5;
    SharedPreferences prefs;
    Button btnupload,btnskip,btnsubmit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_editfitnessdetails, container, false);
        loaddata5();
        btnskip=(Button)view.findViewById(R.id.buttonskip);
        btnsubmit=(Button)view.findViewById(R.id.buttonsubmit);
        spwearable=(Spinner)view.findViewById(R.id.spinner);
        spActivity=(Spinner)view.findViewById(R.id.spinner2);
        spDietary=(Spinner)view.findViewById(R.id.spinner3);
        spWeightunit=(Spinner)view.findViewById(R.id.spinnerweight);
        spheightnew=(Spinner)view.findViewById(R.id.spinnerheightnew);
        spheightnew2=(Spinner)view.findViewById(R.id.spinnerheightnew2);
        spheightunit=(Spinner)view.findViewById(R.id.spinnerheightunit);
        prefs= PreferenceManager.getDefaultSharedPreferences(getActivity());

        ArrayAdapter adp= new ArrayAdapter(getActivity(),R.layout.item,R.id.kljjjjjjj,Data);
        spwearable.setAdapter(adp);
        ArrayAdapter adp2= new ArrayAdapter(getActivity(),R.layout.item,R.id.kljjjjjjj,Data2);
        spActivity.setAdapter(adp2);
        ArrayAdapter adp3= new ArrayAdapter(getActivity(),R.layout.item,R.id.kljjjjjjj,Data3);
        spDietary.setAdapter(adp3);
        ArrayAdapter adp4= new ArrayAdapter(getActivity(),R.layout.item,R.id.kljjjjjjj,Data4);
        spWeightunit.setAdapter(adp4);
        adp5=new ArrayAdapter(getActivity(),R.layout.item,R.id.kljjjjjjj,Data5);
        spheightnew.setAdapter(adp5 );
        ArrayAdapter adp7= new ArrayAdapter(getActivity(),R.layout.item,R.id.kljjjjjjj,Data8);
        spheightnew2.setAdapter(adp7);
        ArrayAdapter adp6= new ArrayAdapter(getActivity(),R.layout.item,R.id.kljjjjjjj,Data6);
        spheightunit.setAdapter(adp6);

        spheightunit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position == 1) {
                    spheightnew2.setVisibility(View.VISIBLE);
                    adp5 = new ArrayAdapter(getActivity(), R.layout.item, R.id.kljjjjjjj, Data7);
                    spheightnew.setAdapter(adp5);

                } else {
                    spheightnew2.setVisibility(View.GONE);
                    adp5 = new ArrayAdapter(getActivity(), R.layout.item, R.id.kljjjjjjj, Data5);
                    spheightnew.setAdapter(adp5);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnskip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Editprofile.activity.finish();

            }
        });

        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Loggedin.class);
                // intent.putExtra("name", One_Fragment.Fname.getText().toString());
                // intent.putExtra("aboutme", Three_FRAGMENT.aboutme.getText().toString());
                SharedPreferences.Editor edit2 = prefs.edit();
                edit2.putBoolean("pref_previously_started", Boolean.TRUE);
                edit2.commit();
                MainActivity.croppedimage=1;

                MainActivity.intentcaled = 2;
                startActivity(intent);
                SharedPreferences.Editor edit = prefs.edit();
                edit.putString("name", AccountDetails.Fname.getText().toString() + " " + AccountDetails.lname.getText().toString());
                edit.commit();
                Editprofile.activity.finish();
                getActivity().finish();
                Loggedin.loggedin.finish();
            }
        });


        return view;
    }



    public void loaddata5(){
        for(int i=120;i<=240;i++)
        {
            Data5.add(""+i);
        }

    }






    public static Editfitnessdetails newInstance(String text) {

        Editfitnessdetails f = new Editfitnessdetails();
        Bundle b = new Bundle();

        b.putString("msg", text);


        f.setArguments(b);

        return f;
    }


    @Override
    public void onResume() {
        super.onResume();

        //loaddata();
    }
}