package com.android.brtest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.widget.Toast;


public class MyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        // 监听wifi的打开与关闭，与wifi的连接无关
        if (WifiManager.WIFI_STATE_CHANGED_ACTION.equals(intent.getAction())) {
            int wifiState = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, 0);
            switch (wifiState) {
                case WifiManager.WIFI_STATE_ENABLED:
                    Toast.makeText(context, "接收到了打开wifi广播", Toast.LENGTH_SHORT).show();
                    break;
                case WifiManager.WIFI_STATE_DISABLING:
                    Toast.makeText(context, "接收到了关闭wifi广播", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
        if (intent.getAction().equals("con.android.brtest.receiver")){
            String content = intent.getStringExtra("content");
            Toast.makeText(context, "接收到了自定义广播"+content, Toast.LENGTH_SHORT).show();
        }

    }
}
