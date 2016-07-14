package com.example.abled.helloandroidstudio;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    TextView text_view_status;
    Button button;
    Intent bg_service_intent;
    ResponseReceiver response_receiver;
    static final String url="http://marknad.karlskronahem.se/Res/themes/Karlskronahem/HSS/Default.aspx?cmguid=dee9b16d-7b30-470d-a80a-50857e4c0c61";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        response_receiver=new ResponseReceiver();
        IntentFilter status_intent_filter=new IntentFilter(CONSTANTS.BROADCAST_ACTION);
        LocalBroadcastManager.getInstance(this).registerReceiver(response_receiver,status_intent_filter);
        bg_service_intent=new Intent(this,BackgroundService.class);
        bg_service_intent.setData(Uri.parse(url));
        text_view_status = (TextView) findViewById(R.id.textView_status);
        text_view_status.setText("MainActivity");
        button=(Button) findViewById(R.id.button_start_bg_task);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
               startService(bg_service_intent);
                }catch(Exception e){
                    Log.e("Main",e.getMessage());}

            }
        });
    }
    class ResponseReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
                Bundle bundle=intent.getExtras();
                String status=bundle.getString(CONSTANTS.BROADCAST_STATUS);

                    text_view_status.setText(status);


        }
    }
}
