package com.ns.utils;

import com.ns.model.LoginResponse.CitiesBean;

import java.util.ArrayList;
import java.util.List;

public class ConstantListMethod {

    public static List<CitiesBean> nSaveTiemList() {
        List<CitiesBean> mItemList = new ArrayList<>();

        CitiesBean bean = new CitiesBean();
        bean.setId(0);
        bean.setName("Boarding Pass");
        mItemList.add(bean);

        CitiesBean bean1 = new CitiesBean();
        bean1.setId(1);
        bean1.setName("My Bookings");
        mItemList.add(bean1);

        CitiesBean bean2 = new CitiesBean();
        bean2.setId(2);
        bean2.setName("Preferences");
        mItemList.add(bean2);

        CitiesBean bean3 = new CitiesBean();
        bean3.setId(3);
        bean3.setName("Buy Seats");
        mItemList.add(bean3);


        return mItemList;
    }

    public static List<CitiesBean> nSaveTiemLists() {
        List<CitiesBean> mItemList = new ArrayList<>();

        CitiesBean bean = new CitiesBean();
        bean.setId(0);
        bean.setName("Boarding Pass");
        mItemList.add(bean);

        CitiesBean bean1 = new CitiesBean();
        bean1.setId(1);
        bean1.setName("My Bookings");
        mItemList.add(bean1);


        return mItemList;
    }
}
