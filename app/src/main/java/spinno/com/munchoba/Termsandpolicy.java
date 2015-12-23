package spinno.com.munchoba;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import spinno.com.munchoba.parsingforapi.parsingfortermpolicy;

public class Termsandpolicy extends Activity {
    public static TextView head,head2,subtitle,subtitle2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_termsandpolicy);
        head=(TextView)findViewById(R.id.head);
        head2=(TextView)findViewById(R.id.head);
        subtitle=(TextView)findViewById(R.id.termandc);
        subtitle2=(TextView)findViewById(R.id.privp);
        parsingfortermpolicy.parsingpolicy(Termsandpolicy.this);
    }
}
