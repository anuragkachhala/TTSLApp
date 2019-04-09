package com.software.ttsl.Sql;

public class DataBaseConstant {


    //constant

    public static final String EMPLOYEES ="EMPLOYEES";
    public static final String INDUSTRIES ="INDUSTRIES";
    public static final String ACCOUNTS ="ACCOUNTS";
    public static final String SECTORS ="SECTORS";
    public static final String CONTACTS="CONTACTS";
    public static final String LEADS ="LEADS";
    public static final String DEPARTMENTS="DEPARTMENTS";


    // Database Name
    public static final String DATABASE_NAME = "TransportSystem.db";
    // Database Version
    static final int DATABASE_VERSION = 2;

    // port data table
    static final String TABLE_NAME_PORT = "port_data";

    // column port data table....
    static final String COLUMN_PORT_ID = "port_id";
    static final String COLUMN_PORT_CODE = "port_code";
    static final String COLUMN_PORT_CITY = "port_city";
    static final String COLUMN_PORT_COUNTRY = "port_country";

    // create table port data .....


    final static String CREATE_PORT_TABLE = "CREATE TABLE " + TABLE_NAME_PORT + " (" + COLUMN_PORT_ID + " INTEGER NOT NULL," + COLUMN_PORT_CODE + " TEXT NOT NULL," + COLUMN_PORT_CITY + " TEXT NOT NULL, " + COLUMN_PORT_COUNTRY + " TEXT NOT NULL " +

            "); ";

    // drop table port .......

    final static String DROP_PORT_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME_PORT;


    // get data from port

    //  ship tracking constraint data....
    final static String TABLE_TRACKING_CONSTRAINT = "tracking_constraint";

    final static String SELECT_TACKING_CONSTRAINT = " SELECT * FROM " + TABLE_TRACKING_CONSTRAINT;


    // column for tracking type...like(Container no.,HBL no.)
    final static String COLUMN_TRACKING_TYPE = "tracking_type";

    final static String COLUMN_TRACKING_NO_MAX_LENGTH = "tn_max_length";

    final static String COLUMN_TRACKING_NO_MIN_LENGTH = "tn_min_length";

    // column fo tracking no type like (numeric,alpha numeric)
    final static String COLUMN_TRACKING_NO_TYPE = "tn_type";

    final static String COLUMN_TRACKING_NO_START_WITH = "tn_start_with";

    final static String COLUMN_TRACKING_NO_START_WITH_LENGTH = "tn_start_with_length";

    final static String COLUMN_TRACKING_ID = "tn_id";

    // create table tracking constraint ......
    final static String CREATE_TABLE_TRACKING_CONSTRAINT = "CREATE TABLE " + TABLE_TRACKING_CONSTRAINT + " ( " + COLUMN_TRACKING_TYPE + " TEXT NOT NULL," + COLUMN_TRACKING_NO_MAX_LENGTH + " long NOT NULL," + COLUMN_TRACKING_NO_MIN_LENGTH + "  long NOT NULL," + COLUMN_TRACKING_NO_TYPE + " TEXT NOT NULL ," + COLUMN_TRACKING_NO_START_WITH + " TEXT," + COLUMN_TRACKING_NO_START_WITH_LENGTH + " TEXT NOT NULL, " + COLUMN_TRACKING_ID + " INTEGER NOT NULL " + ");";


    //invoice column

    final static String TABLE_PENDING_INVOICE = "pendingInvoice";
    //Column for pendingInvoice bill number
    final static String COLUMN_INVOICE_ID = "invoice_id";
    //column for pendingInvoice Amount
    final static String COLUMN_INVOICE_AMOUNT = "invoice_amount";
    final static String COLUMN_INVOICE_DUE_DATE = "invoice_due_date";
    final static String COLUMN_INVOICE_NUMBER = "invoice_number";
    final static String COLUMN_INVOICE_BOOKING_NUMBER = "invoice_booking_number";
    final static String COLUMN_INVOICE_MONTH_ID = "invoice_month_id";
    final static String COLUMN_INVOICE_MONTH = "invoice_month";
    final static String CREATE_TABLE_PENDING_INVOICE = "CREATE TABLE " + TABLE_PENDING_INVOICE + " ( " + COLUMN_INVOICE_ID + " TEXT NOT NULL," + COLUMN_INVOICE_DUE_DATE + " TEXT NOT NULL," + COLUMN_INVOICE_NUMBER + " TEXT NOT NULL," + COLUMN_INVOICE_BOOKING_NUMBER + " TEXT NOT NULL," + COLUMN_INVOICE_AMOUNT + " TEXT NOT NULL, " + COLUMN_INVOICE_MONTH + " TEXT NOT NULL, " + COLUMN_INVOICE_MONTH_ID + " TEXT NOT NULL " + ");";

    final static String SELECT_ALL_PENDING_INVOICES = "SELECT  * FROM " + TABLE_PENDING_INVOICE;

    final static String PENDING_INVOICE_SORT_BY_MONTH_ASC = "SELECT * FROM " + TABLE_PENDING_INVOICE + " ORDER BY " + COLUMN_INVOICE_MONTH + " ASC ;";
    final static String PENDING_INVOICE_SORT_BY_MONTH_DESC = "SELECT * FROM " + TABLE_PENDING_INVOICE + " ORDER BY " + COLUMN_INVOICE_MONTH + " DESC ;";
    final static String PENDING_INVOICE_SORT_BY_AMOUNT_ASC = "SELECT * FROM " + TABLE_PENDING_INVOICE + " ORDER BY " + COLUMN_INVOICE_AMOUNT + " ASC ;";
    final static String PENDING_INVOICE_SORT_BY_AMOUNT_DESC = "SELECT * FROM " + TABLE_PENDING_INVOICE + " ORDER BY " + COLUMN_INVOICE_AMOUNT + " DESC ;";

    final static String DROP_TABLE_PENDING_INVOICE = "DROP TABLE IF EXISTS  " + TABLE_PENDING_INVOICE;


    final static String TABLE_BILL_OF_LADING = "BillOfLading";

    final static String COLUMN_BILL_OF_LADING_BOOKING_NO = "booking_No";
    final static String COLUMN_BILL_OF_LADING_BOOKING_DATE = "booking_date";
    final static String COLUMN_BILL_OF_LADING_ETA = "eta";
    final static String COLUMN_BILL_OF_LADING_ETD = "etd";

    final static String CREATE_TABLE_BILL_OF_LADING = "CREATE TABLE " + TABLE_BILL_OF_LADING + " ( " + COLUMN_BILL_OF_LADING_BOOKING_NO + " TEXT NOT NULL ," + COLUMN_BILL_OF_LADING_BOOKING_DATE + " TEXT NOT NULL," + COLUMN_BILL_OF_LADING_ETD + " TEXT NOT NULL, " + COLUMN_BILL_OF_LADING_ETA + " TEXT NOT NULL " + ");";


    final static String SELECT_ALL_BILL_LIST = " SELECT * FROM " + TABLE_BILL_OF_LADING;

    final static String DROP_TABLE_BL_LIST = "DROP TABLE IF EXISTS  " + TABLE_BILL_OF_LADING;
    final static String TABLE_VESSEL = "VesselList";

    final static String COLUMN_VESSEL_ID = "vessel_id";
    final static String COLUMN_VESSEL_NAME = "vessel_name";

    final static String CREATE_TABLE_VESSLE_LIST = " CREATE TABLE " + TABLE_VESSEL + "(" + COLUMN_VESSEL_ID + " INTEGER PRIMARY KEY ," + COLUMN_VESSEL_NAME + " TEXT NOT NULL" + ");";

