package spinno.com.munchoba;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.security.Policy;


public class Splashactivity extends Activity {
    private static int SPLASH_TIME_OUT = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashactivity);


        ImageView img = (ImageView) findViewById(R.id.imageView);
        TextView tv2 = (TextView) findViewById(R.id.textView2);

       /* final Animation an = AnimationUtils.loadAnimation(getBaseContext(), R.anim.anim);
        img.setAnimation(an);
        an.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {


            }

            @Override
            public void onAnimationEnd(Animation animation) {

                startnextactivity();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });*/
    }


}
/*
SpannableString content = new SpannableString(tv2.getText().toString());
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        tv2.setText(content);
 */