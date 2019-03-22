package com.software.ttsl;

import android.app.Application;

import com.software.ttsl.Utils.ConnectivityReceiver;
import com.software.ttsl.Utils.SessionManager;
import com.crashlytics.android.Crashlytics;
import com.facebook.stetho.Stetho;
import com.tekit.solution.horizontalcalender.utils.AppController;

import io.fabric.sdk.android.Fabric;

public class TransportApp extends AppController {

    private static TransportApp mInstance;

    private SessionManager sessionManager;

    public void onCreate() {
        super.onCreate();
        mInstance = this;
        Stetho.initializeWithDefaults(this);
        Fabric.with(this, new Crashlytics());
        /*sessionManager = new SessionManager(getApplicationContext());

        sessionManager.checkLogin();
*/


    }

    public static TransportApp getInstance() {
        return mInstance;
    }

    public void setConnectivityListener(ConnectivityReceiver.ConnectivityReceiverListener listener) {
        ConnectivityReceiver.connectivityReceiverListener = listener;
    }
}
