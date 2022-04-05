package com.ns.model.FoodDay;

import java.util.List;

public class FoodDaySelectPojo {
    private String food_category;

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    private String heading;
    private String cat_key;

    public String getCat_key() {
        return cat_key;
    }

    public void setCat_key(String cat_key) {
        this.cat_key = cat_key;
    }

    private List<DayList> days;
    private boolean status;
    private boolean clickStatus;
    private boolean show_days;

    public boolean isShow_days() {
        return show_days;
    }

    public void setShow_days(boolean show_days) {
        this.show_days = show_days;
    }

    public boolean isClickStatus() {
        return clickStatus;
    }

    public void setClickStatus(boolean clickStatus) {
        this.clickStatus = clickStatus;
    }

    public FoodDaySelectPojo(boolean status, String food_category, String food_key, List<DayList> days, boolean isShowDay, String heading) {
        this.food_category = food_category;
        this.cat_key = food_key;
        this.days = days;
        this.status = status;
        this.show_days = isShowDay;
        this.heading = heading;
    }

    public boolean isStatus() {

        return status;
    }

    public void setFood_category(String food_category) {
        this.food_category = food_category;
    }

    public void setDays(List<DayList> days) {
        this.days = days;
    }

    public String getFood_category() {
        return food_category;
    }

    public List<DayList> getDays() {
        return days;
    }


}
