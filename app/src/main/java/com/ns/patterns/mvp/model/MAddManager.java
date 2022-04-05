package com.ns.patterns.mvp.model;

import android.text.TextUtils;
import android.util.Patterns;

import com.ns.utils.UiConstants;

public class MAddManager implements IMAddManager {

    private String name;
    private String phone;
    private String emai;

    public MAddManager(String name, String phone, String emai) {
        this.name = name;
        this.phone = phone;
        this.emai = emai;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getPhone() {
        return phone;
    }

    @Override
    public String getEmail() {
        return emai;
    }

    @Override
    public int isValiDate() {

        if (TextUtils.isEmpty(getName())) {
            return UiConstants.NAME_EMPTY;
        } else if (TextUtils.isEmpty(getPhone())) {
            return UiConstants.PHONE_EMPTY;
        } else if (getPhone().length() < 10) {
            return UiConstants.PHONE_NOT_LESSTHEN_10;
        } else if (TextUtils.isEmpty(getEmail())) {
            return UiConstants.EMAIL_EMPTY;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(getEmail()).matches()) {
            return UiConstants.EMAIL_WRONG_FORMATE;
        }

        return UiConstants.SUCCESS;
    }
}