    final static String DROP_TABLE_VESSEL = "DROP TABLE IF EXISTS  " + TABLE_VESSEL;


    final static String TABLE_LEAD = "leadMaster";

    final static String COLUMN_LEAD_ID = "lead_id";
    final static String COLUMN_LEAD_IMAGE = "lead_image";
    final static String COLUMN_LEAD_OWNER = "lead_owner";
    //new column
    final static String COLUMN_LEAD_CODE ="lead_code";
    final static String COLUMN_LEAD_NAME="lead_name";
    final static String COLUMN_OWNER_ID="OWNER_ID";

    final static String COLUMN_LEAD_COMPANY = "lead_company_name";
    final static String COLUMN_LEAD_FIRST_NAME = "lead_first_name";
    final static String COLUMN_LEAD_LAST_NAME = "lead_last_name";
    final static String COLUMN_LEAD_EMAIL = "lead_email";
    final static String COLUMN_LEAD_TITLE = "lead_title";
    final static String COLUMN_LEAD_PHONE = "lead_phone";
    final static String COLUMN_LEAD_FAX = "lead_fax";
    final static String COLUMN_LEAD_MOBILE = "lead_mobile";
    final static String COLUMN_LEAD_WEB_SITE = "lead_website";
    final static String COLUMN_LEAD_SOURCE = "lead_source";
    //status ,industry,revenue,rating , city ,street , country  string to int
    final static String COLUMN_LEAD_STATUS = "lead_status";
    final static String COLUMN_LEAD_STATUS_ID = "lead_status_id";
    final static String COLUMN_LEAD_INDUSTRY_ID="lead_industry_id";
    final static String COLUMN_LEAD_RATING_ID="lead_rating_id";

    final static String COLUMN_LEAD_INDUSTRY = "lead_industry";
    final static String COLUMN_LEAD_EMPLOYEES = "lead_no_employees";
    final static String COLUMN_LEAD_REVENUE = "lead_revenue";
    final static String COLUMN_LEAD_RATING = "lead_rating";
    final static String COLUMN_LEAD_CREATED_BY = "lead_created_by";
    final static String COLUMN_LEAD_MODIFY_BY = "lad_modify_by";
    final static String COLUMN_LEAD_CREATED_TIME = "lead_created_time";
    final static String COLUMN_LEAD_MODIFIED_TIME = "lead_modified_time";
    final static String COLUMN_LEAD_EMAIL_OPT = "lead_email_opt";
    final static String COLUMN_LEAD_SKYPE_ID = "lead_skype_id";
    final static String COLUMN_LEAD_SOLUTION = "lead_solution";
    final static String COLUMN_LEAD_SECONDARY_MAIL = "lead_secondary_mail";
    final static String COLUMN_LEAD_TWITTER = "lead_twitter_id";
    final static String COLUMN_LEAD_DISCRIPTION = "lead_discription";
    final static String COLUMN_LEAD_STREET = "lead_street";
    final static String COLUMN_LEAD_CITY_ID="lead_city_id";
    final static String COLUMN_LEAD_CITY = "lead_city";
    final static String COLUMN_LEAD_STATE = "lead_state";
    final static String COLUMN_LEAD_STATE_ID="lead_state_id";
    final static String COLUMN_LEAD_ZIP_CODE = "lead_zip_code";
    final static String COLUMN_LEAD_COUNTRY = "lead_country";
    final static String COLUMN_LEAD_COUNTRY_ID ="lead_country_id";
    final static String COLUMN_LEAD_IS_SYNC = "lead_is_sync";


    final static String CREATE_TABLE_LEAD = " CREATE TABLE " + TABLE_LEAD + "( " +
            COLUMN_LEAD_ID + " INTEGER PRIMARY KEY, " +
            COLUMN_LEAD_OWNER + " TEXT, " +
            COLUMN_LEAD_CODE +" TEXT, "+
            COLUMN_LEAD_NAME +" TEXT, "+
            COLUMN_OWNER_ID+" INTEGER, "+
            COLUMN_LEAD_IMAGE + " BLOB, " +
            COLUMN_LEAD_IS_SYNC + " BOOLEAN, " +
            COLUMN_LEAD_FIRST_NAME + " TEXT, " +
            COLUMN_LEAD_LAST_NAME + " TEXT NOT NULL, " +
            COLUMN_LEAD_COMPANY + " TEXT NOT NULL, " +
            COLUMN_LEAD_TITLE + " TEXT, " +
            COLUMN_LEAD_EMAIL + " TEXT, " +
            COLUMN_LEAD_PHONE + " TEXT, " +
            COLUMN_LEAD_FAX + " TEXT, " +
            COLUMN_LEAD_MOBILE + " TEXT, " +
            COLUMN_LEAD_WEB_SITE + " TEXT, " +
            COLUMN_LEAD_SOURCE + " TEXT, " +
            COLUMN_LEAD_STATUS + " TEXT, " +
            COLUMN_LEAD_STATUS_ID+ " INTEGER, "+
            COLUMN_LEAD_INDUSTRY + " TEXT, " +
            COLUMN_LEAD_EMPLOYEES + " TEXT, " +
            COLUMN_LEAD_REVENUE + " TEXT, " +
            COLUMN_LEAD_RATING + " TEXT, " +
            COLUMN_LEAD_RATING_ID+" INTEGER, "+
            COLUMN_LEAD_EMAIL_OPT + " BOOLEAN, " +
            COLUMN_LEAD_SKYPE_ID + " TEXT, " +
            COLUMN_LEAD_DISCRIPTION + " TEXT, " +
            COLUMN_LEAD_SOLUTION + " TEXT, " +
            COLUMN_LEAD_SECONDARY_MAIL + " TEXT, " +
            COLUMN_LEAD_TWITTER + " TEXT, " +
            COLUMN_LEAD_CREATED_BY + " TEXT, " +
            COLUMN_LEAD_MODIFY_BY + " TEXT, " +
            COLUMN_LEAD_CREATED_TIME + " TEXT, " +
            COLUMN_LEAD_MODIFIED_TIME + " TEXT, " +
            COLUMN_LEAD_STREET + " TEXT, " +
            COLUMN_LEAD_CITY + " TEXT, " +
            COLUMN_LEAD_COUNTRY_ID+" INTEGER, "+
            COLUMN_LEAD_STATE + " TEXT, " +
            COLUMN_LEAD_STATE_ID+" INTEGER, "+
            COLUMN_LEAD_ZIP_CODE + " TEXT, " +
            COLUMN_LEAD_COUNTRY + " TEXT "
            + " );";


    final static String GET_LEAD_BY_ID = " SELECT * FROM " + TABLE_LEAD + " WHERE " + COLUMN_LEAD_ID + " = ";

    final static String SELECT_TABLE_LEAD = "SELECT * FROM " + TABLE_LEAD + " ORDER BY " + COLUMN_LEAD_CREATED_TIME + " ASC ;";

    final static String SELECT_UN_SYNC_LEAD = " SELECT * FROM " + TABLE_LEAD + " WHERE " + COLUMN_LEAD_IS_SYNC + " = 0";

