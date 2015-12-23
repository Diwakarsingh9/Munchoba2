package spinno.com.munchoba;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Locale;


public class AccountDetails extends Fragment {

    Spinner spgender, spcountry, spcity, spstate, sprace;
    ArrayList<String> countries = new ArrayList<String>();
    String country;
    TextView passworderrror;
    public static EditText Fname, lname, password, confirmpassword;
    String Data[] = {"Gender", "Male", "Female"};
    String Data5[] = {"Ethnicity", "Asian (South)", "Asian (East)", "Asian (Southeast)", "African", "Hispanic", "Caucasian", "Arab"};
    String Data3[] = {"State", "Not Applicable"};
    String Data4[] = {"City", "Not Applicable"};
    int mYear, myear;
    int mMonth;
    int mDay;


    TextView tvdate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_account_details, container, false);
        spgender = (Spinner) view.findViewById(R.id.spinner);
        spcountry = (Spinner) view.findViewById(R.id.spinner2);
        spstate = (Spinner) view.findViewById(R.id.spinner3);
        spcity = (Spinner) view.findViewById(R.id.spinner4);
        sprace = (Spinner) view.findViewById(R.id.spinner5);
        Fname = (EditText) view.findViewById(R.id.frstname);
        lname = (EditText) view.findViewById(R.id.lstnm);

        Locale[] locale = Locale.getAvailableLocales();

        for (Locale loc : locale) {
            country = loc.getDisplayCountry();
            if (country.length() > 0 && !countries.contains(country)) {
                countries.add(country);
            }
        }
        Collections.sort(countries, String.CASE_INSENSITIVE_ORDER);
        countries.add(0, "Country");

        tvdate = (TextView) view.findViewById(R.id.TextViewfordate);
        ArrayAdapter adp = new ArrayAdapter(getActivity(), R.layout.item, R.id.kljjjjjjj, Data);
        spgender.setAdapter(adp);
        ArrayAdapter adp2 = new ArrayAdapter(getActivity(), R.layout.item, R.id.kljjjjjjj, countries);
        spcountry.setAdapter(adp2);

        ArrayAdapter adp3 = new ArrayAdapter(getActivity(), R.layout.item, R.id.kljjjjjjj, Data3);
        spstate.setAdapter(adp3);
        ArrayAdapter adp4 = new ArrayAdapter(getActivity(), R.layout.item, R.id.kljjjjjjj, Data4);
        spcity.setAdapter(adp4);
        ArrayAdapter adp5 = new ArrayAdapter(getActivity(), R.layout.item, R.id.kljjjjjjj, Data5);
        sprace.setAdapter(adp5);


        tvdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });


        return view;
    }

    private void showDialog() {
        if (mYear == 0 || mMonth == 0 || mDay == 0) {
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            myear = mYear - 18;
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);
        }

        DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {

            // when dialog box is closed, below method will be called.
            public void onDateSet(DatePicker view, int selectedYear,
                                  int selectedMonth, int selectedDay) {
                int monthofyear = selectedMonth + 1;
                tvdate.setText(selectedDay + "/" + monthofyear + "/"
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
        datePickerDialog.setTitle("Select Date Of Birth");
        datePickerDialog.setCancelable(false);
        datePickerDialog.show();
    }

    public boolean checkPassWordAndConfirmPassword(String password, String confirmPassword) {
        boolean pstatus = false;
        if (confirmPassword != null && password != null) {
            if (password.equals(confirmPassword)) {
                pstatus = true;
            }
        }
        return pstatus;
    }


    public static AccountDetails newInstance(String text) {

        AccountDetails f = new AccountDetails();
        Bundle b = new Bundle();

        b.putString("msg", text);


        f.setArguments(b);

        return f;
    }


    @Override
    public void onResume() {
        super.onResume();

    }
}