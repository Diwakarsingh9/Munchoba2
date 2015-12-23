package spinno.com.munchoba.fragmentsformainscreen;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
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

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import spinno.com.munchoba.BaseAlbumDirFactory;
import spinno.com.munchoba.Excelmaker.DBManager;
import spinno.com.munchoba.Excelmaker.DatabaseHelper;
import spinno.com.munchoba.Exercisediarydetails;
import spinno.com.munchoba.FroyoAlbumDirFactory;
import spinno.com.munchoba.R;
import spinno.com.munchoba.parsingforexercisegraph;


public class Exercise_Diary extends Fragment {
    public static TextView from, todate, date, search, activitytype;
    public static Button download, share , details, add, share2, okay;
    public static   Spinner hours, mins;
    public static  int mYear,myear;
    public static  ImageView wearableband, cancel;
    public static  int mMonth,mMonth2;
    public static  int mDay;
    public static ListView lv11;
    public static  GraphView graph;
    public static EditText searchbar;
    public static ArrayAdapter adp2233;
    String str_date="11-June-07";
    String str_date2="12-July-07";
    String str_date3="22-July-07";
    private DBManager dbManager;
        public  static ProgressBar pb;
    Date date2, date3, date4 ;
    public static Context ctc;
    public static SimpleDateFormat formatter,formatter2 ;
    public static Date dates ,dates2;
    //public static LinearLayout graph1;
    public static  String[] minsdata;
       ArrayList<String> Data5=new ArrayList<String>();
    public static String Hoursdata[]={"Hours","1","2","3","4","5","6","7","8","9","10","11","12","13",
            "14","15","16","17","18","19","20","21","22","23","00"};
    public static  String activitydata[]={"Select Data",
            "Not Available" };
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);

        View v = inflater.inflate(R.layout.activity_exercise__diary, container, false);
        Data5.add("Mins");
        for(int i=0;i<60;i++) {
            if (i < 10) {
                Data5.add(String.valueOf("0" + i));
            } else {
                Data5.add(String.valueOf(i));
            }
        }

        minsdata = new String[Data5.size()];
        minsdata = Data5.toArray(minsdata);
        from=(TextView)v.findViewById(R.id.fromdate);
        todate=(TextView)v.findViewById(R.id.todate);
        date=(TextView)v.findViewById(R.id.date);
        search=(TextView)v.findViewById(R.id.search);
        download= (Button) v.findViewById(R.id.download);
        share= (Button) v.findViewById(R.id.share);
        pb= (ProgressBar) v.findViewById(R.id.pb);
        add= (Button) v.findViewById(R.id.add);
        wearableband=(ImageView)v.findViewById(R.id.connectdevice);
        details= (Button) v.findViewById(R.id.details);
        hours=(Spinner)v.findViewById(R.id.sphours);
        mins=(Spinner)v.findViewById(R.id.spmins);
        activitytype=(TextView)v.findViewById(R.id.textv123);
        //graph1=(LinearLayout)v.findViewById(R.id.layoutgraph);

        ctc = getActivity();

        graph = (GraphView) v.findViewById(R.id.graph);

        parsingforexercisegraph.parsingdataforcaloriesburn(getActivity());
        parsingforexercisegraph.parsingdataforcalorieseat(getActivity());
        parsingforexercisegraph.parsingdataforactivitydata(getActivity());

        dbManager = new DBManager(getActivity());
        dbManager.open();
        Cursor cursor = dbManager.fetch();

        activitytype.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               showDialogforactivity();

            }
        });

        from.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(from,1);

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
       wearableband.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Toast.makeText(getActivity(),"Connect to wearable device",Toast.LENGTH_SHORT).show();

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

        details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), Exercisediarydetails.class);

                in.putExtra("date", parsingforexercisegraph.date13);
                in.putExtra("time", parsingforexercisegraph.time12);
                in.putExtra("activitytype", parsingforexercisegraph.activitytype12);
                in.putExtra("workouttype", parsingforexercisegraph.workouttype12);
                in.putExtra("count", parsingforexercisegraph.count12);
                in.putExtra("unit", parsingforexercisegraph.measurement12);
                in.putExtra("calories", parsingforexercisegraph.calories12);


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
        return v;
    }

    private void showDialogforactivity() {
        final Dialog dialog = new Dialog(getActivity(),android.R.style.Theme_Translucent_NoTitleBar);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window=dialog.getWindow();
        dialog.setCancelable(false);
        window.setGravity(Gravity.CENTER);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.dialogforactivitychoose);
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        cancel = (ImageView)dialog.findViewById(R.id.closeoopr);
        searchbar= (EditText)dialog.findViewById(R.id.searchbar);
        lv11=(ListView)dialog.findViewById(R.id.lsst);

        adp2233 = new ArrayAdapter(getActivity(),R.layout.itemlayoutfordialogactivity,R.id.kklljj,parsingforexercisegraph.titlework);
        lv11.setAdapter(adp2233);
        lv11.setTextFilterEnabled(true);

        searchbar.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                InputMethodManager imm = (InputMethodManager)ctc.getSystemService(Context.INPUT_METHOD_SERVICE);
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
                activitytype.setText(txt33);


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

    /* public  static void callgraph(){
         if(parsingforexercisegraph.countcompleted==2) {
             parsingforexercisegraph.parsingcompleted();
         }
     }*/
    public static void setgraph() {


        LineGraphSeries<DataPoint>  series = new LineGraphSeries<DataPoint>(generateData());

        graph.addSeries(series);
        series.setColor(Color.parseColor("#FF991C"));
        // titles
        LineGraphSeries<DataPoint> series2 = new LineGraphSeries<DataPoint>(generateData2());
        graph.addSeries(series2);
        series2.setColor(Color.parseColor("#029834"));

        graph.setTitle("Exercise Diary");
        graph.setTitleColor(Color.parseColor("#a61c1e"));
        series.setTitle("Calories Burned");
        series2.setTitle("Calories Eaten");
        graph.getGridLabelRenderer().setVerticalAxisTitle("Calories");
        graph.getGridLabelRenderer().setHorizontalAxisTitle("Date");

        // optional styles
        graph.setTitleTextSize(25);
        graph.getViewport().setXAxisBoundsManual(true);
//        graph.onDataChanged(false, false);
        graph.getLegendRenderer().setVisible(true);
        graph.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);
        // enable scrolling
        // graph.getViewport().setScrollable(true);
        try {
            graph.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(ctc.getApplicationContext()));
        }catch (Throwable e){}
        graph.getGridLabelRenderer().setNumHorizontalLabels(3); // only 4 because of the space
        graph.getGridLabelRenderer().setVerticalAxisTitleColor(Color.parseColor("#a61c1e"));
        graph.getGridLabelRenderer().setHorizontalAxisTitleColor(Color.parseColor("#a61c1e"));
        graph.getViewport().setXAxisBoundsManual(true);

    }

    public static  DataPoint[] generateData() {
        int count = parsingforexercisegraph.Calorieseat.size();
        DataPoint[] values = new DataPoint[count];
        formatter = new SimpleDateFormat("dd-MM-yyyy");
        Calendar calendar = Calendar.getInstance();
        Log.e("count1", "" + count);
        for (int i=0; i<count; i++) {
            try {
                Log.e("date1111", " " + parsingforexercisegraph.date11.get(i));
                dates = (Date) formatter.parse(parsingforexercisegraph.date11.get(i));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Calendar cal=Calendar.getInstance();
            cal.setTime(dates);
            Date x =  dates;
            //double f = mRand.nextDouble()*0.15+0.3;
            Float y = Float.parseFloat(parsingforexercisegraph.Calorieseat.get(i));
            DataPoint v = new DataPoint(x, y);
            values[i] = v;
            Log.e("values1", " " + values[i]);
        }

        return values;
    }
    public static DataPoint[] generateData2() {
        int count2 = parsingforexercisegraph.Caloriesburn.size();
        DataPoint[] values2 = new DataPoint[count2];
        formatter2 = new SimpleDateFormat("dd-MM-yyyy");
        Calendar calendar = Calendar.getInstance();
        Log.e("count2", "" + count2);
        for (int i=0; i<count2; i++) {
            try {
                Log.e("date2222", " " + parsingforexercisegraph.date12.get(i));
                dates2 = (Date) formatter2.parse(parsingforexercisegraph.date12.get(i));

            } catch (ParseException e) {
                e.printStackTrace();
            }
            Calendar cal2=Calendar.getInstance();
            cal2.setTime(dates2);
            Date x2 =  dates2;
            //double f = mRand.nextDouble()*0.15+0.3;
            Float y2 = Float.parseFloat(parsingforexercisegraph.Caloriesburn.get(i));
            DataPoint v2 = new DataPoint(x2, y2);
            values2[i] = v2;
            Log.e("values2", " " + values2[i]);
        }

        return values2;
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

    public  void showCalorieseatendialog() {

        final Dialog dialog = new Dialog(getActivity(),android.R.style.Theme_Translucent_NoTitleBar);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window=dialog.getWindow();
        dialog.setCancelable(false);
        window.setGravity(Gravity.CENTER);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.caloriesburned);
        share2 = (Button)dialog.findViewById(R.id.button2);
        okay = (Button)dialog.findViewById(R.id.button1);



        share2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Toast.makeText(getActivity(), "Shared Successfully", Toast.LENGTH_SHORT).show();

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

    private void exportToExcel(Cursor cursor) {

        final String fileName = "Exercise_Log.xls";

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
            WritableSheet sheet = workbook.createSheet("My_Exercise_Log", 0);

            try {
                parsingforexercisegraph.Datefordownload.add(0, "Date");
                parsingforexercisegraph.Caloriesfordownload.add(0, "Calories Burn");

                for(int i=0;i<parsingforexercisegraph.Datefordownload.size();i++){
                    sheet.addCell(new Label(0, i, parsingforexercisegraph.Datefordownload.get(i))); // column and row
                    sheet.addCell(new Label(1, i, parsingforexercisegraph.Caloriesfordownload.get(i)));
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


    public static Exercise_Diary newInstance(String text) {

        Exercise_Diary f = new Exercise_Diary();

        Bundle b = new Bundle();
        b.putString("msg", text);
        f.setArguments(b);
        return f;
    }

}
