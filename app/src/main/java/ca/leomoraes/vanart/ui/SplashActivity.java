package ca.leomoraes.vanart.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import ca.leomoraes.vanart.R;
import ca.leomoraes.vanart.service.LoadDataService;

public class SplashActivity extends AppCompatActivity {

    private TextView description;
    MyReceiver myReceiver;

    public static final String FILTER_ACTION_KEY = "FILTER_KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        description = findViewById(R.id.splash_desc);
    }


    private void setReceiver() {
        myReceiver = new MyReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(FILTER_ACTION_KEY);

        LocalBroadcastManager.getInstance(this).registerReceiver(myReceiver, intentFilter);
    }

    @Override
    protected void onStart() {
        super.onStart();

/*        startActivity( new Intent(SplashActivity.this, HomeActivity.class) );
        SplashActivity.this.finish();*/

        setReceiver();

        // Start the service
        Intent intent = new Intent(SplashActivity.this, LoadDataService.class);
        startService(intent);

        super.onStart();
    }

    private class MyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {

            if(intent.getBooleanExtra("broadcastMessage", false)){
                startActivity( new Intent(SplashActivity.this, HomeActivity.class) );
                SplashActivity.this.finish();
            }

        }
    }
}
