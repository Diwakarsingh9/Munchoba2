package spinno.com.munchoba.fragmentsformainscreen;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.viewpagerindicator.CirclePageIndicator;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import spinno.com.munchoba.Excelmaker.DBManager;
import spinno.com.munchoba.Excelmaker.DatabaseHelper;
import spinno.com.munchoba.One_Fragment;
import spinno.com.munchoba.R;
import spinno.com.munchoba.Three_FRAGMENT;
import spinno.com.munchoba.Two_FRAGMENT;
import spinno.com.munchoba.Weightmanagementdetails;
import spinno.com.munchoba.parsingbodyfat;
import spinno.com.munchoba.parsingforapi.parsingforaddingweightandbodyfat;
import spinno.com.munchoba.settergetter.Innerdata2;
import spinno.com.munchoba.settergetter.Weightadd;
import spinno.com.munchoba.settergetter.fooddiary;
import spinno.com.munchoba.singleton.VolleySingleton;
import spinno.com.munchoba.urlapi.Api_s;


public class Weight_Management extends Fragment {


    public static ViewPager pager;
    public static CirclePageIndicator titleIndicator;
    public  static FragmentManager fragmentManager;

    public static List<Innerdata2> data_list1;
    public static ProgressBar pb;
    SimpleDateFormat formatter ;
    Date dates ;
    public static TextView  date2, date;
    public static EditText weight, bodyfat;
    public static int mYear,myear;
    public static DBManager dbManager;

