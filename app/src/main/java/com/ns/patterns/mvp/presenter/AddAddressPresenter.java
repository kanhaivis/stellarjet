package com.ns.patterns.mvp.presenter;

import com.ns.patterns.mvp.model.MADDAddress;
import com.ns.patterns.mvp.view.IAddAddressView;

import static com.ns.utils.UiConstants.ADDRESSFULL;
import static com.ns.utils.UiConstants.ADDRESSTAG;
import static com.ns.utils.UiConstants.HOUSEFLAT;
import static com.ns.utils.UiConstants.SUCCESS;

public class AddAddressPresenter implements IAddAddressPresenter {

    private IAddAddressView iAddAddressView;

    public AddAddressPresenter(IAddAddressView addAddressView) {
        iAddAddressView  = addAddressView;
    }

    @Override
    public void onAddAddress(String addressfull, String houseflat, String addresstag) {
        MADDAddress address = new MADDAddress(addressfull, houseflat, addresstag);
        int count = address.isValiDate();
        switch (count) {
            case SUCCESS:
                iAddAddressView.show(addressfull, houseflat, addresstag);
                break;
            case ADDRESSFULL:
                iAddAddressView.error("Full Address  not null!");
                break;
            case HOUSEFLAT:
                iAddAddressView.error("House/flat not null!");
                break;
            case ADDRESSTAG:
                iAddAddressView.error("Address tag!");
                break;
        }


    }
}
