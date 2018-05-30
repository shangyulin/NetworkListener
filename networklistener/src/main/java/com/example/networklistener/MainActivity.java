package com.example.networklistener;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements NetStatusMonitor{

    private boolean netStatus;

    private NetBroadcastReceiver netBroadcastReceiver;


    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==100){
                tvNet.setVisibility(View.VISIBLE);
            }else {
                tvNet.setVisibility(View.GONE);
            }
    }
    };
    private TextView tvNet;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        tvNet = findViewById(R.id.tv_net);
    }

    @Override
    protected void onStart() {
        super.onStart();
        registerBroadCast();
    }

    private void registerBroadCast() {
        if (netBroadcastReceiver == null) {
            netBroadcastReceiver = new NetBroadcastReceiver();
            IntentFilter filter = new IntentFilter();
            filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
            registerReceiver(netBroadcastReceiver, filter);

            netBroadcastReceiver.setStatusMonitor(this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (netBroadcastReceiver != null) {
            unregisterReceiver(netBroadcastReceiver);
        }
    }

    @Override
    public void onNetChange(boolean netStatus) {
        this.netStatus = netStatus;
        isNetConnect();
    }

    private void isNetConnect() {
        Message message=new Message();
        if (netStatus){
            message.what=99;
            handler.sendMessage(message);
        }else {
            message.what=100;
            handler.sendMessage(message);
        }
    }

    @Override
    public void setStatusMonitor(NetStatusMonitor netStatusMonitor) {

    }
}