    final static String TABLE_PROMOTIONAL_MAIL = "promotional_mail";
    final static String COLUMN_MAIL_ID = "mail_id";
    final static String COLUMN_MAIL_FROM = "mail_from";
    final static String COLUMN_MAIl_TO = "mail_to";
    final static String COLUMN_MAIL_COMMERCIAL = "mail_commercial";
    final static String COLUMN_MAIL_SEND_BY = "mail_send_by";
    final static String COLUMN_MAIL_SUBJECT = "mail_subject";
    final static String COLUMN_MAIL_COUNTRY = "mail_country";
    final static String COLUMN_MAIL_CATEGORY = "mail_category";
    final static String COLUMN_MAIL_ATTACHMENT = "mail_attachment";
    final static String COLUMN_MAIL_IS_ATTACH_IN_BODY = "mail_is_body";
    final static String COLUMN_MAIL_BODY = "mail_body";
    final static String COLUMN_MAIL_CREATED_BY = "mail_created_by";
    final static String COLUMN_MAIL_CREATED_TIME = "mail_created_time";
    final static String COLUMN_MAIL_CREATED_DATE = "mail_created_date";
    final static String COLUMN_MAIL_MODIFIED_DATE = "mail_modify";
    final static String COLUMN_MAIL_MODIFIED_BY = "mail_modify_data";
    final static String COLUMN_MAIL_MODIFIED_TIME = "mail_modified_time";


    final static String CREATE_TABLE_PROMOTIONAL_MAIL = " CREATE TABLE " + TABLE_PROMOTIONAL_MAIL + "(" +
            COLUMN_MAIL_ID + " INTEGER PRIMARY KEY, " +
            COLUMN_MAIL_FROM + " TEXT NOT NULL, " +
            COLUMN_MAIl_TO + " TEXT NOT NULL, " +
            COLUMN_MAIL_COMMERCIAL + " TEXT, " +
            COLUMN_MAIL_SEND_BY + " TEXT, " +
            COLUMN_MAIL_SUBJECT + " TEXT, " +
            COLUMN_MAIL_COUNTRY + " TEXT, " +
            COLUMN_MAIL_CATEGORY + " TEXT, " +
            COLUMN_MAIL_IS_ATTACH_IN_BODY + " BOOLEAN, " +
            COLUMN_MAIL_ATTACHMENT + " TEXT, " +
            COLUMN_MAIL_BODY + " TEXT, " +
            COLUMN_MAIL_CREATED_BY + "TEXT, " +
            COLUMN_MAIL_CREATED_TIME + " INTEGER, " +
            COLUMN_MAIL_CREATED_DATE + " INTEGER, " +
            COLUMN_MAIL_MODIFIED_BY + " TEXT," +
            COLUMN_MAIL_MODIFIED_DATE + " INTEGER, " +
            COLUMN_MAIL_MODIFIED_TIME + " INTEGER " +
            ")";


    final static String DROP_TABLE_MAIL = "DROP TABLE IF EXISTS  " + TABLE_PROMOTIONAL_MAIL;


    final static String TABLE_CUSTOMER_CHALLENGE = "customer_challenge";
    final static String COLUMN_CUSTOMER_ID = "customer_id";
    final static String COLUMN_CUSTOMER_NAME = "customer_name";
    final static String COLUMN_CUSTOMER_CONTACT = "customer_contact";
    final static String COLUMN_CUSTOMER_CONTACT_ID = "customer_contact_id";
    final static String COLUMN_CUSTOMER_LOG_DATE = "customer_lag_date";
    final static String COLUMN_CUSTOMER_PRIORITY = "customer_priority";
    final static String COLUMN_CUSTOMER_ORIGIN = "customer_origin";
    final static String COLUMN_CUSTOMER_REASON = "customer_reason";
    final static String COLUMN_CUSTOMER_DUE_DATE = "customer_due_date";
    final static String COLUMN_CUSTOMER_CLOSED_ON = "customer_closed_on";
    final static String COLUMN_CUSTOMER_INCHARGE = "customer_incharge";
    final static String COLUMN_CUSTOMER_CC_MAIL_ID = "customer_cc_mail";
    final static String COLUMN_CUSTOMER_STATUS = "customer_status";
    final static String COLUMN_CUSTOMER_SUBJECT = "customer_subject";
    final static String COLUMN_CUSTOMER_NOTE = "customer_note";
    final static String COLUMN_CUSTOMER_DISCRIPTION = "customer_discription";
    final static String COLUMN_CUSTOMER_INTERNAL_NOTE = "customer_internal_note";
    final static String COLUMN_CUSTOMER_CREATED_BY = "customer_created_by";
    final static String COLUMN_CUSTOMER_MODIFY_BY = "customer_modify_by";
    final static String COLUMN_CUSTOMER_CREATED_TIME = "customer_created_time";
    final static String COLUMN_CUSTOMER_MODIFY_TIME = "customer_modify_time";
    final static String COLUMN_CUSTOMER_FEED_BACK = "customer_feed_back";
    final static String COLUMN_CUSTOMER_IS_SYNC = "customer_is_sync";
    final static String COLUMN_CUSTOMER_LEAD = "customer_lead";
    final static String COLUMN_CUSTOMER_LEAD_ID = "customer_lead_id";

    final static String CREATE_TABLE_CUSTOMER_CHALLENGE = " CREATE TABLE " +
            TABLE_CUSTOMER_CHALLENGE + "( " +
            COLUMN_CUSTOMER_ID + " INTEGER PRIMARY KEY  NOT NULL," +
            COLUMN_CUSTOMER_NAME + " TEXT NOT NULL, " +
            COLUMN_CUSTOMER_IS_SYNC + " BOOLEAN, " +
            COLUMN_CUSTOMER_CONTACT + " TEXT NOT NULL, " +
            COLUMN_CUSTOMER_CONTACT_ID + " INTEGER, " +
            COLUMN_CUSTOMER_LEAD + " TEXT NOT NULL, " +
            COLUMN_CUSTOMER_LEAD_ID + " INTEGER, " +
            COLUMN_CUSTOMER_LOG_DATE + " TEXT," +
            COLUMN_CUSTOMER_PRIORITY + " TEXT, " +
            COLUMN_CUSTOMER_ORIGIN + " TEXT, " +
            COLUMN_CUSTOMER_REASON + " TEXT, " +
            COLUMN_CUSTOMER_DUE_DATE + " TEXT, " +
            COLUMN_CUSTOMER_CLOSED_ON + " TEXT," +
            COLUMN_CUSTOMER_INCHARGE + " TEXT, " +
            COLUMN_CUSTOMER_CC_MAIL_ID + " TEXT," +
            COLUMN_CUSTOMER_STATUS + " TEXT," +
            COLUMN_CUSTOMER_SUBJECT + " TEXT," +
            COLUMN_CUSTOMER_NOTE + " TEXT," +
            COLUMN_CUSTOMER_CREATED_BY + " TEXT, " +
            COLUMN_CUSTOMER_MODIFY_BY + " TEXT, " +
            COLUMN_CUSTOMER_CREATED_TIME + " INTEGER, " +
            COLUMN_CUSTOMER_MODIFY_TIME + " INTEGER, " +
            COLUMN_CUSTOMER_DISCRIPTION + " TEXT," +
            COLUMN_CUSTOMER_INTERNAL_NOTE + " TEXT, " +
            COLUMN_CUSTOMER_FEED_BACK + " TEXT " +

            ")";

    final static String DROP_TABLE_CUSTOMER_CHALLENGE = " DROP TABLE IF EXISTS  " + TABLE_CUSTOMER_CHALLENGE;
    final static String SELECT_UN_SYNC_CUSTOMER = " SELECT * FROM " + TABLE_CUSTOMER_CHALLENGE + " WHERE " + COLUMN_CUSTOMER_IS_SYNC + " = 0";


    final static String GET_CUSTOMER_CHALLENGE_BY_ID = " SELECT * FROM " + TABLE_CUSTOMER_CHALLENGE + " WHERE " + COLUMN_CUSTOMER_ID + " = ";

    final static String SELECT_CUSTOMER_CHALLENGE = "SELECT  * FROM " + TABLE_CUSTOMER_CHALLENGE + " ORDER BY " + COLUMN_CUSTOMER_CREATED_TIME + " DESC ";

