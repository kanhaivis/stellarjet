package com.ns.model.FoodMenuList;

import java.util.Objects;

public class FoodMenuDataBean {
    /**
     * id : 1
     * name : Malai Koftaa
     * food_type : veg
     * description : malai kofta is a popular as well as most sought after vegetarian indian dish in restaurants. malai means cream and kofta are fried dumpling balls. usually they are made up of mashed potatoes, mix vegetables or paneer.
     * food_image : 1546945935.jpeg
     * image_path : uploads/food_packages
     * created_at : null
     * updated_at : null
     * food_type_text : Vegetarian
     * img_url : http://dev.stellarjet.com/app/v2/storage/uploads/food_packages/1546945935.jpeg
     */

    private int id;
    private String name;
    private String food_type;
    private String description;
    private String food_image;
    private String image_path;
    private Object created_at;
    private Object updated_at;
    private String food_type_text;
    private String img_url;
    private boolean status;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFood_image() {
        return food_image;
    }

    public void setFood_image(String food_image) {
        this.food_image = food_image;
    }

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }

    public Object getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Object created_at) {
        this.created_at = created_at;
    }

    public Object getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Object updated_at) {
        this.updated_at = updated_at;
    }

    public String getFood_type_text() {
        return food_type_text;
    }

    public void setFood_type_text(String food_type_text) {
        this.food_type_text = food_type_text;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }


    @Override
    public boolean equals(Object obj)
    {

        // if both the object references are
        // referring to the same object.
        if(this == obj)
            return true;

        // it checks if the argument is of the
        // type FoodMenuDataBean by comparing the classes
        // of the passed argument and this object.
        if(obj == null || obj.getClass()!= this.getClass())
            return false;

        // type casting of the argument.
        FoodMenuDataBean foodMenuDataBean = (FoodMenuDataBean) obj;

        // comparing the state of argument with
        // the state of 'this' Object.
        return (foodMenuDataBean.getFood_type_text().equals(this.getFood_type_text()));
    }

    @Override
    public int hashCode()
    {

        // We are returning the food type
        // as a hashcode value.
        // we can also return some
        // other calculated value or may
        // be memory address of the
        // Object on which it is invoked.
        // it depends on how you implement
        // hashCode() method.
        return getFood_type_text().hashCode();
    }
}
