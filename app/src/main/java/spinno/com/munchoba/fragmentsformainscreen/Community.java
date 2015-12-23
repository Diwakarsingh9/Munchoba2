package spinno.com.munchoba.fragmentsformainscreen;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

import spinno.com.munchoba.R;
import spinno.com.munchoba.parsingforapi.parsingforcommunity;
import spinno.com.munchoba.trytt.URL_MAKER;


public class Community extends Fragment {
    public  static ListView listviewcommunity;
   int edittextshow=0;
    int edittextshow2=0;
    int i=0;
    int count = 0;
    LinearLayout ll,lf;
    String country;
    EditText editextforvalues;
    ImageView plus,minus;
    public  static ProgressBar pb;
    public  static ArrayAdapter adp;
    String s22,s33;
    public  static Communityadapter communityadapter;
    ArrayList<String> countries = new ArrayList<String>();
    String weightdata[]={"40 to 45",
            "46 to 50",
            "51 to 55",
            "56 to 60",
            "61 to 65",
            "66 to 70",
            "71 to 75",
            "76 to 80",
            "81 to 85",
            "86 to 90",
            "91 to 95",
            "96 to 100",
            "101 to 105",
            "106 to 110",
            "111 to 120",
            "121 to 130"};
    String commondata[]={"1 to 5",
            "6 to 10",
            "11 to 15",
            "16 to 20",
            "21 to 25",
            "26 to 30",
            "31 to 35",
            "36 to 40",
            "41 to 45",
            "46 to 50",
            "51 to 55",
            "56 to 60",
            "61 to 65",
            "66 to 70"};
    String age[]={
            "15 to 20",
            "21 to 25",
            "26 to 30",
            "31 to 35",
            "36 to 40",
            "41 to 45",
            "46 to 50",
            "51 to 55",
            "56 to 60",
            "61 to 65",
            "66 to 70"};
    String wearable[]={"Fitbit One","Fitbit Zip","Fitbit Flex","Fitbit Charge","Fitbit Charge HR","Fitbit Surge","Runtastic Orbit","Garmin Vivoactive","Garmin Vivofit 2","Garmin Vivofit","Garmin Vivosmart","Garmin Swim","Basis Peak","Withings Activite","Withings Activite Pop","Withings Pulse Ox","Sony Smartband (Roxy) SWR10","Sony Smartband SWR10","Sony Smartband Talk SWR30","Jawbone UP Move","Jawbone UP 2","Jawbone UP 3","Jawbone UP 24","Apple Watch","Nike+ Fuelband","Nike+ Fuelband SE","Pebble Smartwarch","Samsung Gear S","Samsung Gear 2","Samsung Gear 2 Neo","Samsung Gear Circle","Samsung Gear Fit","Other","None"};
    String Data3[]={"Fruitarian","Lacto vegetarian","Lacto-ovo vegetarian","Vegan","Pescatarian","Non-vegetarian","Other"};
String parsevalue[]={"&diet=","&gender=","&country=","&state=","&city=","&race=","&wearabletechnology=","&weight=","&bmi=","&bodyfat=",
        "&weightchange=","&age="};
    String s[]={"Person1","Person2","Person3","Person1","Person4","Person3","Person1","Person2","Person3","Person1","Person2","Person3","Person1","Person2","Person3","Person1","Person2","Person3"};
    String s2[]={"Male","Female","Male","Male","Female","Male","Male","Female","Male","Male","Female","Male","Male","Female","Male","Male","Female","Male"};
        Spinner spfilter, spfiltervalues;
    String gender[]={"Male","Female"};
    String Race[]={"Asian","African","Italian","Caucasian","Arab"};
    String []filterdata ={"Diet Type","Gender","Country","State","City/Town","Race","Wearable Technology Type","Weight(Kg)","BMI","Body Fat(%)","Weight change(Kg)","Age"};
    Button search;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);

        View v = inflater.inflate(R.layout.activity_community, container, false);
        listviewcommunity=(ListView)v.findViewById(R.id.listView2);
        Locale[] locale = Locale.getAvailableLocales();
        pb = (ProgressBar)v.findViewById(R.id.pb22);
        for( Locale loc : locale ){
            country = loc.getDisplayCountry();
            if( country.length() > 0 && !countries.contains(country) ){
                countries.add( country );
            }
        }

        Collections.sort(countries, String.CASE_INSENSITIVE_ORDER);
        URL_MAKER.parameters1.clear();
       new URL_MAKER("s");
       ll = (LinearLayout)v.findViewById(R.id.layout1);
