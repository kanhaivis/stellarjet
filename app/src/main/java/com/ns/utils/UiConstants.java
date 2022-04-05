package com.ns.utils;

public class UiConstants {

    public static final int REALM_SCHEMA_VERSION = 0;

    /**
     * String constant for SharedPreferences for User Name
     */
    public static final String USER_NAME = "name";
    /**
     * String constant for SharedPreferences for User Email
     */
    public static final String USER_EMAIL = "email";
    /**
     * String constant for SharedPreferences for User Phone
     */
    public static final String USER_PHONE = "phone";

    /**
     * String constant for SharedPreferences for Passcode
     */
    public static final String PREFERENCES_PASSCODE = "PassCode";

    /**
     * String constant for SharedPreferences for login status
     */
    public static final String PREFERENCES_IS_LOGGED_IN = "IsLoggedIn";
    /**
     * String constant for SharedPreferences subscription seats count
     */
    public static final String PREFERENCES_SEAT_COUNT = "seatCount";

    /**
     * String constant for SharedPreferences membership user type(pay_as_u_go/subscription)
     */
    public static final String PREFERENCES_USER_MEMBERSHIP_TYPE = "membershipUserType";
    /**
     * String constant for SharedPreferences membership user type - pay_as_u_go
     */
    public static final String PREFERENCES_MEMBERSHIP_PAY_AS_U_GO = "PayAsYouGo";
    /**
     * String constant for SharedPreferences membership user type - subscription
     */
    public static final String PREFERENCES_MEMBERSHIP_SUBSCRIPTION = "Subscription";
    /**
     * String constant for SharedPreferences individual seat cost
     */
    public static final String PREFERENCES_SEAT_COST = "seatCost";

    /**
     * String constant for SharedPreferences Tocken
     */
    public static final String PREFERENCES_TOCKEN = "token";
    public static final String PREFERENCES_RE_TOCKEN = "refresh_token";





    public static final String CUSTOME_HELP_PHONE_NUMBER = "helpphone_number";

    public static final String MEMBERSHIP_ID = "membership_id";
    public static final String MEMBERSHIP_DETAILS = "membership_details";


    public static final String SECONDERY_USER = "seconderyuser";


    public static final String STATIC_TC_URL = "static_tc";


    public static final String FLIGHT_FROM_NAME = "from_name";
    public static final String FLIGHT_FROM_ID = "from_id";
    public static final String FLIGHT_TO_NAME = "to_name";
    public static final String FLIGHT_TO_ID = "to_id";


    public static final String PRIMARY_USER_ID = "primaryuserid";
    public static final String PRIMARY_USER_POS = "primaryuserpos";

    /**
     * String constant for Flight day
     */
    public static final String FLIGHT_DAY = "90";


    public static final String NO_NETWORK = "No Network";


    /**
     * Validation constant values
     */
    public static final int SUCCESS = 0;
    public static final int NAME_EMPTY = 1;
    public static final int PHONE_EMPTY = 2;
    public static final int PHONE_NOT_LESSTHEN_10 = 3;
    public static final int EMAIL_EMPTY = 4;
    public static final int EMAIL_WRONG_FORMATE = 5;
    public static final int ADDRESSFULL = 6;
    public static final int ADDRESSTAG = 7;
    public static final int HOUSEFLAT = 8;


    // External Folder name
    public static final String FOLDER_NAME = "Stellarjets";


    //  Fragment open milisecond
    public static final int DELAYMILLISECOND = 200;


    // Pagination


    /// Purchase
    public static final String PURCHASE_PURCHASE_ID = "PurchaseId";


    // PickUp DropLocation
    public static final String PICKUP = "pick";
    public static final String DROPUP = "drop";


}
