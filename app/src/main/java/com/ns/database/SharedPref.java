package com.ns.database;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.ns.model.SecondaryUser.PrimaryUsersBean;
import com.ns.model.TCDataBean;
import com.ns.utils.UiConstants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SharedPref {

    private static SharedPref sSharedPref;

    private static SharedPreferences sSharedPreferences;
    private static SharedPreferences.Editor mEditor;

    public SharedPref(Context context) {
        sSharedPreferences = context.getSharedPreferences("stellerjet", Context.MODE_PRIVATE);
        mEditor = sSharedPreferences.edit();
    }

    public static SharedPref getSharedPreferences(Context context) {
        if (sSharedPref == null) {
            sSharedPref = new SharedPref(context);
        }
        return sSharedPref;
    }


    public void setUserData(String name, String email, String phone) {
        mEditor.putString(UiConstants.USER_NAME, name);
        mEditor.putString(UiConstants.USER_EMAIL, email);
        mEditor.putString(UiConstants.USER_PHONE, phone);
        mEditor.commit();
    }


    public void setUserType(String user, String userType) {
        mEditor.putString("user", user);
        mEditor.putString("userType", userType);
        mEditor.commit();
    }

    public String getUserName() {
        return sSharedPreferences.getString(UiConstants.USER_NAME, null);
    }

    public String getUserPhone() {
        return sSharedPreferences.getString(UiConstants.USER_PHONE, null);
    }

    public String getUserEmail() {
        return sSharedPreferences.getString(UiConstants.USER_EMAIL, null);
    }


    public String getUser() {
        return sSharedPreferences.getString("user", null);
    }

    public String getUserType() {
        return sSharedPreferences.getString("userType", null);
    }

    public void savePassCode(String pinCode) {
        mEditor.putString(UiConstants.PREFERENCES_PASSCODE, pinCode);
        mEditor.apply();

    }

    public String getPassCode() {
        return sSharedPreferences.getString(
                UiConstants.PREFERENCES_PASSCODE, ""
        );
    }

    public void saveLoginStatus(boolean isPresent) {
        mEditor.putBoolean(UiConstants.PREFERENCES_IS_LOGGED_IN, isPresent);
        mEditor.apply();
    }

    public void saveSeatCount(Integer seatCount) {
        mEditor.putInt(UiConstants.PREFERENCES_SEAT_COUNT, seatCount);
        mEditor.apply();
    }

    public int getSeatCount() {
        return sSharedPreferences.getInt(UiConstants.PREFERENCES_SEAT_COUNT, 0);
    }

    public void saveMembershipType(String membershipType) {
        mEditor.putString(UiConstants.PREFERENCES_USER_MEMBERSHIP_TYPE, membershipType);
        mEditor.apply();
    }

    public void saveSeatCost(Integer seatCost) {
        mEditor.putInt(UiConstants.PREFERENCES_SEAT_COST, seatCost);
        mEditor.apply();
    }

    public int getSeatCost() {
        return sSharedPreferences.getInt(UiConstants.PREFERENCES_SEAT_COST, 0);
    }


    public void saveToken(String token, String re_token) {
        mEditor.putString(UiConstants.PREFERENCES_TOCKEN, token);
        mEditor.putString(UiConstants.PREFERENCES_RE_TOCKEN, re_token);
        mEditor.commit();
    }

    public String getTocken() {
        return sSharedPreferences.getString(UiConstants.PREFERENCES_TOCKEN, null);
    }

    public String getReTocken() {
        return sSharedPreferences.getString(UiConstants.PREFERENCES_RE_TOCKEN, null);
    }



    public void setHelloAndAgainMessage(int no) {
        mEditor.putInt("hello_again_msg", no);
        mEditor.apply();
    }

    public int getHelloAndAgainMessage() {
        return sSharedPreferences.getInt("hello_again_msg", 0);
    }

    public void setUserFirstTimeStatus(boolean status) {
        mEditor.putBoolean("user_first_time", status);
        mEditor.apply();
    }

    public boolean getUserFirstTimeStatus() {
        return sSharedPreferences.getBoolean("user_first_time", false);
    }


    public void clearAllSharedPreferencesData() {

    }


    // Customer support number
    public void saveCustomeHelpPhoneNumber(String helpPhoneNumber) {
        mEditor.putString(UiConstants.CUSTOME_HELP_PHONE_NUMBER, helpPhoneNumber);
        mEditor.apply();
    }

    public String getCustomeHelpPhoneNumber() {
        return sSharedPreferences.getString(UiConstants.CUSTOME_HELP_PHONE_NUMBER, null);
    }


    // Member ship details
    public void setMembershipDdetails(String prepaid_terms, int mid) {
        mEditor.putString(UiConstants.MEMBERSHIP_DETAILS, prepaid_terms);
        mEditor.putInt(UiConstants.MEMBERSHIP_ID, mid);
        mEditor.apply();
    }

    public String getMembershipPreparedTerms() {
        return sSharedPreferences.getString(UiConstants.MEMBERSHIP_DETAILS, null);
    }

    public int getMembershipId() {
        return sSharedPreferences.getInt(UiConstants.MEMBERSHIP_ID, 0);
    }


    public void clearData() {
        mEditor.clear();
        mEditor.commit();
    }


    public void setPrimaryUser(ArrayList<PrimaryUsersBean> favorites) {

        Gson gson = new Gson();
        String jsonUser = gson.toJson(favorites);

        mEditor.putString(UiConstants.SECONDERY_USER, jsonUser);
        mEditor.commit();
    }

    public ArrayList<PrimaryUsersBean> getPrimaryUser() {

        List<PrimaryUsersBean> favorites;

        String jsonUser = sSharedPreferences.getString(UiConstants.SECONDERY_USER, null);
        Gson gson = new Gson();
        PrimaryUsersBean[] favoriteItems = gson.fromJson(jsonUser, PrimaryUsersBean[].class);

        favorites = Arrays.asList(favoriteItems);
        favorites = new ArrayList<PrimaryUsersBean>(favorites);

        return (ArrayList<PrimaryUsersBean>) favorites;
    }


    public void setSinglePrimaryId(int pid, int pos) {
        mEditor.putInt(UiConstants.PRIMARY_USER_ID, pid);
        mEditor.putInt(UiConstants.PRIMARY_USER_POS, pos);
        mEditor.apply();
    }

    public int getSinglePrimaryId() {
        return sSharedPreferences.getInt(UiConstants.PRIMARY_USER_ID, 0);
    }

    public int getSinglePrimaryPos() {
        return sSharedPreferences.getInt(UiConstants.PRIMARY_USER_POS, -1);
    }


    public void setTCUrl(ArrayList<TCDataBean> tcUrl) {

        Gson gson = new Gson();
        String jsonUser = gson.toJson(tcUrl);

        mEditor.putString(UiConstants.STATIC_TC_URL, jsonUser);
        mEditor.commit();
    }

    public ArrayList<TCDataBean> getTCUrl() {

        List<TCDataBean> favorites;

        String jsonUser = sSharedPreferences.getString(UiConstants.STATIC_TC_URL, null);
        Gson gson = new Gson();
        TCDataBean[] favoriteItems = gson.fromJson(jsonUser, TCDataBean[].class);

        favorites = Arrays.asList(favoriteItems);
        favorites = new ArrayList<TCDataBean>(favorites);

        return (ArrayList<TCDataBean>) favorites;
    }


    /// Purchase
    public void savePurchaseId(int id) {
        mEditor.putInt(UiConstants.PURCHASE_PURCHASE_ID, id);
        mEditor.apply();
    }

    public int getPurchaseId() {
        return sSharedPreferences.getInt(UiConstants.PURCHASE_PURCHASE_ID, 0);
    }


    public void setFeedBackDetails(int status){
        mEditor.putInt("status", status);
        mEditor.apply();
    }

    public int getFeedBackDeatils() {
        return sSharedPreferences.getInt("status",0);
    }

    public void setPickUpDropUpkDetails(int status){
        mEditor.putInt("status", status);
        mEditor.apply();
    }

    public int getPickUpDropUpDeatils() {
        return sSharedPreferences.getInt("status",0);
    }





    public void setDiningkDetails(int status, int position){
        mEditor.putInt("status", status);
        mEditor.putInt("pos", position);
        mEditor.apply();
    }


    public void setDiningkDetailsTest(int status){
        mEditor.putInt("statustest", status);
        mEditor.apply();
    }
    public int getDiningDeatils() {
        return sSharedPreferences.getInt("statustest",0);
    }

    public  List<Integer> getDiningSelectList() {
        List<Integer> list = new ArrayList<>();

        list.add(sSharedPreferences.getInt("status",0));
        list.add(sSharedPreferences.getInt("pos",0));
        return list;
    }

    public void saveSelectdFoodCategories(ArrayList<String> list){

            Gson gson = new Gson();
            String jsonUser = gson.toJson(list);

            mEditor.putString("select_food_categories", jsonUser);
            mEditor.commit();

    }

    public ArrayList<String> getSelectdFoodCategories() {

        List<String> foodList;

        String jsonUser = sSharedPreferences.getString("select_food_categories", null);
        Gson gson = new Gson();
        String[] favoriteItems = gson.fromJson(jsonUser, String[].class);

        foodList = Arrays.asList(favoriteItems);
        foodList = new ArrayList<String>(foodList);

        return (ArrayList<String>) foodList;
    }


}
