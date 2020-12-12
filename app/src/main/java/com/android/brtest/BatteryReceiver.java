package com.android.brtest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

public class BatteryReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        if (Intent.ACTION_BATTERY_OKAY.equals(intent.getAction())) {
            Toast.makeText(context, "电量已恢复，可以使用!", Toast.LENGTH_LONG).show();
        }
        if (Intent.ACTION_BATTERY_LOW.equals(intent.getAction())) {
            Toast.makeText(context, "电量过低，请尽快充电！", Toast.LENGTH_LONG).show();
        }
        if (intent.getAction().equals("con.android.brtest.receiver")) {
            String content = intent.getStringExtra("content");
            Toast.makeText(context, "接收到了自定义广播" + content, Toast.LENGTH_SHORT).show();
        }
        if (Intent.ACTION_BATTERY_CHANGED.equals(intent.getAction())) {
            Bundle bundle = intent.getExtras();
            // 获取当前电量  
            int current = bundle.getInt("level");
            // 获取总电量  
            int total = bundle.getInt("scale");
            StringBuffer sb = new StringBuffer();
            sb.append("当前电量为：" + current * 100 / total + "%" + "  ");
            // 如果当前电量小于总电量的15%  
            if (current * 1.0 / total < 0.15) {
                sb.append("电量过低，请尽快充电！");
            } else {
                sb.append("电量足够，请放心使用！");
            }
            Toast.makeText(context, sb.toString(), Toast.LENGTH_LONG).show();
        }
        if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {
            // 监听开机广播
            if (Build.VERSION.SDK_INT >= 26) {
                context.startForegroundService(new Intent(context, OpenService.class));
            } else {
                context.startService(new Intent(context, OpenService.class));
            }
        }


    }

} 
