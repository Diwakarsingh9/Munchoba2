package spinno.com.munchoba;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import spinno.com.munchoba.parsingforapi.parsingforforgotpasword;


public class Forgotpassword extends Activity {
    public static   EditText emailaddtosubmit,username,verificationcode;
    Button btncancel,btnsubmit;
    String email1;
    public static LinearLayout part1,part2;
    public static TextView head;
    public static ImageView emailval;
    String emailPattern;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);
        head=(TextView)findViewById(R.id.head);
        emailaddtosubmit=(EditText)findViewById(R.id.emailsss);
        username=(EditText)findViewById(R.id.username);
        part1 = (LinearLayout)findViewById(R.id.part1);
        part2 = (LinearLayout)findViewById(R.id.part2);
        verificationcode=(EditText)findViewById(R.id.VERIFY);
        btncancel=(Button)findViewById(R.id.buttoncancel);
        btnsubmit=(Button)findViewById(R.id.buttonsubmit);
        emailval=(ImageView)findViewById(R.id.emailvalidat);

        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               parsingforforgotpasword.parsingforfrgtpass(Forgotpassword.this, emailaddtosubmit.getText().toString().trim());
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(emailaddtosubmit.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

            }
        });
        emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        emailaddtosubmit .addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                email1 = emailaddtosubmit.getText().toString().trim();
                if (email1.matches(emailPattern) && s.length() > 0) {
                    emailval.setImageResource(R.drawable.right);


                } else {
                    emailval.setImageResource(R.drawable.cross);

                }
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // other stuffs
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // other stuffs
            }
        });
        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(emailaddtosubmit.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                Forgotpassword.this.finish();
            }
        });
    }


}
