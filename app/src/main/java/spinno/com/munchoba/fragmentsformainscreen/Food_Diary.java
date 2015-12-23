package spinno.com.munchoba.fragmentsformainscreen;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
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
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

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
import spinno.com.munchoba.MainActivity;
import spinno.com.munchoba.R;
import spinno.com.munchoba.parsingforapi.parsingforfooddiarydetails;
import spinno.com.munchoba.parsingforexercisegraph;
import spinno.com.munchoba.settergetter.Innerdata2;

import spinno.com.munchoba.settergetter.fooddiary;
import spinno.com.munchoba.singleton.VolleySingleton;
import spinno.com.munchoba.urlapi.Api_s;


public class Food_Diary extends Fragment  {
    String strdates[]={"01-05-2007","12-06-2007","22-07-2007"};
    String str_date="11-June-07";
    String str_date2="12-July-07";
    String str_date3="22-July-07";
    SimpleDateFormat formatter ;
    Date dates ;
    RequestQueue queue;
    public static StringRequest sr1,sr2;
    public static List<Innerdata2> data_list1;
    public static ProgressBar pb,pb2;
    Date d1,d2,d3;
    private DBManager dbManager;
//int a[]={5,6,9};
public static ListView lv11;
       // LinearLayout graph;
       public static TextView from, todate, date, search,addfood;
    public static Button download, share , details, add, share2, okay;
    public static Spinner hours, mins, mealtype;
    public static int mYear,myear;
    public static ImageView camera,cancel1;
    int mMonth,mMonth2;
    int mDay;
    public static  GraphView graph;
    public static LinearLayout ll1;
    public static LineGraphSeries<DataPoint> series;
    public static ArrayAdapter adp2233;
    public static Button cancel;
    public static String[] minsdata;
    public static  ArrayList<String> Data5=new ArrayList<String>();
    public static EditText searchbar;
    public static ArrayList<String> date11 = new ArrayList<String>();
    public static ArrayList<String> date12 = new ArrayList<String>();
    public static ArrayList<String> time = new ArrayList<String>();
    public static ArrayList<String> meal_type = new ArrayList<String>();
    public static ArrayList<String> measurement = new ArrayList<String>();
    public static ArrayList<String> calories = new ArrayList<String>();
    public static ArrayList<String> calories12 = new ArrayList<String>();
    public static ArrayList<String> count11 = new ArrayList<String>();
    public static ArrayList<String> food = new ArrayList<String>();
    public static ArrayAdapter adp3;
    public static ArrayList<String> datefordownload = new ArrayList<String>();
    public static ArrayList<String> caloriesfordownload = new ArrayList<String>();

