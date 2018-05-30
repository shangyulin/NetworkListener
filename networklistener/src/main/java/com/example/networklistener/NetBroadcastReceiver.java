package com.example.networklistener;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;

/**
 * Created by shangyulin on 2018/5/30.
 */

public class NetBroadcastReceiver extends BroadcastReceiver implements NetStatusMonitor{

    private NetStatusMonitor monitor;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
            //获取网络状态的类型
            boolean netStatus = NetUtil.getNetStatus(context);
            if (monitor != null) {
                // 接口传递网络状态的类型到注册广播的页面
                monitor.onNetChange(netStatus);
            }
        }
    }

    @Override
    public void onNetChange(boolean netStatus) {

    }

    @Override
    public void setStatusMonitor(NetStatusMonitor netStatusMonitor) {
        this.monitor = netStatusMonitor;
    }
}
