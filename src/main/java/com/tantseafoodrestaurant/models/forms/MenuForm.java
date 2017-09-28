package com.tantseafoodrestaurant.models.forms;

import com.tantseafoodrestaurant.models.Category;
import com.tantseafoodrestaurant.models.Pound;
import com.tantseafoodrestaurant.models.Price;
import com.tantseafoodrestaurant.models.Spicy;
import com.tantseafoodrestaurant.models.data.MenuItemData;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;


public class MenuForm {

    @NotNull
    @Size(min=1, message = "Name may not be empty")
    private String name;

    @NotNull
    private int categoriesId;

    @NotNull
    private int pricesId;

    @NotNull
    private int spiciesId;

    @NotNull
    private int poundsId;

    /*
        Included other fields needed to create a menu,
        with correct validation attributes and display names.
     */

    private ArrayList<Category> categories;
    private ArrayList<Price> prices;
    private ArrayList<Spicy> spicies;
    private ArrayList<Pound> pounds;

    public MenuForm() {

        MenuItemData menuItemData = MenuItemData.getInstance();

        /*
          populate the other ArrayList collections needed in the view
        */
        categories = menuItemData.getCategories().findAll();
        prices = menuItemData.getPrices().findAll();
        spicies = menuItemData.getSpicies().findAll();
        pounds = menuItemData.getPounds().findAll();

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Category> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<Category> categories) {
        this.categories = categories;
    }

    public ArrayList<Price> getPrices() {
        return prices;
    }

    public void setPrices(ArrayList<Price> prices) {
        this.prices = prices;
    }

    public ArrayList<Spicy> getSpicies() {
        return spicies;
    }

    public void setSpicies(ArrayList<Spicy> spicies) {
        this.spicies = spicies;
    }

    public ArrayList<Pound> getPounds() {
        return pounds;
    }

    public void setPounds(ArrayList<Pound> pounds) {
        this.pounds = pounds;
    }

    public int getCategoriesId() {
        return categoriesId;
    }

    public void setCategoriesId(int categoriesId) {
        this.categoriesId = categoriesId;
    }

    public int getPricesId() {
        return pricesId;
    }

    public void setPricesId(int pricesId) {
        this.pricesId = pricesId;
    }

    public int getSpiciesId() {
        return spiciesId;
    }

    public void setSpiciesId(int spiciesId) {
        this.spiciesId = spiciesId;
    }

    public int getPoundsId() {
        return poundsId;
    }

    public void setPoundsId(int poundsId) {
        this.poundsId = poundsId;
    }
}
