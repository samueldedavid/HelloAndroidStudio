package com.example.abled.helloandroidstudio;

import android.app.Activity;
import android.app.Service;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by abled on 04/07/2016.
 */
public class TestService extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tv=(TextView)findViewById(R.id.textView_status);
        Log.e("Test","Test");
        tv.setText("TestActivity");

    }
}