    final static String TABLE_ADD_ACCOUNT = "add_account";


    final static String COLUMN_ACCOUNT_ID = "account_id";
    final static String COLUMN_ACCOUNT_OWNER = "account_owner";
    final static String COLUMN_ACCOUNT_RATING = "account_rating";
    final static String COLUMN_ACCOUNT_NAME = "account_name";
    final static String COLUMN_ACCOUNT_PHONE = "account_phone";
    final static String COLUMN_ACCOUNT_SITE = "account_site";
    final static String COLUMN_ACCOUNT_FAX = "account_fax";
    final static String COLUMN_ACCOUNT_PARENT_ACCOUNT = "account_parent";
    final static String COLUMN_PARENT_ACCOUNT_ID = "parent_account_id";
    final static String COLUMN_ACCOUNT_WEBSITE = "account_website";
    final static String COLUMN_ACCOUNT_ACCOUNT_NUMBER = "account_number";
    final static String COLUMN_ACCOUNT_TICKER_SYMBOL = "account_ticker_symbol";
    final static String COLUMN_ACCOUNT_ACCOUNT_TYPE = "account_type";
    final static String COLUMN_ACCOUNT_OWNERSHIP = "account_ownership";
    final static String COLUMN_ACCOUNT_INDUSTRY = "account_industry";
    final static String COLUMN_ACCOUNT_EMPLOYEE = "account_employees";
    final static String COLUMN_ACCOUNT_REVENUE = "account_revenue";
    final static String COLUMN_ACCOUNT_SIC_CODE = "account_sic_code";
    final static String COLUMN_ACCOUNT_BILLING_ADDRESS_STREET = "account_bill_address_street";
    final static String COLUMN_ACCOUNT_BILLING_ADDRESS_CITY = "account_bill_address_city";
    final static String COLUMN_ACCOUNT_BILLING_ADDRESS_STATE = "account_bill_address_state";
    final static String COLUMN_ACCOUNT_BILLING_ADDRESS_CODE = "account_bill_address_code";
    final static String COLUMN_ACCOUNT_BILLING_ADDRESS_COUNTRY = "account_bill_address_country";
    final static String COLUMN_ACCOUNT_SHIPPING_ADDRESS_STREET = "account_ship_address_street";
    final static String COLUMN_ACCOUNT_SHIPPING_ADDRESS_CITY = "account_ship_address_city";
    final static String COLUMN_ACCOUNT_SHIPPING_ADDRESS_STATE = "account_ship_address_state";
    final static String COLUMN_ACCOUNT_SHIPPING_ADDRESS_CODE = "account_ship_address_code";
    final static String COLUMN_ACCOUNT_SHIPPING_ADDRESS_COUNTRY = "account_ship_address_country";
    final static String COLUMN_ACCOUNT_DISCRIPTION = "account_discription";
    final static String COLUMN_ACCOUNT_CREATED_BY = "account_created_by";
    final static String COLUMN_ACCOUNT_MODIFY_BY = "account_modify_by";
    final static String COLUMN_ACCOUNT_MODIFY_TIME = "account_modify_time";
    final static String COLUMN_ACCOUNT_CREATED_TIME = "account_created_time";
    final static String COLUMN_ACCOUNT_IS_SYNC = "account_is_sync";


    final static String CREATE_TABLE_ADD_ACCOUNT = " CREATE TABLE " + TABLE_ADD_ACCOUNT + "(" +
            COLUMN_ACCOUNT_ID + " INTEGER PRIMARY KEY, " +
            COLUMN_ACCOUNT_IS_SYNC + " BOOLEAN, " +
            COLUMN_ACCOUNT_OWNER + " TEXT, " +
            COLUMN_ACCOUNT_RATING + " TEXT, " +
            COLUMN_ACCOUNT_NAME + " TEXT, " +
            COLUMN_ACCOUNT_PHONE + " TEXT, " +
            COLUMN_ACCOUNT_SITE + " TEXT, " +
            COLUMN_ACCOUNT_FAX + " TEXT, " +
            COLUMN_ACCOUNT_PARENT_ACCOUNT + " TEXT, " +
            COLUMN_PARENT_ACCOUNT_ID + " INTEGER ," +
            COLUMN_ACCOUNT_WEBSITE + " TEXT," +
            COLUMN_ACCOUNT_ACCOUNT_NUMBER + " INTEGER, " +
            COLUMN_ACCOUNT_TICKER_SYMBOL + " TEXT, " +
            COLUMN_ACCOUNT_ACCOUNT_TYPE + " TEXT, " +
            COLUMN_ACCOUNT_OWNERSHIP + " TEXT, " +
            COLUMN_ACCOUNT_INDUSTRY + " TEXT, " +
            COLUMN_ACCOUNT_EMPLOYEE + " TEXT, " +
            COLUMN_ACCOUNT_REVENUE + " TEXT, " +
            COLUMN_ACCOUNT_SIC_CODE + " TEXT, " +
            COLUMN_ACCOUNT_BILLING_ADDRESS_STREET + " TEXT, " +
            COLUMN_ACCOUNT_BILLING_ADDRESS_CITY + " TEXT, " +
            COLUMN_ACCOUNT_BILLING_ADDRESS_STATE + " TEXT, " +
            COLUMN_ACCOUNT_BILLING_ADDRESS_CODE + " TEXT, " +
            COLUMN_ACCOUNT_BILLING_ADDRESS_COUNTRY + " TEXT, " +
            COLUMN_ACCOUNT_SHIPPING_ADDRESS_STREET + " TEXT, " +
            COLUMN_ACCOUNT_SHIPPING_ADDRESS_CITY + " TEXT, " +
            COLUMN_ACCOUNT_SHIPPING_ADDRESS_STATE + " TEXT, " +
            COLUMN_ACCOUNT_SHIPPING_ADDRESS_CODE + " TEXT, " +
            COLUMN_ACCOUNT_SHIPPING_ADDRESS_COUNTRY + " TEXT, " +
            COLUMN_ACCOUNT_DISCRIPTION + " TEXT, " +
            COLUMN_ACCOUNT_MODIFY_BY + " TEXT, " +
            COLUMN_ACCOUNT_CREATED_BY + " TEXT, " +
            COLUMN_ACCOUNT_MODIFY_TIME + " TEXT, " +
            COLUMN_ACCOUNT_CREATED_TIME + " TEXT " +
            ") ;";


    final static String SELECT_TABLE_ACCOUNT = "SELECT * FROM " + TABLE_ADD_ACCOUNT + " ORDER BY " + COLUMN_ACCOUNT_NAME + " ASC ;";


    final static String DROP_TABLE_ADD_ACCOUNT = "++ DROP TABLE IF EXISTS  " + TABLE_ADD_ACCOUNT;

    final static String SELECT_UN_SYNC_ACCOUNT = " SELECT * FROM " + TABLE_ADD_ACCOUNT + " WHERE " + COLUMN_ACCOUNT_IS_SYNC + " = 0";

