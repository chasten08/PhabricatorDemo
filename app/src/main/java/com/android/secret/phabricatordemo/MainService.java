package com.android.secret.phabricatordemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by secret on 16/8/8.
 */
public class MainService extends Service {

    private Timer timer;

    private TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            Log.d("TAG", "service is running");
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        timer = new Timer();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        timer.scheduleAtFixedRate(timerTask, 1000, 1000);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        timer.cancel();
        timer.purge();
        timer = null;
        timerTask = null;
    }
}
