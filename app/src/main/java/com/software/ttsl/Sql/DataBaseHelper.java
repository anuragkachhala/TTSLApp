package com.software.ttsl.Sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String TAG =DataBaseHelper.class.getName();
    public DataBaseHelper(Context context) {
        super(context, DataBaseConstant.DATABASE_NAME, null, DataBaseConstant.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        try
        {
            sqLiteDatabase.execSQL(DataBaseConstant.CREATE_PORT_TABLE);

            sqLiteDatabase.execSQL(DataBaseConstant.CREATE_TABLE_TRACKING_CONSTRAINT);

            sqLiteDatabase.execSQL(DataBaseConstant.CREATE_TABLE_PENDING_INVOICE);

            Log.v(TAG,DataBaseConstant.CREATE_TABLE_PENDING_INVOICE);

            sqLiteDatabase.execSQL(DataBaseConstant.CREATE_TABLE_BILL_OF_LADING);
            Log.v(TAG,DataBaseConstant.CREATE_TABLE_BILL_OF_LADING);

            sqLiteDatabase.execSQL(DataBaseConstant.CREATE_TABLE_VESSLE_LIST);
            Log.v(TAG,DataBaseConstant.CREATE_TABLE_VESSLE_LIST);

            sqLiteDatabase.execSQL(DataBaseConstant.CREATE_TABLE_LEAD);
            Log.v(TAG,DataBaseConstant.CREATE_TABLE_LEAD);

            sqLiteDatabase.execSQL(DataBaseConstant.CREATE_TABLE_ADD_CONTACT);
            Log.v(TAG, DataBaseConstant.CREATE_TABLE_ADD_CONTACT);

            sqLiteDatabase.execSQL(DataBaseConstant.CREATE_TABLE_DEAL);
            Log.v(TAG,DataBaseConstant.CREATE_TABLE_DEAL);

            sqLiteDatabase.execSQL(DataBaseConstant.CREATE_TABLE_ADD_ACCOUNT);
            Log.v(TAG, DataBaseConstant.CREATE_TABLE_ADD_ACCOUNT);

            sqLiteDatabase.execSQL(DataBaseConstant.CREATE_TABLE_SALES_BUDGET);
            Log.v(TAG,DataBaseConstant.CREATE_TABLE_SALES_BUDGET);

            sqLiteDatabase.execSQL(DataBaseConstant.CREATE_TABLE_CUSTOMER_CHALLENGE);
            Log.v(TAG,DataBaseConstant.CREATE_TABLE_CUSTOMER_CHALLENGE);

            sqLiteDatabase.execSQL(DataBaseConstant.CREATE_TABLE_PROMOTIONAL_MAIL);
            Log.v(TAG, DataBaseConstant.CREATE_TABLE_PROMOTIONAL_MAIL);

            sqLiteDatabase.execSQL(DataBaseConstant.CREATE_TABLE_ADD_TASK);
            Log.v(TAG,DataBaseConstant.CREATE_TABLE_ADD_TASK);

            sqLiteDatabase.execSQL(DataBaseConstant.CREATE_TABLE_ADD_EVENT);
            Log.v(TAG,DataBaseConstant.CREATE_TABLE_ADD_EVENT);

            sqLiteDatabase.execSQL(DataBaseConstant.CREATE_TABLE_PARTICIPANTS);
            Log.v(TAG,DataBaseConstant.CREATE_TABLE_PARTICIPANTS);

            sqLiteDatabase.execSQL(DataBaseConstant.CREATE_TABLE_CALL);
            Log.v(TAG,DataBaseConstant.CREATE_TABLE_CALL);


            sqLiteDatabase.execSQL(DataBaseConstant.CREATE_TABLE_IMAGE_MASTER);
            Log.v(TAG,DataBaseConstant.CREATE_TABLE_IMAGE_MASTER);



        }catch (Exception ex)
        {
            ex.printStackTrace();
            sqLiteDatabase.execSQL(DataBaseConstant.CREATE_TABLE_IMAGE_MASTER);
            Log.v(TAG,DataBaseConstant.CREATE_TABLE_IMAGE_MASTER);
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {


        sqLiteDatabase.execSQL(DataBaseConstant.DROP_PORT_TABLE);

        sqLiteDatabase.execSQL(DataBaseConstant.DROP_TABLE_BL_LIST);

        sqLiteDatabase.execSQL(DataBaseConstant.DROP_TABLE_PENDING_INVOICE);

        sqLiteDatabase.execSQL(DataBaseConstant.DROP_TABLE_VESSEL);

        onCreate(sqLiteDatabase);

        }
}