    final static String TABLE_ADD_TASK = "addTask";
    final static String COLUMN_ADD_TASK_ID = "task_id";
    final static String COLUMN_ADD_TASK_OWNER = "task_owner";
    final static String COLUMN_ADD_TASK_ACCOUNT = "task_account";
    final static String COLUMN_ADD_TASK_ACCOUNT_ID = "task_account_id";
    final static String COLUMN_ADD_TASK_CONTACT_ID = "task_contact_id";
    final static String COLUMN_ADD_TASK_CONTACT = "task_contact";
    final static String COLUMN_ADD_TASK_SUBJECT = "task_subject";
    final static String COLUMN_ADD_TASK_DUE_DATE = "task_due_date";
    final static String COLUMN_ADD_TASK_STATUS = "task_status";
    final static String COLUMN_ADD_TASK_PRIORITY = "task_priority";
    final static String COLUMN_ADD_TASK_SEND_NOTIFICATION_MAIL = "task_notification_mail";
    final static String COLUMN_ADD_TASK_DESCRIPTION = "task_description";
    final static String COLUMN_ADD_TASK_CREATED_BY = "task_created_by";
    final static String COLUMN_ADD_TASK_MODIFY_BY = "task_modify_by";
    final static String COLUMN_ADD_TASK_CREATED_DATE = "task_created_date";
    final static String COLUMN_ADD_TASK_MODIFY_DATE = "task_modify_date";
    final static String COLUMN_ADD_TASK_LEAD = "task_lead";
    final static String COLUMN_ADD_TASK_LEAD_ID = "task_lead_id";
    final static String COLUMN_ADD_TASK_IS_SYNC = "task_is_sync";


    final static String GET_TASK_BY_ID = " SELECT * FROM " + TABLE_ADD_TASK + " WHERE " + COLUMN_ADD_TASK_ID + " = ";
    final static String GET_TASK_BY_LEAD_ID = "SELECT * FROM " + TABLE_ADD_TASK + " WHERE " + COLUMN_ADD_TASK_LEAD_ID + " = ";
    final static String GET_TASK_BY_ACCOUNT_ID = "SELECT * FROM " + TABLE_ADD_TASK + " WHERE " + COLUMN_ADD_TASK_ACCOUNT_ID + " = ";

    final static String CREATE_TABLE_ADD_TASK = " CREATE TABLE " + TABLE_ADD_TASK + "( " +
            COLUMN_ADD_TASK_ID + " INTEGER PRIMARY KEY, " +
            COLUMN_ADD_TASK_OWNER + " TEXT, " +
            COLUMN_ADD_TASK_ACCOUNT + " TEXT, " +
            COLUMN_ADD_TASK_ACCOUNT_ID + " INTEGER, " +
            COLUMN_ADD_TASK_CONTACT + " TEXT, " +
            COLUMN_ADD_TASK_CONTACT_ID + " TEXT, " +
            COLUMN_ADD_TASK_SUBJECT + " TEXT, " +
            COLUMN_ADD_TASK_DUE_DATE + " INTEGER," +
            COLUMN_ADD_TASK_STATUS + " TEXT, " +
            COLUMN_ADD_TASK_PRIORITY + " TEXT, " +
            COLUMN_ADD_TASK_SEND_NOTIFICATION_MAIL + " BOOLEAN, " +
            COLUMN_ADD_TASK_MODIFY_BY + " TEXT," +
            COLUMN_ADD_TASK_CREATED_BY + " TEXT, " +
            COLUMN_ADD_TASK_CREATED_DATE + " INTEGER, " +
            COLUMN_ADD_TASK_DESCRIPTION + " TEXT, " +
            COLUMN_ADD_TASK_LEAD + " TEXT, " +
            COLUMN_ADD_TASK_LEAD_ID + " INTEGER, " +
            COLUMN_ADD_TASK_IS_SYNC + " BOOLEAN, " +
            COLUMN_ADD_TASK_MODIFY_DATE + " INTEGER " +
            ")";


    final static String SELECT_TABLE_TASK = "SELECT * FROM " + TABLE_ADD_TASK + " ORDER BY " + COLUMN_ADD_TASK_CREATED_DATE + " ASC ";

    final static String SELECT_UN_SYNC_TASK = " SELECT * FROM " + TABLE_ADD_TASK + " WHERE " + COLUMN_ADD_TASK_IS_SYNC + " = 0";

    final static String TABLE_ADD_CONTACT = "AddContact";

    final static String COLUMN_CONTACT_ID = "contact_id";
    final static String COLUMN_CONTACT_OWNER = "contact_owner";
    final static String COLUMN_CONTACT_NAME = "contact_name";
    final static String COLUMN_CONTACT_PHONE = "contact_phone";
    final static String COLUMN_CONTACT_LEAD_SOURCE = "contact_lead_source";
    final static String COLUMN_CONTACT_FIRST_NAME = "contact_first_name";
    final static String COLUMN_CONTACT_LAST_NAME = "contact_last_name";
    final static String COLUMN_CONTACT_EMAIL = "contact_email";
    final static String COLUMN_CONTACT_TITLE = "contact_title";
    final static String COLUMN_CONTACT_DEPARTMENT = "contact_department";
    final static String COLUMN_CONTACT_HOME_PHONE = "contact_home_phone";
    final static String COLUMN_CONTACT_OTHER_PHONE = "contact_other_phone";
    final static String COLUMN_CONTACT_ACCOUNT_NAME = "contact_account_name";
    final static String COLUMN_CONTACT_ACCOUNT_ID = "contact_account_id";
    final static String COLUMN_CONTACT_FAX = "contact_fax";
    final static String COLUMN_CONTACT_MOBILE = "contact_mobile";
    final static String COLUMN_CONTACT_DOB = "contact_dob";
    final static String COLUMN_CONTACT_ASSISTANT = "contact_assistant";
    final static String COLUMN_CONTACT_ASST_PHONE = "contact_asst_phone";
    final static String COLUMN_CONTACT_REPORT_TO = "contact_report_to";
    final static String COLUMN_CONTACT_EMAIL_OPT = "contact_email_opt";
    final static String COLUMN_CONTACT_SKYPE_ID = "contact_skype_id";
    final static String COLUMN_CONTACT_SOLUTION = "contact_solution";
    final static String COLUMN_CONTACT_SECONDARY_MAIL = "contact_secondary_mail";
    final static String COLUMN_CONTACT_TWITTER = "contact_twitter";
    final static String COLUMN_CONTACT_MAILING_STREET = "contact_mailing_street";
    final static String COLUMN_CONTACT_MAILING_CITY = "contact_mailing_city";
    final static String COLUMN_CONTACT_MAILING_CODE = "contact_mailing_code";
    final static String COLUMN_CONTACT_MAILING_COUNTRY = "contact_mailing_country";
    final static String COLUMN_CONTACT_MAILING_STATE = "contact_mailing_state";
    final static String COLUMN_CONTACT_OTHER_STREET = "contact_other_street";
    final static String COLUMN_CONTACT_OTHER_CITY = "contact_other_city";
    final static String COLUMN_CONTACT_OTHER_CODE = "contact_other_code";
    final static String COLUMN_CONTACT_OTHER_COUNTRY = "contact_other_country";
    final static String COLUMN_CONTACT_OTHER_STATE = "contact_other_state";
    final static String COLUMN_CONTACT_CREATED_BY = "contact_created_by";
    final static String COLUMN_CONTACT_MODIFIED_BY = "contact_modified_by";
    final static String COLUMN_CONTACT_CREATED_TIME = "contact_created_time";
    final static String COLUMN_CONTACT_MODIFIED_TIME = "contact_modified_time";
    final static String COLUMN_CONTACT_DESCRIPTION = "contact_description";
    final static String COLUMN_CONTACT_IS_SYNC = "contact_is_sync";

    final static String SELECT_TABLE_CONTACT = "SELECT * FROM " + TABLE_ADD_CONTACT + " ORDER BY " + COLUMN_CONTACT_NAME + " ASC ;";