    public static String hourvalue, minsvalue, wgtunitvalue, datevalue;
    public static String[] minsdata;
    public static Spinner hours, mins, wgtunit;
    public static ArrayList<String> Data5=new ArrayList<String>();
    public static String weightunitdata[]={"Kgs","Lbs"};
    public static String Hoursdata[]={"Hours","1","2","3","4","5","6","7","8","9","10","11","12","13",
            "14","15","16","17","18","19","20","21","22","23","00"};
    public static int mMonth,mMonth2;
    public static int mDay;
    public static Button download, share , details, save, save2;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);

        View v = inflater.inflate(R.layout.activity_weight__management, container, false);
        Data5.add("Mins");
        for(int i=0;i<60;i++) {
            if (i < 10) {
                Data5.add(String.valueOf("0" + i));
            } else {
                Data5.add(String.valueOf(i));
            }
        }

        fragmentManager=getChildFragmentManager();
       // parsingbodyfat.parsingdata(getActivity());
        pager = (ViewPager)v.findViewById(R.id.pager);

        pager.setAdapter(new MyPagerAdapter(getChildFragmentManager()));
        titleIndicator= (CirclePageIndicator)v.findViewById(R.id.titles);

        pb= (ProgressBar) v.findViewById(R.id.pb);

        minsdata = new String[Data5.size()];
        minsdata = Data5.toArray(minsdata);
        date=(TextView)v.findViewById(R.id.date);
       // date2=(TextView)v.findViewById(R.id.date2);
        details= (Button) v.findViewById(R.id.details);
        download= (Button) v.findViewById(R.id.download);
        save= (Button) v.findViewById(R.id.save);
       // save2= (Button) v.findViewById(R.id.save2);
        share= (Button) v.findViewById(R.id.share);
        bodyfat=(EditText)v.findViewById(R.id.bodyfatedittext);
        weight=(EditText)v.findViewById(R.id.weightedt);
        hours=(Spinner)v.findViewById(R.id.sphours);
        mins=(Spinner)v.findViewById(R.id.spmins);
        wgtunit=(Spinner)v.findViewById(R.id.wgtunit);

        dbManager = new DBManager(getActivity());
        dbManager.open();
        Cursor cursor = dbManager.fetch();

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(date, 0);

            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               if(weight.getText().toString().trim().equals("")&&bodyfat.getText().toString().trim().equals("")){
                   Toast.makeText(getActivity(), "Please fill required details.", Toast.LENGTH_SHORT).show();
               }
                else if(!weight.getText().toString().trim().equals("")&&bodyfat.getText().toString().trim().equals("")){
                   parsingforaddingweightandbodyfat.parsingforaddingweight(getActivity());
               }
               else if(weight.getText().toString().trim().equals("")&&!bodyfat.getText().toString().trim().equals("")){
                   parsingforaddingweightandbodyfat.parsingforaddingbodyfat(getActivity());
               }
               else {
                   parsingforaddingweightandbodyfat.parsingforaddingweight(getActivity());
                   parsingforaddingweightandbodyfat.parsingforaddingbodyfat(getActivity());
               }


            }
        });


        ArrayAdapter adp= new ArrayAdapter(getActivity(),R.layout.layoutitemforweightmanagement,R.id.kljjjjjjj,Hoursdata);
        hours.setAdapter(adp);
        ArrayAdapter adp2= new ArrayAdapter(getActivity(),R.layout.layoutitemforweightmanagement,R.id.kljjjjjjj,minsdata);
        mins.setAdapter(adp2);
        ArrayAdapter adp3= new ArrayAdapter(getActivity(),R.layout.layoutitemforweightmanagement,R.id.kljjjjjjj,weightunitdata);
        wgtunit.setAdapter(adp3);

        hours.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                hourvalue = Hoursdata[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mins.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                minsvalue= minsdata[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        wgtunit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){
                    wgtunitvalue= "kg";
                }
                else{
                    wgtunitvalue= "lb";
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), Weightmanagementdetails.class);
                in.putExtra("date", Myweightgraph.date11);
                in.putExtra("titles", Myweightgraph.weight);
                in.putExtra("subtitles", "Weight");
                in.putExtra("Heading", "Weight Log");
                startActivity(in);
            }
        });


        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Cursor cursor = dbManager.fetch();
                exportToExcelweight(cursor);

            }
        });


        titleIndicator.setViewPager(pager);

        titleIndicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    details.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent in = new Intent(getActivity(), Weightmanagementdetails.class);
                            in.putExtra("date", Myweightgraph.date11);
                            in.putExtra("titles", Myweightgraph.weight);
                            in.putExtra("subtitles", "Weight");
                            in.putExtra("Heading", "Weight Log");
                            startActivity(in);
                        }
                    });

                    download.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {


                            Cursor cursor = dbManager.fetch();
                            exportToExcelweight(cursor);

                        }
                    });
                } else if (position == 1) {
                    details.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent in = new Intent(getActivity(), Weightmanagementdetails.class);
                            in.putExtra("date", parsingbodyfat.date11);
                            in.putExtra("titles", parsingbodyfat.fat11);
                            in.putExtra("subtitles", "Body Fat Percentage");
                            in.putExtra("Heading", "Body Fat % Log");
                            startActivity(in);
                        }
                    });
                    download.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {


                            Cursor cursor = dbManager.fetch();
                            exportToExcelBodyfat(cursor);

                        }
                    });
                } else {
                    details.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(getActivity(), "Data not fetched. Under development", Toast.LENGTH_SHORT).show();
                        }
                    });
                    download.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(getActivity(), "Data not fetched. Under development", Toast.LENGTH_SHORT).show();


                        }
                    });
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        return v;
    }
    private void showDialog(final TextView todate, int i) {
        if (mYear == 0 || mMonth == 0 || mDay == 0) {
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            myear= mYear;
            mMonth = c.get(Calendar.MONTH);
            mMonth2=mMonth-i;
            mDay = c.get(Calendar.DAY_OF_MONTH);
        }

        DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {

            // when dialog box is closed, below method will be called.
            public void onDateSet(DatePicker view, int selectedYear,
                                  int selectedMonth, int selectedDay) {
                int monthofyear= selectedMonth+1;
                todate.setText(selectedDay + "-" + monthofyear + "-"
                        + selectedYear);
                datevalue=selectedYear + "-" + monthofyear + "-"
                        + selectedDay;
                mYear = selectedYear;
                mMonth = selectedMonth;
                mDay = selectedDay;
            }
        };

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                getActivity(), datePickerListener, myear, mMonth, mDay);

        datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == DialogInterface.BUTTON_NEGATIVE) {
                            dialog.cancel();
                        }
                    }
                });
        datePickerDialog.setTitle("Select Date Of Birth");
        datePickerDialog.setCancelable(false);
        datePickerDialog.show();
    }
    public static Weight_Management newInstance(String text) {

        Weight_Management f = new Weight_Management();

        Bundle b = new Bundle();
        b.putString("msg", text);
        f.setArguments(b);
        return f;
    }
    class MyPagerAdapter extends FragmentStatePagerAdapter {

        private final String[] TITLES = {"My Weight Graph","My Body Fat Percentage Graph","My BMI Graph" };

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return TITLES[position];
        }

        @Override
        public int getCount() {
            return TITLES.length;
        }
        @Override
        public Fragment getItem(int position) {
            switch (position) {

                case 0:

                    return Myweightgraph.newInstance("FirstFragment"," Instance 1");
                case 1:

                    return Mybodyfatpercentagegraph.newInstance("SecondFragment"," Instance 1");
                case 2:

                    return Mybmigraph.newInstance("ThirdFragment","Instance 1");


                default:
                    return null;

            }

        }


    }

    private void exportToExcelweight(Cursor cursor) {

        final String fileName = "Weight_Log.xls";

        //Saving file in external storage
        File sdCard = Environment.getExternalStorageDirectory();

        File directory = new File(sdCard.getAbsolutePath() + "/munchoba.todo");

        //create directory if not exist
        if(!directory.isDirectory()){
            directory.mkdirs();
        }

        //file path
        File file = new File(directory, fileName);

        WorkbookSettings wbSettings = new WorkbookSettings();
        wbSettings.setLocale(new Locale("en", "EN"));

        WritableWorkbook workbook;

        try {
            workbook = Workbook.createWorkbook(file, wbSettings);

            //Excel sheet name. 0 represents first sheet
            WritableSheet sheet = workbook.createSheet("My_weight_log", 0);

            try {
                Myweightgraph.date12.add(0, "Date");
                Myweightgraph.weight12.add(0, "Weight");

                for(int i=0;i<Myweightgraph.date12.size();i++){
                    sheet.addCell(new Label(0, i, Myweightgraph.date12.get(i))); // column and row
                    sheet.addCell(new Label(1, i, Myweightgraph.weight12.get(i)));
                }

                //sheet.addCell(new Label(0, 1, "hhh"));
                //sheet.addCell(new Label(1, 1, "jhh"));

                if (cursor.moveToFirst()) {
                    do {
                        String title = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TODO_SUBJECT));
                        String desc = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TODO_DESC));

                        int i = cursor.getPosition() + 1;

                        sheet.addCell(new Label(0, i, title));
                        sheet.addCell(new Label(1, i, desc));


                    } while (cursor.moveToNext());
                }

                //closing cursor
                cursor.close();
                Toast.makeText(getActivity(),"Download Complete",Toast.LENGTH_SHORT).show();
            } catch (RowsExceededException e) {
                Toast.makeText(getActivity(),"Downloading Error",Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            } catch (WriteException e) {
                e.printStackTrace();
                Toast.makeText(getActivity(),"Downloading Error",Toast.LENGTH_SHORT).show();
            }

            workbook.write();

            try {
                workbook.close();
            } catch (WriteException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void exportToExcelBodyfat(Cursor cursor) {

        final String fileName = "Body_Fat_Percentage_Log.xls";

        //Saving file in external storage
        File sdCard = Environment.getExternalStorageDirectory();

        File directory = new File(sdCard.getAbsolutePath() + "/munchoba.todo");

        //create directory if not exist
        if(!directory.isDirectory()){
            directory.mkdirs();
        }

        //file path
        File file = new File(directory, fileName);

        WorkbookSettings wbSettings = new WorkbookSettings();
        wbSettings.setLocale(new Locale("en", "EN"));

        WritableWorkbook workbook;

        try {
            workbook = Workbook.createWorkbook(file, wbSettings);

            //Excel sheet name. 0 represents first sheet
            WritableSheet sheet = workbook.createSheet("My_Body_Fat_Percentage_Log", 0);

            try {
                parsingbodyfat.date12.add(0, "Date");
                parsingbodyfat.fat12.add(0, "Body_Fat_%");

                for(int i=0;i< parsingbodyfat.date12.size();i++){
                    sheet.addCell(new Label(0, i,  parsingbodyfat.date12.get(i))); // column and row
                    sheet.addCell(new Label(1, i, parsingbodyfat.fat12.get(i)));
                }

                //sheet.addCell(new Label(0, 1, "hhh"));
                //sheet.addCell(new Label(1, 1, "jhh"));

                if (cursor.moveToFirst()) {
                    do {
                        String title = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TODO_SUBJECT));
                        String desc = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TODO_DESC));

                        int i = cursor.getPosition() + 1;

                        sheet.addCell(new Label(0, i, title));
                        sheet.addCell(new Label(1, i, desc));


                    } while (cursor.moveToNext());
                }

                //closing cursor
                cursor.close();
                Toast.makeText(getActivity(),"Download Complete",Toast.LENGTH_SHORT).show();
            } catch (RowsExceededException e) {
                Toast.makeText(getActivity(),"Downloading Error",Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            } catch (WriteException e) {
                e.printStackTrace();
                Toast.makeText(getActivity(),"Downloading Error",Toast.LENGTH_SHORT).show();
            }

            workbook.write();

            try {
                workbook.close();
            } catch (WriteException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
