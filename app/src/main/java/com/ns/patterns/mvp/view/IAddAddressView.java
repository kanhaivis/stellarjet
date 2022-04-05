package com.ns.patterns.mvp.view;

public interface IAddAddressView {
    void show(String name, String phone, String email);
    void error(String mag);
}