    final static String CREATE_TABLE_ADD_CONTACT = " CREATE TABLE " + TABLE_ADD_CONTACT + "( " +
            COLUMN_CONTACT_ID + " INTEGER PRIMARY KEY, " +
            COLUMN_CONTACT_IS_SYNC + " BOOLEAN, " +
            COLUMN_CONTACT_OWNER + " TEXT, " +
            COLUMN_CONTACT_NAME + " TEXT, " +
            COLUMN_CONTACT_PHONE + " TEXT, " +
            COLUMN_CONTACT_LEAD_SOURCE + " TEXT, " +
            COLUMN_CONTACT_FIRST_NAME + " TEXT, " +
            COLUMN_CONTACT_LAST_NAME + " TEXT, " +
            COLUMN_CONTACT_EMAIL + " TEXT, " +
            COLUMN_CONTACT_TITLE + " TEXT, " +
            COLUMN_CONTACT_DEPARTMENT + " TEXT, " +
            COLUMN_CONTACT_HOME_PHONE + " TEXT, " +
            COLUMN_CONTACT_ACCOUNT_NAME + " TEXT, " +
            COLUMN_CONTACT_ACCOUNT_ID + " INTEGER, " +
            COLUMN_CONTACT_OTHER_PHONE + " TEXT, " +
            COLUMN_CONTACT_FAX + " TEXT, " +
            COLUMN_CONTACT_MOBILE + " TEXT, " +
            COLUMN_CONTACT_DOB + " TEXT, " +
            COLUMN_CONTACT_ASSISTANT + " TEXT, " +
            COLUMN_CONTACT_ASST_PHONE + " TEXT, " +
            COLUMN_CONTACT_REPORT_TO + " TEXT, " +
            COLUMN_CONTACT_EMAIL_OPT + " BOOLEAN, " +
            COLUMN_CONTACT_SECONDARY_MAIL + " TEXT, " +
            COLUMN_CONTACT_SKYPE_ID + " TEXT, " +
            COLUMN_CONTACT_SOLUTION + " TEXT, " +
            COLUMN_CONTACT_TWITTER + " TEXT, " +
            COLUMN_CONTACT_MAILING_STREET + " TEXT, " +
            COLUMN_CONTACT_MAILING_CITY + " TEXT, " +
            COLUMN_CONTACT_MAILING_CODE + " TEXT, " +
            COLUMN_CONTACT_MAILING_COUNTRY + " TEXT, " +
            COLUMN_CONTACT_OTHER_STATE + " TEXT, " +
            COLUMN_CONTACT_MAILING_STATE + " TEXT, " +
            COLUMN_CONTACT_OTHER_STREET + " TEXT, " +
            COLUMN_CONTACT_OTHER_CODE + " TEXT, " +
            COLUMN_CONTACT_OTHER_CITY + " TEXT, " +
            COLUMN_CONTACT_OTHER_COUNTRY + " TEXT, " +
            COLUMN_CONTACT_DESCRIPTION + " TEXT, " +
            COLUMN_CONTACT_CREATED_TIME + " INTEGER, " +
            COLUMN_CONTACT_CREATED_BY + " TEXT, " +
            COLUMN_CONTACT_MODIFIED_TIME + " INTEGER, " +
            COLUMN_CONTACT_MODIFIED_BY + " TEXT " +
            " ) ";


    final static String GET_CONTACT_BY_ID = " SELECT * FROM " + TABLE_ADD_CONTACT + " WHERE " + COLUMN_CONTACT_ID + " = ";

    final static String SELECT_UN_SYNC_CONTACT = " SELECT * FROM " + TABLE_ADD_CONTACT + " WHERE " + COLUMN_CONTACT_IS_SYNC + " = 0";

    final static String TABLE_ACCOUNT_TYPE = "Account_type";
    final static String COLUMN_ACCOUNT_TYPE_ID = " _id";
    final static String COLUMN_ACCOUNT_TYPE = " account_type";

    final static String CREATE_TABLE_ACCOUNT_TYPE = " CREATE TABLE " + TABLE_ACCOUNT_TYPE + " ( " +
            COLUMN_ACCOUNT_TYPE_ID + " INTEGER PRIMARY KEY, " +
            COLUMN_ACCOUNT_TYPE + " TEXT " +
            ") ";


    final static String DROP_TABLE_ACCOUNT_TYPE = " DROP TABLE IF EXISTS " + TABLE_ACCOUNT_TYPE;
    final static String SELECT_ACCOUNT_TYPE = " SELECT * FROM " + TABLE_ACCOUNT_TYPE;


    final static String GET_ACCOUNT_BY_ID = " SELECT * FROM " + TABLE_ADD_ACCOUNT + " WHERE " + COLUMN_ACCOUNT_ID + " = ";

    final static String COLUMN_DEAL_IS_SYNC = "deal_is_sync";
    final static String TABLE_ADD_DEAL = "addDeal";
    final static String COLUMN_DEAL_ID = "deal_id";
    final static String COLUMN_DEAL_OWNER = "deal_owner";
    final static String COLUMN_DEAL_AMOUNT = "deal_amount";
    final static String COLUMN_DEAL_NAME = "deal_name";
    final static String COLUMN_DEAL_DATE = "deal_date";
    final static String COLUMN_DEAL_ACCOUNT_NAME = "deal_account_name";
    final static String COLUMN_DEAL_ACCOUNT_ID = "deal_account_id";
    final static String COLUMN_DEAL_STATE = "deal_stage";
    final static String COLUMN_DEAL_TYPE = "deal_type";
    final static String COLUMN_DEAL_PROBABILITY = "deal_probability";
    final static String COLUMN_DEAL_NEXT_STEP = "deal_next_step";
    final static String COLUMN_DEAL_EXPECTED_REVENUE = "deal_expected";
    final static String COLUMN_DEAL_LEAD_SOURCE = "deal_lead_source";
    final static String COLUMN_DEAL_CAMPAIGN_SOURCE = "deal_campaign_source";
    final static String COLUMN_DEAL_CONTACT_ID = "deal_contact_id";
    final static String COLUMN_DEAL_CONTACT_NAME = "deal_contact_name";
    final static String COLUMN_DEAL_DESCRIPTION = "deal_description";
    final static String COLUMN_DEAL_CREATED_BY = "deal_created_by";
    final static String COLUMN_DEAL_MODIFY_BY = "deal_modify_by";
    final static String COLUMN_DEAL_CREATED_TIME = "deal_created_time";
    final static String COLUMN_DEAL_MODIFIED_TIME = "deal_modified_time";


    final static String CREATE_TABLE_DEAL = " CREATE TABLE " + TABLE_ADD_DEAL + "( " +
            COLUMN_DEAL_ID + " INTEGER PRIMARY KEY, " +
            COLUMN_DEAL_OWNER + " TEXT, " +
            COLUMN_DEAL_IS_SYNC + " BOOLEAN, " +
            COLUMN_DEAL_AMOUNT + " TEXT, " +
            COLUMN_DEAL_NAME + " TEXT, " +
            COLUMN_DEAL_DATE + " TEXT, " +
            COLUMN_DEAL_ACCOUNT_NAME + " TEXT, " +
            COLUMN_DEAL_ACCOUNT_ID + " INTEGER, " +
            COLUMN_DEAL_STATE + " TEXT, " +
            COLUMN_DEAL_TYPE + " TEXT, " +
            COLUMN_DEAL_PROBABILITY + " TEXT, " +
            COLUMN_DEAL_NEXT_STEP + " TEXT, " +
            COLUMN_DEAL_EXPECTED_REVENUE + " TEXT, " +
            COLUMN_DEAL_LEAD_SOURCE + " TEXT, " +
            COLUMN_DEAL_CAMPAIGN_SOURCE + " TEXT, " +
            COLUMN_DEAL_CONTACT_ID + " INTEGER, " +
            COLUMN_DEAL_CONTACT_NAME + " TEXT, " +
            COLUMN_DEAL_DESCRIPTION + " TEXT, " +
            COLUMN_DEAL_CREATED_BY + " TEXT, " +
            COLUMN_DEAL_MODIFY_BY + " TEXT, " +
            COLUMN_DEAL_CREATED_TIME + " INTEGER, " +
            COLUMN_DEAL_MODIFIED_TIME + " INTEGER " +