//        lf = (LinearLayout)v.findViewById(R.id.layoutforfilter);
//        spfilter=(Spinner)v.findViewById(R.id.spinnerfilter);
//        editextforvalues=(EditText)v.findViewById(R.id.edittextforvalues);
        search=(Button)v.findViewById(R.id.search);
        plus=(ImageView)v.findViewById(R.id.pluscategory);
//        minus=(ImageView)v.findViewById(R.id.minuscategry);
//        spfiltervalues=(Spinner)v.findViewById(R.id.spinnerfortitles);

        parsingforcommunity.parsingforcomm(getActivity(),"&gender=Male");
//        adp= new ArrayAdapter(getActivity(),R.layout.layoutforfilter,R.id.kljjjjjjj,filterdata);
//        spfilter.setAdapter(adp);
//        ArrayAdapter adp345= new ArrayAdapter(getActivity(),R.layout.layoutforfilter,R.id.kljjjjjjj,Data3);
//        spfiltervalues.setAdapter(adp345);

        ll.addView(ordersview(R.layout.layoutforaddingcategoryfilter,count));
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i < 11) {
                    i++;
                    count ++;
                   // Toast.makeText(getActivity(), ""+i, Toast.LENGTH_SHORT).show();
                    ll.addView(ordersview(R.layout.layoutforaddingcategoryfilter,count));

                } else {
                    //
                }
            }
        });
//        minus.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//              i--;
//                URL_MAKER.parameters2.clear();
//                URL_MAKER.parameters2.add("&null=");
//                URL_MAKER.values2.clear();
//                URL_MAKER.values2.add("null");
//                lf.removeAllViews();
//                lf.setVisibility(View.GONE);
//            }
//        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getActivity(), ""+ll.getChildCount(), Toast.LENGTH_SHORT).show();

                s33="";
                URL_MAKER.parameters1.clear();
                URL_MAKER.values1.clear();
                for(int i = 0 ; i<ll.getChildCount(); i++){

                    View vv = ll.getChildAt(i);
                    Spinner sp = (Spinner) vv.findViewById(R.id.spinnerfortitles11);
                    EditText edt22 = (EditText) vv.findViewById(R.id.edittextforvalues2);
                    TextView  tvt = (TextView) vv.findViewById(R.id.edtvalue);
                    if(tvt.getText().toString().equals("&state=")||tvt.getText().toString().equals("&city=")){
                    s22=edt22.getText()+"";
                    }
                    else {
                        s22=sp.getSelectedItem().toString() ;

                    }
                    URL_MAKER.parameters1.add(tvt.getText()+""+s22);
                    s33=s33.concat(URL_MAKER.parameters1.get(i));
                   // URL_MAKER.values1.clear();
                }
                parsingforcommunity.parsingforcomm(getActivity(),s33);
                //Toast.makeText(getActivity() , ""+s33, Toast.LENGTH_SHORT).show();

            }
        });
