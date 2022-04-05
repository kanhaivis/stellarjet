package com.ns.model.FoodMenuList;

import java.util.ArrayList;
import java.util.List;

public class FoodMenuListResponse {

    /**
     * resultcode : 1
     * message : Food Menu for 2019-05-07
     * data : [{"id":1,"name":"Malai Koftaa","food_type":"veg","description":"malai kofta is a popular as well as most sought after vegetarian indian dish in restaurants. malai means cream and kofta are fried dumpling balls. usually they are made up of mashed potatoes, mix vegetables or paneer.","food_image":"1546945935.jpeg","image_path":"uploads/food_packages","created_at":null,"updated_at":null,"food_type_text":"Vegetarian","img_url":"http://dev.stellarjet.com/app/v2/storage/uploads/food_packages/1546945935.jpeg"},{"id":2,"name":"European cuisine","food_type":"continental","description":"Prune pits, peach pits, orange peels, Gloppy glumps of cold oatmeal, Pizza crusts and withered greens, Soggy beans, and tangerines, Crusts of black-burned buttered toast, Grisly bits of beefy roast. The garbage rolled on down the halls, It raised the roof, it broke the walls, I mean, greasy napkins, cookie crumbs, Blobs of gooey bubble gum, Cellophane from old bologna, Rubbery, blubbery macaroni, Peanut butter, caked and dry, Curdled milk, and crusts of pie, Rotting melons, dried-up mustard, Eggshells mixed with lemon custard, Cold French fries and rancid meat, Yellow lumps of Cream of Wheat.","food_image":"1544616157.jpg","image_path":"uploads/food_packages","created_at":null,"updated_at":null,"food_type_text":"Continental","img_url":"http://dev.stellarjet.com/app/v2/storage/uploads/food_packages/1544616157.jpg"},{"id":3,"name":"Chicken And Cheese Salad","food_type":"non-veg","description":"Prune pits, peach pits, orange peels, Gloppy glumps of cold oatmeal, Pizza crusts and withered greens, Soggy beans, and tangerines, Crusts of black-burned buttered toast, Grisly bits of beefy roast. The garbage rolled on down the halls, It raised the roof, it broke the walls, I mean, greasy napkins, cookie crumbs, Blobs of gooey bubble gum, Cellophane from old bologna, Rubbery, blubbery macaroni, Peanut butter, caked and dry, Curdled milk, and crusts of pie, Rotting melons, dried-up mustard, Eggshells mixed with lemon custard, Cold French fries and rancid meat, Yellow lumps of Cream of Wheat.","food_image":"1544616231.jpeg","image_path":"uploads/food_packages","created_at":null,"updated_at":null,"food_type_text":"Non-Vegetarian","img_url":"http://dev.stellarjet.com/app/v2/storage/uploads/food_packages/1544616231.jpeg"},{"id":5,"name":"Egg omelet","food_type":"non-veg","description":"An omelette or omelet is a dish made from beaten eggs fried with butter or oil in a frying pan. It is quite common for the omelette to be folded around a filling such as cheese, chives, vegetables, mushrooms, meat, or some combination of the above.","food_image":"1547183449.jpeg","image_path":"uploads/food_packages","created_at":null,"updated_at":null,"food_type_text":"Non-Vegetarian","img_url":"http://dev.stellarjet.com/app/v2/storage/uploads/food_packages/1547183449.jpeg"},{"id":6,"name":"Aloo paratha","food_type":"veg","description":"Aloo Paratha is a bread dish originating from the Indian subcontinent; the recipe is one of the most popular breakfast dishes throughout western, central and northern regions of India as well as in Pakistan.","food_image":"1547183551.jpeg","image_path":"uploads/food_packages","created_at":null,"updated_at":null,"food_type_text":"Vegetarian","img_url":"http://dev.stellarjet.com/app/v2/storage/uploads/food_packages/1547183551.jpeg"}]
     */

    private int resultcode;
    private String message;
    private ArrayList<FoodMenuDataBean> data;

    public int getResultcode() {
        return resultcode;
    }

    public void setResultcode(int resultcode) {
        this.resultcode = resultcode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<FoodMenuDataBean> getData() {
        return data;
    }

    public void setData(ArrayList<FoodMenuDataBean> data) {
        this.data = data;
    }

}
