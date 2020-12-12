package com.android.brtest;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class OpenService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("OpenService","系统开机，服务已启动");
    }
}