    String Hoursdata[]={"Hours","1","2","3","4","5","6","7","8","9","10","11","12","13",
            "14","15","16","17","18","19","20","21","22","23","00"};
        String mealsdata[]={"Afternoon Snack",
                            "Breakfast" ,
        "Dinner","Evening Snack","Lunch","Morning Snack"};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);

        View v = inflater.inflate(R.layout.fragment_food__diary, container, false);

        Data5.add("Mins");
        for(int i=0;i<60;i++) {
            if (i < 10) {
                Data5.add(String.valueOf("0" + i));
            } else {
                Data5.add(String.valueOf(i));
            }
        }
        dbManager = new DBManager(getActivity());
        dbManager.open();
        Cursor cursor = dbManager.fetch();

       minsdata = new String[Data5.size()];
        minsdata = Data5.toArray(minsdata);
       // graph=(LinearLayout)v.findViewById(R.id.layoutgraph);
        from=(TextView)v.findViewById(R.id.fromdate);
        todate=(TextView)v.findViewById(R.id.todate);
        date=(TextView)v.findViewById(R.id.date);
        search=(TextView)v.findViewById(R.id.search);
        addfood=(TextView)v.findViewById(R.id.addfood);
        download= (Button) v.findViewById(R.id.download);
        pb= (ProgressBar) v.findViewById(R.id.pb);
        pb2= (ProgressBar) v.findViewById(R.id.pb2);
        share= (Button) v.findViewById(R.id.share);
        add= (Button) v.findViewById(R.id.add);
        details= (Button) v.findViewById(R.id.details);
        camera=(ImageView)v.findViewById(R.id.cameraforfooddiary);
        hours=(Spinner)v.findViewById(R.id.sphours);
        mins=(Spinner)v.findViewById(R.id.spmins);
        mealtype=(Spinner)v.findViewById(R.id.spmealtype);
        ll1=(LinearLayout)v.findViewById(R.id.layoutforfooddetails);
        graph = (GraphView) v.findViewById(R.id.graph);
        if(MainActivity.countfooditem==0) {
            parsingforfooddiarydetails.parsingforfooddetails(getActivity());
        }
       // pb.getIndeterminateDrawable().setColorFilter(Color.parseColor("#a61c1e"), PorterDuff.Mode.MULTIPLY);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String id = prefs.getString("id", null);
        id=id.trim();
        Log.e("id", "id:" + id);
        if (id.equals("")) {
            Toast.makeText(getActivity(), "Please Check Your Internet Connection.", Toast.LENGTH_SHORT).show();
        }
        else {

            String foodidurl = Api_s.fooddiarygraph.concat(id);
            Log.e("id", "" + foodidurl);
            queue = VolleySingleton.getInstance(getActivity()).getRequestQueue();
            sr2 = new StringRequest(Request.Method.GET, foodidurl, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    pb.setVisibility(View.GONE);
                    graph.setVisibility(View.VISIBLE);
                    date11.clear();
                    meal_type.clear();
                    time.clear();
                    measurement.clear();
                    calories.clear();
                    count11.clear();
                    food.clear();

                    try {
                        GsonBuilder gsonBuilder = new GsonBuilder();
                        final Gson gson = gsonBuilder.create();
                        fooddiary received2 = new fooddiary();
                        received2 = gson.fromJson(response, fooddiary.class);
                        String status = received2.message;
                        data_list1=received2.innerdataafooddiary;
                        for(int i=0;i<data_list1.size();i++){
                            date11.add(data_list1.get(i).dates11);
                            meal_type.add(data_list1.get(i).meal_type);
                            time.add(data_list1.get(i).time);
                            measurement.add(data_list1.get(i).measurement);
                            calories.add(data_list1.get(i).calories);
                            count11.add(data_list1.get(i).counts);
                            food.add(data_list1.get(i).food);
                        }
                        date12=date11;
                        calories12=calories;
                        Collections.reverse(date11);
                        Collections.reverse(calories);
                        datefordownload=date11;
                        caloriesfordownload=calories;

                        Log.e("data", "" + date11+ " "+" "+calories);
                    setgraph();

                    } catch (JsonSyntaxException e) {
                        e.printStackTrace();
                        Log.e("exception", "" + e);
                    }

                }


            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                }
            });
            sr2.setRetryPolicy(new DefaultRetryPolicy(50000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            queue.add(sr2);
            pb.setVisibility(View.VISIBLE);
            graph.setVisibility(View.GONE);

        }




        from.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(from, 1);

            }
        });
        todate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(todate, 0);

            }
        });
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(date, 0);

            }
        });



        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               showCalorieseatendialog();

            }


        });

        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getActivity(),"Google reverse image search will open",Toast.LENGTH_SHORT).show();
                showfinderfooddialog();
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "searching", Toast.LENGTH_SHORT).show();

            }
        });
        ArrayAdapter adp= new ArrayAdapter(getActivity(),R.layout.itemlayoutforfooddiary,R.id.kljjjjjjj,Hoursdata);
        hours.setAdapter(adp);
        ArrayAdapter adp2= new ArrayAdapter(getActivity(),R.layout.itemlayoutforfooddiary,R.id.kljjjjjjj,minsdata);
        mins.setAdapter(adp2);
    adp3= new ArrayAdapter(getActivity(),R.layout.itemlayoutforfooddiary,R.id.kljjjjjjj,parsingforfooddiarydetails.titlemeal);
        mealtype.setAdapter(adp3);

        details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), Detailsactivity.class);

                in.putExtra("date", date12);
                in.putExtra("time", time);
                in.putExtra("mealtype", meal_type);
                in.putExtra("meal", food);
                in.putExtra("count", count11);
                in.putExtra("unit", measurement);
                in.putExtra("calories", calories12);

                startActivity(in);

            }
        });

        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Cursor cursor = dbManager.fetch();
                exportToExcel(cursor);

            }
        });

        addfood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                showDialogforactivity();
            }
        });

        return v;
    }

    private void showDialogforactivity() {
        final Dialog dialog = new Dialog(getActivity(),android.R.style.Theme_Translucent_NoTitleBar);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window=dialog.getWindow();
        dialog.setCancelable(false);
        window.setGravity(Gravity.CENTER);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.dialogforfooditem);
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        cancel1 = (ImageView)dialog.findViewById(R.id.closeoopr);
        searchbar= (EditText)dialog.findViewById(R.id.searchbar);
        lv11=(ListView)dialog.findViewById(R.id.lsst);

        adp2233 = new ArrayAdapter(getActivity(),R.layout.itemlayoutfordialogactivity,R.id.kklljj, parsingforfooddiarydetails.titlefooditem);
        lv11.setAdapter(adp2233);
        lv11.setTextFilterEnabled(true);

        searchbar.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        });

        searchbar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adp2233.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        lv11.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String txt33 = (String) lv11.getItemAtPosition(position);
                addfood.setText(txt33);


                dialog.dismiss();
            }
        });
        cancel1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                dialog.dismiss();


            }
        });

        dialog.show();

    }
    private void exportToExcel(Cursor cursor) {

        final String fileName = "Food_Log.xls";

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
            WritableSheet sheet = workbook.createSheet("My_Food_Log", 0);

            try {
                datefordownload.add(0, "Date");
                caloriesfordownload.add(0, "Calories Eaten");

                for(int i=0;i<datefordownload.size();i++){
                    sheet.addCell(new Label(0, i, datefordownload.get(i))); // column and row
                    sheet.addCell(new Label(1, i, caloriesfordownload.get(i)));
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


    private void setgraph() {
        LineGraphSeries<DataPoint>  series = new LineGraphSeries<DataPoint>(generateData());

        graph.addSeries(series);
        series.setColor(Color.parseColor("#FF991C"));
        // titles

        graph.setTitle("Food Diary");
        graph.setTitleColor(Color.parseColor("#a61c1e"));
        graph.getGridLabelRenderer().setVerticalAxisTitle("Calories");
        graph.getGridLabelRenderer().setHorizontalAxisTitle("Date");

        // optional styles
        graph.setTitleTextSize(25);
        graph.getViewport().setXAxisBoundsManual(true);
        graph.onDataChanged(false, false);

        // enable scrolling
       // graph.getViewport().setScrollable(true);
            try {
                graph.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(getActivity()));
            }catch (Exception e){}
        graph.getGridLabelRenderer().setNumHorizontalLabels(3); // only 4 because of the space
        graph.getGridLabelRenderer().setVerticalAxisTitleColor(Color.parseColor("#a61c1e"));
        graph.getGridLabelRenderer().setHorizontalAxisTitleColor(Color.parseColor("#a61c1e"));
        graph.getViewport().setXAxisBoundsManual(true);
    }

    private DataPoint[] generateData() {
        int count = calories.size();
        DataPoint[] values = new DataPoint[count];
       formatter = new SimpleDateFormat("dd-MM-yyyy");
       Calendar calendar = Calendar.getInstance();
        for (int i=0; i<count; i++) {
            try {
                Log.e("date"," "+date11.get(i));
                dates = (Date) formatter.parse(date11.get(i));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Calendar cal=Calendar.getInstance();
            cal.setTime(dates);
            Date x =  dates;
            //double f = mRand.nextDouble()*0.15+0.3;
            Float y = Float.parseFloat(calories.get(i));
            DataPoint v = new DataPoint(x, y);
            values[i] = v;
        }
        return values;
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
                todate.setText(selectedDay + "/" + monthofyear + "/"
                        + selectedYear);
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
        datePickerDialog.setTitle("Select Date");
        datePickerDialog.setCancelable(false);
        datePickerDialog.show();
    }
    public  void showfinderfooddialog() {

        final Dialog dialog = new Dialog(getActivity(),android.R.style.Theme_Translucent_NoTitleBar);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window=dialog.getWindow();
        dialog.setCancelable(false);
        window.setGravity(Gravity.CENTER);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.foodfinder);
        cancel = (Button)dialog.findViewById(R.id.button1);
        LinearLayout button = (LinearLayout)dialog.findViewById(R.id.layout12);
        LinearLayout button1 = (LinearLayout)dialog.findViewById(R.id.layout13);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(),Barcodescanneractivity.class);
                startActivity(in);
                dialog.dismiss();



            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"It will help you to search your product !!!!",Toast.LENGTH_SHORT).show();
                onLunchAnotherApp();
                dialog.dismiss();



            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                dialog.dismiss();
            }
        });

        dialog.show();
    }

    public  void showCalorieseatendialog() {

        final Dialog dialog = new Dialog(getActivity(),android.R.style.Theme_Translucent_NoTitleBar);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window=dialog.getWindow();
        dialog.setCancelable(false);
        window.setGravity(Gravity.CENTER);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.calorieseaten);
        share2 = (Button)dialog.findViewById(R.id.button2);
        okay = (Button)dialog.findViewById(R.id.button1);



        share2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
               Toast.makeText(getActivity(),"Shared Successfully",Toast.LENGTH_SHORT).show();

            }
        });
        okay.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                dialog.dismiss();
            }
        });

        dialog.show();
    }
    public static Food_Diary newInstance(String text) {

        Food_Diary f = new Food_Diary();

        Bundle b = new Bundle();
        b.putString("msg", text);
        f.setArguments(b);
        return f;
    }

    public void onLunchAnotherApp() {

        final String appPackageName = "com.msearcher.camfind";

        Intent intent = getActivity().getPackageManager().getLaunchIntentForPackage("com.msearcher.camfind");
        if (intent != null) {

            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

        } else {

            onGoToAnotherInAppStore(intent, appPackageName);

        }

    }

    public void onGoToAnotherInAppStore(Intent intent, String appPackageName) {

        try {

            intent = new Intent(Intent.ACTION_VIEW);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setData(Uri.parse("market://details?id=" + appPackageName));
            startActivity(intent);

        } catch (android.content.ActivityNotFoundException anfe) {

            intent = new Intent(Intent.ACTION_VIEW);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setData(Uri.parse("http://play.google.com/store/apps/details?id=" + appPackageName));
            startActivity(intent);

        }
    }



}
