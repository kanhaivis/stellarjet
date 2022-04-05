package com.ns.model.FoodDay;

public class FoodPrefsUpdatePojo {
    private String token;
    private String food_prefs;

    public FoodPrefsUpdatePojo(String token, String food_prefs) {
        this.token = token;
        this.food_prefs = food_prefs;
    }

    public String getToken() {
        return token;
    }

    public String getFood_prefs() {
        return food_prefs;
    }
}
