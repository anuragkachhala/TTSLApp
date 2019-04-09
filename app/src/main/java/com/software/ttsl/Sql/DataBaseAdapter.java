package com.software.ttsl.Sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.software.ttsl.Request.AccountDataModel;
import com.software.ttsl.Request.CallDataModel;
import com.software.ttsl.Request.DealDataModel;
import com.software.ttsl.Request.EventDataModel;
import com.software.ttsl.Request.LeadDataModel;
import com.software.ttsl.Request.Participant;
import com.software.ttsl.Request.SyncAllDataModel;
import com.software.ttsl.Request.TaskDataModel;
import com.software.ttsl.Response.FormDropDown.DropDownDataModel;
import com.software.ttsl.Response.ImageResponse;
import com.software.ttsl.Response.PendingInvoiceResponce;
import com.software.ttsl.Response.PortDataResponse;
import com.software.ttsl.Response.TrackingNoConstraintResponse;
import com.software.ttsl.Response.VesselListResponse;
import com.software.ttsl.Utils.ContactListSortingData;
import com.software.ttsl.Utils.DealListSortingData;
import com.software.ttsl.Utils.SingletonTrackingConstraint;
import com.software.ttsl.model.AddContactData;
import com.software.ttsl.model.AddLeadData;
import com.software.ttsl.model.BillListData;
import com.software.ttsl.model.CustomerChallengeModel;
import com.software.ttsl.model.LeadListSortingData;
import com.software.ttsl.model.OrderByData;
import com.software.ttsl.model.ParticipantsData;
import com.software.ttsl.model.PendingInvoice;
import com.software.ttsl.model.PortData;
import com.software.ttsl.model.SalesBudgetModel;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.software.ttsl.Sql.DataBaseConstant.COLUMN_BUDGET_ID;
import static com.software.ttsl.Sql.DataBaseConstant.COLUMN_BUDGET_IS_SYNC;
import static com.software.ttsl.Sql.DataBaseConstant.COLUMN_CALL_ID;
import static com.software.ttsl.Sql.DataBaseConstant.COLUMN_CALL_IS_SYNC;
import static com.software.ttsl.Sql.DataBaseConstant.COLUMN_CUSTOMER_ID;
import static com.software.ttsl.Sql.DataBaseConstant.COLUMN_CUSTOMER_IS_SYNC;
import static com.software.ttsl.Utils.EmployConstantUtil.ACCOUNT_TYPE;
import static com.software.ttsl.Utils.EmployConstantUtil.CONTACT_TYPE;
import static com.software.ttsl.Utils.EmployConstantUtil.LEAD_TYPE;


public class DataBaseAdapter {

    public static final String LEAD_SYNC = "LEAD";
    public static final String ACCOUNT_SYNC = "ACCOUNT";
    public static final String CONTACT_SYNC = "CONTACT";
    public static final String TASK_SYNC = "TASK";
    public static final String EVENT_SYNC = "EVENT";
    public static final String DEAL_SYNC = "DEAL";
    public static final String CALL_SYNC = "CALL";
    public static final String CUSTOMER_SYNC = "CUSTOMER";
    public static final String SALES_SYNC = "SALES";

    public static final String TAG = DataBaseAdapter.class.getName();


    Context context;
    DataBaseHelper dataBaseHelper;
    SQLiteDatabase sqLiteDatabase;

    public DataBaseAdapter(Context context) {
        this.context = context;
        dataBaseHelper = new DataBaseHelper(context);
    }

