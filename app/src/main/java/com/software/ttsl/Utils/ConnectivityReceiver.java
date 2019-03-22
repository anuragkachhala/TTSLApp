
package com.software.ttsl.Utils;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

import com.software.ttsl.TransportApp;

public class ConnectivityReceiver extends BroadcastReceiver {
     private static final String TAG = ConnectivityReceiver.class.getName();

        public static ConnectivityReceiverListener connectivityReceiverListener;

        public ConnectivityReceiver() {
            super();
        }

        @Override
        public void onReceive(Context context, Intent arg1) {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();

            Log.e(TAG ,"ON RECEIVED "+isConnected);
            //Toast.makeText(context,"ON RECEIVED "+isConnected,Toast.LENGTH_LONG).show();

            if (connectivityReceiverListener != null) {
                connectivityReceiverListener.onNetworkConnectionChanged(isConnected);
            }
        }

        public static boolean isConnected() {
            ConnectivityManager cm = (ConnectivityManager) TransportApp.getInstance().getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        }


        public interface ConnectivityReceiverListener {
            void onNetworkConnectionChanged(boolean isConnected);
        }
    }
