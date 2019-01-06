package vtcac.thuhuong.mytrip;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class LauncherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        // no need
//        setContentView(R.layout.activity_launcher);

        // do something
        // start MainActivity
        startActivity(new Intent(this, MainActivity.class));

        // finish this activity
        finish();
    }
}