//       spfilter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//           @Override
//           public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//               URL_MAKER.parameters2.clear();
//               URL_MAKER.parameters2.add(parsevalue[position]);
//
//
//
//               //Toast.makeText(getActivity(),""+position,Toast.LENGTH_SHORT).show();
//               if (position == 3) {
//
//                   spfiltervalues.setVisibility(View.GONE);
//                   // Toast.makeText(getActivity(),"chla",Toast.LENGTH_SHORT).show();
//                   editextforvalues.setVisibility(View.VISIBLE);
//
//               } else if (position == 5) {
//
//                   ArrayAdapter adp2 = new ArrayAdapter(getActivity(), R.layout.layoutforfilter, R.id.kljjjjjjj, Race);
//                   spfiltervalues.setAdapter(adp2);
//                   spfiltervalues.setVisibility(View.VISIBLE);
//                   editextforvalues.setVisibility(View.GONE);
//
//               } else if (position == 4) {
//
//                   spfiltervalues.setVisibility(View.GONE);
//
//                   editextforvalues.setVisibility(View.VISIBLE);
//
//               } else if (position == 1) {
//
//
//                   ArrayAdapter adp2 = new ArrayAdapter(getActivity(), R.layout.layoutforfilter, R.id.kljjjjjjj, gender);
//                   spfiltervalues.setAdapter(adp2);
//                   spfiltervalues.setVisibility(View.VISIBLE);
//                   editextforvalues.setVisibility(View.GONE);
//
//
//               } else if (position == 2) {
//                   ArrayAdapter adp3 = new ArrayAdapter(getActivity(), R.layout.layoutforfilter, R.id.kljjjjjjj, countries);
//                   spfiltervalues.setAdapter(adp3);
//                   spfiltervalues.setVisibility(View.VISIBLE);
//                   editextforvalues.setVisibility(View.GONE);
//
//               } else if (position == 6) {
//                   ArrayAdapter adp4 = new ArrayAdapter(getActivity(), R.layout.layoutforfilter, R.id.kljjjjjjj, wearable);
//                   spfiltervalues.setAdapter(adp4);
//                   spfiltervalues.setVisibility(View.VISIBLE);
//                   editextforvalues.setVisibility(View.GONE);
//
//               } else if (position == 7) {
//                   ArrayAdapter adp5 = new ArrayAdapter(getActivity(), R.layout.layoutforfilter, R.id.kljjjjjjj, weightdata);
//                   spfiltervalues.setAdapter(adp5);
//                   spfiltervalues.setVisibility(View.VISIBLE);
//                   editextforvalues.setVisibility(View.GONE);
//
//               } else if (position == 8) {
//                   ArrayAdapter adp6 = new ArrayAdapter(getActivity(), R.layout.layoutforfilter, R.id.kljjjjjjj, commondata);
//                   spfiltervalues.setAdapter(adp6);
//                   spfiltervalues.setVisibility(View.VISIBLE);
//                   editextforvalues.setVisibility(View.GONE);
//
//               } else if (position == 9) {
//                   ArrayAdapter adp6 = new ArrayAdapter(getActivity(), R.layout.layoutforfilter, R.id.kljjjjjjj, commondata);
//                   spfiltervalues.setAdapter(adp6);
//                   spfiltervalues.setVisibility(View.VISIBLE);
//                   editextforvalues.setVisibility(View.GONE);
//
//               } else if (position == 10) {
//                   ArrayAdapter adp6 = new ArrayAdapter(getActivity(), R.layout.layoutforfilter, R.id.kljjjjjjj, commondata);
//                   spfiltervalues.setAdapter(adp6);
//                   spfiltervalues.setVisibility(View.VISIBLE);
//                   editextforvalues.setVisibility(View.GONE);
//
//               } else if (position == 11) {
//                   ArrayAdapter adp7 = new ArrayAdapter(getActivity(), R.layout.layoutforfilter, R.id.kljjjjjjj, age);
//                   spfiltervalues.setAdapter(adp7);
//                   spfiltervalues.setVisibility(View.VISIBLE);
//                   editextforvalues.setVisibility(View.GONE);
//
//               } else {
//                   // Toast.makeText(getActivity(),"chla firse",Toast.LENGTH_SHORT).show();
//
//                   ArrayAdapter adp34 = new ArrayAdapter(getActivity(), R.layout.layoutforfilter, R.id.kljjjjjjj, Data3);
//                   spfiltervalues.setAdapter(adp34);
//                   spfiltervalues.setVisibility(View.VISIBLE);
//                   editextforvalues.setVisibility(View.GONE);
//
//               }
//           }
//
//           @Override
//           public void onNothingSelected(AdapterView<?> parent) {
//               // Toast.makeText(getActivity(),"chla firse 2",Toast.LENGTH_SHORT).show();
//               ArrayAdapter adp34 = new ArrayAdapter(getActivity(), R.layout.layoutforfilter, R.id.kljjjjjjj, Data3);
//               spfiltervalues.setAdapter(adp34);
//               spfiltervalues.setVisibility(View.VISIBLE);
//               editextforvalues.setVisibility(View.GONE);
//               URL_MAKER.parameters2.clear();
//               URL_MAKER.parameters2.add(parsevalue[0]);
//
//
//           }
//       });
        //communityadapter  = new Communityadapter(getActivity(), parsingforcommunity.name11,parsingforcommunity.gender11 );
        communityadapter  = new Communityadapter(getActivity(), parsingforcommunity.name11,parsingforcommunity.gender11, parsingforcommunity.age);
        Log.e("ffff", "adapter comm chla");
        listviewcommunity.setAdapter(communityadapter);
        setListViewHeightBasedOnChildren(listviewcommunity);
        listviewcommunity.setFocusable(false);

        return v;
    }

    private View ordersview(int layout_name,int count) {

        LayoutInflater layoutInflater =
                (LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        final View addView = layoutInflater.inflate(layout_name, null);

        final Spinner spfilter11 = (Spinner) addView.findViewById(R.id.spinnerfilter11);
        final Spinner spfiltervalues11 = (Spinner) addView.findViewById(R.id.spinnerfortitles11);
         final ImageView minus2 = (ImageView) addView.findViewById(R.id.minuscat2);
        final EditText edt2 = (EditText) addView.findViewById(R.id.edittextforvalues2);
        final TextView edtvalue = (TextView) addView.findViewById(R.id.edtvalue);

       // addView.setTag(count);

        minus2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i--;
              //  Toast.makeText(getActivity(), ""+i, Toast.LENGTH_SHORT).show();
               ll.removeView(addView);
//                int g= (int) addView.getTag();
//                Toast.makeText(getActivity(), ""+g, Toast.LENGTH_SHORT).show();

            }
        });

        ArrayAdapter adp= new ArrayAdapter(getActivity(),R.layout.layoutforfilter,R.id.kljjjjjjj,filterdata);
        spfilter11.setAdapter(adp);
        ArrayAdapter adp345= new ArrayAdapter(getActivity(),R.layout.layoutforfilter,R.id.kljjjjjjj,Data3);
        spfiltervalues11.setAdapter(adp345);

        spfilter11.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position==3||position==4){
                    edittextshow2=1;
                }
                else {
                    edittextshow2=0;
                }
                //Toast.makeText(getActivity(),""+position,Toast.LENGTH_SHORT).show();
                if (position == 3) {

                    spfiltervalues11.setVisibility(View.GONE);
                   // Toast.makeText(getActivity(), "chla", Toast.LENGTH_SHORT).show();
                    edt2.setVisibility(View.VISIBLE);
                    edtvalue.setText("&state=");
                } else if (position == 5) {

                    ArrayAdapter adp2 = new ArrayAdapter(getActivity(), R.layout.layoutforfilter, R.id.kljjjjjjj, Race);
                    spfiltervalues11.setAdapter(adp2);
                    spfiltervalues11.setVisibility(View.VISIBLE);
                    edt2.setVisibility(View.GONE);
                    edtvalue.setText("&race=");
                } else if (position == 4) {

                    spfiltervalues11.setVisibility(View.GONE);
                    edt2.setVisibility(View.VISIBLE);
                    edtvalue.setText("&city=");

                } else if (position == 1) {
                    ArrayAdapter adp2 = new ArrayAdapter(getActivity(), R.layout.layoutforfilter, R.id.kljjjjjjj, gender);
                    spfiltervalues11.setAdapter(adp2);
                    spfiltervalues11.setVisibility(View.VISIBLE);
                    edt2.setVisibility(View.GONE);
                    edtvalue.setText("&gender=");
                } else if (position == 2) {
                    ArrayAdapter adp3 = new ArrayAdapter(getActivity(), R.layout.layoutforfilter, R.id.kljjjjjjj, countries);
                    spfiltervalues11.setAdapter(adp3);
                    spfiltervalues11.setVisibility(View.VISIBLE);
                    edt2.setVisibility(View.GONE);
                    edtvalue.setText("&country=");
                } else if (position == 6) {
                    ArrayAdapter adp4 = new ArrayAdapter(getActivity(), R.layout.layoutforfilter, R.id.kljjjjjjj, wearable);
                    spfiltervalues11.setAdapter(adp4);
                    spfiltervalues11.setVisibility(View.VISIBLE);
                    edt2.setVisibility(View.GONE);
                    edtvalue.setText("&wearabletechnology=");
                } else if (position == 7) {
                    ArrayAdapter adp5 = new ArrayAdapter(getActivity(), R.layout.layoutforfilter, R.id.kljjjjjjj, weightdata);
                    spfiltervalues11.setAdapter(adp5);
                    spfiltervalues11.setVisibility(View.VISIBLE);
                    edt2.setVisibility(View.GONE);
                    edtvalue.setText("&weight=");
                } else if (position == 8) {
                    ArrayAdapter adp6 = new ArrayAdapter(getActivity(), R.layout.layoutforfilter, R.id.kljjjjjjj, commondata);
                    spfiltervalues11.setAdapter(adp6);
                    spfiltervalues11.setVisibility(View.VISIBLE);
                    edt2.setVisibility(View.GONE);
                    edtvalue.setText("&bmi=");
                } else if (position == 9) {
                    ArrayAdapter adp6 = new ArrayAdapter(getActivity(), R.layout.layoutforfilter, R.id.kljjjjjjj, commondata);
                    spfiltervalues11.setAdapter(adp6);
                    spfiltervalues11.setVisibility(View.VISIBLE);
                    edt2.setVisibility(View.GONE);
                    edtvalue.setText("&bodyfat=");
                } else if (position == 10) {
                    ArrayAdapter adp6 = new ArrayAdapter(getActivity(), R.layout.layoutforfilter, R.id.kljjjjjjj, commondata);
                    spfiltervalues11.setAdapter(adp6);
                    spfiltervalues11.setVisibility(View.VISIBLE);
                    edt2.setVisibility(View.GONE);
                    edtvalue.setText("&weightchange=");
                } else if (position == 11) {
                    ArrayAdapter adp7 = new ArrayAdapter(getActivity(), R.layout.layoutforfilter, R.id.kljjjjjjj, age);
                    spfiltervalues11.setAdapter(adp7);
                    spfiltervalues11.setVisibility(View.VISIBLE);
                    edt2.setVisibility(View.GONE);
                    edtvalue.setText("&age=");
                } else {
                    // Toast.makeText(getActivity(),"chla firse",Toast.LENGTH_SHORT).show();

                    ArrayAdapter adp34 = new ArrayAdapter(getActivity(), R.layout.layoutforfilter, R.id.kljjjjjjj, Data3);
                    spfiltervalues11.setAdapter(adp34);
                    spfiltervalues11.setVisibility(View.VISIBLE);
                    edt2.setVisibility(View.GONE);
                    edtvalue.setText("&diet=");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Toast.makeText(getActivity(),"chla firse 2",Toast.LENGTH_SHORT).show();
                ArrayAdapter adp34 = new ArrayAdapter(getActivity(), R.layout.layoutforfilter, R.id.kljjjjjjj, Data3);
                spfiltervalues11.setAdapter(adp34);
                spfiltervalues11.setVisibility(View.VISIBLE);
                edt2.setVisibility(View.GONE);
                edtvalue.setText("&diet=");
            }
        });

        return addView ;
    }


    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null)
            return;

        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.UNSPECIFIED);
        int totalHeight = 0;
        View view = null;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            view = listAdapter.getView(i, view, listView);
            if (i == 0)
                view.setLayoutParams(new ViewGroup.LayoutParams(desiredWidth, AbsListView.LayoutParams.WRAP_CONTENT));

            view.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += view.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }


}
