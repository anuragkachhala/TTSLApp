package com.software.ttsl.Utils;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import com.software.ttsl.Interfacce.AlertDialogCallback;
import com.software.ttsl.Interfacce.AlertDialogCallback1;

import static android.content.DialogInterface.*;

public class AlertDialogManager {

    public void showAlertDialog(Context context, String title, String message, Boolean status) {
        final AlertDialog.Builder alertDialog  = new AlertDialog.Builder(context);

        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setCancelable(true);

        /*if(status != null)
            // Setting alert dialog icon
            alertDialog.setIcon((status) ? R.drawable.success : R.drawable.fail);*/

         alertDialog.setPositiveButton("ok", new OnClickListener() {
             @Override
             public void onClick(DialogInterface dialogInterface, int i) {
                  dialogInterface.dismiss();
             }
         }) ;

        AlertDialog dialog = alertDialog.create();
        // Showing Alert Message
        alertDialog.show();
    }


    public static void showAlertDialogMessage(Context context, String title, String message, Boolean status, final AlertDialogCallback listener) {
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);

        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setCancelable(status);

        /*if(status != null)
            // Setting alert dialog icon
            alertDialog.setIcon((status) ? R.drawable.success : R.drawable.fail);*/

        alertDialog.setPositiveButton("ok", new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                if (listener == null) {
                    dialogInterface.dismiss();
                } else {
                    listener.alertDialogCallbackOk();
                }
            }
        });

        alertDialog.setNegativeButton("cancel", new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                if (listener == null) {
                    dialogInterface.dismiss();
                } else {
                    listener.alertDialogCallbackCancel();
                }

            }
        });

        AlertDialog dialog = alertDialog.create();
        // Showing Alert Message
        alertDialog.show();
    }



    public static void showAlertDialogMessage1(Context context, String title, String message, Boolean status, final AlertDialogCallback1 listener) {
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);

        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setCancelable(status);

        /*if(status != null)
            // Setting alert dialog icon
            alertDialog.setIcon((status) ? R.drawable.success : R.drawable.fail);*/

        alertDialog.setPositiveButton("ok", new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                if (listener == null) {
                    dialogInterface.dismiss();
                } else {
                    listener.alertDialogCallbackOk1();
                }
            }
        });



        AlertDialog dialog = alertDialog.create();
        // Showing Alert Message
        alertDialog.show();
    }





}

