package com.haier.shopdemo.adapter;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by centling on 2016/10/10.
 */
public class MAIDLServerService extends Service {
    private RemoteCallbackList<IOnNewGirlListenter> iOnNewGirlListenters = new RemoteCallbackList<>();
    private List<Girl> girls = new ArrayList<>();

    public MAIDLServerService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Girl girl = new Girl();
        girl.setAge(1);
        girl.setName("zhangsan");
        girls.add(girl);
        new Thread(new ServiceWork()).start();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;

    }

    private Binder binder = new IMyAidlInterface.Stub() {

        @Override
        public List<Girl> getGirleList() throws RemoteException {
            return girls;
        }

        @Override
        public void addGirle(Girl girle) throws RemoteException {
            girls.add(girle);
        }

        @Override
        public void registerListenter(IOnNewGirlListenter listneter) throws RemoteException {
//            if (!iOnNewGirlListenters.contains(listneter)) {
//                iOnNewGirlListenters.add(listneter);
//            }
            iOnNewGirlListenters.register(listneter);
        }

        @Override
        public void unregisterListenter(IOnNewGirlListenter listneter) throws RemoteException {
//            if (iOnNewGirlListenters.contains(listneter)) {
//                iOnNewGirlListenters.remove(listneter);
//            }
            iOnNewGirlListenters.unregister(listneter);
        }
    };

    private void onNewGirlArrived(Girl girl) throws RemoteException {
        girls.add(girl);
        // Log.d("kong", iOnNewGirlListenters.size() + "个数");
        final int N = iOnNewGirlListenters.beginBroadcast();
        for (int i = 0; i < N; i++) {
            IOnNewGirlListenter listenter = iOnNewGirlListenters.getBroadcastItem(i);
            if (listenter != null) {
                listenter.onNewGirl(girl);
            }

        }
        iOnNewGirlListenters.finishBroadcast();
    }

    private class ServiceWork implements Runnable {

        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int age = girls.size() + 1;
                Girl girl = new Girl();
                girl.setName("kong");
                girl.setAge(age);
                try {
                    onNewGirlArrived(girl);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}

