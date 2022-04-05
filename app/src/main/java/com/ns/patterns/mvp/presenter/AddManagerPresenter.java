package com.ns.patterns.mvp.presenter;

import com.ns.patterns.mvp.model.IMAddManager;
import com.ns.patterns.mvp.model.MAddManager;
import com.ns.patterns.mvp.view.IAddManagerView;

import static com.ns.utils.UiConstants.EMAIL_EMPTY;
import static com.ns.utils.UiConstants.EMAIL_WRONG_FORMATE;
import static com.ns.utils.UiConstants.NAME_EMPTY;
import static com.ns.utils.UiConstants.PHONE_EMPTY;
import static com.ns.utils.UiConstants.PHONE_NOT_LESSTHEN_10;
import static com.ns.utils.UiConstants.SUCCESS;


public class AddManagerPresenter implements IAddManagerPresenter {

    private IAddManagerView iAddManagerView;

    public AddManagerPresenter(IAddManagerView view) {
        iAddManagerView = view;
    }

    @Override
    public void onAddManager(String name, String phone, String email) {

        MAddManager mAddManager = new MAddManager(name, phone, email);

        int count = mAddManager.isValiDate();
        switch (count) {
            case SUCCESS:
                iAddManagerView.show(name, phone, email);
                break;
            case NAME_EMPTY:
                iAddManagerView.error("Name not null!");
                break;
            case PHONE_EMPTY:
                iAddManagerView.error("Phone not null!");
                break;
            case PHONE_NOT_LESSTHEN_10:
                iAddManagerView.error("Phone not less then 10!");
                break;
            case EMAIL_EMPTY:
                iAddManagerView.error("Email not null!");
                break;
            case EMAIL_WRONG_FORMATE:
                iAddManagerView.error("Email wrong formate!");
                break;
        }

    }
}
