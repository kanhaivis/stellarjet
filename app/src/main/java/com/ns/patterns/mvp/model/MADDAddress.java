package com.ns.patterns.mvp.model;

import android.text.TextUtils;
import android.util.Patterns;

import com.ns.utils.UiConstants;

public class MADDAddress implements IADDAddress {

    private String fullAddress;
    private String houseFlat;
    private String addressTag;

    public MADDAddress(String fullAddress, String houseFlat, String addressTag) {
        this.fullAddress = fullAddress;
        this.houseFlat = houseFlat;
        this.addressTag = addressTag;
    }

    @Override
    public String getFullAddress() {
        return fullAddress;
    }

    @Override
    public String getHouseFlat() {
        return houseFlat;
    }

    @Override
    public String getAddressTag() {
        return addressTag;
    }

    @Override
    public int isValiDate() {
        if (TextUtils.isEmpty(getFullAddress())) {
            return UiConstants.ADDRESSFULL;
        } else if (TextUtils.isEmpty(getHouseFlat())) {
            return UiConstants.HOUSEFLAT;
        }  else if (TextUtils.isEmpty(getAddressTag())) {
            return UiConstants.ADDRESSTAG;
        }
        return UiConstants.SUCCESS;
    }
}