    // open DataBase
    public void openDataBase() {
        try {
            sqLiteDatabase = dataBaseHelper.getWritableDatabase();
            //sqLiteDatabase.beginTransaction();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //close DataBase
    public void closeDataBase() {

        try {
            dataBaseHelper.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public void dropTables() {

        openDataBase();
        sqLiteDatabase.execSQL("delete from " + DataBaseConstant.TABLE_PENDING_INVOICE);
        sqLiteDatabase.execSQL("delete from " + DataBaseConstant.TABLE_BILL_OF_LADING);



    }
    public void dropCRMTable(){
        openDataBase();
        sqLiteDatabase.execSQL("delete from "+ DataBaseConstant.TABLE_LEAD);
        sqLiteDatabase.execSQL("delete from "+ DataBaseConstant.TABLE_ADD_ACCOUNT);
        sqLiteDatabase.execSQL("delete from "+ DataBaseConstant.TABLE_ADD_CONTACT);
        sqLiteDatabase.execSQL("delete from "+ DataBaseConstant.TABLE_ADD_EVENT);
        sqLiteDatabase.execSQL("delete from "+ DataBaseConstant.TABLE_ADD_CALL);
        sqLiteDatabase.execSQL("delete from "+ DataBaseConstant.TABLE_IMAGE_MASTER);
        sqLiteDatabase.execSQL("delete from "+ DataBaseConstant.TABLE_ADD_TASK);
        sqLiteDatabase.execSQL("delete from "+ DataBaseConstant.TABLE_ADD_DEAL);
        sqLiteDatabase.execSQL("delete from "+ DataBaseConstant.TABLE_SALES_BUDGET);
        sqLiteDatabase.execSQL("delete from "+ DataBaseConstant.TABLE_CUSTOMER_CHALLENGE);
        sqLiteDatabase.execSQL("delete from "+ DataBaseConstant.TABLE_PARTICIPANTS);
        sqLiteDatabase.execSQL("delete from "+ DataBaseConstant.TABLE_PROMOTIONAL_MAIL);

    }


    public List<PortData> retrievePortData(String searchTerm) {
        List<PortData> portDataList = new ArrayList<PortData>();
        Cursor cursor = null;
        SQLiteDatabase db = dataBaseHelper.getReadableDatabase();

        String getPortDataQuery = "SELECT * FROM " + DataBaseConstant.TABLE_NAME_PORT +
                " WHERE " + DataBaseConstant.COLUMN_PORT_CODE + " LIKE '%" + searchTerm + "%' OR "
                + DataBaseConstant.COLUMN_PORT_CITY + " LIKE '%" + searchTerm + "%' OR "
                + DataBaseConstant.COLUMN_PORT_COUNTRY + " LIKE '%" + searchTerm + "%'";
        cursor = db.rawQuery(getPortDataQuery, null);
        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                PortData portData = new PortData();
                portData.setId(cursor.getInt(cursor.getColumnIndex(DataBaseConstant.COLUMN_PORT_ID)));
                portData.setPortCode(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_PORT_CODE)));
                portData.setState(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_PORT_CITY)));
                portData.setCountry(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_PORT_COUNTRY)));

                portDataList.add(portData);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return portDataList;

    }


    public List<VesselListResponse> retrieveVesseData(String searchTerm) {
        List<VesselListResponse> vesselList = new ArrayList<VesselListResponse>();
        Cursor cursor = null;
        SQLiteDatabase db = dataBaseHelper.getReadableDatabase();
        String getVesselQuery = "SELECT * FROM " + DataBaseConstant.TABLE_VESSEL +
                " WHERE " + DataBaseConstant.COLUMN_VESSEL_NAME + " LIKE '%" + searchTerm + "%'";
        cursor = db.rawQuery(getVesselQuery, null);
        if (cursor.moveToFirst()) {
            do {
                VesselListResponse vesselListResponse = new VesselListResponse();
                vesselListResponse.setVesselId(cursor.getInt(cursor.getColumnIndex(DataBaseConstant.COLUMN_VESSEL_ID)));
                vesselListResponse.setVesselName(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_VESSEL_NAME)));
                vesselList.add(vesselListResponse);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return vesselList;

    }


    public void addTrackingNoConstraint(List<TrackingNoConstraintResponse> constrainList) {
        openDataBase();

        long rowId;
        ContentValues values;
        HashMap<String, TrackingNoConstraintResponse> constraintHashMap = SingletonTrackingConstraint.getInstance().getHashMap();

        for (TrackingNoConstraintResponse noConstraintResponse : constrainList) {
            constraintHashMap.put(noConstraintResponse.getTrackType(), noConstraintResponse);

            values = new ContentValues();
            values.put(DataBaseConstant.COLUMN_TRACKING_TYPE, noConstraintResponse.getTrackType());
            values.put(DataBaseConstant.COLUMN_TRACKING_NO_MAX_LENGTH, noConstraintResponse.getMaxLength());
            values.put(DataBaseConstant.COLUMN_TRACKING_NO_MIN_LENGTH, noConstraintResponse.getMinLength());
            values.put(DataBaseConstant.COLUMN_TRACKING_NO_TYPE, noConstraintResponse.getType());
            values.put(DataBaseConstant.COLUMN_TRACKING_NO_START_WITH, noConstraintResponse.getStartWith());
            values.put(DataBaseConstant.COLUMN_TRACKING_NO_START_WITH_LENGTH, noConstraintResponse.getStartWithLength());
            values.put(DataBaseConstant.COLUMN_TRACKING_ID, noConstraintResponse.getTrackingId());
            rowId = sqLiteDatabase.insert(DataBaseConstant.TABLE_TRACKING_CONSTRAINT, null, values);

            Log.e(TAG, "" + values + "  " + rowId);
        }
    }


    public void addImage(List<ImageResponse> imageResponsesList) {
        openDataBase();
        long rowId;
        ContentValues values;
        for (ImageResponse imageResponse : imageResponsesList) {
            values = new ContentValues();
            values.put(DataBaseConstant.COLUMN_IMAGE_ID, imageResponse.getImageId());
            values.put(DataBaseConstant.COLUMN_IMAGE_FILE, imageResponse.getImageFile());
            rowId = sqLiteDatabase.insert(DataBaseConstant.TABLE_IMAGE_MASTER, null, values);

            Log.e(TAG, "" + values + "  " + rowId);
        }
    }

    public String getImage(long id){
        String image= null;
        sqLiteDatabase = dataBaseHelper.getReadableDatabase();
        String getLeadQuery = DataBaseConstant.SELECT_IMAGE+ " " + id;
        Cursor cursor = sqLiteDatabase.rawQuery(getLeadQuery, null);
        if (cursor.moveToFirst()) {
        image = cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_IMAGE_FILE));

        }


        return image;

    }


    public void addImage(ImageResponse imageResponse){
        openDataBase();
        long rowId;
        ContentValues values;

            values = new ContentValues();
            values.put(DataBaseConstant.COLUMN_IMAGE_ID, imageResponse.getImageId());
            values.put(DataBaseConstant.COLUMN_IMAGE_FILE, imageResponse.getImageFile());
            rowId = sqLiteDatabase.insert(DataBaseConstant.TABLE_IMAGE_MASTER, null, values);

            Log.e(TAG, "" + values + "  " + rowId);


    }



    public List<TrackingNoConstraintResponse> getTrackingNoConstatraint() {
        ArrayList<TrackingNoConstraintResponse> trackingNoConstraintResponsesList = new ArrayList<>();
        sqLiteDatabase = dataBaseHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(DataBaseConstant.SELECT_TACKING_CONSTRAINT, null);
        if (cursor.moveToFirst()) {
            do {
                TrackingNoConstraintResponse trackingNoConstraintResponse = new TrackingNoConstraintResponse();
                trackingNoConstraintResponse.setTrackingId(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_TRACKING_ID)));
                trackingNoConstraintResponse.setMaxLength(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_TRACKING_NO_MAX_LENGTH)));
                trackingNoConstraintResponse.setMinLength(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_TRACKING_NO_MIN_LENGTH)));
                trackingNoConstraintResponse.setTrackType(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_TRACKING_TYPE)));
                trackingNoConstraintResponse.setType(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_TRACKING_NO_TYPE)));
                trackingNoConstraintResponse.setStartWith(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_TRACKING_NO_START_WITH)));
                trackingNoConstraintResponse.setStartWithLength(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_TRACKING_NO_START_WITH_LENGTH)));
                trackingNoConstraintResponsesList.add(trackingNoConstraintResponse);

            } while (cursor.moveToNext());
        }
        return trackingNoConstraintResponsesList;
    }


    public void addPendingInvoices(List<PendingInvoiceResponce> pendingInvoiceResponceList) {
        openDataBase();
        long rowId;
        ContentValues values;

        for (PendingInvoiceResponce invoiceResponce : pendingInvoiceResponceList) {
            values = new ContentValues();
            values.put(DataBaseConstant.COLUMN_INVOICE_ID, invoiceResponce.getBookingId());
            values.put(DataBaseConstant.COLUMN_INVOICE_BOOKING_NUMBER, invoiceResponce.getBookingNumber());
            values.put(DataBaseConstant.COLUMN_INVOICE_NUMBER, invoiceResponce.getInvoiceNumber());
            values.put(DataBaseConstant.COLUMN_INVOICE_AMOUNT, invoiceResponce.getLocalAmount());
            values.put(DataBaseConstant.COLUMN_INVOICE_DUE_DATE, invoiceResponce.getDueDate());
            values.put(DataBaseConstant.COLUMN_INVOICE_MONTH_ID, getMonth(String.valueOf(invoiceResponce.getDueDate())));
            values.put(DataBaseConstant.COLUMN_INVOICE_MONTH, new DateFormatSymbols().getMonths()[getMonth(invoiceResponce.getDueDate()) - 1]);
            rowId = sqLiteDatabase.insert(DataBaseConstant.TABLE_PENDING_INVOICE, null, values);

            Log.e(TAG, values + " " + rowId);

        }
    }

    private int getMonth(String dueDate) {
        String[] date = dueDate.split("-");
        int monthid = Integer.parseInt(date[1]);
        return monthid;
    }

    public void addBillOfLadingList(List<BillListData> billOfLadingLists) {
        openDataBase();
        long rowId;
        ContentValues values;
        for (BillListData billListData : billOfLadingLists) {
            values = new ContentValues();
            values.put(DataBaseConstant.COLUMN_BILL_OF_LADING_BOOKING_NO, billListData.getBookingNumber());
            values.put(DataBaseConstant.COLUMN_BILL_OF_LADING_BOOKING_DATE, billListData.getBookingDate());
            values.put(DataBaseConstant.COLUMN_BILL_OF_LADING_ETA, billListData.getEta());
            values.put(DataBaseConstant.COLUMN_BILL_OF_LADING_ETD, billListData.getEtd());
            rowId = sqLiteDatabase.insert(DataBaseConstant.TABLE_BILL_OF_LADING, null, values);
            Log.e(TAG, values + " " + rowId);
        }
    }


    public List<BillListData> getAllBillOfLading() {
        ArrayList<BillListData> billOfLadingList = new ArrayList<>();
        sqLiteDatabase = dataBaseHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(DataBaseConstant.SELECT_ALL_BILL_LIST, null);
        if (cursor.moveToFirst()) {
            do {
                BillListData billListData = new BillListData();
                billListData.setBookingNumber(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_BILL_OF_LADING_BOOKING_NO)));
                billListData.setBookingDate(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_BILL_OF_LADING_BOOKING_DATE)));
                billListData.setEta(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_BILL_OF_LADING_ETA)));
                billListData.setEtd(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_BILL_OF_LADING_ETD)));
                billOfLadingList.add(billListData);
            } while (cursor.moveToNext());
        }
        return billOfLadingList;
    }


    public List<PendingInvoice> getAllPendingInvoice() {
        ArrayList<PendingInvoice> invoiceArrayList = new ArrayList<>();
        sqLiteDatabase = dataBaseHelper.getWritableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery(DataBaseConstant.SELECT_ALL_PENDING_INVOICES, null);

        if (cursor.moveToFirst()) {
            do {
                PendingInvoice pendingInvoice = new PendingInvoice();
                pendingInvoice.setChecked(false);
                pendingInvoice.setAmount(Double.parseDouble(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_INVOICE_AMOUNT))));
                pendingInvoice.setDueDate(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_INVOICE_DUE_DATE)));
                pendingInvoice.setBillNumber(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_INVOICE_NUMBER)));
                pendingInvoice.setMounth(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_INVOICE_MONTH)));
                invoiceArrayList.add(pendingInvoice);
            } while (cursor.moveToNext());
        }

        return invoiceArrayList;
    }

    public List<PendingInvoice> getAllPendingInvoicesBy(String orderBy, String sortingType) {
        ArrayList<PendingInvoice> invoiceArrayList = new ArrayList<>();
        sqLiteDatabase = dataBaseHelper.getWritableDatabase();
        Cursor cursor = null;
        if (orderBy.equals("Month") && sortingType.equals("asc")) {
            cursor = sqLiteDatabase.rawQuery(DataBaseConstant.PENDING_INVOICE_SORT_BY_MONTH_ASC, null);
        } else if (orderBy.equals("Month") && sortingType.equals("desc")) {
            cursor = sqLiteDatabase.rawQuery(DataBaseConstant.PENDING_INVOICE_SORT_BY_MONTH_DESC, null);
        } else if (orderBy.equals("Amount") && sortingType.equals("asc")) {
            cursor = sqLiteDatabase.rawQuery(DataBaseConstant.PENDING_INVOICE_SORT_BY_AMOUNT_ASC, null);
        } else if (orderBy.equals("Amount") && sortingType.equals("desc")) {
            cursor = sqLiteDatabase.rawQuery(DataBaseConstant.PENDING_INVOICE_SORT_BY_AMOUNT_DESC, null);
        }


        if (cursor.moveToFirst()) {
            do {
                PendingInvoice pendingInvoice = new PendingInvoice();
                pendingInvoice.setChecked(false);
                pendingInvoice.setAmount(Double.parseDouble(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_INVOICE_AMOUNT))));
                pendingInvoice.setDueDate(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_INVOICE_DUE_DATE)));
                pendingInvoice.setBillNumber(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_INVOICE_NUMBER)));
                pendingInvoice.setMounth(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_INVOICE_MONTH)));
                invoiceArrayList.add(pendingInvoice);
            } while (cursor.moveToNext());
        }


        return invoiceArrayList;
    }

    public void addPortData(Map<String, List<PortDataResponse>> portDataList) {
        //openDataBase();
        sqLiteDatabase = dataBaseHelper.getWritableDatabase();
        sqLiteDatabase.beginTransaction();

        Log.e("start time", String.valueOf(System.currentTimeMillis()));
        long rowId;
        ContentValues values;
        try {

            for (Map.Entry<String, List<PortDataResponse>> entry : portDataList.entrySet()) {

                String portCountry = entry.getKey();
                List<PortDataResponse> portDataResponsesList = entry.getValue();
                //use key and value
                for (PortDataResponse portDataResponse : portDataResponsesList) {
                    values = new ContentValues();
                    values.put(DataBaseConstant.COLUMN_PORT_ID, portDataResponse.getPortId());
                    values.put(DataBaseConstant.COLUMN_PORT_CODE, portDataResponse.getCityCode());
                    values.put(DataBaseConstant.COLUMN_PORT_CITY, portDataResponse.getCityName());
                    values.put(DataBaseConstant.COLUMN_PORT_COUNTRY, portCountry);
                    rowId = sqLiteDatabase.insert(DataBaseConstant.TABLE_NAME_PORT, null, values);
                    Log.e("port data", rowId + " " + values + " ");


                }

            }

            sqLiteDatabase.setTransactionSuccessful();

        } finally {
            sqLiteDatabase.endTransaction();
        }


        Log.e("End time", String.valueOf(System.currentTimeMillis()));


    }


    public void addVessels(List<VesselListResponse> vesselListResponses) {


        sqLiteDatabase = dataBaseHelper.getWritableDatabase();
        sqLiteDatabase.beginTransaction();


        long rowId;
        ContentValues values;
        try {
            for (VesselListResponse vesselListResponse : vesselListResponses) {
                values = new ContentValues();
                values.put(DataBaseConstant.COLUMN_VESSEL_ID, vesselListResponse.getVesselId());
                values.put(DataBaseConstant.COLUMN_VESSEL_NAME, vesselListResponse.getVesselName());
                rowId = sqLiteDatabase.insert(DataBaseConstant.TABLE_VESSEL, null, values);
                Log.e(TAG, values + " " + rowId);
            }
            sqLiteDatabase.setTransactionSuccessful();
        } finally {
            sqLiteDatabase.endTransaction();
        }
    }

    public long addPort(PortData portData) {

        long rowId;
        openDataBase();

        ContentValues values = new ContentValues();
        values.put(DataBaseConstant.COLUMN_PORT_CODE, portData.getPortCode());
        values.put(DataBaseConstant.COLUMN_PORT_CITY, portData.getState());
        values.put(DataBaseConstant.COLUMN_PORT_COUNTRY, portData.getCountry());
        rowId = sqLiteDatabase.insert(DataBaseConstant.TABLE_NAME_PORT, null, values);

        return rowId;
    }


    // add  Lead....
    public long addLeadData(LeadDataModel leadDataModel) {
        long rowID = 0;
        openDataBase();
        ContentValues values = new ContentValues();
        values.put(DataBaseConstant.COLUMN_LEAD_ID, leadDataModel.getLeadId());
        values.put(DataBaseConstant.COLUMN_LEAD_IMAGE, leadDataModel.getUploadedInputStream());
        values.put(DataBaseConstant.COLUMN_LEAD_OWNER, leadDataModel.getLeadOwner());
        values.put(DataBaseConstant.COLUMN_LEAD_FIRST_NAME, leadDataModel.getFirstName());
        values.put(DataBaseConstant.COLUMN_LEAD_LAST_NAME, leadDataModel.getLastName());
        values.put(DataBaseConstant.COLUMN_LEAD_COMPANY, leadDataModel.getCompany());
        values.put(DataBaseConstant.COLUMN_LEAD_TITLE, leadDataModel.getTitle());
        values.put(DataBaseConstant.COLUMN_LEAD_PHONE, leadDataModel.getPhone());
        values.put(DataBaseConstant.COLUMN_LEAD_FAX, leadDataModel.getFax());
        values.put(DataBaseConstant.COLUMN_LEAD_MOBILE, leadDataModel.getMobile());
        values.put(DataBaseConstant.COLUMN_LEAD_WEB_SITE, leadDataModel.getWebsite());
        values.put(DataBaseConstant.COLUMN_LEAD_SOURCE, leadDataModel.getLeadSource());
        values.put(DataBaseConstant.COLUMN_LEAD_STATUS, leadDataModel.getLeadStatus());
        values.put(DataBaseConstant.COLUMN_LEAD_INDUSTRY, leadDataModel.getIndustry());
        values.put(DataBaseConstant.COLUMN_LEAD_EMPLOYEES, leadDataModel.getNoOfEmployees());
        values.put(DataBaseConstant.COLUMN_LEAD_REVENUE, leadDataModel.getAnnualRevenue());
        values.put(DataBaseConstant.COLUMN_LEAD_RATING, leadDataModel.getRating());
        values.put(DataBaseConstant.COLUMN_LEAD_EMAIL_OPT, leadDataModel.getEmailOptOut());
        values.put(DataBaseConstant.COLUMN_LEAD_SKYPE_ID, leadDataModel.getSkypeId());
        values.put(DataBaseConstant.COLUMN_LEAD_SOLUTION, leadDataModel.getSalutation());
        values.put(DataBaseConstant.COLUMN_LEAD_DISCRIPTION, leadDataModel.getDescription());
        values.put(DataBaseConstant.COLUMN_LEAD_EMAIL, leadDataModel.getEmail());
        values.put(DataBaseConstant.COLUMN_LEAD_INDUSTRY, leadDataModel.getIndustry());
        values.put(DataBaseConstant.COLUMN_LEAD_SECONDARY_MAIL, leadDataModel.getSecondaryEmailId());
        values.put(DataBaseConstant.COLUMN_LEAD_CREATED_BY, leadDataModel.getCreatedBy());
        values.put(DataBaseConstant.COLUMN_LEAD_MODIFY_BY, leadDataModel.getModifyBy());
        values.put(DataBaseConstant.COLUMN_LEAD_CREATED_TIME, leadDataModel.getCreatedDate());
        values.put(DataBaseConstant.COLUMN_LEAD_MODIFIED_TIME, leadDataModel.getModifyDate());
        values.put(DataBaseConstant.COLUMN_LEAD_STREET, leadDataModel.getAddressStreet());
        values.put(DataBaseConstant.COLUMN_LEAD_CITY, leadDataModel.getAddressCity());
        values.put(DataBaseConstant.COLUMN_LEAD_STATE, leadDataModel.getAddressState());
        values.put(DataBaseConstant.COLUMN_LEAD_ZIP_CODE, leadDataModel.getAddressZipCode());
        values.put(DataBaseConstant.COLUMN_LEAD_COUNTRY, leadDataModel.getAddressCounty());
        values.put(DataBaseConstant.COLUMN_LEAD_TWITTER, leadDataModel.getTwitter());
        values.put(DataBaseConstant.COLUMN_LEAD_IS_SYNC, leadDataModel.isSync());
        values.put(DataBaseConstant.COLUMN_LEAD_CITY_ID,leadDataModel.getCityId());
        values.put(DataBaseConstant.COLUMN_LEAD_STATE_ID,leadDataModel.getStateId());
        values.put(DataBaseConstant.COLUMN_LEAD_COUNTRY_ID,leadDataModel.getCountryID());
        values.put(DataBaseConstant.COLUMN_LEAD_NAME,leadDataModel.getLeadName());
        rowID = sqLiteDatabase.insert(DataBaseConstant.TABLE_LEAD, null, values);
        Log.e(TAG, " " + rowID + " " + values);
        return rowID;
    }

    public int updateImage(ImageResponse imageResponse){
        int result;
        openDataBase();
        ContentValues values = new ContentValues();
        if(imageResponse!=null) {
            values.put(DataBaseConstant.COLUMN_IMAGE_FILE, imageResponse.getImageFile());

            result = sqLiteDatabase.update(DataBaseConstant.TABLE_IMAGE_MASTER, values, "" + DataBaseConstant.COLUMN_IMAGE_ID + " = '" + imageResponse.getImageId() + "'", null);

            Log.e(TAG, " " + imageResponse.getImageId() + " " + values);

            return result;
        }

        return 0;


    }

    // update lead ......
    public int updateLeadData(LeadDataModel leadDataModel, long leadId) {
        int result;
        openDataBase();
        ContentValues values = new ContentValues();
        // values.put(DataBaseConstant.COLUMN_LEAD_ID, leadDataModel.getLeadId());
        values.put(DataBaseConstant.COLUMN_LEAD_IMAGE, leadDataModel.getUploadedInputStream());
        values.put(DataBaseConstant.COLUMN_LEAD_OWNER, leadDataModel.getLeadOwner());
        values.put(DataBaseConstant.COLUMN_LEAD_FIRST_NAME, leadDataModel.getFirstName());
        values.put(DataBaseConstant.COLUMN_LEAD_LAST_NAME, leadDataModel.getLastName());
        values.put(DataBaseConstant.COLUMN_LEAD_COMPANY, leadDataModel.getCompany());
        values.put(DataBaseConstant.COLUMN_LEAD_TITLE, leadDataModel.getTitle());
        values.put(DataBaseConstant.COLUMN_LEAD_PHONE, leadDataModel.getPhone());
        values.put(DataBaseConstant.COLUMN_LEAD_FAX, leadDataModel.getFax());
        values.put(DataBaseConstant.COLUMN_LEAD_MOBILE, leadDataModel.getMobile());
        values.put(DataBaseConstant.COLUMN_LEAD_WEB_SITE, leadDataModel.getWebsite());
        values.put(DataBaseConstant.COLUMN_LEAD_SOURCE, leadDataModel.getLeadSource());
        values.put(DataBaseConstant.COLUMN_LEAD_STATUS, leadDataModel.getLeadStatus());
        values.put(DataBaseConstant.COLUMN_LEAD_INDUSTRY, leadDataModel.getIndustry());
        values.put(DataBaseConstant.COLUMN_LEAD_EMPLOYEES, leadDataModel.getNoOfEmployees());
        values.put(DataBaseConstant.COLUMN_LEAD_REVENUE, leadDataModel.getAnnualRevenue());
        values.put(DataBaseConstant.COLUMN_LEAD_RATING, leadDataModel.getRating());
        values.put(DataBaseConstant.COLUMN_LEAD_EMAIL_OPT, leadDataModel.getEmailOptOut());
        values.put(DataBaseConstant.COLUMN_LEAD_SKYPE_ID, leadDataModel.getSkypeId());
        values.put(DataBaseConstant.COLUMN_LEAD_SOLUTION, leadDataModel.getSalutation());
        values.put(DataBaseConstant.COLUMN_LEAD_DISCRIPTION, leadDataModel.getDescription());
        values.put(DataBaseConstant.COLUMN_LEAD_EMAIL, leadDataModel.getEmail());
        values.put(DataBaseConstant.COLUMN_LEAD_INDUSTRY, leadDataModel.getIndustry());
        values.put(DataBaseConstant.COLUMN_LEAD_SECONDARY_MAIL, leadDataModel.getSecondaryEmailId());
        values.put(DataBaseConstant.COLUMN_LEAD_CREATED_BY, leadDataModel.getCreatedBy());
        values.put(DataBaseConstant.COLUMN_LEAD_MODIFY_BY, leadDataModel.getModifyBy());
        values.put(DataBaseConstant.COLUMN_LEAD_CREATED_TIME, leadDataModel.getCreatedDate());
        values.put(DataBaseConstant.COLUMN_LEAD_MODIFIED_TIME, leadDataModel.getModifyDate());
        values.put(DataBaseConstant.COLUMN_LEAD_STREET, leadDataModel.getAddressStreet());
        values.put(DataBaseConstant.COLUMN_LEAD_CITY, leadDataModel.getAddressCity());
        values.put(DataBaseConstant.COLUMN_LEAD_STATE, leadDataModel.getAddressState());
        values.put(DataBaseConstant.COLUMN_LEAD_ZIP_CODE, leadDataModel.getAddressZipCode());
        values.put(DataBaseConstant.COLUMN_LEAD_COUNTRY, leadDataModel.getAddressCounty());
        values.put(DataBaseConstant.COLUMN_LEAD_TWITTER, leadDataModel.getTwitter());
        values.put(DataBaseConstant.COLUMN_LEAD_IS_SYNC, leadDataModel.isSync());
        result = sqLiteDatabase.update(DataBaseConstant.TABLE_LEAD, values, "" + DataBaseConstant.COLUMN_LEAD_ID + " = '" + leadId + "'", null);
        Log.e(TAG, " " + leadId + " " + values);

        return result;

    }

    //get All Lead.......
    public List<LeadDataModel> getAllLead() {
        List<LeadDataModel> leadDataModelList = new ArrayList<>();
        LeadDataModel leadDataModel;
        sqLiteDatabase = dataBaseHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(DataBaseConstant.SELECT_TABLE_LEAD, null);
        Log.v(TAG, DataBaseConstant.SELECT_TABLE_LEAD);
        if (cursor.moveToFirst()) {
            do {
                leadDataModel = new LeadDataModel();
                leadDataModel.setCompany(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_LEAD_COMPANY)));
                leadDataModel.setLeadId(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_LEAD_ID)));
                leadDataModel.setCreatedDate(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_LEAD_CREATED_TIME)));
                leadDataModel.setFirstName(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_LEAD_FIRST_NAME)));
                leadDataModel.setLastName(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_LEAD_LAST_NAME)));
                leadDataModel.setLeadOwner(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_LEAD_OWNER)));
                leadDataModelList.add(leadDataModel);
            } while (cursor.moveToNext());
        }
        return leadDataModelList;
    }

    public void setAllLead(List<LeadDataModel> leadDataModelList) {

        for (LeadDataModel leadDataModel : leadDataModelList) {
            leadDataModel.setSync(true);
            addLeadData(leadDataModel);
        }

    }

    public void setDropDown(String dropDownName, List<DropDownDataModel> dropDownDataModelList){

        for(DropDownDataModel dropDownDataModel: dropDownDataModelList){
            addDropDown(dropDownDataModel,dropDownName);
        }


    }

    public List<DropDownDataModel> getDropDown(String dropDownName){
        List<DropDownDataModel> dropDownDataModelList = new ArrayList<DropDownDataModel>();
        DropDownDataModel dropDownDataModel = null;

        String selectQuery = "SELECT * FROM " + DataBaseConstant.MASTER_TABLE_DROP_DOWN+ " WHERE " + DataBaseConstant.COLUMN_KEY_CONSTANT + " = '" + dropDownName+ "'";
        sqLiteDatabase = dataBaseHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(selectQuery, null);
        Log.v(TAG, DataBaseConstant.SELECT_UN_SYNC_LEAD);
        if (cursor.moveToFirst()) {
            do {
                dropDownDataModel = new DropDownDataModel();
                dropDownDataModel.setKey(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_KEY)));
                dropDownDataModel.setValue(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_DROP_DOWN_VALUE)));
                dropDownDataModelList.add(dropDownDataModel);

            }
            while (cursor.moveToNext());
        }


        return dropDownDataModelList;
    }

    public void addDropDown(DropDownDataModel dropDownDataModel,String dropDownName){
        long rowID = 0;
        openDataBase();
        ContentValues values = new ContentValues();
        values.put(DataBaseConstant.COLUMN_KEY, dropDownDataModel.getKey());
        values.put(DataBaseConstant.COLUMN_DROP_DOWN_VALUE, dropDownDataModel.getValue());
        values.put(DataBaseConstant.COLUMN_KEY_CONSTANT,dropDownName);

        rowID = sqLiteDatabase.insert(DataBaseConstant.MASTER_TABLE_DROP_DOWN, null, values);
        Log.e(TAG, " " + rowID + " " + values);

        }

    public void setAllTask(List<TaskDataModel> taskDataModelList){
        for(TaskDataModel taskDataModel: taskDataModelList){
            taskDataModel.setSync(true);
            addTask(taskDataModel);
        }
    }

    public void  setAllAccounts(List<AccountDataModel> accountDataModelList){
        for(AccountDataModel accountDataModel: accountDataModelList){
            accountDataModel.setSync(true);
            addAccount(accountDataModel);
        }
    }

    public void  setAllContacts(List<AddContactData> contactDataList){
        for(AddContactData addContactData : contactDataList){
            addContactData.setSync(true);
            addContact(addContactData);
        }
    }
    public void setAllEvent(List<EventDataModel> eventDataModelList){
        for(EventDataModel eventDataModel : eventDataModelList){
            eventDataModel.setSync(true);
            addEvent(eventDataModel);
            ArrayList<Participant> participantList = new ArrayList<>();
            List<Participant> participants = eventDataModel.getParticipants();
            if(participants!=null) {
                for (Participant participant : eventDataModel.getParticipants()) {
                    Participant participant1 = null;
                    if (participant == null) {
                        continue;
                    }
                    String con = "null";
                    if(participant.getConstant()!=null){
                        con = participant.getConstant();
                    }
                    switch (con) {

                        case "leads":
                            participant1 = getParticipantLeadByID(participant.getParticipant());
                            if (participant1 != null) {
                                participant.setParticipantEmail(participant1.getParticipantEmail());
                                participant.setParticipantName(participant1.getParticipantName());
                            }
                            break;
                        case "contacts":
                            participant1 = getParticipantContactByID(participant.getParticipant());
                            if (participant1 != null) {
                                participant.setParticipantEmail(participant1.getParticipantEmail());
                                participant.setParticipantName(participant1.getParticipantName());
                            }
                            break;
                        case "null":
                            break;


                    }
                    participantList.add(participant);
                }
            }

            addParticipants(participantList);
        }
    }


    public Participant getParticipantLeadByID(long leadId) {
        Participant participant= null;

        Log.e(TAG, "get data from lead id " + leadId);

        sqLiteDatabase = dataBaseHelper.getWritableDatabase();

        String getLeadQuery = DataBaseConstant.GET_LEAD_BY_ID + " " + leadId;
        Cursor cursor = sqLiteDatabase.rawQuery(getLeadQuery, null);
        if (cursor.moveToFirst()) {
            participant = new Participant();
            participant.setParticipantName((cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_LEAD_FIRST_NAME))) + " " + (cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_LEAD_LAST_NAME))));
            participant.setParticipantEmail(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_LEAD_EMAIL)));

        }


        return participant;
    }

    public Participant getParticipantContactByID(long contactId) {
        Participant participant= null;

        Log.e(TAG, "get data from lead id " + contactId);

        sqLiteDatabase = dataBaseHelper.getWritableDatabase();

        String getLeadQuery = DataBaseConstant.GET_CONTACT_BY_ID+ " " + contactId;
        Cursor cursor = sqLiteDatabase.rawQuery(getLeadQuery, null);
        if (cursor.moveToFirst()) {
            participant = new Participant();
            participant.setParticipantName((cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CONTACT_FIRST_NAME))) + " " + (cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CONTACT_LAST_NAME))));
            participant.setParticipantEmail(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CONTACT_EMAIL)));

        }


        return participant;
    }






    public void setAllDeal(List<DealDataModel> dealDataModelList){
        for(DealDataModel dealDataModel : dealDataModelList){
            dealDataModel.setSync(true);
            addDeal(dealDataModel);
        }
    }

    public void setAllCall(List<CallDataModel> callDataModelList){
        for(CallDataModel callDataModel : callDataModelList){
            callDataModel.setSync(true);
            addCall(callDataModel);
        }
    }

    public void setAllSalesBudget(List<SalesBudgetModel> salesBudgetModelList){
        for(SalesBudgetModel salesBudgetModel : salesBudgetModelList){
            salesBudgetModel.setSync(true);
            addSalesBudget(salesBudgetModel);
        }
    }

    public void setAllCustomerChallenge(List<CustomerChallengeModel> customerChallengeModelList){
        for(CustomerChallengeModel customerChallengeModel : customerChallengeModelList){
            customerChallengeModel.setSync(true);
            addCustomerChallenge(customerChallengeModel);
        }
    }

    //get All Unsync Lead.......
    public List<LeadDataModel> getAllUnSyncLead() {
        List<LeadDataModel> leadDataModelList = new ArrayList<>();
        ImageResponse imageResponse = null;
        long leadId;
        LeadDataModel leadDataModel;
        sqLiteDatabase = dataBaseHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(DataBaseConstant.SELECT_UN_SYNC_LEAD, null);
        Log.v(TAG, DataBaseConstant.SELECT_UN_SYNC_LEAD);
        if (cursor.moveToFirst()) {
            do {
                leadDataModel = new LeadDataModel();
                leadId =cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_LEAD_ID));
                leadDataModel.setLeadId(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_LEAD_ID)));
                leadDataModel.setUploadedInputStream(cursor.getBlob(cursor.getColumnIndex(DataBaseConstant.COLUMN_LEAD_IMAGE)));
                leadDataModel.setLeadOwner(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_LEAD_OWNER)));
                leadDataModel.setFirstName(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_LEAD_FIRST_NAME)));
                leadDataModel.setLastName(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_LEAD_LAST_NAME)));
                leadDataModel.setTitle(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_LEAD_TITLE)));
                leadDataModel.setEmail(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_LEAD_EMAIL)));
                leadDataModel.setCompany(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_LEAD_COMPANY)));
                leadDataModel.setPhone(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_LEAD_PHONE)));
                leadDataModel.setFax(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_LEAD_FAX)));
                leadDataModel.setMobile(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_LEAD_MOBILE)));
                leadDataModel.setWebsite(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_LEAD_WEB_SITE)));
                leadDataModel.setIndustry(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_LEAD_INDUSTRY)));
                leadDataModel.setLeadSource(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_LEAD_SOURCE)));
                leadDataModel.setLeadStatus(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_LEAD_STATUS)));
                leadDataModel.setAnnualRevenue(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_LEAD_REVENUE)));
                leadDataModel.setRating(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_LEAD_RATING)));
                leadDataModel.setNoOfEmployees(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_LEAD_EMPLOYEES)));
                leadDataModel.setSkypeId(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_LEAD_SKYPE_ID)));
                leadDataModel.setSecondaryEmailId(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_LEAD_SECONDARY_MAIL)));
                leadDataModel.setAddressStreet(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_LEAD_STREET)));
                leadDataModel.setAddressCity(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_LEAD_CITY)));
                leadDataModel.setAddressZipCode(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_LEAD_ZIP_CODE)));
                leadDataModel.setAddressState(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_LEAD_STATE)));
                leadDataModel.setAddressCounty(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_LEAD_COUNTRY)));
                leadDataModel.setDescription(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_LEAD_DISCRIPTION)));
                leadDataModel.setEmailOptOut(cursor.getInt(cursor.getColumnIndex(DataBaseConstant.COLUMN_LEAD_EMAIL_OPT)) > 0);
                leadDataModel.setCreatedBy(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_LEAD_CREATED_BY)));
                leadDataModel.setCreatedDate(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_LEAD_CREATED_TIME)));
                leadDataModel.setModifyDate(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_LEAD_MODIFIED_TIME)));
                leadDataModel.setSalutation(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_LEAD_SOLUTION)));
                leadDataModel.setModifyBy(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_LEAD_MODIFY_BY)));
                leadDataModel.setTwitter(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_LEAD_TWITTER)));
                String image = getImage(leadId) ;
                if(image!=null) {
                    imageResponse = new ImageResponse();
                    imageResponse.setImageId(leadId);
                    imageResponse.setImageFile(getImage(leadId));
                    leadDataModel.setImageResponse(imageResponse);
                }
                leadDataModelList.add(leadDataModel);


            } while (cursor.moveToNext());
        }
        return leadDataModelList;
    }


    public List<ParticipantsData> getLeads() {
        List<ParticipantsData> participantsDataList = new ArrayList();
        ParticipantsData participantsData;
        sqLiteDatabase = dataBaseHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(DataBaseConstant.SELECT_TABLE_LEAD, null);
        Log.v(TAG, DataBaseConstant.SELECT_TABLE_LEAD);
        if (cursor.moveToFirst()) {
            do {
                participantsData = new ParticipantsData();
                participantsData.setId(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_LEAD_ID)));
                participantsData.setParticipantsName((cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_LEAD_FIRST_NAME))) + " " + (cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_LEAD_LAST_NAME))));
                participantsData.setParticipantsEmail(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_LEAD_EMAIL)));

                participantsDataList.add(participantsData);
            } while (cursor.moveToNext());
        }


        return participantsDataList;

    }

    //get Lead by ID
    public LeadDataModel getLeadByID(long leadId) {
        LeadDataModel leadDataModel = null;

        Log.e(TAG, "get data from lead id " + leadId);

        sqLiteDatabase = dataBaseHelper.getWritableDatabase();

        String getLeadQuery = DataBaseConstant.GET_LEAD_BY_ID + " " + leadId;
        Cursor cursor = sqLiteDatabase.rawQuery(getLeadQuery, null);
        if (cursor.moveToFirst()) {
            leadDataModel = new LeadDataModel();
            leadDataModel.setLeadId(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_LEAD_ID)));
            leadDataModel.setSync(cursor.getInt(cursor.getColumnIndex(DataBaseConstant.COLUMN_LEAD_IS_SYNC)) > 0);
            leadDataModel.setUploadedInputStream(cursor.getBlob(cursor.getColumnIndex(DataBaseConstant.COLUMN_LEAD_IMAGE)));
            leadDataModel.setLeadOwner(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_LEAD_OWNER)));
            leadDataModel.setFirstName(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_LEAD_FIRST_NAME)));
            leadDataModel.setLastName(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_LEAD_LAST_NAME)));
            leadDataModel.setTitle(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_LEAD_TITLE)));
            leadDataModel.setEmail(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_LEAD_EMAIL)));
            leadDataModel.setCompany(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_LEAD_COMPANY)));
            leadDataModel.setPhone(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_LEAD_PHONE)));
            leadDataModel.setFax(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_LEAD_FAX)));
            leadDataModel.setMobile(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_LEAD_MOBILE)));
            leadDataModel.setWebsite(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_LEAD_WEB_SITE)));
            leadDataModel.setIndustry(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_LEAD_INDUSTRY)));
            leadDataModel.setLeadSource(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_LEAD_SOURCE)));
            leadDataModel.setLeadStatus(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_LEAD_STATUS)));
            leadDataModel.setAnnualRevenue(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_LEAD_REVENUE)));
            leadDataModel.setRating(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_LEAD_RATING)));
            leadDataModel.setNoOfEmployees(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_LEAD_EMPLOYEES)));
            leadDataModel.setSkypeId(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_LEAD_SKYPE_ID)));
            leadDataModel.setSecondaryEmailId(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_LEAD_SECONDARY_MAIL)));
            leadDataModel.setAddressStreet(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_LEAD_STREET)));
            leadDataModel.setAddressCity(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_LEAD_CITY)));
            leadDataModel.setAddressZipCode(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_LEAD_ZIP_CODE)));
            leadDataModel.setAddressState(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_LEAD_STATE)));
            leadDataModel.setAddressCounty(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_LEAD_COUNTRY)));
            leadDataModel.setDescription(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_LEAD_DISCRIPTION)));
            leadDataModel.setEmailOptOut(cursor.getInt(cursor.getColumnIndex(DataBaseConstant.COLUMN_LEAD_EMAIL_OPT)) > 0);
            leadDataModel.setCreatedBy(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_LEAD_CREATED_BY)));
            leadDataModel.setCreatedDate(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_LEAD_CREATED_TIME)));
            leadDataModel.setModifyDate(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_LEAD_MODIFIED_TIME)));
            leadDataModel.setSalutation(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_LEAD_SOLUTION)));
            leadDataModel.setModifyBy(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_LEAD_MODIFY_BY)));
            leadDataModel.setTwitter(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_LEAD_TWITTER)));
        }


        return leadDataModel;
    }


    //


    // delete lead
    public boolean deleteLead(long leadId) {
        sqLiteDatabase = dataBaseHelper.getWritableDatabase();
        return  sqLiteDatabase.delete(DataBaseConstant.TABLE_LEAD, DataBaseConstant.COLUMN_LEAD_ID + " = " + leadId, null) > 0;
    }

    public boolean deleteImage(long imageId){
        sqLiteDatabase = dataBaseHelper.getWritableDatabase();
        return sqLiteDatabase.delete(DataBaseConstant.TABLE_IMAGE_MASTER, DataBaseConstant.COLUMN_IMAGE_ID+ " = " + imageId, null) > 0;
    }

    //Add Task

    public long addTask(TaskDataModel taskDataModel) {
        long rowID = 0;
        openDataBase();
        ContentValues values = new ContentValues();
        values.put(DataBaseConstant.COLUMN_ADD_TASK_ID, taskDataModel.getTaskId());
        values.put(DataBaseConstant.COLUMN_ADD_TASK_OWNER, taskDataModel.getTaskOwner());
        values.put(DataBaseConstant.COLUMN_ADD_TASK_SUBJECT, taskDataModel.getTaskSubject());
        values.put(DataBaseConstant.COLUMN_ADD_TASK_PRIORITY, taskDataModel.getTaskPriority());
        values.put(DataBaseConstant.COLUMN_ADD_TASK_CONTACT, taskDataModel.getTaskContact());
        values.put(DataBaseConstant.COLUMN_ADD_TASK_ACCOUNT_ID, taskDataModel.getTaskAccountId());
        values.put(DataBaseConstant.COLUMN_ADD_TASK_CONTACT_ID, taskDataModel.getTaskContactId());
        values.put(DataBaseConstant.COLUMN_ADD_TASK_ACCOUNT, taskDataModel.getTaskAccount());
        values.put(DataBaseConstant.COLUMN_ADD_TASK_STATUS, taskDataModel.getTaskStatus());
        values.put(DataBaseConstant.COLUMN_ADD_TASK_CREATED_BY, taskDataModel.getTaskCreatedBy());
        values.put(DataBaseConstant.COLUMN_ADD_TASK_MODIFY_BY, taskDataModel.getTaskModifyBy());
        values.put(DataBaseConstant.COLUMN_ADD_TASK_MODIFY_DATE, taskDataModel.getTaskModifyTime());
        values.put(DataBaseConstant.COLUMN_ADD_TASK_CREATED_DATE, taskDataModel.getTaskCreatedTime());
        values.put(DataBaseConstant.COLUMN_ADD_TASK_DESCRIPTION, taskDataModel.getTaskDescription());
        values.put(DataBaseConstant.COLUMN_ADD_TASK_DUE_DATE, taskDataModel.getTaskDueDate());
        values.put(DataBaseConstant.COLUMN_ADD_TASK_LEAD_ID, taskDataModel.getLeadId());
        values.put(DataBaseConstant.COLUMN_ADD_TASK_LEAD, taskDataModel.getLeadName());
        values.put(DataBaseConstant.COLUMN_ADD_TASK_IS_SYNC, taskDataModel.isSync());
        rowID = sqLiteDatabase.insert(DataBaseConstant.TABLE_ADD_TASK, null, values);
        Log.e(TAG, " " + rowID + " " + values);
        return rowID;
    }

    //get all task
    public List<TaskDataModel> getAllTask() {
        List<TaskDataModel> taskList = new ArrayList<>();

        sqLiteDatabase = dataBaseHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(DataBaseConstant.SELECT_TABLE_TASK, null);
        Log.e(TAG, DataBaseConstant.SELECT_TABLE_TASK);
        if (cursor.moveToFirst()) {
            do {
                TaskDataModel taskDataModel = new TaskDataModel();
                taskDataModel.setTaskSubject(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_ADD_TASK_SUBJECT)));
                taskDataModel.setTaskId(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_ADD_TASK_ID)));
                taskDataModel.setTaskContact(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_ADD_TASK_CONTACT)));
                taskDataModel.setTaskPriority(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_ADD_TASK_PRIORITY)));
                taskDataModel.setTaskOwner(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_ADD_TASK_OWNER)));
                taskDataModel.setTaskCreatedBy(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_ADD_TASK_CREATED_BY)));
                taskDataModel.setTaskCreatedTime(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_ADD_TASK_CREATED_DATE)));
                taskDataModel.setTaskDescription(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_ADD_TASK_DESCRIPTION)));
                taskDataModel.setTaskStatus(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_ADD_TASK_STATUS)));
                taskList.add(taskDataModel);
            } while (cursor.moveToNext());
        }

        return taskList;
    }

    public List<EventDataModel> getAllEventsById(String getEventQuery) {
        ArrayList<EventDataModel> eventDataList = new ArrayList<>();
        EventDataModel eventDataModel;
        sqLiteDatabase = dataBaseHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(getEventQuery, null);
        Log.v(TAG, DataBaseConstant.SELECT_TABLE_EVENT);
        if (cursor.moveToFirst()) {
            do {
                eventDataModel = new EventDataModel();
                eventDataModel.setEventId(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_EVENT_ID)));
                eventDataModel.setFromTime(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_EVENT_FROM_TIME)));
                eventDataModel.setToTime(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_EVENT_TO)));
                eventDataModel.setFromDate(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_EVENT_FROM)));
                eventDataModel.setToDate(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_EVENT_TO)));
                eventDataModel.setAllDay(cursor.getInt(cursor.getColumnIndex(DataBaseConstant.COLUMN_EVENT_IS_ALL_DAY)) > 0);
                eventDataModel.setTitle(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_EVENT_TITLE)));
                eventDataModel.setCreatedTime(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_EVENT_CREATED_TIME)));
                eventDataList.add(eventDataModel);
            } while (cursor.moveToNext());
        }


        return eventDataList;

    }

    public List<CallDataModel> getCallById(String type, long id) {
        String getCallByIdQuery;
        switch (type) {
            case LEAD_TYPE:
                getCallByIdQuery = "SELECT * FROM " + DataBaseConstant.TABLE_ADD_CALL + " WHERE " + DataBaseConstant.COLUMN_CALL_LEAD_ID + " = " + id;
                return getAllCallById(getCallByIdQuery);
            case ACCOUNT_TYPE:
                getCallByIdQuery = "SELECT * FROM " + DataBaseConstant.TABLE_ADD_CALL + " WHERE " + DataBaseConstant.COLUMN_CALL_ACCOUNT_ID + " = " + id;
                return getAllCallById(getCallByIdQuery);
            case CONTACT_TYPE:
                getCallByIdQuery = "SELECT * FROM " + DataBaseConstant.TABLE_ADD_CALL + " WHERE " + DataBaseConstant.COLUMN_CALL_CONTACT_ID + " = " + id;
                return getAllCallById(getCallByIdQuery);
        }
        return null;
    }

    public List<CallDataModel> getAllCallById(String getCallQuery) {
        ArrayList<CallDataModel> callDataModelList = new ArrayList<>();
        CallDataModel callDataModel;
        sqLiteDatabase = dataBaseHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(getCallQuery, null);
        Log.v(TAG, DataBaseConstant.SELECT_TABLE_EVENT);
        if (cursor.moveToFirst()) {
            do {
                callDataModel = new CallDataModel();
                callDataModel.setCallId(cursor.getLong(cursor.getColumnIndex(COLUMN_CALL_ID)));
                callDataModel.setSubject(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CALL_SUBJECT)));
                callDataModel.setContact(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CALL_CONTACT)));
                callDataModel.setCallDuration(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_CALL_DURATION)));
                callDataModel.setCallStartTime(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_CALL_START_TIME)));
                callDataModel.setCallStartDate(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_CALL_START_DATE)));
                callDataModel.setCallType(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CALL_TYPE)));
                callDataModel.setCreatedTime(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_CALL_CREATED_TIME)));
                callDataModelList.add(callDataModel);
            } while (cursor.moveToNext());
        }

        return callDataModelList;
    }

    public List<EventDataModel> getEventById(String type, long id) {
        String getEventByIdQuery;
        switch (type) {
            case LEAD_TYPE:
                getEventByIdQuery = "SELECT * FROM " + DataBaseConstant.TABLE_ADD_EVENT + " WHERE " + DataBaseConstant.COLUMN_EVENT_LEAD_ID + " = " + id;
                return getAllEventsById(getEventByIdQuery);
            case ACCOUNT_TYPE:
                getEventByIdQuery = "SELECT * FROM " + DataBaseConstant.TABLE_ADD_EVENT + " WHERE " + DataBaseConstant.COLUMN_EVENT_ACCOUNT_ID + " = " + id;
                return getAllEventsById(getEventByIdQuery);
            case CONTACT_TYPE:
                getEventByIdQuery = "SELECT * FROM " + DataBaseConstant.TABLE_ADD_EVENT + " WHERE " + DataBaseConstant.COLUMN_EVENT_CONTACT_ID + " = " + id;
                return getAllEventsById(getEventByIdQuery);
            default:
                break;
        }
        return null;
    }


    public List<TaskDataModel> getTaskById(String type, long id) {

        String getTaskByIdQuery;
        switch (type) {
            case LEAD_TYPE:
                getTaskByIdQuery = "SELECT * FROM " + DataBaseConstant.TABLE_ADD_TASK + " WHERE " + DataBaseConstant.COLUMN_ADD_TASK_LEAD_ID + " = " + id;
                return getAllTaskById(getTaskByIdQuery);
            case ACCOUNT_TYPE:
                getTaskByIdQuery = "SELECT * FROM " + DataBaseConstant.TABLE_ADD_TASK + " WHERE " + DataBaseConstant.COLUMN_ADD_TASK_ACCOUNT_ID + " = " + id;
                return getAllTaskById(getTaskByIdQuery);
            case CONTACT_TYPE:
                getTaskByIdQuery = "SELECT * FROM " + DataBaseConstant.TABLE_ADD_TASK + " WHERE " + DataBaseConstant.COLUMN_ADD_TASK_CONTACT_ID + " = " + id;
                return getAllTaskById(getTaskByIdQuery);
            default:
                break;
        }
        return null;
    }

    public List<TaskDataModel> getAllTaskById(String getTaskQuery) {
        openDataBase();
        List<TaskDataModel> taskList = new ArrayList<>();
        sqLiteDatabase = dataBaseHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(getTaskQuery, null);
        Log.e(TAG, DataBaseConstant.SELECT_TABLE_TASK);
        if (cursor.moveToFirst()) {
            do {
                TaskDataModel taskDataModel = new TaskDataModel();
                taskDataModel.setTaskSubject(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_ADD_TASK_SUBJECT)));
                taskDataModel.setTaskId(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_ADD_TASK_ID)));
                taskDataModel.setTaskContact(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_ADD_TASK_CONTACT)));
                taskDataModel.setTaskPriority(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_ADD_TASK_PRIORITY)));
                taskDataModel.setTaskOwner(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_ADD_TASK_OWNER)));
                taskDataModel.setTaskCreatedBy(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_ADD_TASK_CREATED_BY)));
                taskDataModel.setTaskCreatedTime(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_ADD_TASK_CREATED_DATE)));
                taskDataModel.setTaskDescription(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_ADD_TASK_DESCRIPTION)));
                taskDataModel.setTaskStatus(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_ADD_TASK_STATUS)));
                taskList.add(taskDataModel);
            } while (cursor.moveToNext());
        }

        closeDataBase();

        return taskList;

    }

    public List<TaskDataModel> getAllTaskByAccountId(long eventId) {
        openDataBase();
        List<TaskDataModel> taskList = new ArrayList<>();
        sqLiteDatabase = dataBaseHelper.getWritableDatabase();
        String getAccountQuery = DataBaseConstant.GET_TASK_BY_ACCOUNT_ID + " " + eventId;
        Cursor cursor = sqLiteDatabase.rawQuery(getAccountQuery, null);
        Log.e(TAG, DataBaseConstant.SELECT_TABLE_TASK);
        if (cursor.moveToFirst()) {
            do {
                TaskDataModel taskDataModel = new TaskDataModel();
                taskDataModel.setTaskSubject(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_ADD_TASK_SUBJECT)));
                taskDataModel.setTaskId(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_ADD_TASK_ID)));
                taskDataModel.setTaskContact(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_ADD_TASK_CONTACT)));
                taskDataModel.setTaskPriority(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_ADD_TASK_PRIORITY)));
                taskDataModel.setTaskOwner(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_ADD_TASK_OWNER)));
                taskDataModel.setTaskCreatedBy(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_ADD_TASK_CREATED_BY)));
                taskDataModel.setTaskCreatedTime(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_ADD_TASK_CREATED_DATE)));
                taskDataModel.setTaskDescription(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_ADD_TASK_DESCRIPTION)));
                taskDataModel.setTaskStatus(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_ADD_TASK_STATUS)));
                taskList.add(taskDataModel);
            } while (cursor.moveToNext());
        }

        closeDataBase();

        return taskList;

    }

    //get all Unsync task
    public List<TaskDataModel> getAllUnSyncTask() {
        List<TaskDataModel> taskList = new ArrayList<>();

        sqLiteDatabase = dataBaseHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(DataBaseConstant.SELECT_UN_SYNC_TASK, null);
        Log.e(TAG, DataBaseConstant.SELECT_UN_SYNC_TASK);
        if (cursor.moveToFirst()) {
            do {
                TaskDataModel taskDataModel = new TaskDataModel();
                taskDataModel.setTaskId(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_ADD_TASK_ID)));
                taskDataModel.setTaskSubject(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_ADD_TASK_SUBJECT)));
                taskDataModel.setTaskContact(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_ADD_TASK_CONTACT)));
                taskDataModel.setTaskPriority(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_ADD_TASK_PRIORITY)));
                taskDataModel.setTaskOwner(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_ADD_TASK_OWNER)));
                taskDataModel.setTaskCreatedBy(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_ADD_TASK_CREATED_BY)));
                taskDataModel.setTaskCreatedTime(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_ADD_TASK_CREATED_DATE)));
                taskDataModel.setTaskAccount(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_ADD_TASK_ACCOUNT)));
                taskDataModel.setTaskDescription(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_ADD_TASK_DESCRIPTION)));
                taskDataModel.setTaskContactId(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_ADD_TASK_CONTACT_ID)));
                taskDataModel.setTaskAccountId(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_ADD_TASK_ACCOUNT_ID)));
                taskDataModel.setTaskStatus(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_ADD_TASK_STATUS)));
                taskDataModel.setTaskModifyBy(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_ADD_TASK_MODIFY_BY)));
                taskDataModel.setTaskModifyTime(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_ADD_TASK_MODIFY_DATE)));
                taskDataModel.setTaskDueDate(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_ADD_TASK_DUE_DATE)));
                taskDataModel.setLeadName(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_ADD_TASK_LEAD)));
                taskDataModel.setLeadId(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_ADD_TASK_LEAD_ID)));
                taskList.add(taskDataModel);
            } while (cursor.moveToNext());
        }

        return taskList;
    }


    // getTask by Id
    public TaskDataModel getTaskByID(long taskId) {
        TaskDataModel taskDataModel = new TaskDataModel();

        Log.e(TAG, "get data from task id " + taskId);

        sqLiteDatabase = dataBaseHelper.getWritableDatabase();

        String getAccountQuery = DataBaseConstant.GET_TASK_BY_ID + " " + taskId;
        Cursor cursor = sqLiteDatabase.rawQuery(getAccountQuery, null);
        if (cursor.moveToFirst()) {
            taskDataModel.setSync(cursor.getInt(cursor.getColumnIndex(DataBaseConstant.COLUMN_ADD_TASK_IS_SYNC)) > 0);
            taskDataModel.setTaskSubject(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_ADD_TASK_SUBJECT)));
            taskDataModel.setTaskContact(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_ADD_TASK_CONTACT)));
            taskDataModel.setTaskPriority(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_ADD_TASK_PRIORITY)));
            taskDataModel.setTaskOwner(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_ADD_TASK_OWNER)));
            taskDataModel.setTaskCreatedBy(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_ADD_TASK_CREATED_BY)));
            taskDataModel.setTaskCreatedTime(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_ADD_TASK_CREATED_DATE)));
            taskDataModel.setTaskAccount(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_ADD_TASK_ACCOUNT)));
            taskDataModel.setTaskDescription(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_ADD_TASK_DESCRIPTION)));
            taskDataModel.setTaskContactId(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_ADD_TASK_CONTACT_ID)));
            taskDataModel.setTaskAccountId(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_ADD_TASK_ACCOUNT_ID)));
            taskDataModel.setTaskStatus(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_ADD_TASK_STATUS)));
            taskDataModel.setTaskModifyBy(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_ADD_TASK_MODIFY_BY)));
            taskDataModel.setTaskModifyTime(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_ADD_TASK_MODIFY_DATE)));
            taskDataModel.setTaskDueDate(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_ADD_TASK_DUE_DATE)));
            taskDataModel.setLeadName(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_ADD_TASK_LEAD)));
            taskDataModel.setLeadId(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_ADD_TASK_LEAD_ID)));

        }

        return taskDataModel;

    }

    //update task

    public long updateTask(TaskDataModel taskDataModel, long taskId) {
        long rowID = 0;
        openDataBase();
        ContentValues values = new ContentValues();
        values.put(DataBaseConstant.COLUMN_ADD_TASK_OWNER, taskDataModel.getTaskOwner());
        values.put(DataBaseConstant.COLUMN_ADD_TASK_SUBJECT, taskDataModel.getTaskSubject());
        values.put(DataBaseConstant.COLUMN_ADD_TASK_PRIORITY, taskDataModel.getTaskPriority());
        values.put(DataBaseConstant.COLUMN_ADD_TASK_CONTACT, taskDataModel.getTaskContact());
        values.put(DataBaseConstant.COLUMN_ADD_TASK_ACCOUNT_ID, taskDataModel.getTaskAccountId());
        values.put(DataBaseConstant.COLUMN_ADD_TASK_CONTACT_ID, taskDataModel.getTaskContactId());
        values.put(DataBaseConstant.COLUMN_ADD_TASK_ACCOUNT, taskDataModel.getTaskAccount());
        values.put(DataBaseConstant.COLUMN_ADD_TASK_STATUS, taskDataModel.getTaskStatus());
        values.put(DataBaseConstant.COLUMN_ADD_TASK_CREATED_BY, taskDataModel.getTaskCreatedBy());
        values.put(DataBaseConstant.COLUMN_ADD_TASK_MODIFY_BY, taskDataModel.getTaskModifyBy());
        values.put(DataBaseConstant.COLUMN_ADD_TASK_MODIFY_DATE, taskDataModel.getTaskModifyTime());
        values.put(DataBaseConstant.COLUMN_ADD_TASK_CREATED_DATE, taskDataModel.getTaskCreatedTime());
        values.put(DataBaseConstant.COLUMN_ADD_TASK_DESCRIPTION, taskDataModel.getTaskDescription());
        values.put(DataBaseConstant.COLUMN_ADD_TASK_DUE_DATE, taskDataModel.getTaskDueDate());
        values.put(DataBaseConstant.COLUMN_ADD_TASK_IS_SYNC, taskDataModel.isSync());
        values.put(DataBaseConstant.COLUMN_ADD_TASK_LEAD_ID,taskDataModel.getLeadId());
        values.put(DataBaseConstant.COLUMN_ADD_TASK_LEAD,taskDataModel.getLeadName());
        rowID = sqLiteDatabase.update(DataBaseConstant.TABLE_ADD_TASK, values,
                DataBaseConstant.COLUMN_ADD_TASK_ID + " =" + taskId, null);
        Log.e(TAG, " " + rowID + " " + values);
        return rowID;

    }


    public void updateTaskStatus(String status, long taskId){

        openDataBase();
        ContentValues cv = new ContentValues();
        cv.put(DataBaseConstant.COLUMN_ADD_TASK_STATUS, status);
        sqLiteDatabase.update(DataBaseConstant.TABLE_ADD_TASK, cv,
                DataBaseConstant.COLUMN_ADD_TASK_ID + " =" + taskId, null);
    }


    // delete task
    public boolean deleteTask(long taskId) {
        sqLiteDatabase = dataBaseHelper.getWritableDatabase();
        return sqLiteDatabase.delete(DataBaseConstant.TABLE_ADD_TASK, DataBaseConstant.COLUMN_ADD_TASK_ID + " = " + taskId, null) > 0;
    }


    //ADD ACCOUNT
    public long addAccount(AccountDataModel accountDataModel) {
        long rowID = 0;
        openDataBase();
        ContentValues values = new ContentValues();
        values.put(DataBaseConstant.COLUMN_ACCOUNT_ID, accountDataModel.getId());
        values.put(DataBaseConstant.COLUMN_ACCOUNT_OWNER, accountDataModel.getAccountOwner());
        values.put(DataBaseConstant.COLUMN_ACCOUNT_RATING, accountDataModel.getRating());
        values.put(DataBaseConstant.COLUMN_ACCOUNT_NAME, accountDataModel.getAccountName());
        values.put(DataBaseConstant.COLUMN_ACCOUNT_PHONE, accountDataModel.getPhone());
        values.put(DataBaseConstant.COLUMN_ACCOUNT_SITE, accountDataModel.getAccountSite());
        values.put(DataBaseConstant.COLUMN_ACCOUNT_FAX, accountDataModel.getFax());
        values.put(DataBaseConstant.COLUMN_ACCOUNT_PARENT_ACCOUNT, accountDataModel.getParentAccount());
        values.put(DataBaseConstant.COLUMN_PARENT_ACCOUNT_ID, accountDataModel.getParentAccountId());
        values.put(DataBaseConstant.COLUMN_ACCOUNT_WEBSITE, accountDataModel.getWebSite());
        values.put(DataBaseConstant.COLUMN_ACCOUNT_ACCOUNT_NUMBER, accountDataModel.getAccountNumber());
        values.put(DataBaseConstant.COLUMN_ACCOUNT_TICKER_SYMBOL, accountDataModel.getTickerSymbol());
        values.put(DataBaseConstant.COLUMN_ACCOUNT_ACCOUNT_TYPE, accountDataModel.getAccountType());
        values.put(DataBaseConstant.COLUMN_ACCOUNT_OWNERSHIP, accountDataModel.getOwnerShip());
        values.put(DataBaseConstant.COLUMN_ACCOUNT_REVENUE, accountDataModel.getAnnualRevenue());
        values.put(DataBaseConstant.COLUMN_ACCOUNT_EMPLOYEE, accountDataModel.getEmployees());
        values.put(DataBaseConstant.COLUMN_ACCOUNT_INDUSTRY, accountDataModel.getIndustry());
        values.put(DataBaseConstant.COLUMN_ACCOUNT_SIC_CODE, accountDataModel.getSicCode());
        values.put(DataBaseConstant.COLUMN_ACCOUNT_BILLING_ADDRESS_STREET, accountDataModel.getBillingAddressStreet());
        values.put(DataBaseConstant.COLUMN_ACCOUNT_BILLING_ADDRESS_CITY, accountDataModel.getBillingAddressCity());
        values.put(DataBaseConstant.COLUMN_ACCOUNT_BILLING_ADDRESS_STATE, accountDataModel.getBillingAddressState());
        values.put(DataBaseConstant.COLUMN_ACCOUNT_BILLING_ADDRESS_CODE, accountDataModel.getBillingAddressCode());
        values.put(DataBaseConstant.COLUMN_ACCOUNT_BILLING_ADDRESS_COUNTRY, accountDataModel.getBillingAddressCountry());
        values.put(DataBaseConstant.COLUMN_ACCOUNT_SHIPPING_ADDRESS_STREET, accountDataModel.getShippingAddressStreet());
        values.put(DataBaseConstant.COLUMN_ACCOUNT_SHIPPING_ADDRESS_CITY, accountDataModel.getShippingAddressCity());
        values.put(DataBaseConstant.COLUMN_ACCOUNT_SHIPPING_ADDRESS_STATE, accountDataModel.getShippingAddressState());
        values.put(DataBaseConstant.COLUMN_ACCOUNT_SHIPPING_ADDRESS_CODE, accountDataModel.getShippingAddressCode());
        values.put(DataBaseConstant.COLUMN_ACCOUNT_SHIPPING_ADDRESS_COUNTRY, accountDataModel.getShippingAddressCountry());
        values.put(DataBaseConstant.COLUMN_ACCOUNT_DISCRIPTION, accountDataModel.getDescription());
        values.put(DataBaseConstant.COLUMN_ACCOUNT_CREATED_BY, accountDataModel.getCreatedBy());
        values.put(DataBaseConstant.COLUMN_ACCOUNT_CREATED_TIME, accountDataModel.getCreatedTime());
        values.put(DataBaseConstant.COLUMN_ACCOUNT_MODIFY_BY, accountDataModel.getModifyBy());
        values.put(DataBaseConstant.COLUMN_ACCOUNT_MODIFY_TIME, accountDataModel.getModifyTime());
        values.put(DataBaseConstant.COLUMN_ACCOUNT_IS_SYNC, accountDataModel.isSync());

        rowID = sqLiteDatabase.insert(DataBaseConstant.TABLE_ADD_ACCOUNT, null, values);
        Log.e(TAG, " " + rowID + " " + values);
        return rowID;
    }


    public long setAllAccount(List<AccountDataModel> list) {
        long rowID = -1;
        for (AccountDataModel accountDataModel : list) {
            rowID = addAccount(accountDataModel);
        }
        closeDataBase();
        return rowID;

    }

    public List<TaskDataModel> getTaskById() {
        List<TaskDataModel> list = new ArrayList<>();

        return list;
    }

    // get All Account
    public List<AccountDataModel> getAllAccounts() {
        List<AccountDataModel> accountDataModelList = new ArrayList<>();

        sqLiteDatabase = dataBaseHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(DataBaseConstant.SELECT_TABLE_ACCOUNT, null);
        Log.e(TAG, DataBaseConstant.SELECT_TABLE_ACCOUNT);

        if (cursor.moveToFirst()) {
            do {
                AccountDataModel accountDataModel = new AccountDataModel();
                accountDataModel.setAccountName(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_ACCOUNT_NAME)));
                accountDataModel.setAccountOwner(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_ACCOUNT_OWNER)));
                accountDataModel.setId(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_ACCOUNT_ID)));
                accountDataModelList.add(accountDataModel);
            } while (cursor.moveToNext());
        }
        return accountDataModelList;
    }


    // get All Unsync Account
    public List<AccountDataModel> getAllUnSyncAccounts() {
        List<AccountDataModel> accountDataModelList = new ArrayList<>();

        sqLiteDatabase = dataBaseHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(DataBaseConstant.SELECT_UN_SYNC_ACCOUNT, null);
        Log.e(TAG, DataBaseConstant.SELECT_UN_SYNC_ACCOUNT);

        if (cursor.moveToFirst()) {
            do {
                AccountDataModel accountDataModel = new AccountDataModel();
                accountDataModel.setId(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_ACCOUNT_ID)));
                accountDataModel.setAccountOwner(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_ACCOUNT_OWNER)));
                accountDataModel.setRating(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_ACCOUNT_RATING)));
                accountDataModel.setAccountName(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_ACCOUNT_NAME)));
                accountDataModel.setPhone(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_ACCOUNT_PHONE)));
                accountDataModel.setFax(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_ACCOUNT_FAX)));
                accountDataModel.setAnnualRevenue(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_ACCOUNT_REVENUE)));
                accountDataModel.setEmployees(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_ACCOUNT_EMPLOYEE)));
                accountDataModel.setAccountSite(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_ACCOUNT_SITE)));
                accountDataModel.setParentAccount(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_ACCOUNT_PARENT_ACCOUNT)));
                accountDataModel.setParentAccountId(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_PARENT_ACCOUNT_ID)));
                accountDataModel.setWebSite(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_ACCOUNT_WEBSITE)));
                accountDataModel.setAccountNumber(cursor.getInt(cursor.getColumnIndex(DataBaseConstant.COLUMN_ACCOUNT_ACCOUNT_NUMBER)));
                accountDataModel.setTickerSymbol(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_ACCOUNT_TICKER_SYMBOL)));
                accountDataModel.setAccountType(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_ACCOUNT_ACCOUNT_TYPE)));
                accountDataModel.setOwnerShip(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_ACCOUNT_OWNERSHIP)));
                accountDataModel.setIndustry(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_ACCOUNT_INDUSTRY)));
                accountDataModel.setSicCode(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_ACCOUNT_SIC_CODE)));
                accountDataModel.setBillingAddressStreet(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_ACCOUNT_BILLING_ADDRESS_STREET)));
                accountDataModel.setBillingAddressCountry(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_ACCOUNT_BILLING_ADDRESS_COUNTRY)));
                accountDataModel.setBillingAddressCode(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_ACCOUNT_BILLING_ADDRESS_CODE)));
                accountDataModel.setBillingAddressState(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_ACCOUNT_BILLING_ADDRESS_STATE)));
                accountDataModel.setBillingAddressCity(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_ACCOUNT_BILLING_ADDRESS_CITY)));
                accountDataModel.setShippingAddressStreet(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_ACCOUNT_SHIPPING_ADDRESS_STREET)));
                accountDataModel.setShippingAddressState(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_ACCOUNT_SHIPPING_ADDRESS_STATE)));
                accountDataModel.setShippingAddressCountry(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_ACCOUNT_SHIPPING_ADDRESS_COUNTRY)));
                accountDataModel.setShippingAddressCode(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_ACCOUNT_SHIPPING_ADDRESS_CODE)));
                accountDataModel.setShippingAddressCity(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_ACCOUNT_SHIPPING_ADDRESS_CITY)));
                accountDataModel.setDescription(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_ACCOUNT_DISCRIPTION)));
                accountDataModel.setCreatedBy(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_ACCOUNT_CREATED_BY)));
                accountDataModel.setModifyBy(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_ACCOUNT_MODIFY_BY)));
                accountDataModel.setCreatedTime(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_ACCOUNT_CREATED_TIME)));
                accountDataModel.setModifyTime(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_ACCOUNT_MODIFY_TIME)));
                accountDataModelList.add(accountDataModel);
            } while (cursor.moveToNext());
        }
        return accountDataModelList;
    }


    // get account by id .......

    public AccountDataModel getAccountById(long accountId) {
        AccountDataModel accountDataModel = null;

        Log.e(TAG, "get data from account id " + accountId);

        sqLiteDatabase = dataBaseHelper.getWritableDatabase();

        String getAccountQuery = DataBaseConstant.GET_ACCOUNT_BY_ID + " " + accountId;
        Cursor cursor = sqLiteDatabase.rawQuery(getAccountQuery, null);
        if (cursor.moveToFirst()) {
            accountDataModel = new AccountDataModel();
            accountDataModel.setAccountOwner(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_ACCOUNT_OWNER)));
            accountDataModel.setRating(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_ACCOUNT_RATING)));
            accountDataModel.setAccountName(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_ACCOUNT_NAME)));
            accountDataModel.setPhone(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_ACCOUNT_PHONE)));
            accountDataModel.setFax(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_ACCOUNT_FAX)));
            accountDataModel.setAnnualRevenue(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_ACCOUNT_REVENUE)));
            accountDataModel.setEmployees(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_ACCOUNT_EMPLOYEE)));
            accountDataModel.setAccountSite(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_ACCOUNT_SITE)));
            accountDataModel.setParentAccount(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_ACCOUNT_PARENT_ACCOUNT)));
            accountDataModel.setParentAccountId(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_PARENT_ACCOUNT_ID)));
            accountDataModel.setWebSite(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_ACCOUNT_WEBSITE)));
            accountDataModel.setAccountNumber(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_ACCOUNT_ACCOUNT_NUMBER)));
            accountDataModel.setTickerSymbol(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_ACCOUNT_TICKER_SYMBOL)));
            accountDataModel.setAccountType(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_ACCOUNT_ACCOUNT_TYPE)));
            accountDataModel.setOwnerShip(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_ACCOUNT_OWNERSHIP)));
            accountDataModel.setIndustry(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_ACCOUNT_INDUSTRY)));
            accountDataModel.setSicCode(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_ACCOUNT_SIC_CODE)));
            accountDataModel.setBillingAddressStreet(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_ACCOUNT_BILLING_ADDRESS_STREET)));
            accountDataModel.setBillingAddressCountry(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_ACCOUNT_BILLING_ADDRESS_COUNTRY)));
            accountDataModel.setBillingAddressCode(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_ACCOUNT_BILLING_ADDRESS_CODE)));
            accountDataModel.setBillingAddressState(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_ACCOUNT_BILLING_ADDRESS_STATE)));
            accountDataModel.setBillingAddressCity(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_ACCOUNT_BILLING_ADDRESS_CITY)));
            accountDataModel.setShippingAddressStreet(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_ACCOUNT_SHIPPING_ADDRESS_STREET)));
            accountDataModel.setShippingAddressState(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_ACCOUNT_SHIPPING_ADDRESS_STATE)));
            accountDataModel.setShippingAddressCountry(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_ACCOUNT_SHIPPING_ADDRESS_COUNTRY)));
            accountDataModel.setShippingAddressCode(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_ACCOUNT_SHIPPING_ADDRESS_CODE)));
            accountDataModel.setShippingAddressCity(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_ACCOUNT_SHIPPING_ADDRESS_CITY)));
            accountDataModel.setDescription(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_ACCOUNT_DISCRIPTION)));
            accountDataModel.setCreatedBy(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_ACCOUNT_CREATED_BY)));
            accountDataModel.setModifyBy(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_ACCOUNT_MODIFY_BY)));
            accountDataModel.setCreatedTime(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_ACCOUNT_CREATED_TIME)));
            accountDataModel.setModifyTime(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_ACCOUNT_MODIFY_TIME)));
            accountDataModel.setSync(cursor.getInt(cursor.getColumnIndex(DataBaseConstant.COLUMN_ACCOUNT_IS_SYNC)) > 0);
        }

        return accountDataModel;
    }


    // update Account ............
    public int updateAccount(AccountDataModel accountDataModel, long accountId) {
        int result;
        openDataBase();
        ContentValues values = new ContentValues();
        values.put(DataBaseConstant.COLUMN_ACCOUNT_OWNER, accountDataModel.getAccountOwner());
        values.put(DataBaseConstant.COLUMN_ACCOUNT_RATING, accountDataModel.getRating());
        values.put(DataBaseConstant.COLUMN_ACCOUNT_NAME, accountDataModel.getAccountName());
        values.put(DataBaseConstant.COLUMN_ACCOUNT_PHONE, accountDataModel.getPhone());
        values.put(DataBaseConstant.COLUMN_ACCOUNT_SITE, accountDataModel.getAccountSite());
        values.put(DataBaseConstant.COLUMN_ACCOUNT_FAX, accountDataModel.getFax());
        values.put(DataBaseConstant.COLUMN_ACCOUNT_PARENT_ACCOUNT, accountDataModel.getParentAccount());
        values.put(DataBaseConstant.COLUMN_PARENT_ACCOUNT_ID, accountDataModel.getParentAccountId());
        values.put(DataBaseConstant.COLUMN_ACCOUNT_WEBSITE, accountDataModel.getWebSite());
        values.put(DataBaseConstant.COLUMN_ACCOUNT_ACCOUNT_NUMBER, accountDataModel.getAccountNumber());
        values.put(DataBaseConstant.COLUMN_ACCOUNT_TICKER_SYMBOL, accountDataModel.getTickerSymbol());
        values.put(DataBaseConstant.COLUMN_ACCOUNT_ACCOUNT_TYPE, accountDataModel.getAccountType());
        values.put(DataBaseConstant.COLUMN_ACCOUNT_OWNERSHIP, accountDataModel.getOwnerShip());
        values.put(DataBaseConstant.COLUMN_ACCOUNT_REVENUE, accountDataModel.getAnnualRevenue());
        values.put(DataBaseConstant.COLUMN_ACCOUNT_EMPLOYEE, accountDataModel.getEmployees());
        values.put(DataBaseConstant.COLUMN_ACCOUNT_INDUSTRY, accountDataModel.getIndustry());
        values.put(DataBaseConstant.COLUMN_ACCOUNT_SIC_CODE, accountDataModel.getSicCode());
        values.put(DataBaseConstant.COLUMN_ACCOUNT_BILLING_ADDRESS_STREET, accountDataModel.getBillingAddressStreet());
        values.put(DataBaseConstant.COLUMN_ACCOUNT_BILLING_ADDRESS_CITY, accountDataModel.getBillingAddressCity());
        values.put(DataBaseConstant.COLUMN_ACCOUNT_BILLING_ADDRESS_STATE, accountDataModel.getBillingAddressState());
        values.put(DataBaseConstant.COLUMN_ACCOUNT_BILLING_ADDRESS_CODE, accountDataModel.getBillingAddressCode());
        values.put(DataBaseConstant.COLUMN_ACCOUNT_BILLING_ADDRESS_COUNTRY, accountDataModel.getBillingAddressCountry());
        values.put(DataBaseConstant.COLUMN_ACCOUNT_SHIPPING_ADDRESS_STREET, accountDataModel.getShippingAddressStreet());
        values.put(DataBaseConstant.COLUMN_ACCOUNT_SHIPPING_ADDRESS_CITY, accountDataModel.getShippingAddressCity());
        values.put(DataBaseConstant.COLUMN_ACCOUNT_SHIPPING_ADDRESS_STATE, accountDataModel.getShippingAddressState());
        values.put(DataBaseConstant.COLUMN_ACCOUNT_SHIPPING_ADDRESS_CODE, accountDataModel.getShippingAddressCode());
        values.put(DataBaseConstant.COLUMN_ACCOUNT_SHIPPING_ADDRESS_COUNTRY, accountDataModel.getShippingAddressCountry());
        values.put(DataBaseConstant.COLUMN_ACCOUNT_DISCRIPTION, accountDataModel.getDescription());
        values.put(DataBaseConstant.COLUMN_ACCOUNT_CREATED_BY, accountDataModel.getCreatedBy());
        values.put(DataBaseConstant.COLUMN_ACCOUNT_CREATED_TIME, accountDataModel.getCreatedTime());
        values.put(DataBaseConstant.COLUMN_ACCOUNT_MODIFY_BY, accountDataModel.getModifyBy());
        values.put(DataBaseConstant.COLUMN_ACCOUNT_MODIFY_TIME, accountDataModel.getModifyTime());
        values.put(DataBaseConstant.COLUMN_ACCOUNT_IS_SYNC, accountDataModel.isSync());
        result = sqLiteDatabase.update(DataBaseConstant.TABLE_ADD_ACCOUNT, values,
                DataBaseConstant.COLUMN_ACCOUNT_ID + " =" + accountId, null);
        Log.e(TAG, " " + accountId + " " + values + " " + result);

        return result;

    }


    // delete account
    public boolean deleteAccount(long accountId) {
        sqLiteDatabase = dataBaseHelper.getWritableDatabase();
        return sqLiteDatabase.delete(DataBaseConstant.TABLE_ADD_ACCOUNT, DataBaseConstant.COLUMN_ACCOUNT_ID + " = " + accountId, null) > 0;
    }

    public boolean deleteEvent(long eventId) {

        sqLiteDatabase = dataBaseHelper.getWritableDatabase();
        return sqLiteDatabase.delete(DataBaseConstant.TABLE_ADD_EVENT, DataBaseConstant.COLUMN_EVENT_ID + " = " + eventId, null) > 0;

    }


    //add event
    public long addEvent(EventDataModel eventDataModel) {
        long rowId = 0;
        openDataBase();
        ContentValues values = new ContentValues();
        values.put(DataBaseConstant.COLUMN_EVENT_ID, eventDataModel.getEventId());
        values.put(DataBaseConstant.COLUMN_EVENT_TITLE, eventDataModel.getTitle());
        values.put(DataBaseConstant.COLUMN_EVENT_LOCATION, eventDataModel.getLocation());
        values.put(DataBaseConstant.COLUMN_EVENT_IS_ALL_DAY, eventDataModel.getAllDay());
        values.put(DataBaseConstant.COLUMN_EVENT_FROM, eventDataModel.getFromDate());
        values.put(DataBaseConstant.COLUMN_EVENT_TO, eventDataModel.getToDate());
        values.put(DataBaseConstant.COLUMN_EVENT_FROM_TIME, eventDataModel.getFromTime());
        values.put(DataBaseConstant.COLUMN_EVENT_TO_TIME, eventDataModel.getToTime());
        values.put(DataBaseConstant.COLUMN_EVENT_HOST, eventDataModel.getHost());
        values.put(DataBaseConstant.COLUMN_EVENT_PARTICIPANTS, eventDataModel.getNoOfParticipants());
        values.put(DataBaseConstant.COLUMN_EVENT_CONTACT_ID, eventDataModel.getContactId());
        values.put(DataBaseConstant.COLUMN_EVENT_ACCOUNT_ID, eventDataModel.getAccountId());
        values.put(DataBaseConstant.COLUMN_EVENT_CONTACT_NAME, eventDataModel.getContact());
        values.put(DataBaseConstant.COLUMN_EVENT_ACCOUNT_NAME, eventDataModel.getAccount());
        values.put(DataBaseConstant.COLUMN_EVENT_DESCRIPTION, eventDataModel.getDescription());
        values.put(DataBaseConstant.COLUMN_EVENT_CREATED_BY, eventDataModel.getCreatedBy());
        values.put(DataBaseConstant.COLUMN_EVENT_MODIFIED_BY, eventDataModel.getModifyBy());
        values.put(DataBaseConstant.COLUMN_EVENT_CREATED_TIME, eventDataModel.getCreatedTime());
        values.put(DataBaseConstant.COLUMN_EVENT_MODIFIED_TIME, eventDataModel.getModifyTime());
        values.put(DataBaseConstant.COLUMN_EVENT_IS_SYNC, eventDataModel.isSync());
        values.put(DataBaseConstant.COLUMN_EVENT_LEAD_NAME, eventDataModel.getLeadName());
        values.put(DataBaseConstant.COLUMN_EVENT_LEAD_ID, eventDataModel.getLeadId());
        rowId = sqLiteDatabase.insert(DataBaseConstant.TABLE_ADD_EVENT, null, values);
        Log.e(TAG, values + " " + rowId);
        return rowId;


    }

    public long addCall(CallDataModel callDataModel) {
        long rowId = 0;
        openDataBase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_CALL_ID, callDataModel.getCallId());
        values.put(DataBaseConstant.COLUMN_CALL_TITLE, callDataModel.getType());
        values.put(DataBaseConstant.COLUMN_CALL_SUBJECT, callDataModel.getSubject());
        values.put(DataBaseConstant.COLUMN_CALL_CONTACT, callDataModel.getContact());
        values.put(DataBaseConstant.COLUMN_CALL_CONTACT_ID, callDataModel.getContactId());
        values.put(DataBaseConstant.COLUMN_CALL_ACCOUNT, callDataModel.getAccount());
        values.put(DataBaseConstant.COLUMN_CALL_ACCOUNT_ID, callDataModel.getAccountId());
        values.put(DataBaseConstant.COLUMN_CALL_PURPOSE, callDataModel.getCallPurpose());
        values.put(DataBaseConstant.COLUMN_CALL_START_DATE, callDataModel.getCallStartDate());
        values.put(DataBaseConstant.COLUMN_CALL_START_TIME, callDataModel.getCallStartTime());
        values.put(DataBaseConstant.COLUMN_CALL_CREATED_BY, callDataModel.getCreatedBy());
        values.put(DataBaseConstant.COLUMN_CALL_CREATED_TIME, callDataModel.getCreatedTime());
        values.put(DataBaseConstant.COLUMN_CALL_MODIFIED_BY, callDataModel.getModifiedBy());
        values.put(DataBaseConstant.COLUMN_CALL_MODIFIED_TIME, callDataModel.getModifiedTime());
        values.put(DataBaseConstant.COLUMN_CALL_RESULT, callDataModel.getCallResult());
        values.put(DataBaseConstant.COLUMN_CALL_DESCRIPTION, callDataModel.getDescription());
        values.put(DataBaseConstant.COLUMN_CALL_DURATION, callDataModel.getCallDuration());
        values.put(DataBaseConstant.COLUMN_CALL_TYPE, callDataModel.getCallType());
        values.put(DataBaseConstant.COLUMN_CALL_LEAD_ID, callDataModel.getLeadId());
        values.put(DataBaseConstant.COLUMN_CALL_LEAD_NAME, callDataModel.getContactLeadName());
        values.put(COLUMN_CALL_IS_SYNC, callDataModel.isSync());


        rowId = sqLiteDatabase.insert(DataBaseConstant.TABLE_ADD_CALL, null, values);
        Log.e(TAG, values + " " + rowId);


        return rowId;

    }

    public int updateCall(CallDataModel callDataModel, long callId) {
        int result;
        ContentValues values = new ContentValues();
        values.put(DataBaseConstant.COLUMN_CALL_TITLE, callDataModel.getType());
        values.put(DataBaseConstant.COLUMN_CALL_SUBJECT, callDataModel.getSubject());
        values.put(DataBaseConstant.COLUMN_CALL_CONTACT, callDataModel.getContact());
        values.put(DataBaseConstant.COLUMN_CALL_CONTACT_ID, callDataModel.getContactId());
        values.put(DataBaseConstant.COLUMN_CALL_ACCOUNT, callDataModel.getAccount());
        values.put(DataBaseConstant.COLUMN_CALL_ACCOUNT_ID, callDataModel.getAccountId());
        values.put(DataBaseConstant.COLUMN_CALL_PURPOSE, callDataModel.getCallPurpose());
        values.put(DataBaseConstant.COLUMN_CALL_START_DATE, callDataModel.getCallStartDate());
        values.put(DataBaseConstant.COLUMN_CALL_START_TIME, callDataModel.getCallStartTime());
        values.put(DataBaseConstant.COLUMN_CALL_CREATED_BY, callDataModel.getCreatedBy());
        values.put(DataBaseConstant.COLUMN_CALL_CREATED_TIME, callDataModel.getCreatedTime());
        values.put(DataBaseConstant.COLUMN_CALL_MODIFIED_BY, callDataModel.getModifiedBy());
        values.put(DataBaseConstant.COLUMN_CALL_MODIFIED_TIME, callDataModel.getModifiedTime());
        values.put(DataBaseConstant.COLUMN_CALL_RESULT, callDataModel.getCallResult());
        values.put(DataBaseConstant.COLUMN_CALL_DESCRIPTION, callDataModel.getDescription());
        values.put(DataBaseConstant.COLUMN_CALL_DURATION, callDataModel.getCallDuration());
        values.put(DataBaseConstant.COLUMN_CALL_TYPE, callDataModel.getCallType());
        values.put(DataBaseConstant.COLUMN_CALL_LEAD_NAME,callDataModel.getContactLeadName());
        values.put(DataBaseConstant.COLUMN_CALL_LEAD_ID,callDataModel.getLeadId());
        values.put(COLUMN_CALL_IS_SYNC, callDataModel.isSync());

        result = sqLiteDatabase.update(DataBaseConstant.TABLE_ADD_CALL, values,
                COLUMN_CALL_ID + " =" + callId, null);
        Log.e(TAG, " " + callId + " " + values + " " + result);

        return result;


    }


    //GET ALL CALL
    public ArrayList<CallDataModel> getAllCall() {
        ArrayList<CallDataModel> callDataModelList = new ArrayList<>();
        CallDataModel callDataModel;
        sqLiteDatabase = dataBaseHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(DataBaseConstant.SELECT_TABLE_CALL, null);
        Log.v(TAG, DataBaseConstant.SELECT_TABLE_CALL);
        if (cursor.moveToFirst()) {
            do {
                callDataModel = new CallDataModel();

                callDataModel.setCallId(cursor.getLong(cursor.getColumnIndex(COLUMN_CALL_ID)));
                callDataModel.setSubject(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CALL_SUBJECT)));
                callDataModel.setContact(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CALL_CONTACT)));
                callDataModel.setCallDuration(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_CALL_DURATION)));
                callDataModel.setCallStartTime(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_CALL_START_TIME)));
                callDataModel.setCallStartDate(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_CALL_START_DATE)));
                callDataModel.setCallType(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CALL_TYPE)));
                callDataModel.setCreatedTime(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_CALL_CREATED_TIME)));
                callDataModelList.add(callDataModel);
            } while (cursor.moveToNext());
        }

        return callDataModelList;
    }

    //GET ALL UNSYNC CALL
    public ArrayList<CallDataModel> getAllUnSyncCall() {
        ArrayList<CallDataModel> callDataModelList = new ArrayList<>();
        CallDataModel callDataModel;
        sqLiteDatabase = dataBaseHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(DataBaseConstant.SELECT_UN_SYNC_CALL, null);
        Log.v(TAG, DataBaseConstant.SELECT_UN_SYNC_CALL);
        if (cursor.moveToFirst()) {
            do {
                callDataModel = new CallDataModel();

                callDataModel.setCallId(cursor.getLong(cursor.getColumnIndex(COLUMN_CALL_ID)));
                callDataModel.setSubject(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CALL_SUBJECT)));
                callDataModel.setContact(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CALL_CONTACT)));
                callDataModel.setCallDuration(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_CALL_DURATION)));
                callDataModel.setCallStartTime(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_CALL_START_TIME)));
                callDataModel.setCallStartDate(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_CALL_START_DATE)));
                callDataModel.setCallType(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CALL_TYPE)));
                callDataModel.setCallPurpose(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CALL_PURPOSE)));
                callDataModel.setDescription(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CALL_DESCRIPTION)));
                callDataModel.setType(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CALL_TITLE)));
                callDataModel.setCallResult(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CALL_RESULT)));
                callDataModel.setAccount(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CALL_ACCOUNT)));
                callDataModel.setContactId(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_CALL_CONTACT_ID)));
                callDataModel.setAccountId(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_CALL_ACCOUNT_ID)));
                callDataModel.setCreatedBy(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CALL_CREATED_BY)));
                callDataModel.setModifiedBy(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CALL_MODIFIED_BY)));
                callDataModel.setCreatedTime(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_CALL_CREATED_TIME)));
                callDataModel.setModifiedTime(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_CALL_MODIFIED_BY)));
                callDataModel.setLeadId(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_CALL_LEAD_ID)));
                callDataModel.setContactLeadName(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CALL_LEAD_NAME)));
                callDataModelList.add(callDataModel);
            } while (cursor.moveToNext());
        }

        return callDataModelList;
    }


    public CallDataModel getCallByID(long callId) {
        CallDataModel callDataModel = new CallDataModel();
        EventDataModel eventDataModel = null;
        sqLiteDatabase = dataBaseHelper.getWritableDatabase();
        String getCallQuery = DataBaseConstant.SELECT_CALL_BY_ID + " " + callId;
        Cursor cursor = sqLiteDatabase.rawQuery(getCallQuery, null);
        Log.v(TAG, DataBaseConstant.SELECT_CALL_BY_ID);
        if (cursor.moveToFirst()) {
            callDataModel.setSync(cursor.getInt(cursor.getColumnIndex(COLUMN_CALL_IS_SYNC)) > 0);
            callDataModel.setCallId(cursor.getLong(cursor.getColumnIndex(COLUMN_CALL_ID)));
            callDataModel.setSubject(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CALL_SUBJECT)));
            callDataModel.setContact(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CALL_CONTACT)));
            callDataModel.setCallDuration(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_CALL_DURATION)));
            callDataModel.setCallStartTime(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_CALL_START_TIME)));
            callDataModel.setCallStartDate(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_CALL_START_DATE)));
            callDataModel.setCallType(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CALL_TYPE)));
            callDataModel.setCallPurpose(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CALL_PURPOSE)));
            callDataModel.setDescription(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CALL_DESCRIPTION)));
            callDataModel.setType(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CALL_TITLE)));
            callDataModel.setCallResult(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CALL_RESULT)));
            callDataModel.setAccount(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CALL_ACCOUNT)));
            callDataModel.setContactId(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_CALL_CONTACT_ID)));
            callDataModel.setAccountId(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_CALL_ACCOUNT_ID)));
            callDataModel.setCreatedBy(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CALL_CREATED_BY)));
            callDataModel.setModifiedBy(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CALL_MODIFIED_BY)));
            callDataModel.setCreatedTime(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_CALL_CREATED_TIME)));
            callDataModel.setModifiedTime(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_CALL_MODIFIED_BY)));
            callDataModel.setLeadId(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_CALL_LEAD_ID)));
            callDataModel.setContactLeadName(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CALL_LEAD_NAME)));

        }


        return callDataModel;

    }

    // get all events
    public ArrayList<EventDataModel> getAllEvents() {
        ArrayList<EventDataModel> eventDataList = new ArrayList<>();
        EventDataModel eventDataModel;
        sqLiteDatabase = dataBaseHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(DataBaseConstant.SELECT_TABLE_EVENT, null);
        Log.v(TAG, DataBaseConstant.SELECT_TABLE_EVENT);
        if (cursor.moveToFirst()) {
            do {
                eventDataModel = new EventDataModel();
                eventDataModel.setEventId(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_EVENT_ID)));
                eventDataModel.setFromTime(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_EVENT_FROM_TIME)));
                eventDataModel.setToTime(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_EVENT_TO)));
                eventDataModel.setFromDate(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_EVENT_FROM)));
                eventDataModel.setToDate(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_EVENT_TO)));
                eventDataModel.setAllDay(cursor.getInt(cursor.getColumnIndex(DataBaseConstant.COLUMN_EVENT_IS_ALL_DAY)) > 0);
                eventDataModel.setTitle(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_EVENT_TITLE)));
                eventDataModel.setCreatedTime(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_EVENT_CREATED_TIME)));
                eventDataList.add(eventDataModel);
            } while (cursor.moveToNext());
        }


        return eventDataList;

    }


    // get all Unsync events
    public ArrayList<EventDataModel> getAllUnSyncEvents() {
        ArrayList<EventDataModel> eventDataList = new ArrayList<>();
        EventDataModel eventDataModel;
        sqLiteDatabase = dataBaseHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(DataBaseConstant.SELECT_UN_SYNC_EVENT, null);
        Log.v(TAG, DataBaseConstant.SELECT_UN_SYNC_EVENT);
        if (cursor.moveToFirst()) {
            do {
                eventDataModel = new EventDataModel();
                eventDataModel.setEventId(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_EVENT_ID)));
                eventDataModel.setFromTime(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_EVENT_FROM_TIME)));
                eventDataModel.setToTime(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_EVENT_TO)));
                eventDataModel.setFromDate(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_EVENT_FROM)));
                eventDataModel.setToDate(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_EVENT_TO)));
                eventDataModel.setAllDay(cursor.getInt(cursor.getColumnIndex(DataBaseConstant.COLUMN_EVENT_IS_ALL_DAY)) > 0);
                eventDataModel.setTitle(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_EVENT_TITLE)));
                eventDataModel.setLocation(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_EVENT_LOCATION)));
                eventDataModel.setCreatedBy(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_EVENT_CREATED_BY)));
                eventDataModel.setModifyBy(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_EVENT_MODIFIED_BY)));
                eventDataModel.setCreatedTime(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_EVENT_CREATED_TIME)));
                eventDataModel.setModifyTime(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_EVENT_MODIFIED_TIME)));
                eventDataModel.setContact(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_EVENT_CONTACT_NAME)));
                eventDataModel.setContactId(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_EVENT_CONTACT_ID)));
                eventDataModel.setDescription(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_EVENT_DESCRIPTION)));
                eventDataModel.setHost(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_EVENT_HOST)));
                eventDataModel.setNoOfParticipants(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_EVENT_PARTICIPANTS)));
                eventDataModel.setParticipants(getAllParticipants(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_EVENT_ID))));


                eventDataList.add(eventDataModel);
            } while (cursor.moveToNext());
        }


        return eventDataList;

    }


    // get Participants
    public ArrayList<Participant> getAllParticipants(long eventID) {
        ArrayList<Participant> participantList = new ArrayList<>();
        Participant participant;
        sqLiteDatabase = dataBaseHelper.getWritableDatabase();
        String query = "SELECT * FROM " + DataBaseConstant.TABLE_PARTICIPANTS + " WHERE " + DataBaseConstant.COLUMN_PARTICIPANTS_EVENT_ID + " =  " + eventID + " ORDER BY " + DataBaseConstant.COLUMN_PARTICIPANTS_NAME + " ASC ";
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        Log.v(TAG, DataBaseConstant.SELECT_PARTICIPANTS);
        if (cursor.moveToFirst()) {
            do {
                participant = new Participant();
                participant.setEventId(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_PARTICIPANTS_EVENT_ID)));
                participant.setParticipant(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_PARTICIPANTS_ID)));
                participant.setConstant(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_PARTICIPANTS_CONSTANT)));
                participant.setParticipantName(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_PARTICIPANTS_NAME)));
                participant.setParticipantEmail(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_PARTICIPANTS_EMAIL)));
                participantList.add(participant);
            } while (cursor.moveToNext());
        }

        return participantList;
    }

    public ArrayList<Participant> getAllParticipants(long eventID, String constant) {
        ArrayList<Participant> participantList = new ArrayList<>();
        Participant participant;
        sqLiteDatabase = dataBaseHelper.getWritableDatabase();
        String query = "SELECT * FROM " + DataBaseConstant.TABLE_PARTICIPANTS + "WHERE " + DataBaseConstant.COLUMN_PARTICIPANTS_EVENT_ID + " =  " + eventID + " AND " + DataBaseConstant.COLUMN_PARTICIPANTS_CONSTANT + " = " + constant + " ORDER BY " + DataBaseConstant.COLUMN_PARTICIPANTS_NAME + " ASC ";
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        Log.v(TAG, query);
        if (cursor.moveToFirst()) {
            do {
                participant = new Participant();
                participant.setEventId(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_PARTICIPANTS_EVENT_ID)));
                participant.setParticipant(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_PARTICIPANTS_ID)));
                participant.setConstant(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_PARTICIPANTS_CONSTANT)));
                participant.setParticipantName(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_PARTICIPANTS_NAME)));
                participant.setParticipantEmail(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_PARTICIPANTS_EMAIL)));
                participantList.add(participant);
            } while (cursor.moveToNext());
        }

        return participantList;
    }


    // get Event by ID
    public EventDataModel getEventByID(long eventID) {
        EventDataModel eventDataModel = null;
        sqLiteDatabase = dataBaseHelper.getWritableDatabase();
        String getEventQuery = DataBaseConstant.SELECT_EVENT_BY_ID + " " + eventID;
        Cursor cursor = sqLiteDatabase.rawQuery(getEventQuery, null);
        Log.v(TAG, DataBaseConstant.SELECT_TABLE_EVENT);
        if (cursor.moveToFirst()) {

            eventDataModel = new EventDataModel();
            eventDataModel.setSync(cursor.getInt(cursor.getColumnIndex(DataBaseConstant.COLUMN_EVENT_IS_SYNC)) > 0);
            eventDataModel.setFromTime(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_EVENT_FROM_TIME)));
            eventDataModel.setToTime(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_EVENT_TO)));
            eventDataModel.setFromDate(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_EVENT_FROM)));
            eventDataModel.setToDate(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_EVENT_TO)));
            eventDataModel.setAllDay(cursor.getInt(cursor.getColumnIndex(DataBaseConstant.COLUMN_EVENT_IS_ALL_DAY)) > 0);
            eventDataModel.setTitle(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_EVENT_TITLE)));
            eventDataModel.setLocation(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_EVENT_LOCATION)));
            eventDataModel.setCreatedBy(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_EVENT_CREATED_BY)));
            eventDataModel.setModifyBy(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_EVENT_MODIFIED_BY)));
            eventDataModel.setCreatedTime(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_EVENT_CREATED_TIME)));
            eventDataModel.setModifyTime(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_EVENT_MODIFIED_TIME)));
            eventDataModel.setContact(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_EVENT_CONTACT_NAME)));
            eventDataModel.setContactId(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_EVENT_CONTACT_ID)));
            eventDataModel.setDescription(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_EVENT_DESCRIPTION)));
            eventDataModel.setHost(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_EVENT_HOST)));
            eventDataModel.setNoOfParticipants(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_EVENT_PARTICIPANTS)));
            eventDataModel.setAccountId(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_EVENT_ACCOUNT_ID)));
            eventDataModel.setAccount(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_EVENT_ACCOUNT_NAME)));
            eventDataModel.setLeadId(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_EVENT_LEAD_ID)));
            eventDataModel.setLeadName(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_EVENT_LEAD_NAME)));

        }


        return eventDataModel;


    }


    public List<EventDataModel> getEventByContactId(long contactId) {
        List<EventDataModel> eventDataModelList = new ArrayList<>();
        EventDataModel eventDataModel = null;

        sqLiteDatabase = dataBaseHelper.getWritableDatabase();
        String getEventQuery = DataBaseConstant.SELECT_EVENT_BY_CONTACT_ID + " " + contactId;
        Cursor cursor = sqLiteDatabase.rawQuery(getEventQuery, null);
        Log.v(TAG, DataBaseConstant.SELECT_EVENT_BY_CONTACT_ID);
        if (cursor.moveToFirst()) {
            do {
                eventDataModel = new EventDataModel();
                eventDataModel.setFromTime(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_EVENT_FROM_TIME)));
                eventDataModel.setToTime(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_EVENT_TO)));
                eventDataModel.setFromDate(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_EVENT_FROM)));
                eventDataModel.setToDate(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_EVENT_TO)));
                eventDataModel.setAllDay(cursor.getInt(cursor.getColumnIndex(DataBaseConstant.COLUMN_EVENT_IS_ALL_DAY)) > 0);
                eventDataModel.setTitle(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_EVENT_TITLE)));
                eventDataModelList.add(eventDataModel);

            } while (cursor.moveToNext());
        }


        return eventDataModelList;


    }


    public long addParticipants(ArrayList<Participant> participantList) {
        long rowID = 0;
        openDataBase();
        ContentValues values;
        for (Participant participant : participantList) {
            values = new ContentValues();
            values.put(DataBaseConstant.COLUMN_PARTICIPANTS_EVENT_ID, participant.getEventId());
            values.put(DataBaseConstant.COLUMN_PARTICIPANTS_ID, participant.getParticipant());
            values.put(DataBaseConstant.COLUMN_PARTICIPANTS_CONSTANT, participant.getConstant());
            values.put(DataBaseConstant.COLUMN_PARTICIPANTS_NAME, participant.getParticipantName());
            values.put(DataBaseConstant.COLUMN_PARTICIPANTS_EMAIL, participant.getParticipantEmail());
            rowID = sqLiteDatabase.insert(DataBaseConstant.TABLE_PARTICIPANTS, null, values);
            Log.e(TAG, values + " " + rowID);

        }
        return rowID;
    }

    //get Participant By EventID

    public List<Participant> getParticipantByEventID(long eventID) {
        List<Participant> participantList = new ArrayList<>();
        Participant participant = null;
        sqLiteDatabase = dataBaseHelper.getWritableDatabase();
        String getParticipantQuery = DataBaseConstant.SELECT_PARTICIPANTS + " " + eventID;
        Cursor cursor = sqLiteDatabase.rawQuery(getParticipantQuery, null);
        Log.v(TAG, DataBaseConstant.SELECT_PARTICIPANTS);
        if (cursor.moveToFirst()) {
            do {
                participant = new Participant();
                participant.setEventId(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_PARTICIPANTS_EVENT_ID)));
                participant.setConstant(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_PARTICIPANTS_CONSTANT)));
                participant.setParticipantEmail(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_PARTICIPANTS_EMAIL)));
                participant.setParticipantName(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_PARTICIPANTS_NAME)));
                participant.setParticipant(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_EVENT_PARTICIPANTS)));
            } while (cursor.moveToNext());

        }

        return participantList;
    }


    public long updateParticipants(ArrayList<Participant> participantList, long eventId) {

        long rowID = 0;
        sqLiteDatabase = dataBaseHelper.getWritableDatabase();
        sqLiteDatabase.delete(DataBaseConstant.TABLE_PARTICIPANTS, DataBaseConstant.COLUMN_PARTICIPANTS_EVENT_ID + " = " + eventId, null);
        ContentValues values;
        for (Participant participant : participantList) {
            values = new ContentValues();
            values.put(DataBaseConstant.COLUMN_PARTICIPANTS_EVENT_ID, participant.getEventId());
            values.put(DataBaseConstant.COLUMN_PARTICIPANTS_EVENT_ID, participant.getEventId());
            values.put(DataBaseConstant.COLUMN_PARTICIPANTS_ID, participant.getParticipant());
            values.put(DataBaseConstant.COLUMN_PARTICIPANTS_CONSTANT, participant.getConstant());
            values.put(DataBaseConstant.COLUMN_PARTICIPANTS_NAME, participant.getParticipantName());
            values.put(DataBaseConstant.COLUMN_PARTICIPANTS_EMAIL, participant.getParticipantEmail());
            rowID = sqLiteDatabase.insert(DataBaseConstant.TABLE_PARTICIPANTS, null, values);
            Log.e(TAG, values + " " + rowID);

        }
        return rowID;

    }


    // add Contact
    public long addContact(AddContactData addContactData) {
        long rowID = 0;
        openDataBase();
        ContentValues values = new ContentValues();
        values.put(DataBaseConstant.COLUMN_CONTACT_ID, addContactData.getContactId());
        values.put(DataBaseConstant.COLUMN_CONTACT_LEAD_SOURCE, addContactData.getLeadSource());
        values.put(DataBaseConstant.COLUMN_CONTACT_OWNER, addContactData.getContactOwner());
        values.put(DataBaseConstant.COLUMN_CONTACT_FIRST_NAME, addContactData.getFirstName());
        values.put(DataBaseConstant.COLUMN_CONTACT_LAST_NAME, addContactData.getLastName());
        values.put(DataBaseConstant.COLUMN_CONTACT_ACCOUNT_NAME, addContactData.getAccountName());
        values.put(DataBaseConstant.COLUMN_CONTACT_ACCOUNT_ID, addContactData.getAccountId());
        values.put(DataBaseConstant.COLUMN_CONTACT_EMAIL, addContactData.getEmail());
        values.put(DataBaseConstant.COLUMN_CONTACT_TITLE, addContactData.getTitle());
        values.put(DataBaseConstant.COLUMN_CONTACT_DEPARTMENT, addContactData.getDepartment());
        values.put(DataBaseConstant.COLUMN_CONTACT_PHONE, addContactData.getPhone());
        values.put(DataBaseConstant.COLUMN_CONTACT_HOME_PHONE, addContactData.getHomePhone());
        values.put(DataBaseConstant.COLUMN_CONTACT_OTHER_PHONE, addContactData.getOtherPhone());
        values.put(DataBaseConstant.COLUMN_CONTACT_FAX, addContactData.getFax());
        values.put(DataBaseConstant.COLUMN_CONTACT_MOBILE, addContactData.getMobile());
        values.put(DataBaseConstant.COLUMN_CONTACT_DOB, addContactData.getDateOfBirth());
        values.put(DataBaseConstant.COLUMN_CONTACT_ASSISTANT, addContactData.getAssistant());
        values.put(DataBaseConstant.COLUMN_CONTACT_ASST_PHONE, addContactData.getAsstPhone());
        values.put(DataBaseConstant.COLUMN_CONTACT_REPORT_TO, addContactData.getReportsTo());
        values.put(DataBaseConstant.COLUMN_CONTACT_EMAIL_OPT, addContactData.getEmailOptOut());
        values.put(DataBaseConstant.COLUMN_CONTACT_SKYPE_ID, addContactData.getSkypeId());
        values.put(DataBaseConstant.COLUMN_CONTACT_SOLUTION, addContactData.getSalutation());
        values.put(DataBaseConstant.COLUMN_CONTACT_SECONDARY_MAIL, addContactData.getSecondaryEmail());
        values.put(DataBaseConstant.COLUMN_CONTACT_TWITTER, addContactData.getTwitter());
        values.put(DataBaseConstant.COLUMN_CONTACT_DESCRIPTION, addContactData.getDescription());
        values.put(DataBaseConstant.COLUMN_CONTACT_MAILING_STREET, addContactData.getMailingAddressStreet());
        values.put(DataBaseConstant.COLUMN_CONTACT_MAILING_STATE, addContactData.getMailingAddressState());
        values.put(DataBaseConstant.COLUMN_CONTACT_MAILING_CODE, addContactData.getMailingAddressZip());
        values.put(DataBaseConstant.COLUMN_CONTACT_MAILING_CITY, addContactData.getMailingAddressCity());
        values.put(DataBaseConstant.COLUMN_CONTACT_MAILING_COUNTRY, addContactData.getMailingAddressCountry());
        values.put(DataBaseConstant.COLUMN_CONTACT_OTHER_STREET, addContactData.getOtherAddressStreet());
        values.put(DataBaseConstant.COLUMN_CONTACT_OTHER_STATE, addContactData.getOtherAddressState());
        values.put(DataBaseConstant.COLUMN_CONTACT_OTHER_CODE, addContactData.getOtherAddressZip());
        values.put(DataBaseConstant.COLUMN_CONTACT_OTHER_CITY, addContactData.getOtherAddressCity());
        values.put(DataBaseConstant.COLUMN_CONTACT_OTHER_COUNTRY, addContactData.getOtherAddressCountry());
        values.put(DataBaseConstant.COLUMN_CONTACT_CREATED_BY, addContactData.getCreatedBy());
        values.put(DataBaseConstant.COLUMN_CONTACT_CREATED_TIME, addContactData.getCreatedDate());
        values.put(DataBaseConstant.COLUMN_CONTACT_MODIFIED_BY, addContactData.getModifyBy());
        values.put(DataBaseConstant.COLUMN_CONTACT_MODIFIED_TIME, addContactData.getModifyDate());
        values.put(DataBaseConstant.COLUMN_CONTACT_IS_SYNC, addContactData.isSync());
        rowID = sqLiteDatabase.insert(DataBaseConstant.TABLE_ADD_CONTACT, null, values);
        Log.e(TAG, values + " " + rowID);
        return rowID;
    }


    // GET ALL CONTACT
    public List<AddContactData> getAllContact() {
        List<AddContactData> addContactDataList = new ArrayList<>();

        sqLiteDatabase = dataBaseHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(DataBaseConstant.SELECT_TABLE_CONTACT, null);
        Log.e(TAG, DataBaseConstant.SELECT_TABLE_CONTACT);

        if (cursor.moveToFirst()) {
            do {
                AddContactData addContactData = new AddContactData();
                addContactData.setLastName(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CONTACT_LAST_NAME)));
                addContactData.setContactId(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_CONTACT_ID)));
                addContactData.setFirstName(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CONTACT_FIRST_NAME)));
                addContactData.setContactOwner(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CONTACT_OWNER)));
                addContactData.setCreatedDate(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_CONTACT_CREATED_TIME)));
                addContactData.setAccountName(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CONTACT_ACCOUNT_NAME)));
                addContactDataList.add(addContactData);
            } while (cursor.moveToNext());
        }
        return addContactDataList;
    }

    // GET ALL Unsync CONTACT
    public List<AddContactData> getAllUnSyncContact() {
        List<AddContactData> addContactDataList = new ArrayList<>();

        sqLiteDatabase = dataBaseHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(DataBaseConstant.SELECT_UN_SYNC_CONTACT, null);
        Log.e(TAG, DataBaseConstant.SELECT_UN_SYNC_CONTACT);

        if (cursor.moveToFirst()) {
            do {
                AddContactData addContactData = new AddContactData();
                addContactData.setContactId(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_CONTACT_ID)));
                addContactData.setContactOwner(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CONTACT_OWNER)));
                addContactData.setLeadSource(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CONTACT_LEAD_SOURCE)));
                addContactData.setFirstName(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CONTACT_FIRST_NAME)));
                addContactData.setLastName(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CONTACT_LAST_NAME)));
                addContactData.setEmail(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CONTACT_EMAIL)));
                addContactData.setTitle(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CONTACT_TITLE)));
                addContactData.setAccountId(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_CONTACT_ACCOUNT_ID)));
                addContactData.setDepartment(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CONTACT_DEPARTMENT)));
                addContactData.setPhone(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CONTACT_PHONE)));
                addContactData.setHomePhone(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CONTACT_HOME_PHONE)));
                addContactData.setOtherPhone(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CONTACT_OTHER_PHONE)));
                addContactData.setFax(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CONTACT_FAX)));
                addContactData.setMobile(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CONTACT_MOBILE)));
                addContactData.setDateOfBirth(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CONTACT_DOB)));
                addContactData.setEmailOptOut(cursor.getInt(cursor.getColumnIndex(DataBaseConstant.COLUMN_CONTACT_EMAIL_OPT)) > 0);
                addContactData.setAssistant(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CONTACT_ASSISTANT)));
                addContactData.setAsstPhone(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CONTACT_ASST_PHONE)));
                addContactData.setReportsTo(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CONTACT_REPORT_TO)));
                addContactData.setSkypeId(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CONTACT_SKYPE_ID)));
                addContactData.setCreatedDate(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_CONTACT_CREATED_TIME)));
                addContactData.setAccountName(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CONTACT_ACCOUNT_NAME)));
                addContactData.setDescription(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CONTACT_DESCRIPTION)));
                addContactData.setSecondaryEmail(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CONTACT_SECONDARY_MAIL)));
                addContactData.setSalutation(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CONTACT_SOLUTION)));
                addContactData.setTwitter(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CONTACT_TWITTER)));
                addContactData.setMailingAddressStreet(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CONTACT_MAILING_STREET)));
                addContactData.setMailingAddressState(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CONTACT_MAILING_STATE)));
                addContactData.setMailingAddressZip(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CONTACT_MAILING_CODE)));
                addContactData.setMailingAddressCountry(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CONTACT_MAILING_COUNTRY)));
                addContactData.setMailingAddressCity(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CONTACT_MAILING_CITY)));
                addContactData.setOtherAddressStreet(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CONTACT_OTHER_STREET)));
                addContactData.setOtherAddressState(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CONTACT_OTHER_STATE)));
                addContactData.setOtherAddressZip(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CONTACT_OTHER_CODE)));
                addContactData.setOtherAddressCountry(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CONTACT_OTHER_COUNTRY)));
                addContactData.setOtherAddressCity(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CONTACT_OTHER_CITY)));
                addContactData.setCreatedBy(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CONTACT_CREATED_BY)));
                addContactData.setModifyBy(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CONTACT_MODIFIED_BY)));
                addContactData.setCreatedDate(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_CONTACT_CREATED_TIME)));
                addContactData.setModifyDate(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_CONTACT_MODIFIED_TIME)));
                addContactDataList.add(addContactData);
            } while (cursor.moveToNext());
        }
        return addContactDataList;
    }


    public List<AddContactData> getAllLeads() {
        List<AddContactData> addContactDataList = new ArrayList<>();
        sqLiteDatabase = dataBaseHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(DataBaseConstant.SELECT_TABLE_LEAD, null);
        Log.e(TAG, DataBaseConstant.SELECT_TABLE_CONTACT);

        if (cursor.moveToFirst()) {
            do {
                AddContactData addContactData = new AddContactData();
                addContactData.setLastName(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_LEAD_LAST_NAME)));
                addContactData.setContactId(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_LEAD_ID)));
                addContactData.setFirstName(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_LEAD_FIRST_NAME)));
                addContactDataList.add(addContactData);
            } while (cursor.moveToNext());
        }
        return addContactDataList;

    }


    public ArrayList<ParticipantsData> getContacts() {
        ArrayList<ParticipantsData> participantsDataList = new ArrayList<>();
        ParticipantsData participantsData;
        sqLiteDatabase = dataBaseHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(DataBaseConstant.SELECT_TABLE_CONTACT, null);
        Log.e(TAG, DataBaseConstant.SELECT_TABLE_CONTACT);

        if (cursor.moveToFirst()) {
            do {
                participantsData = new ParticipantsData();
                participantsData.setParticipantsName((cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CONTACT_FIRST_NAME))) + " " + (cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CONTACT_LAST_NAME))));
                participantsData.setId(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_CONTACT_ID)));
                participantsData.setParticipantsEmail(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CONTACT_EMAIL)));

                participantsDataList.add(participantsData);
            } while (cursor.moveToNext());
        }


        return participantsDataList;
    }


    // get Contact by ID
    public AddContactData getContactById(long contactId) {
        AddContactData addContactData = null;
        Log.v(TAG, "get data from contact id " + contactId);

        sqLiteDatabase = dataBaseHelper.getWritableDatabase();

        String getContactQuery = DataBaseConstant.GET_CONTACT_BY_ID + " " + contactId;
        Cursor cursor = sqLiteDatabase.rawQuery(getContactQuery, null);
        if (cursor.moveToFirst()) {
            addContactData = new AddContactData();
            addContactData.setContactOwner(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CONTACT_OWNER)));
            addContactData.setLeadSource(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CONTACT_LEAD_SOURCE)));
            addContactData.setFirstName(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CONTACT_FIRST_NAME)));
            addContactData.setLastName(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CONTACT_LAST_NAME)));
            addContactData.setEmail(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CONTACT_EMAIL)));
            addContactData.setTitle(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CONTACT_TITLE)));
            addContactData.setAccountId(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_CONTACT_ACCOUNT_ID)));
            addContactData.setDepartment(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CONTACT_DEPARTMENT)));
            addContactData.setPhone(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CONTACT_PHONE)));
            addContactData.setHomePhone(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CONTACT_HOME_PHONE)));
            addContactData.setOtherPhone(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CONTACT_OTHER_PHONE)));
            addContactData.setFax(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CONTACT_FAX)));
            addContactData.setMobile(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CONTACT_MOBILE)));
            addContactData.setDateOfBirth(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CONTACT_DOB)));
            addContactData.setEmailOptOut(cursor.getInt(cursor.getColumnIndex(DataBaseConstant.COLUMN_CONTACT_EMAIL_OPT)) > 0);
            addContactData.setAssistant(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CONTACT_ASSISTANT)));
            addContactData.setAsstPhone(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CONTACT_ASST_PHONE)));
            addContactData.setReportsTo(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CONTACT_REPORT_TO)));
            addContactData.setSkypeId(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CONTACT_SKYPE_ID)));
            addContactData.setCreatedDate(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_CONTACT_CREATED_TIME)));
            addContactData.setAccountName(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CONTACT_ACCOUNT_NAME)));
            addContactData.setDescription(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CONTACT_DESCRIPTION)));
            addContactData.setSecondaryEmail(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CONTACT_SECONDARY_MAIL)));
            addContactData.setSalutation(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CONTACT_SOLUTION)));
            addContactData.setTwitter(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CONTACT_TWITTER)));
            addContactData.setMailingAddressStreet(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CONTACT_MAILING_STREET)));
            addContactData.setMailingAddressState(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CONTACT_MAILING_STATE)));
            addContactData.setMailingAddressZip(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CONTACT_MAILING_CODE)));
            addContactData.setMailingAddressCountry(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CONTACT_MAILING_COUNTRY)));
            addContactData.setMailingAddressCity(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CONTACT_MAILING_CITY)));
            addContactData.setOtherAddressStreet(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CONTACT_OTHER_STREET)));
            addContactData.setOtherAddressState(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CONTACT_OTHER_STATE)));
            addContactData.setOtherAddressZip(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CONTACT_OTHER_CODE)));
            addContactData.setOtherAddressCountry(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CONTACT_OTHER_COUNTRY)));
            addContactData.setOtherAddressCity(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CONTACT_OTHER_CITY)));
            addContactData.setCreatedBy(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CONTACT_CREATED_BY)));
            addContactData.setModifyBy(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CONTACT_MODIFIED_BY)));
            addContactData.setCreatedDate(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_CONTACT_CREATED_TIME)));
            addContactData.setModifyDate(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_CONTACT_MODIFIED_TIME)));
            addContactData.setSync(cursor.getInt(cursor.getColumnIndex(DataBaseConstant.COLUMN_CONTACT_IS_SYNC)) > 0);

        }
        return addContactData;
    }


    //update event
    public int updateEvent(EventDataModel eventDataModel, long eventID) {

        int result;
        openDataBase();
        ContentValues values = new ContentValues();
        values.put(DataBaseConstant.COLUMN_EVENT_TITLE, eventDataModel.getTitle());
        values.put(DataBaseConstant.COLUMN_EVENT_LOCATION, eventDataModel.getLocation());
        values.put(DataBaseConstant.COLUMN_EVENT_IS_ALL_DAY, eventDataModel.getAllDay());
        values.put(DataBaseConstant.COLUMN_EVENT_FROM, eventDataModel.getFromDate());
        values.put(DataBaseConstant.COLUMN_EVENT_TO, eventDataModel.getToDate());
        values.put(DataBaseConstant.COLUMN_EVENT_FROM_TIME, eventDataModel.getFromTime());
        values.put(DataBaseConstant.COLUMN_EVENT_TO_TIME, eventDataModel.getToTime());
        values.put(DataBaseConstant.COLUMN_EVENT_HOST, eventDataModel.getHost());
        values.put(DataBaseConstant.COLUMN_EVENT_PARTICIPANTS, eventDataModel.getNoOfParticipants());
        values.put(DataBaseConstant.COLUMN_EVENT_CONTACT_ID, eventDataModel.getContactId());
        values.put(DataBaseConstant.COLUMN_EVENT_ACCOUNT_ID, eventDataModel.getAccountId());
        values.put(DataBaseConstant.COLUMN_EVENT_CONTACT_NAME, eventDataModel.getContact());
        values.put(DataBaseConstant.COLUMN_EVENT_ACCOUNT_NAME, eventDataModel.getAccount());
        values.put(DataBaseConstant.COLUMN_EVENT_DESCRIPTION, eventDataModel.getDescription());
        values.put(DataBaseConstant.COLUMN_EVENT_CREATED_BY, eventDataModel.getCreatedBy());
        values.put(DataBaseConstant.COLUMN_EVENT_MODIFIED_BY, eventDataModel.getModifyBy());
        values.put(DataBaseConstant.COLUMN_EVENT_CREATED_TIME, eventDataModel.getCreatedTime());
        values.put(DataBaseConstant.COLUMN_EVENT_MODIFIED_TIME, eventDataModel.getModifyTime());
        values.put(DataBaseConstant.COLUMN_EVENT_IS_SYNC, eventDataModel.isSync());
        values.put(DataBaseConstant.COLUMN_EVENT_LEAD_ID,eventDataModel.getLeadId());
        values.put(DataBaseConstant.COLUMN_EVENT_LEAD_NAME,eventDataModel.getLeadName());
        result = sqLiteDatabase.update(DataBaseConstant.TABLE_ADD_EVENT, values,
                DataBaseConstant.COLUMN_EVENT_ID + " = " + eventID, null);
        Log.e(TAG, " " + eventID + " " + values + " " + result);
        return result;

    }

    //update contact
    public int updateContact(AddContactData addContactData, long contactId) {
        int result;
        openDataBase();
        ContentValues values = new ContentValues();
        values.put(DataBaseConstant.COLUMN_CONTACT_LEAD_SOURCE, addContactData.getLeadSource());
        values.put(DataBaseConstant.COLUMN_CONTACT_OWNER, addContactData.getContactOwner());
        values.put(DataBaseConstant.COLUMN_CONTACT_FIRST_NAME, addContactData.getFirstName());
        values.put(DataBaseConstant.COLUMN_CONTACT_LAST_NAME, addContactData.getLastName());
        values.put(DataBaseConstant.COLUMN_CONTACT_ACCOUNT_NAME, addContactData.getAccountName());
        values.put(DataBaseConstant.COLUMN_CONTACT_ACCOUNT_ID, addContactData.getAccountId());
        values.put(DataBaseConstant.COLUMN_CONTACT_EMAIL, addContactData.getEmail());
        values.put(DataBaseConstant.COLUMN_CONTACT_TITLE, addContactData.getTitle());
        values.put(DataBaseConstant.COLUMN_CONTACT_DEPARTMENT, addContactData.getDepartment());
        values.put(DataBaseConstant.COLUMN_CONTACT_PHONE, addContactData.getPhone());
        values.put(DataBaseConstant.COLUMN_CONTACT_HOME_PHONE, addContactData.getHomePhone());
        values.put(DataBaseConstant.COLUMN_CONTACT_OTHER_PHONE, addContactData.getOtherPhone());
        values.put(DataBaseConstant.COLUMN_CONTACT_FAX, addContactData.getFax());
        values.put(DataBaseConstant.COLUMN_CONTACT_MOBILE, addContactData.getMobile());
        values.put(DataBaseConstant.COLUMN_CONTACT_DOB, addContactData.getDateOfBirth());
        values.put(DataBaseConstant.COLUMN_CONTACT_ASSISTANT, addContactData.getAssistant());
        values.put(DataBaseConstant.COLUMN_CONTACT_ASST_PHONE, addContactData.getAsstPhone());
        values.put(DataBaseConstant.COLUMN_CONTACT_REPORT_TO, addContactData.getReportsTo());
        values.put(DataBaseConstant.COLUMN_CONTACT_EMAIL_OPT, addContactData.getEmailOptOut());
        values.put(DataBaseConstant.COLUMN_CONTACT_SKYPE_ID, addContactData.getSkypeId());
        values.put(DataBaseConstant.COLUMN_CONTACT_SOLUTION, addContactData.getSalutation());
        values.put(DataBaseConstant.COLUMN_CONTACT_SECONDARY_MAIL, addContactData.getSecondaryEmail());
        values.put(DataBaseConstant.COLUMN_CONTACT_TWITTER, addContactData.getTwitter());
        values.put(DataBaseConstant.COLUMN_CONTACT_DESCRIPTION, addContactData.getDescription());
        values.put(DataBaseConstant.COLUMN_CONTACT_MAILING_STREET, addContactData.getMailingAddressStreet());
        values.put(DataBaseConstant.COLUMN_CONTACT_MAILING_STATE, addContactData.getMailingAddressState());
        values.put(DataBaseConstant.COLUMN_CONTACT_MAILING_CODE, addContactData.getMailingAddressZip());
        values.put(DataBaseConstant.COLUMN_CONTACT_MAILING_CITY, addContactData.getMailingAddressCity());
        values.put(DataBaseConstant.COLUMN_CONTACT_MAILING_COUNTRY, addContactData.getMailingAddressCountry());
        values.put(DataBaseConstant.COLUMN_CONTACT_OTHER_STREET, addContactData.getOtherAddressStreet());
        values.put(DataBaseConstant.COLUMN_CONTACT_OTHER_STATE, addContactData.getOtherAddressState());
        values.put(DataBaseConstant.COLUMN_CONTACT_OTHER_CODE, addContactData.getOtherAddressZip());
        values.put(DataBaseConstant.COLUMN_CONTACT_OTHER_CITY, addContactData.getOtherAddressCity());
        values.put(DataBaseConstant.COLUMN_CONTACT_OTHER_COUNTRY, addContactData.getOtherAddressCountry());
        values.put(DataBaseConstant.COLUMN_CONTACT_CREATED_BY, addContactData.getCreatedBy());
        values.put(DataBaseConstant.COLUMN_CONTACT_CREATED_TIME, addContactData.getCreatedDate());
        values.put(DataBaseConstant.COLUMN_CONTACT_MODIFIED_BY, addContactData.getModifyBy());
        values.put(DataBaseConstant.COLUMN_CONTACT_MODIFIED_TIME, addContactData.getModifyDate());
        values.put(DataBaseConstant.COLUMN_CONTACT_IS_SYNC, addContactData.isSync());
        result = sqLiteDatabase.update(DataBaseConstant.TABLE_ADD_CONTACT, values,
                DataBaseConstant.COLUMN_CONTACT_ID + " =" + contactId, null);
        Log.e(TAG, " " + contactId + " " + values + " " + result);

        return result;

    }


    // delete contact
    public boolean deleteContact(long contactID) {
        sqLiteDatabase = dataBaseHelper.getWritableDatabase();
        return sqLiteDatabase.delete(DataBaseConstant.TABLE_ADD_CONTACT, DataBaseConstant.COLUMN_CONTACT_ID + " = " + contactID, null) > 0;
    }


    // Add Deal
    public long addDeal(DealDataModel dealDataModel) {
        long rowID = 0;
        openDataBase();
        ContentValues values = new ContentValues();
        values.put(DataBaseConstant.COLUMN_DEAL_ID, dealDataModel.getDealId());
        values.put(DataBaseConstant.COLUMN_DEAL_IS_SYNC, dealDataModel.isSync());
        values.put(DataBaseConstant.COLUMN_DEAL_OWNER, dealDataModel.getDealOwner());
        values.put(DataBaseConstant.COLUMN_DEAL_AMOUNT, String.valueOf(dealDataModel.getAmount()));
        values.put(DataBaseConstant.COLUMN_DEAL_ACCOUNT_NAME, dealDataModel.getAccountName());
        values.put(DataBaseConstant.COLUMN_DEAL_ACCOUNT_ID, dealDataModel.getAccountId());
        values.put(DataBaseConstant.COLUMN_DEAL_NAME, dealDataModel.getDealName());
        values.put(DataBaseConstant.COLUMN_DEAL_DATE, String.valueOf(dealDataModel.getClosingDate()));
        values.put(DataBaseConstant.COLUMN_DEAL_STATE, dealDataModel.getStage());
        values.put(DataBaseConstant.COLUMN_DEAL_TYPE, dealDataModel.getType());
        values.put(DataBaseConstant.COLUMN_DEAL_PROBABILITY, dealDataModel.getProbability());
        values.put(DataBaseConstant.COLUMN_DEAL_NEXT_STEP, dealDataModel.getNextStep());
        values.put(DataBaseConstant.COLUMN_DEAL_EXPECTED_REVENUE, dealDataModel.getExpectedRevenue());
        values.put(DataBaseConstant.COLUMN_DEAL_LEAD_SOURCE, dealDataModel.getLeadSource());
        values.put(DataBaseConstant.COLUMN_DEAL_CAMPAIGN_SOURCE, dealDataModel.getCampaignSource());
        values.put(DataBaseConstant.COLUMN_DEAL_CONTACT_NAME, dealDataModel.getContactName());
        values.put(DataBaseConstant.COLUMN_DEAL_CONTACT_ID, dealDataModel.getContactId());
        values.put(DataBaseConstant.COLUMN_DEAL_DESCRIPTION, dealDataModel.getDescription());
        values.put(DataBaseConstant.COLUMN_DEAL_CREATED_BY, dealDataModel.getCreatedBy());
        values.put(DataBaseConstant.COLUMN_DEAL_MODIFY_BY, dealDataModel.getModifiedBy());
        values.put(DataBaseConstant.COLUMN_DEAL_CREATED_TIME, dealDataModel.getCreatedTime());
        values.put(DataBaseConstant.COLUMN_DEAL_MODIFIED_TIME, dealDataModel.getModifiedTime());

        rowID = sqLiteDatabase.insert(DataBaseConstant.TABLE_ADD_DEAL, null, values);
        Log.e(TAG, " " + rowID + " " + values);
        return rowID;
    }

    // get Deal by Id
    public DealDataModel getDealById(Long dealId) {
        DealDataModel dealDataModel = new DealDataModel();
        Log.e(TAG, "get data from deal id " + dealId);

        sqLiteDatabase = dataBaseHelper.getWritableDatabase();

        String getDealtQuery = DataBaseConstant.GET_DEAL_BY_ID + " " + dealId;
        Cursor cursor = sqLiteDatabase.rawQuery(getDealtQuery, null);
        if (cursor.moveToFirst()) {
            dealDataModel.setSync(cursor.getInt(cursor.getColumnIndex(DataBaseConstant.COLUMN_DEAL_IS_SYNC)) > 0);
            dealDataModel.setDealOwner(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_DEAL_OWNER)));
            dealDataModel.setAmount(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_DEAL_AMOUNT)));
            dealDataModel.setAccountName(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_DEAL_ACCOUNT_NAME)));
            dealDataModel.setAccountId(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_DEAL_ACCOUNT_ID)));
            dealDataModel.setDealName(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_DEAL_NAME)));
            dealDataModel.setClosingDate(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_DEAL_DATE)));
            dealDataModel.setStage(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_DEAL_STATE)));
            dealDataModel.setType(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_DEAL_TYPE)));
            dealDataModel.setProbability(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_DEAL_PROBABILITY)));
            dealDataModel.setCampaignSource(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_DEAL_CAMPAIGN_SOURCE)));
            dealDataModel.setContactName(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_DEAL_CONTACT_NAME)));
            dealDataModel.setContactId(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_DEAL_CONTACT_ID)));
            dealDataModel.setDescription(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_DEAL_DESCRIPTION)));
            dealDataModel.setLeadSource(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_DEAL_LEAD_SOURCE)));
            dealDataModel.setExpectedRevenue(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_DEAL_EXPECTED_REVENUE)));
            dealDataModel.setNextStep(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_DEAL_NEXT_STEP)));
            dealDataModel.setCreatedBy(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_DEAL_CREATED_BY)));
            dealDataModel.setModifiedBy(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_DEAL_MODIFY_BY)));
            dealDataModel.setCreatedTime(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_DEAL_CREATED_TIME)));
            dealDataModel.setModifiedTime(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_DEAL_MODIFIED_TIME)));
        }
        return dealDataModel;
    }


    // get Deal by Id
    public List<DealDataModel> getDealByContactId(long contactId) {
        List<DealDataModel> dealDataModelList = new ArrayList<>();
        DealDataModel dealDataModel = null;
        Log.e(TAG, "get data from contact id " + contactId);

        sqLiteDatabase = dataBaseHelper.getWritableDatabase();

        String getDealtQuery = " SELECT * FROM " + DataBaseConstant.TABLE_ADD_DEAL + " WHERE " + DataBaseConstant.COLUMN_DEAL_CONTACT_ID + " = " + contactId;
        Cursor cursor = sqLiteDatabase.rawQuery(getDealtQuery, null);
        if (cursor.moveToFirst()) {
            do {
                dealDataModel = new DealDataModel();
                dealDataModel.setDealName(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_DEAL_NAME)));
                dealDataModel.setClosingDate(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_DEAL_DATE)));
                dealDataModel.setStage(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_DEAL_STATE)));
                dealDataModel.setType(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_DEAL_TYPE)));
                dealDataModel.setProbability(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_DEAL_PROBABILITY)));
                dealDataModel.setContactId(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_DEAL_CONTACT_ID)));
                dealDataModelList.add(dealDataModel);
            } while (cursor.moveToNext());

        }
        return dealDataModelList;
    }


    public int updateDealByID(DealDataModel dealDataModel, long dealId) {
        int result;
        openDataBase();
        ContentValues values = new ContentValues();
        values.put(DataBaseConstant.COLUMN_DEAL_OWNER, dealDataModel.getDealOwner());
        values.put(DataBaseConstant.COLUMN_DEAL_AMOUNT, String.valueOf(dealDataModel.getAmount()));
        values.put(DataBaseConstant.COLUMN_DEAL_ACCOUNT_NAME, dealDataModel.getAccountName());
        values.put(DataBaseConstant.COLUMN_DEAL_ACCOUNT_ID, dealDataModel.getAccountId());
        values.put(DataBaseConstant.COLUMN_DEAL_NAME, dealDataModel.getDealName());
        values.put(DataBaseConstant.COLUMN_DEAL_DATE, String.valueOf(dealDataModel.getClosingDate()));
        values.put(DataBaseConstant.COLUMN_DEAL_STATE, dealDataModel.getStage());
        values.put(DataBaseConstant.COLUMN_DEAL_TYPE, dealDataModel.getType());
        values.put(DataBaseConstant.COLUMN_DEAL_PROBABILITY, dealDataModel.getProbability());
        values.put(DataBaseConstant.COLUMN_DEAL_NEXT_STEP, dealDataModel.getNextStep());
        values.put(DataBaseConstant.COLUMN_DEAL_EXPECTED_REVENUE, dealDataModel.getExpectedRevenue());
        values.put(DataBaseConstant.COLUMN_DEAL_LEAD_SOURCE, dealDataModel.getLeadSource());
        values.put(DataBaseConstant.COLUMN_DEAL_CAMPAIGN_SOURCE, dealDataModel.getCampaignSource());
        values.put(DataBaseConstant.COLUMN_DEAL_CONTACT_NAME, dealDataModel.getContactName());
        values.put(DataBaseConstant.COLUMN_DEAL_CONTACT_ID, dealDataModel.getContactId());
        values.put(DataBaseConstant.COLUMN_DEAL_DESCRIPTION, dealDataModel.getDescription());
        values.put(DataBaseConstant.COLUMN_DEAL_CREATED_BY, dealDataModel.getCreatedBy());
        values.put(DataBaseConstant.COLUMN_DEAL_MODIFY_BY, dealDataModel.getModifiedBy());
        values.put(DataBaseConstant.COLUMN_DEAL_CREATED_TIME, dealDataModel.getCreatedTime());
        values.put(DataBaseConstant.COLUMN_DEAL_MODIFIED_TIME, dealDataModel.getModifiedTime());
        values.put(DataBaseConstant.COLUMN_DEAL_IS_SYNC, dealDataModel.isSync());
        result = sqLiteDatabase.update(DataBaseConstant.TABLE_ADD_DEAL, values, /*"" + DataBaseConstant.COLUMN_ACCOUNT_ID + " = '" + accountId + "'"*/
                "deal_id =" + dealId, null);
        Log.e(TAG, " " + dealId + " " + values + " " + result);

        return result;
    }

    // delete deal
    public boolean deleteDeal(long dealId) {
        sqLiteDatabase = dataBaseHelper.getWritableDatabase();
        return sqLiteDatabase.delete(DataBaseConstant.TABLE_ADD_DEAL, DataBaseConstant.COLUMN_DEAL_ID + " = " + dealId, null) > 0;
    }

    //delete call

    public boolean deleteCall(long callId) {
        sqLiteDatabase = dataBaseHelper.getWritableDatabase();
        return sqLiteDatabase.delete(DataBaseConstant.TABLE_ADD_CALL, COLUMN_CALL_ID + " = " + callId, null) > 0;
    }

    //addSales budget
    public long addSalesBudget(SalesBudgetModel salesBudgetModel) {
        long rowID = 0;
        openDataBase();
        ContentValues values = new ContentValues();
        values.put(DataBaseConstant.COLUMN_BUDGET_IS_SYNC, salesBudgetModel.isSync());
        values.put(DataBaseConstant.COLUMN_BUDGET_ID, salesBudgetModel.getSalesId());
        values.put(DataBaseConstant.COLUMN_BUDGET_YEAR, salesBudgetModel.getSalesYear());
        /*values.put(DataBaseConstant.COLUMN_BUDGET_SALESMAN_SECTOR, salesBudgetModel.getSalesSalesSector());
        values.put(DataBaseConstant.COLUMN_BUDGET_SALESMAN, salesBudgetModel.getSalesman());
        values.put(DataBaseConstant.COLUMN_BUDGET_SECTOR, salesBudgetModel.getSalesSector());
        values.put(DataBaseConstant.COLUMN_BUDGET_CURRENCY, salesBudgetModel.getSalesCurrency());*/
        values.put(DataBaseConstant.COLUMN_BUDGET_ROE, salesBudgetModel.getSalesROE());
        values.put(DataBaseConstant.COLUMN_BUDGET_NOTE, salesBudgetModel.getSalesNote());
        values.put(DataBaseConstant.COLUMN_BUDGET_CREATED_BY, salesBudgetModel.getSalesCreatedBY());
        values.put(DataBaseConstant.COLUMN_BUDGET_MODIFY_BY, salesBudgetModel.getSalesModifyBy());
        values.put(DataBaseConstant.COLUMN_BUDGET_CREATED_TIME, salesBudgetModel.getSalesCreatedTime());
        values.put(DataBaseConstant.COLUMN_BUDGET_MODIFY_TIME, salesBudgetModel.getSalesModifyTime());
        rowID = sqLiteDatabase.insert(DataBaseConstant.TABLE_SALES_BUDGET, null, values);
        Log.e(TAG, " " + rowID + " " + values);
        return rowID;

    }


    // update sales budget
    public int updateSalseBudget(SalesBudgetModel salesBudgetModel, long salesId) {
        int result;

        openDataBase();
        ContentValues values = new ContentValues();
        values.put(DataBaseConstant.COLUMN_BUDGET_IS_SYNC, salesBudgetModel.isSync());
        values.put(DataBaseConstant.COLUMN_BUDGET_ID, salesBudgetModel.getSalesId());
        values.put(DataBaseConstant.COLUMN_BUDGET_YEAR, salesBudgetModel.getSalesYear());
        /*values.put(DataBaseConstant.COLUMN_BUDGET_SALESMAN_SECTOR, salesBudgetModel.getSalesSalesSector());
        values.put(DataBaseConstant.COLUMN_BUDGET_SALESMAN, salesBudgetModel.getSalesman());
        values.put(DataBaseConstant.COLUMN_BUDGET_SECTOR, salesBudgetModel.getSalesSector());
        values.put(DataBaseConstant.COLUMN_BUDGET_CURRENCY, salesBudgetModel.getSalesCurrency());*/
        values.put(DataBaseConstant.COLUMN_BUDGET_ROE, salesBudgetModel.getSalesROE());
        values.put(DataBaseConstant.COLUMN_BUDGET_NOTE, salesBudgetModel.getSalesNote());
        values.put(DataBaseConstant.COLUMN_BUDGET_CREATED_BY, salesBudgetModel.getSalesCreatedBY());
        values.put(DataBaseConstant.COLUMN_BUDGET_MODIFY_BY, salesBudgetModel.getSalesModifyBy());
        values.put(DataBaseConstant.COLUMN_BUDGET_CREATED_TIME, salesBudgetModel.getSalesCreatedTime());
        values.put(DataBaseConstant.COLUMN_BUDGET_MODIFY_TIME, salesBudgetModel.getSalesModifyTime());
        result = sqLiteDatabase.update(DataBaseConstant.TABLE_SALES_BUDGET, values, DataBaseConstant.COLUMN_BUDGET_ID + " =" + salesId, null);
        Log.e(TAG, " " + result + " " + values);


        return result;
    }


    //get sales by ID
    public SalesBudgetModel getSalesById(long salesId) {
        SalesBudgetModel salesBudgetModel = new SalesBudgetModel();
        Log.e(TAG, "get data from sales id " + salesId);
        sqLiteDatabase = dataBaseHelper.getWritableDatabase();
        String getContactQuery = DataBaseConstant.GET_SALES_BUDGET + " " + salesId;

        Cursor cursor = sqLiteDatabase.rawQuery(getContactQuery, null);
        if (cursor.moveToFirst()) {
            salesBudgetModel.setSync(cursor.getInt(cursor.getColumnIndex(DataBaseConstant.COLUMN_BUDGET_IS_SYNC)) > 0);
            salesBudgetModel.setSalesId(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_BUDGET_ID)));
            salesBudgetModel.setSalesYear(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_BUDGET_YEAR)));
           /* salesBudgetModel.setSalesCurrency(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_BUDGET_CURRENCY)));
            salesBudgetModel.setSalesSector(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_BUDGET_SECTOR)));
            salesBudgetModel.setSalesman(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_BUDGET_SALESMAN)));*/
            salesBudgetModel.setSalesROE(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_BUDGET_ROE)));
            salesBudgetModel.setSalesCreatedBY(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_BUDGET_CREATED_BY)));
            salesBudgetModel.setSalesModifyBy(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_BUDGET_MODIFY_BY)));
            salesBudgetModel.setSalesCreatedTime(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_BUDGET_CREATED_TIME)));
            salesBudgetModel.setSalesModifyTime(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_BUDGET_MODIFY_TIME)));
            salesBudgetModel.setSalesNote(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_BUDGET_NOTE)));

        }


        return salesBudgetModel;
    }


    // get All Sales budget
    public List<SalesBudgetModel> getAllSalesBudget() {
        List<SalesBudgetModel> salesBudgetModelsList = new ArrayList<>();
        sqLiteDatabase = dataBaseHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(DataBaseConstant.SELECT_TABLE_SALES_BUDGET, null);
        Log.e(TAG, DataBaseConstant.SELECT_TABLE_SALES_BUDGET);
        if (cursor.moveToFirst()) {
            do {
                SalesBudgetModel salesBudgetModel = new SalesBudgetModel();
                salesBudgetModel.setSalesId(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_BUDGET_ID)));
                salesBudgetModel.setSalesYear(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_BUDGET_YEAR)));
                /*salesBudgetModel.setSalesCurrency(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_BUDGET_CURRENCY)));
                salesBudgetModel.setSalesSector(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_BUDGET_SECTOR)));
                salesBudgetModel.setSalesman(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_BUDGET_SALESMAN)));*/
                salesBudgetModelsList.add(salesBudgetModel);
            } while (cursor.moveToNext());
        }

        return salesBudgetModelsList;
    }


    // get All UnSync Sales budget
    public List<SalesBudgetModel> getAllUnSyncSalesBudget() {
        List<SalesBudgetModel> salesBudgetModelsList = new ArrayList<>();
        sqLiteDatabase = dataBaseHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(DataBaseConstant.SELECT_UN_SYNC_SALES_BUDGET, null);
        if (cursor.moveToFirst()) {
            do {
                SalesBudgetModel salesBudgetModel = new SalesBudgetModel();
                /*salesBudgetModel.setSync(cursor.getInt(cursor.getColumnIndex(DataBaseConstant.COLUMN_BUDGET_IS_SYNC)) > 0);
                salesBudgetModel.setSalesId(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_BUDGET_ID)));
                salesBudgetModel.setSalesYear(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_BUDGET_YEAR)));
                salesBudgetModel.setSalesCurrency(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_BUDGET_CURRENCY)));
                salesBudgetModel.setSalesSector(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_BUDGET_SECTOR)));
                salesBudgetModel.setSalesman(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_BUDGET_SALESMAN)));
                salesBudgetModel.setSalesROE(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_BUDGET_ROE)));
                salesBudgetModel.setSalesCreatedBY(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_BUDGET_CREATED_BY)));
                salesBudgetModel.setSalesModifyBy(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_BUDGET_MODIFY_BY)));
                salesBudgetModel.setSalesCreatedTime(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_BUDGET_CREATED_TIME)));
                salesBudgetModel.setSalesModifyTime(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_BUDGET_MODIFY_TIME)));
                salesBudgetModel.setSalesNote(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_BUDGET_NOTE)));*/
                salesBudgetModelsList.add(salesBudgetModel);
            } while (cursor.moveToNext());
        }

        return salesBudgetModelsList;
    }


    // delete sales
    public boolean deleteSales(long salesID) {
        sqLiteDatabase = dataBaseHelper.getWritableDatabase();
        return sqLiteDatabase.delete(DataBaseConstant.TABLE_SALES_BUDGET, DataBaseConstant.COLUMN_BUDGET_ID + " = " + salesID, null) > 0;
    }


    //addCustomer challenge
    public long addCustomerChallenge(CustomerChallengeModel customerChallengeModel) {
        long rowID = 0;
        openDataBase();
        ContentValues values = new ContentValues();
        values.put(DataBaseConstant.COLUMN_CUSTOMER_IS_SYNC, customerChallengeModel.isSync());
        values.put(DataBaseConstant.COLUMN_CUSTOMER_ID, customerChallengeModel.getCustomerId());
        values.put(DataBaseConstant.COLUMN_CUSTOMER_NAME, customerChallengeModel.getCustomer());
        values.put(DataBaseConstant.COLUMN_CUSTOMER_CONTACT, customerChallengeModel.getContactName());
        values.put(DataBaseConstant.COLUMN_CUSTOMER_CONTACT_ID, customerChallengeModel.getContactId());
        values.put(DataBaseConstant.COLUMN_CUSTOMER_LOG_DATE, customerChallengeModel.getCustomerLogDate());
        values.put(DataBaseConstant.COLUMN_CUSTOMER_PRIORITY, customerChallengeModel.getCustomerPriority());
        values.put(DataBaseConstant.COLUMN_CUSTOMER_ORIGIN, customerChallengeModel.getCustomerOrigin());
        values.put(DataBaseConstant.COLUMN_CUSTOMER_REASON, customerChallengeModel.getCustomerReason());
        values.put(DataBaseConstant.COLUMN_CUSTOMER_DUE_DATE, customerChallengeModel.getCustomerDueDate());
        values.put(DataBaseConstant.COLUMN_CUSTOMER_CLOSED_ON, customerChallengeModel.getCloseddate());
        values.put(DataBaseConstant.COLUMN_CUSTOMER_INCHARGE, customerChallengeModel.getCustomerInCharge());
        values.put(DataBaseConstant.COLUMN_CUSTOMER_CC_MAIL_ID, customerChallengeModel.getCcMailId());
        values.put(DataBaseConstant.COLUMN_CUSTOMER_STATUS, customerChallengeModel.getStatus());
        values.put(DataBaseConstant.COLUMN_CUSTOMER_SUBJECT, customerChallengeModel.getSubject());
        values.put(DataBaseConstant.COLUMN_CUSTOMER_DISCRIPTION, customerChallengeModel.getDescription());
        values.put(DataBaseConstant.COLUMN_CUSTOMER_INTERNAL_NOTE, customerChallengeModel.getInternalNote());
        values.put(DataBaseConstant.COLUMN_CUSTOMER_CREATED_BY, customerChallengeModel.getCreatedBy());
        values.put(DataBaseConstant.COLUMN_CUSTOMER_MODIFY_BY, customerChallengeModel.getModifyBy());
        values.put(DataBaseConstant.COLUMN_CUSTOMER_CREATED_TIME, customerChallengeModel.getCreatedTime());
        values.put(DataBaseConstant.COLUMN_CUSTOMER_MODIFY_TIME, customerChallengeModel.getModifyTime());
        values.put(DataBaseConstant.COLUMN_CUSTOMER_FEED_BACK, customerChallengeModel.getCustomerFeedback());
        values.put(DataBaseConstant.COLUMN_CUSTOMER_NOTE, customerChallengeModel.getNote());
        values.put(DataBaseConstant.COLUMN_CUSTOMER_LEAD,customerChallengeModel.getLeadName());
        values.put(DataBaseConstant.COLUMN_CUSTOMER_LEAD_ID,customerChallengeModel.getLeadId());
        rowID = sqLiteDatabase.insert(DataBaseConstant.TABLE_CUSTOMER_CHALLENGE, null, values);
        Log.e(TAG, " " + rowID + " " + values);
        return rowID;

    }

    //update Customer Challenge
    public long updateCustomerChallenge(CustomerChallengeModel customerChallengeModel, long customerChallengeId) {
        long result = 0;
        openDataBase();
        ContentValues values = new ContentValues();
        values.put(DataBaseConstant.COLUMN_CUSTOMER_IS_SYNC, customerChallengeModel.isSync());
        values.put(DataBaseConstant.COLUMN_CUSTOMER_ID, customerChallengeModel.getCustomerId());
        values.put(DataBaseConstant.COLUMN_CUSTOMER_NAME, customerChallengeModel.getCustomer());
        values.put(DataBaseConstant.COLUMN_CUSTOMER_CONTACT, customerChallengeModel.getContactName());
        values.put(DataBaseConstant.COLUMN_CUSTOMER_CONTACT_ID, customerChallengeModel.getContactId());
        values.put(DataBaseConstant.COLUMN_CUSTOMER_LOG_DATE, customerChallengeModel.getCustomerLogDate());
        values.put(DataBaseConstant.COLUMN_CUSTOMER_PRIORITY, customerChallengeModel.getCustomerPriority());
        values.put(DataBaseConstant.COLUMN_CUSTOMER_ORIGIN, customerChallengeModel.getCustomerOrigin());
        values.put(DataBaseConstant.COLUMN_CUSTOMER_REASON, customerChallengeModel.getCustomerReason());
        values.put(DataBaseConstant.COLUMN_CUSTOMER_DUE_DATE, customerChallengeModel.getCustomerDueDate());
        values.put(DataBaseConstant.COLUMN_CUSTOMER_CLOSED_ON, customerChallengeModel.getCloseddate());
        values.put(DataBaseConstant.COLUMN_CUSTOMER_INCHARGE, customerChallengeModel.getCustomerInCharge());
        values.put(DataBaseConstant.COLUMN_CUSTOMER_CC_MAIL_ID, customerChallengeModel.getCcMailId());
        values.put(DataBaseConstant.COLUMN_CUSTOMER_STATUS, customerChallengeModel.getStatus());
        values.put(DataBaseConstant.COLUMN_CUSTOMER_SUBJECT, customerChallengeModel.getSubject());
        values.put(DataBaseConstant.COLUMN_CUSTOMER_DISCRIPTION, customerChallengeModel.getDescription());
        values.put(DataBaseConstant.COLUMN_CUSTOMER_INTERNAL_NOTE, customerChallengeModel.getInternalNote());
        values.put(DataBaseConstant.COLUMN_CUSTOMER_CREATED_BY, customerChallengeModel.getCreatedBy());
        values.put(DataBaseConstant.COLUMN_CUSTOMER_MODIFY_BY, customerChallengeModel.getModifyBy());
        values.put(DataBaseConstant.COLUMN_CUSTOMER_CREATED_TIME, customerChallengeModel.getCreatedTime());
        values.put(DataBaseConstant.COLUMN_CUSTOMER_MODIFY_TIME, customerChallengeModel.getModifyTime());
        values.put(DataBaseConstant.COLUMN_CUSTOMER_FEED_BACK, customerChallengeModel.getCustomerFeedback());
        values.put(DataBaseConstant.COLUMN_CUSTOMER_LEAD,customerChallengeModel.getLeadName());
        values.put(DataBaseConstant.COLUMN_CUSTOMER_LEAD_ID,customerChallengeModel.getLeadId());
        values.put(DataBaseConstant.COLUMN_CUSTOMER_NOTE, customerChallengeModel.getNote());
        result = sqLiteDatabase.update(DataBaseConstant.TABLE_CUSTOMER_CHALLENGE, values,
                COLUMN_CUSTOMER_ID + " = " + customerChallengeId, null);

        Log.e(TAG, " " + result + " " + values);
        return result;
    }


    public CustomerChallengeModel getCustomerChallengesById(long customerChallengeId) {
        CustomerChallengeModel customerChallengeModel = new CustomerChallengeModel();


        Log.e(TAG, "get data from customer challengeId  " + customerChallengeId);

        sqLiteDatabase = dataBaseHelper.getWritableDatabase();

        String getDealtQuery = DataBaseConstant.GET_CUSTOMER_CHALLENGE_BY_ID + " " + customerChallengeId;
        Cursor cursor = sqLiteDatabase.rawQuery(getDealtQuery, null);
        if (cursor.moveToFirst()) {
            customerChallengeModel.setSync(cursor.getInt(cursor.getColumnIndex(COLUMN_CUSTOMER_IS_SYNC)) > 0);
            customerChallengeModel.setCustomerId(cursor.getLong(cursor.getColumnIndex(COLUMN_CUSTOMER_ID)));
            customerChallengeModel.setCustomer(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CUSTOMER_NAME)));
            customerChallengeModel.setContactName(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CUSTOMER_CONTACT)));
            customerChallengeModel.setContactId(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_CUSTOMER_CONTACT_ID)));
            customerChallengeModel.setCustomerLogDate(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_CUSTOMER_LOG_DATE)));
            customerChallengeModel.setCustomerPriority(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CUSTOMER_PRIORITY)));
            customerChallengeModel.setCustomerOrigin(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CUSTOMER_ORIGIN)));
            customerChallengeModel.setCustomerReason(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CUSTOMER_REASON)));
            customerChallengeModel.setCustomerDueDate(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_CUSTOMER_DUE_DATE)));
            customerChallengeModel.setCloseddate(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_CUSTOMER_CLOSED_ON)));
            customerChallengeModel.setCustomerInCharge(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CUSTOMER_INCHARGE)));
            customerChallengeModel.setCcMailId(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CUSTOMER_CC_MAIL_ID)));
            customerChallengeModel.setStatus(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CUSTOMER_STATUS)));
            customerChallengeModel.setCreatedBy(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CUSTOMER_CREATED_BY)));
            customerChallengeModel.setModifyBy(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CUSTOMER_MODIFY_BY)));
            customerChallengeModel.setCreatedTime(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_CUSTOMER_CREATED_TIME)));
            customerChallengeModel.setModifyTime(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_CUSTOMER_MODIFY_TIME)));
            customerChallengeModel.setSubject(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CUSTOMER_SUBJECT)));
            customerChallengeModel.setNote(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CUSTOMER_NOTE)));
            customerChallengeModel.setDescription(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CUSTOMER_DISCRIPTION)));
            customerChallengeModel.setInternalNote(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CUSTOMER_INTERNAL_NOTE)));
            customerChallengeModel.setCustomerFeedback(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CUSTOMER_FEED_BACK)));
            customerChallengeModel.setLeadName(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CUSTOMER_LEAD)));
            customerChallengeModel.setLeadId(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_CUSTOMER_LEAD_ID)));
            }


        return customerChallengeModel;
    }


    // delete Customer Challenges
    public boolean deleteCustomerChallenges(long customerChallengesId) {
        sqLiteDatabase = dataBaseHelper.getWritableDatabase();
        return sqLiteDatabase.delete(DataBaseConstant.TABLE_CUSTOMER_CHALLENGE, COLUMN_CUSTOMER_ID + " = " + customerChallengesId, null) > 0;
    }


    public LeadListSortingData getAllData(String groupBy, String order1, String orderBy, String order2) {
        LeadListSortingData leadListSortingData = new LeadListSortingData();
        List<OrderByData> orderByDataList = new ArrayList<OrderByData>();
        List<AddLeadData> addLeadDataList = new ArrayList<AddLeadData>();
        sqLiteDatabase = dataBaseHelper.getWritableDatabase();
        Cursor cursor;
        String query1 = "SELECT lead_created_time,count(lead_created_time) frOM leadMaster group by  lead_created_time order by lead_created_time desc";
        cursor = sqLiteDatabase.rawQuery(query1, null);
        if (cursor.moveToFirst()) {
            do {
                OrderByData orderByData = new OrderByData();
                orderByData.setOrderBy(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_LEAD_CREATED_TIME)));
                orderByData.setCount(cursor.getInt(cursor.getColumnIndex("count(" + DataBaseConstant.COLUMN_LEAD_CREATED_TIME + ")")));
                orderByDataList.add(orderByData);
            } while (cursor.moveToNext());
        }
        String query2 = "seLECT * fROM leadMaster order by lead_company_name desc ,lead_owner desc";
        cursor = sqLiteDatabase.rawQuery(query2, null);
        if (cursor.moveToFirst()) {
            do {
                AddLeadData addLeadData = new AddLeadData();
                addLeadData.setLeadOwner(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_LEAD_OWNER)));
                addLeadData.setLeadId(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_LEAD_ID)));
                addLeadData.setCompany(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_LEAD_COMPANY)));
                addLeadData.setLastName(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_LEAD_LAST_NAME)));
                addLeadData.setFirstName(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_LEAD_FIRST_NAME)));
                addLeadData.setCreatedTime(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_LEAD_CREATED_TIME)));
                addLeadDataList.add(addLeadData);
            } while (cursor.moveToNext());
        }

        leadListSortingData.setOrderByDataList(orderByDataList);
        leadListSortingData.setAddLeadDataList(addLeadDataList);


        return leadListSortingData;
    }


    public ContactListSortingData getAllContacts(String groupBy, String order1, String orderBy, String order2) {
        ContactListSortingData contactListSortingData = new ContactListSortingData();
        List<OrderByData> orderByDataList = new ArrayList<OrderByData>();
        List<AddContactData> addContactDataList = new ArrayList<AddContactData>();
        sqLiteDatabase = dataBaseHelper.getWritableDatabase();
        Cursor cursor;
        String query1 = "SELECT contact_created_time,count(contact_created_time) from  AddContact group by  contact_created_time order by contact_created_time desc";
        cursor = sqLiteDatabase.rawQuery(query1, null);
        if (cursor.moveToFirst()) {
            do {
                OrderByData orderByData = new OrderByData();
                orderByData.setOrderBy(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CONTACT_CREATED_TIME)));
                orderByData.setCount(cursor.getInt(cursor.getColumnIndex("count(" + DataBaseConstant.COLUMN_CONTACT_CREATED_TIME + ")")));
                orderByDataList.add(orderByData);

            } while (cursor.moveToNext());
        }
        String query2 = "seLECT * fROM AddContact order by contact_last_name desc ,contact_owner desc";
        cursor = sqLiteDatabase.rawQuery(query2, null);
        if (cursor.moveToFirst()) {
            do {
                AddContactData addContactData = new AddContactData();
                addContactData.setContactOwner(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CONTACT_OWNER)));
                addContactData.setContactId(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_CONTACT_ID)));
                addContactData.setLastName(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CONTACT_LAST_NAME)));
                addContactData.setFirstName(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CONTACT_FIRST_NAME)));
                addContactData.setCreatedDate(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_CONTACT_CREATED_TIME)));
                addContactDataList.add(addContactData);
            } while (cursor.moveToNext());
        }

        contactListSortingData.setOrderByDataList(orderByDataList);
        contactListSortingData.setAddContactDataList(addContactDataList);


        return contactListSortingData;
    }

    //get all deal
    public DealListSortingData getAllDeals(String groupBy, String order1, String orderBy, String order2) {
        DealListSortingData dealListSortingData = new DealListSortingData();
        List<OrderByData> orderByDataList = new ArrayList<OrderByData>();
        List<DealDataModel> dealDataModelList = new ArrayList<DealDataModel>();
        sqLiteDatabase = dataBaseHelper.getWritableDatabase();
        Cursor cursor;
        String query1 = "SELECT deal_date,count(deal_date) from  addDeal group by  deal_date order by deal_date desc";
        Log.e(TAG, query1);
        cursor = sqLiteDatabase.rawQuery(query1, null);
        if (cursor.moveToFirst()) {
            do {
                OrderByData orderByData = new OrderByData();
                orderByData.setOrderBy(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_DEAL_DATE)));
                orderByData.setCount(cursor.getInt(cursor.getColumnIndex("count(" + DataBaseConstant.COLUMN_DEAL_DATE + ")")));
                orderByDataList.add(orderByData);

            } while (cursor.moveToNext());
        }

        String query2 = "seLECT * fROM addDeal order by deal_name desc ,deal_date desc";
        cursor = sqLiteDatabase.rawQuery(query2, null);
        if (cursor.moveToFirst()) {
            do {
                DealDataModel dealDataModel = new DealDataModel();
                dealDataModel.setDealName(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_DEAL_NAME)));
                dealDataModel.setAmount(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_DEAL_AMOUNT)));
                dealDataModel.setDealId(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_DEAL_ID)));
                dealDataModel.setStage(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_DEAL_STATE)));
                dealDataModel.setDealOwner(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_DEAL_OWNER)));
                dealDataModel.setClosingDate(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_DEAL_DATE)));
                dealDataModel.setProbability(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_DEAL_PROBABILITY)));
                dealDataModelList.add(dealDataModel);
            } while (cursor.moveToNext());
        }

        dealListSortingData.setDealDataModelList(dealDataModelList);
        dealListSortingData.setOrderByDataList(orderByDataList);


        return dealListSortingData;
    }


    //get all UnSync Deal

    public List<DealDataModel> getAllUnSyncDeals() {
        List<DealDataModel> dealDataModelList = new ArrayList<DealDataModel>();
        sqLiteDatabase = dataBaseHelper.getWritableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery(DataBaseConstant.SELECT_UN_SYNC_DEAL, null);


        if (cursor.moveToFirst()) {
            do {
                DealDataModel dealDataModel = new DealDataModel();
                dealDataModel.setDealId(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_DEAL_ID)));
                dealDataModel.setDealOwner(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_DEAL_OWNER)));
                dealDataModel.setAmount(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_DEAL_AMOUNT)));
                dealDataModel.setAccountName(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_DEAL_ACCOUNT_NAME)));
                dealDataModel.setAccountId(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_DEAL_ACCOUNT_ID)));
                dealDataModel.setDealName(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_DEAL_NAME)));
                dealDataModel.setClosingDate(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_DEAL_DATE)));
                dealDataModel.setStage(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_DEAL_STATE)));
                dealDataModel.setType(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_DEAL_TYPE)));
                dealDataModel.setProbability(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_DEAL_PROBABILITY)));
                dealDataModel.setCampaignSource(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_DEAL_CAMPAIGN_SOURCE)));
                dealDataModel.setContactName(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_DEAL_CONTACT_NAME)));
                dealDataModel.setContactId(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_DEAL_CONTACT_ID)));
                dealDataModel.setDescription(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_DEAL_DESCRIPTION)));
                dealDataModel.setLeadSource(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_DEAL_LEAD_SOURCE)));
                dealDataModel.setExpectedRevenue(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_DEAL_EXPECTED_REVENUE)));
                dealDataModel.setNextStep(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_DEAL_NEXT_STEP)));
                dealDataModel.setCreatedBy(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_DEAL_CREATED_BY)));
                dealDataModel.setModifiedBy(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_DEAL_MODIFY_BY)));
                dealDataModel.setCreatedTime(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_DEAL_CREATED_TIME)));
                dealDataModel.setModifiedTime(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_DEAL_MODIFIED_TIME)));
                dealDataModelList.add(dealDataModel);
            } while (cursor.moveToNext());
        }


        return dealDataModelList;
    }


    public void syncAllData(SyncAllDataModel syncAllDataModel, String module) {
        switch (module) {
            case LEAD_SYNC:
                updateSyncLead(syncAllDataModel.getLead());
                break;
            case ACCOUNT_SYNC:
                updateSyncAccount(syncAllDataModel.getAccount());
                break;
            case CONTACT_SYNC:
                updateSyncContact(syncAllDataModel.getContact());
                break;
            case TASK_SYNC:
                updateSyncTask(syncAllDataModel.getTask());
                break;
            case EVENT_SYNC:
                updateSyncEvent(syncAllDataModel.getEvent());
                break;
            case DEAL_SYNC:
                updateSyncDeal(syncAllDataModel.getDeal());
                break;
            case CALL_SYNC:
                updateSyncCall(syncAllDataModel.getCall());
                break;
            case CUSTOMER_SYNC:
                updateSyncCustomerChallenge(syncAllDataModel.getCustomer());
                break;
            case SALES_SYNC:
                updateSyncSalesBudget(syncAllDataModel.getSales());
                break;
        }
    }


    //update sync lead
    public void updateSyncLead(List<LeadDataModel> leadDataModels) {
        if (leadDataModels != null && leadDataModels.size() > 0) {

            String[] ids = new String[leadDataModels.size()];
            sqLiteDatabase = dataBaseHelper.getWritableDatabase();
            String setValue = " IN (";
            int count = 0;
            for (LeadDataModel leadDataModel : leadDataModels) {
                setValue = setValue + "?,";
                ids[count] = String.valueOf(leadDataModel.getLeadId());
                count++;
            }
            setValue = new StringBuilder(setValue).replace(setValue.lastIndexOf(","), setValue.lastIndexOf(",") + 1, "").toString() + ")";

            ContentValues contentValues = new ContentValues();
            contentValues.put(DataBaseConstant.COLUMN_LEAD_IS_SYNC, 1);

            sqLiteDatabase.update(DataBaseConstant.TABLE_LEAD, contentValues, DataBaseConstant.COLUMN_LEAD_ID + setValue, ids);

        }
    }

    //update sync Account

    public void updateSyncAccount(List<AccountDataModel> accountDataModels) {
        if (accountDataModels != null && accountDataModels.size() > 0) {

            String[] ids = new String[accountDataModels.size()];
            sqLiteDatabase = dataBaseHelper.getWritableDatabase();
            String setValue = " IN (";
            int count = 0;
            for (AccountDataModel accountDataModel : accountDataModels) {
                setValue = setValue + "?,";
                ids[count] = String.valueOf(accountDataModel.getId());
                count++;
            }

            setValue = new StringBuilder(setValue).replace(setValue.lastIndexOf(","), setValue.lastIndexOf(",") + 1, "").toString() + ")";

            ContentValues contentValues = new ContentValues();
            contentValues.put(DataBaseConstant.COLUMN_ACCOUNT_IS_SYNC, 1);
            sqLiteDatabase.update(DataBaseConstant.TABLE_ADD_ACCOUNT, contentValues, DataBaseConstant.COLUMN_ACCOUNT_ID + setValue, ids);
        }
    }

    //update sync Contact

    public void updateSyncContact(List<AddContactData> addContactData) {
        if (addContactData != null && addContactData.size() > 0) {

            String[] ids = new String[addContactData.size()];
            sqLiteDatabase = dataBaseHelper.getWritableDatabase();
            String setValue = " IN (";
            int count = 0;
            for (AddContactData contactData : addContactData) {
                setValue = setValue + "?,";
                ids[count] = String.valueOf(contactData.getContactId());
                count++;
            }

            setValue = new StringBuilder(setValue).replace(setValue.lastIndexOf(","), setValue.lastIndexOf(",") + 1, "").toString() + ")";

            ContentValues contentValues = new ContentValues();
            contentValues.put(DataBaseConstant.COLUMN_CONTACT_IS_SYNC, 1);
            sqLiteDatabase.update(DataBaseConstant.TABLE_ADD_CONTACT, contentValues, DataBaseConstant.COLUMN_CONTACT_ID + setValue, ids);
        }
    }

    //update sync Task

    public void updateSyncTask(List<TaskDataModel> taskDataModels) {
        if (taskDataModels != null && taskDataModels.size() > 0) {
            String[] ids = new String[taskDataModels.size()];
            sqLiteDatabase = dataBaseHelper.getWritableDatabase();
            String setValue = " IN (";
            int count = 0;
            for (TaskDataModel taskDataModel : taskDataModels) {
                setValue = setValue + "?,";
                ids[count] = String.valueOf(taskDataModel.getTaskId());
                count++;
            }

            setValue = new StringBuilder(setValue).replace(setValue.lastIndexOf(","), setValue.lastIndexOf(",") + 1, "").toString() + ")";

            ContentValues contentValues = new ContentValues();
            contentValues.put(DataBaseConstant.COLUMN_ADD_TASK_IS_SYNC, 1);
            sqLiteDatabase.update(DataBaseConstant.TABLE_ADD_TASK, contentValues, DataBaseConstant.COLUMN_ADD_TASK_ID + setValue, ids);
        }
    }

    //update sync Event

    public void updateSyncEvent(List<EventDataModel> eventDataModels) {
        if (eventDataModels != null && eventDataModels.size() > 0) {
            String[] ids = new String[eventDataModels.size()];
            sqLiteDatabase = dataBaseHelper.getWritableDatabase();
            String setValue = " IN (";
            int count = 0;
            for (EventDataModel eventDataModel : eventDataModels) {
                setValue = setValue + "?,";
                ids[count] = String.valueOf(eventDataModel.getEventId());
                count++;
            }

            setValue = new StringBuilder(setValue).replace(setValue.lastIndexOf(","), setValue.lastIndexOf(",") + 1, "").toString() + ")";

            ContentValues contentValues = new ContentValues();
            contentValues.put(DataBaseConstant.COLUMN_EVENT_IS_SYNC, 1);
            sqLiteDatabase.update(DataBaseConstant.TABLE_ADD_EVENT, contentValues, DataBaseConstant.COLUMN_EVENT_ID + setValue, ids);
        }
    }

    //update sync Deal

    public void updateSyncDeal(List<DealDataModel> dealDataModels) {

        if (dealDataModels != null && dealDataModels.size() > 0) {

            String[] ids = new String[dealDataModels.size()];
            sqLiteDatabase = dataBaseHelper.getWritableDatabase();
            String setValue = " IN (";
            int count = 0;
            for (DealDataModel dealDataModel : dealDataModels) {
                setValue = setValue + "?,";
                ids[count] = String.valueOf(dealDataModel.getDealId());
                count++;
            }
            setValue = new StringBuilder(setValue).replace(setValue.lastIndexOf(","), setValue.lastIndexOf(",") + 1, "").toString() + ")";

            ContentValues contentValues = new ContentValues();
            contentValues.put(DataBaseConstant.COLUMN_DEAL_IS_SYNC, 1);

            sqLiteDatabase.update(DataBaseConstant.TABLE_ADD_DEAL, contentValues, DataBaseConstant.COLUMN_DEAL_ID + setValue, ids);
        }


    }

    //update sync Call

    public void updateSyncCall(List<CallDataModel> callDataModels) {
        if (callDataModels != null && callDataModels.size() > 0) {
            String[] ids = new String[callDataModels.size()];
            sqLiteDatabase = dataBaseHelper.getWritableDatabase();
            String setValue = " IN (";
            int count = 0;
            for (CallDataModel callDataModel : callDataModels) {
                setValue = setValue + "?,";
                ids[count] = String.valueOf(callDataModel.getCallId());
                count++;
            }

            setValue = new StringBuilder(setValue).replace(setValue.lastIndexOf(","), setValue.lastIndexOf(",") + 1, "").toString() + ")";

            ContentValues contentValues = new ContentValues();
            contentValues.put(COLUMN_CALL_IS_SYNC, 1);
            sqLiteDatabase.update(DataBaseConstant.TABLE_ADD_CALL, contentValues, COLUMN_CALL_ID + setValue, ids);
        }

    }

    //update sync Customer Challenge
    public void updateSyncCustomerChallenge(List<CustomerChallengeModel> customerChallengeModels) {
        if (customerChallengeModels != null && customerChallengeModels.size() > 0) {

            String[] ids = new String[customerChallengeModels.size()];
            sqLiteDatabase = dataBaseHelper.getWritableDatabase();
            String setValue = " IN (";
            int count = 0;
            for (CustomerChallengeModel customerChallengeModel : customerChallengeModels) {
                setValue = setValue + "?,";
                ids[count] = String.valueOf(customerChallengeModel.getCustomerId());
                count++;
            }
            setValue = new StringBuilder(setValue).replace(setValue.lastIndexOf(","), setValue.lastIndexOf(",") + 1, "").toString() + ")";

            ContentValues contentValues = new ContentValues();
            contentValues.put(DataBaseConstant.COLUMN_CUSTOMER_IS_SYNC, 1);

            sqLiteDatabase.update(DataBaseConstant.TABLE_CUSTOMER_CHALLENGE, contentValues, DataBaseConstant.COLUMN_CUSTOMER_ID + setValue, ids);

        }
    }


    //update sync Sales Budget

    public void updateSyncSalesBudget(List<SalesBudgetModel> salesBudgetModels) {
        if (salesBudgetModels != null && salesBudgetModels.size() > 0) {
            String[] ids = new String[salesBudgetModels.size()];
            sqLiteDatabase = dataBaseHelper.getWritableDatabase();
            String setValue = " IN (";
            int count = 0;
            for (SalesBudgetModel salesBudgetModel : salesBudgetModels) {
                setValue = setValue + "?,";
                ids[count] = String.valueOf(salesBudgetModel.getSalesId());
                count++;
            }

            setValue = new StringBuilder(setValue).replace(setValue.lastIndexOf(","), setValue.lastIndexOf(",") + 1, "").toString() + ")";

            ContentValues contentValues = new ContentValues();
            contentValues.put(COLUMN_BUDGET_IS_SYNC, 1);
            sqLiteDatabase.update(DataBaseConstant.TABLE_SALES_BUDGET, contentValues, COLUMN_BUDGET_ID + setValue, ids);
        }

    }


    public List<CustomerChallengeModel> getAllCustomerChallenges() {
        CustomerChallengeModel customerChallengeModel;
        List<CustomerChallengeModel> challengeModelList = new ArrayList<>();
        sqLiteDatabase = dataBaseHelper.getWritableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery(DataBaseConstant.SELECT_CUSTOMER_CHALLENGE, null);
        if (cursor.moveToNext()) {
            do {
                customerChallengeModel = new CustomerChallengeModel();
                customerChallengeModel.setCustomerId(cursor.getLong(cursor.getColumnIndex(COLUMN_CUSTOMER_ID)));
                customerChallengeModel.setCustomer(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CUSTOMER_NAME)));
                customerChallengeModel.setContactName(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CUSTOMER_CONTACT)));
                customerChallengeModel.setCreatedTime(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_CUSTOMER_CREATED_TIME)));
                customerChallengeModel.setLeadName(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CUSTOMER_LEAD)));
                customerChallengeModel.setLeadId(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_CUSTOMER_LEAD_ID)));
                challengeModelList.add(customerChallengeModel);
            } while (cursor.moveToNext());


        }

        return challengeModelList;
    }

    //get all UnSync Customer Challenges

    public List<CustomerChallengeModel> getAllUnSyncCustomerChallenges() {
        CustomerChallengeModel customerChallengeModel;
        List<CustomerChallengeModel> challengeModelList = new ArrayList<>();
        sqLiteDatabase = dataBaseHelper.getWritableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery(DataBaseConstant.SELECT_UN_SYNC_CUSTOMER, null);
        if (cursor.moveToNext()) {
            do {
                customerChallengeModel = new CustomerChallengeModel();
                customerChallengeModel.setCustomerId(cursor.getLong(cursor.getColumnIndex(COLUMN_CUSTOMER_ID)));
                customerChallengeModel.setCustomer(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CUSTOMER_NAME)));
                customerChallengeModel.setCustomer(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CUSTOMER_CONTACT)));
                customerChallengeModel.setContactId(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_CUSTOMER_CONTACT_ID)));
                customerChallengeModel.setCustomerLogDate(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_CUSTOMER_LOG_DATE)));
                customerChallengeModel.setCustomerPriority(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CUSTOMER_PRIORITY)));
                customerChallengeModel.setCustomerOrigin(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CUSTOMER_ORIGIN)));
                customerChallengeModel.setCustomerReason(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CUSTOMER_REASON)));
                customerChallengeModel.setCustomerDueDate(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_CUSTOMER_DUE_DATE)));
                customerChallengeModel.setCloseddate(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_CUSTOMER_CLOSED_ON)));
                customerChallengeModel.setCustomerInCharge(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CUSTOMER_INCHARGE)));
                customerChallengeModel.setCcMailId(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CUSTOMER_CC_MAIL_ID)));
                customerChallengeModel.setStatus(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CUSTOMER_STATUS)));
                customerChallengeModel.setCreatedBy(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CUSTOMER_CREATED_BY)));
                customerChallengeModel.setModifyBy(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CUSTOMER_MODIFY_BY)));
                customerChallengeModel.setCreatedTime(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_CUSTOMER_CREATED_TIME)));
                customerChallengeModel.setModifyTime(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.COLUMN_CUSTOMER_MODIFY_TIME)));
                customerChallengeModel.setSubject(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CUSTOMER_SUBJECT)));
                customerChallengeModel.setNote(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CUSTOMER_NOTE)));
                customerChallengeModel.setDescription(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CUSTOMER_DISCRIPTION)));
                customerChallengeModel.setInternalNote(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CUSTOMER_INTERNAL_NOTE)));
                customerChallengeModel.setCustomerFeedback(cursor.getString(cursor.getColumnIndex(DataBaseConstant.COLUMN_CUSTOMER_FEED_BACK)));
                challengeModelList.add(customerChallengeModel);
            } while (cursor.moveToNext());


        }

        return challengeModelList;
    }


}