            ")";


    final static String GET_DEAL_BY_ID = " SELECT * FROM " + TABLE_ADD_DEAL + " WHERE " + COLUMN_DEAL_ID + " = ";

    final static String SELECT_UN_SYNC_DEAL = " SELECT * FROM " + TABLE_ADD_DEAL + " WHERE " + COLUMN_DEAL_IS_SYNC + " = 0";

    final static String GET_ALL_DEALS = "SELECT * FROM " + TABLE_ADD_DEAL;

    final static String DROP_TABLE_DEALS = " DROP TABLE IF EXIST " + TABLE_ADD_DEAL;


    final static String TABLE_SALES_BUDGET = "SALES_BUDGET";
    final static String COLUMN_BUDGET_IS_SYNC = "budget_is_sync";
    final static String COLUMN_BUDGET_ID = "budget_id";
    final static String COLUMN_BUDGET_YEAR = "budget_year";
    final static String COLUMN_BUDGET_SALESMAN_SECTOR = "budget_salesman_sector";
    final static String COLUMN_BUDGET_SALESMAN = "budget_salesman";
    final static String COLUMN_BUDGET_SECTOR = "budget_sector";
    final static String COLUMN_BUDGET_CURRENCY = "budget_currency";
    final static String COLUMN_BUDGET_ROE = "budget_roe";
    final static String COLUMN_BUDGET_NOTE = "budget_note";
    final static String COLUMN_BUDGET_CREATED_BY = "budget_created_by";
    final static String COLUMN_BUDGET_CREATED_TIME = "budget_created_time";
    final static String COLUMN_BUDGET_MODIFY_BY = "budget_modify_by";
    final static String COLUMN_BUDGET_MODIFY_TIME = "budget_modify_time";


    final static String CREATE_TABLE_SALES_BUDGET = " CREATE TABLE " + TABLE_SALES_BUDGET + "( " +
            COLUMN_BUDGET_ID + " INTEGER PRIMARY KEY, " +
            COLUMN_BUDGET_IS_SYNC + " BOOLEAN, " +
            COLUMN_BUDGET_YEAR + " TEXT, " +
            COLUMN_BUDGET_SALESMAN_SECTOR + " TEXT, " +
            COLUMN_BUDGET_SECTOR + " TEXT, " +
            COLUMN_BUDGET_SALESMAN + " TEXT, " +
            COLUMN_BUDGET_CURRENCY + " TEXT, " +
            COLUMN_BUDGET_ROE + " TEXT, " +
            COLUMN_BUDGET_NOTE + " TEXT, " +
            COLUMN_BUDGET_CREATED_BY + " TEXT, " +
            COLUMN_BUDGET_MODIFY_BY + " TEXT, " +
            COLUMN_BUDGET_CREATED_TIME + " INTEGER, " +
            COLUMN_BUDGET_MODIFY_TIME + " INTEGER " +
            ")";

    final static String SELECT_TABLE_SALES_BUDGET = "SELECT * FROM " + TABLE_SALES_BUDGET + " ORDER BY " + COLUMN_BUDGET_YEAR + " ASC ;";

    final static String SELECT_UN_SYNC_SALES_BUDGET = " SELECT * FROM " + TABLE_SALES_BUDGET + " WHERE " + COLUMN_BUDGET_IS_SYNC + " = 0";
    final static String GET_SALES_BUDGET = " SELECT * FROM " + TABLE_SALES_BUDGET + " WHERE " + COLUMN_BUDGET_ID + " = ";


    final static String TABLE_ADD_EVENT = "addEvent";
    final static String COLUMN_EVENT_ID = "event_id";
    final static String COLUMN_EVENT_IS_SYNC = "event_is_sync";
    final static String COLUMN_EVENT_TITLE = "event_title";
    final static String COLUMN_EVENT_LOCATION = "event_location";
    final static String COLUMN_EVENT_IS_ALL_DAY = "is_event_all_day";
    final static String COLUMN_EVENT_FROM = "event_from";
    final static String COLUMN_EVENT_TO = "event_to";
    final static String COLUMN_EVENT_FROM_TIME = "event_from_time";
    final static String COLUMN_EVENT_TO_TIME = "event_from_to_time";
    final static String COLUMN_EVENT_HOST = "event_host";
    final static String COLUMN_EVENT_PARTICIPANTS = "event_from_participants";
    final static String COLUMN_EVENT_ACCOUNT_NAME = "event_account_name";
    final static String COLUMN_EVENT_CONTACT_NAME = "event_contact_name";
    final static String COLUMN_EVENT_LEAD_NAME = "event_lead_name";
    final static String COLUMN_EVENT_ACCOUNT_ID = "event_account_id";
    final static String COLUMN_EVENT_CONTACT_ID = "event_contact_id";
    final static String COLUMN_EVENT_LEAD_ID = "event_lead_id";
    final static String COLUMN_EVENT_CREATED_BY = "event_created_by";
    final static String COLUMN_EVENT_MODIFIED_BY = "event_modified_by";
    final static String COLUMN_EVENT_CREATED_TIME = "event_created_time";
    final static String COLUMN_EVENT_MODIFIED_TIME = "event_modified_time";
    final static String COLUMN_EVENT_DESCRIPTION = "event_description";

    final static String CREATE_TABLE_ADD_EVENT = " create table " + TABLE_ADD_EVENT + "( " +
            COLUMN_EVENT_ID + " INTEGER PRIMARY KEY, " +
            COLUMN_EVENT_TITLE + " TEXT, " +
            COLUMN_EVENT_IS_SYNC + " BOOLEAN, " +
            COLUMN_EVENT_LOCATION + " TEXT, " +
            COLUMN_EVENT_IS_ALL_DAY + " BOOLEAN ," +
            COLUMN_EVENT_FROM + " INTEGER, " +
            COLUMN_EVENT_TO + " INTEGER, " +
            COLUMN_EVENT_FROM_TIME + " INTEGER ," +
            COLUMN_EVENT_TO_TIME + " INTEGER, " +
            COLUMN_EVENT_HOST + " TEXT, " +
            COLUMN_EVENT_PARTICIPANTS + " INTEGER, " +
            COLUMN_EVENT_ACCOUNT_NAME + " TEXT, " +
            COLUMN_EVENT_CONTACT_NAME + " TEXT, " +
            COLUMN_EVENT_ACCOUNT_ID + " INTEGER, " +
            COLUMN_EVENT_CONTACT_ID + " INTEGER, " +
            COLUMN_EVENT_CREATED_TIME + " INTEGER, " +
            COLUMN_EVENT_CREATED_BY + " TEXT, " +
            COLUMN_EVENT_MODIFIED_BY + " TEXT, " +
            COLUMN_EVENT_MODIFIED_TIME + " INTEGER, " +
            COLUMN_EVENT_LEAD_ID + " INTEGER, " +
            COLUMN_EVENT_LEAD_NAME + " TEXT, " +
            COLUMN_EVENT_DESCRIPTION + " TEXT " +

            ")";

    final static String GET_EVENT_BY_LEAD_ID = "SELECT * FROM " + TABLE_ADD_EVENT + " WHERE " + COLUMN_EVENT_LEAD_ID + " = ";

    final static String SELECT_UN_SYNC_EVENT = " SELECT * FROM " + TABLE_ADD_EVENT + " WHERE " + COLUMN_EVENT_IS_SYNC + " = 0";

