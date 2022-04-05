package com.ns.model.BoardingList;

import java.io.Serializable;

public class FoodItems implements Serializable {


    /**
     * id : 1
     * name : Malai Koftaa
     * food_type : veg
     * food_type_text : Vegetarian
     */

    private int id;
    private String name;
    private String food_type;
    private String food_type_text;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFood_type() {
        return food_type;
    }

    public void setFood_type(String food_type) {
        this.food_type = food_type;
    }

    public String getFood_type_text() {
        return food_type_text;
    }

    public void setFood_type_text(String food_type_text) {
        this.food_type_text = food_type_text;
    }
}
