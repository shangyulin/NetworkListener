package com.example.networklistener;

/**
 * Created by shangyulin on 2018/5/30.
 */

public interface NetStatusMonitor {

    void onNetChange(boolean netStatus);

    void setStatusMonitor(NetStatusMonitor netStatusMonitor);
}
