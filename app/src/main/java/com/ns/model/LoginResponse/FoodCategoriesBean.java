package com.ns.model.LoginResponse;

public class FoodCategoriesBean {
    /**
     * cat_key : veg
     * cat_text : Vegetarian
     * show_days : false
     */

    private String cat_key;
    private String cat_text;
    private boolean show_days;

    public String getCat_key() {
        return cat_key;
    }

    public void setCat_key(String cat_key) {
        this.cat_key = cat_key;
    }

    public String getCat_text() {
        return cat_text;
    }

    public void setCat_text(String cat_text) {
        this.cat_text = cat_text;
    }

    public boolean isShow_days() {
        return show_days;
    }

    public void setShow_days(boolean show_days) {
        this.show_days = show_days;
    }
}