    final static String TABLE_PARTICIPANTS = "add_participants";
    final static String COLUMN_PARTICIPANTS_ID = "participant_id";

    final static String COLUMN_PARTICIPANTS_EVENT_ID = "participants_event_id";
    final static String COLUMN_PARTICIPANTS_CONSTANT = "participants_constant";
    final static String COLUMN_PARTICIPANTS_NAME = "participants_name";
    final static String COLUMN_PARTICIPANTS_EMAIL = "participants_email";

    final static String CREATE_TABLE_PARTICIPANTS = " CREATE TABLE " + TABLE_PARTICIPANTS + " ( " +
            COLUMN_PARTICIPANTS_ID + " INTEGER, " +
            COLUMN_PARTICIPANTS_EVENT_ID + " INTEGER, " +
            COLUMN_PARTICIPANTS_CONSTANT + " TEXT, " +
            COLUMN_PARTICIPANTS_NAME + " TEXT, " +
            COLUMN_PARTICIPANTS_EMAIL + " TEXT " +
            ") ";


    final static String SELECT_TABLE_EVENT = " SELECT * FROM " + TABLE_ADD_EVENT + " ORDER BY " + COLUMN_EVENT_FROM + " ASC ";

    final static String SELECT_EVENT_BY_ID = " SELECT * FROM " + TABLE_ADD_EVENT + " WHERE " + COLUMN_EVENT_ID + " = ";


    final static String SELECT_EVENT_BY_CONTACT_ID = " SELECT * FROM " + TABLE_ADD_EVENT + " WHERE " + COLUMN_EVENT_CONTACT_ID + " = ";


    final static String SELECT_PARTICIPANTS = "SELECT * FROM " + TABLE_PARTICIPANTS + " WHERE " + COLUMN_PARTICIPANTS_EVENT_ID + " = " + " ORDER BY " + COLUMN_PARTICIPANTS_NAME + " ASC ";


    final static String TABLE_ADD_CALL = "add_call";
    final static String COLUMN_CALL_IS_SYNC = "call_is_sync";
    final static String COLUMN_CALL_ID = "call_id";
    final static String COLUMN_CALL_TITLE = "call_title";
    final static String COLUMN_CALL_SUBJECT = "call_subject";
    final static String COLUMN_CALL_CONTACT = "call_contact";
    final static String COLUMN_CALL_CONTACT_ID = "call_contact_id";
    final static String COLUMN_CALL_ACCOUNT = "call_account";
    final static String COLUMN_CALL_ACCOUNT_ID = "call_account_id";
    final static String COLUMN_CALL_CREATED_BY = "call_created_by";
    final static String COLUMN_CALL_MODIFIED_BY = "call_modified_by";
    final static String COLUMN_CALL_CREATED_TIME = "call_created_time";
    final static String COLUMN_CALL_MODIFIED_TIME = "call_modified_time";
    final static String COLUMN_CALL_PURPOSE = "call_purpose";
    final static String COLUMN_CALL_TYPE = "call_type";
    final static String COLUMN_CALL_START_DATE = "call_start_date";
    final static String COLUMN_CALL_START_TIME = "call_start_time";
    final static String COLUMN_CALL_RESULT = "call_result";
    final static String COLUMN_CALL_DURATION = "call_duration";
    final static String COLUMN_CALL_DESCRIPTION = "call_description";
    final static String COLUMN_CALL_LEAD_ID = "call_lead_id";
    final static String COLUMN_CALL_LEAD_NAME = "call_lead_name";

    final static String CREATE_TABLE_CALL = " CREATE TABLE " + TABLE_ADD_CALL + " ( " +
            COLUMN_CALL_ID + " INTEGER PRIMARY KEY, " +
            COLUMN_CALL_IS_SYNC + " BOOLEAN, " +
            COLUMN_CALL_TITLE + " TEXT, " +
            COLUMN_CALL_SUBJECT + " TEXT, " +
            COLUMN_CALL_CONTACT + " TEXT, " +
            COLUMN_CALL_CONTACT_ID + " INTEGER, " +
            COLUMN_CALL_ACCOUNT + " TEXT, " +
            COLUMN_CALL_ACCOUNT_ID + " INTEGER, " +
            COLUMN_CALL_CREATED_BY + " TEXT, " +
            COLUMN_CALL_MODIFIED_BY + " TEXT, " +
            COLUMN_CALL_CREATED_TIME + " INTEGER, " +
            COLUMN_CALL_MODIFIED_TIME + " INTEGER, " +
            COLUMN_CALL_PURPOSE + " TEXT, " +
            COLUMN_CALL_TYPE + " TEXT, " +
            COLUMN_CALL_DURATION + " INTEGER, " +
            COLUMN_CALL_START_DATE + " INTEGER, " +
            COLUMN_CALL_START_TIME + " INTEGER, " +
            COLUMN_CALL_DESCRIPTION + " TEXT, " +
            COLUMN_CALL_LEAD_ID + " INTEGER, " +
            COLUMN_CALL_LEAD_NAME + " TEXT, " +
            COLUMN_CALL_RESULT + " TEXT " +
            ")";


    final static String SELECT_TABLE_CALL = "SELECT * FROM " + TABLE_ADD_CALL + " ORDER BY " + COLUMN_CALL_START_DATE + " ASC ;";
    final static String SELECT_CALL_BY_ID = " SELECT * FROM " + TABLE_ADD_CALL + " WHERE " + COLUMN_CALL_ID + " = ";
    final static String SELECT_UN_SYNC_CALL = " SELECT * FROM " + TABLE_ADD_CALL + " WHERE " + COLUMN_CALL_IS_SYNC + " = 0";

    final static String GET_CALL_BY_LEAD_ID = "SELECT * FROM " + TABLE_ADD_CALL + " WHERE " + COLUMN_CALL_LEAD_ID + " = ";

    final static String TABLE_IMAGE_MASTER = "ImageMaster";
    final static String COLUMN_IMAGE_ID = "imageId";
    final static String COLUMN_IMAGE_FILE = "imageFile";

    final static String CREATE_TABLE_IMAGE_MASTER = "CREATE TABLE " + TABLE_IMAGE_MASTER + " ( " +
            COLUMN_IMAGE_ID + " INTEGER, " +
            COLUMN_IMAGE_FILE + " TEXT  " +
            ")";

    final static String SELECT_IMAGE = "SELECT * FROM " + TABLE_IMAGE_MASTER + " WHERE " + COLUMN_IMAGE_ID + " = ";


    final static String MASTER_TABLE_DROP_DOWN = "MasterTableDropDown";
    final static String COLUMN_KEY = "KEY_ID";
    final static String COLUMN_DROP_DOWN_VALUE = "KEY_VALUE";
    final static String COLUMN_KEY_CONSTANT = "KEY_CONSTANT";

    final static String CREATE_TABLE_DROP_DOWN_MASTER = "CREATE TABLE " + MASTER_TABLE_DROP_DOWN + " ( " +
            COLUMN_KEY + " INTEGER, " +
            COLUMN_DROP_DOWN_VALUE + " TEXT, " +
            COLUMN_KEY_CONSTANT + " TEXT " +
            ")";


    final static String TABLE_DROP_DOWN_CONSTANT = "TABLE_DROP_DOWN_CONSTANT";
    final static String COLUMN_KEY_DROP_CONSTANT= "COLUMN_KEY";
    final static String COLUMN_KEY_CONSTANT_VALUE="COLUMN_KEY_CONSTANT_VALUE";

    final static String SELECT_DROP_DOWN = "SELECT * FROM " + MASTER_TABLE_DROP_DOWN  + " ORDER BY " + COLUMN_ADD_TASK_CREATED_DATE + " ASC ";



}


