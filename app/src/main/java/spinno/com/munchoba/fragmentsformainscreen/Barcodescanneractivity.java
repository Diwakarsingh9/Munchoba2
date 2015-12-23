package spinno.com.munchoba.fragmentsformainscreen;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import spinno.com.munchoba.R;

public class Barcodescanneractivity extends Activity {

    TextView tv1;
    int startfirst=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcodescanneractivity);
        tv1 = (TextView) findViewById(R.id.txt);
        if (startfirst == 0) {
            new IntentIntegrator((Activity) this).initiateScan();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                tv1.setText(""+ result.getContents());
                Toast.makeText(this, "Data Fetched: " + result.getContents(), Toast.LENGTH_LONG).show();

            }
            startfirst=1;
        } else {
            // This is important, otherwise the result will not be passed to the fragment
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
