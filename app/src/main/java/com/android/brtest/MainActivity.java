package com.android.brtest;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * 请输入发送内容
     */
    private EditText mTvContent;
    /**
     * 发送广播
     */
    private Button mBtnSend;
    private MyReceiver myReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        myReceiver = new MyReceiver();
        IntentFilter intentFilter = new IntentFilter("con.android.brtest.receiver");
        intentFilter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
        registerReceiver(myReceiver, intentFilter);
        IntentFilter intentFilter1 = new IntentFilter();
        intentFilter1.addAction(Intent.ACTION_BOOT_COMPLETED);
        this.registerReceiver(new MyServiceReceiver(), intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(myReceiver);
    }

    private void initView() {
        mTvContent = (EditText) findViewById(R.id.tv_content);
        mBtnSend = (Button) findViewById(R.id.btn_send);
        mBtnSend.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btn_send:
                Intent intent = new Intent("con.android.brtest.receiver");
                intent.putExtra("content",mTvContent.getText().toString());
                sendBroadcast(intent);
                break;
        }
    }
}