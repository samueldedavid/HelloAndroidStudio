package com.example.abled.helloandroidstudio;

import android.app.IntentService;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.net.ConnectivityManagerCompat;
import android.util.Log;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class BackgroundService extends IntentService {
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    ScheduledExecutorService scheduler;
    static final String TAG="BackgroundService";
    public BackgroundService() {
        super("BackgroundService");
        scheduler= Executors.newScheduledThreadPool(1);
    }

    private String status;

    protected  void broadcastStatus(String status){
        Intent localIntent=new Intent(CONSTANTS.BROADCAST_ACTION);
               localIntent.putExtra(CONSTANTS.BROADCAST_STATUS,status);
                LocalBroadcastManager.getInstance(this).sendBroadcast(localIntent);
    }
    protected String internetConnectivityInfo(){
        ConnectivityManager cm;
        cm = (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork=cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
            if(isConnected){
                return activeNetwork.getTypeName();
            }else{
                return "noWifi";

            }



    }
    @Override
    protected void onHandleIntent(Intent intent) {
        Log.e(TAG, "onHandleIntent: ");
        scheduler.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                String s=internetConnectivityInfo();
                broadcastStatus(s);
                Log.e(TAG, "onHandleIntent:scheduled "+s);
            }
        },2,1, TimeUnit.SECONDS );

    }
}
