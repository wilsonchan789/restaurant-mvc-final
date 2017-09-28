package com.tantseafoodrestaurant.models.data;

import com.tantseafoodrestaurant.models.*;

import java.util.ArrayList;

public class MenuItemData {

    private ArrayList<Menu> menus = new ArrayList<>();
    private static MenuItemData instance;

    private MenuFieldData<Category> categories = new MenuFieldData<>();
    private MenuFieldData<Price> prices = new MenuFieldData<>();
    private MenuFieldData<Spicy> spicies= new MenuFieldData<>();
    private MenuFieldData<Pound> pounds = new MenuFieldData<>();


    private MenuItemData() {
        MenuItemDataImporter.loadData(this);
    }


    public static MenuItemData getInstance() {
        if (instance == null) {
            instance = new MenuItemData();
        }
        return instance;
    }

    public Menu findById(int id) {
        for (Menu menu : menus) {
            if (menu.getId() == id)
                return menu;
        }

        return null;
    }

    public ArrayList<Menu> findAll() {
        return menus;
    }


    public ArrayList<Menu> findByColumnAndValue(MenuFieldType column, String value) {

        ArrayList<Menu> matchingMenus = new ArrayList<>();

        for (Menu menu : menus) {
            if (getFieldByType(menu, column).contains(value))
                matchingMenus.add(menu);
        }

        return matchingMenus;
    }


    public ArrayList<Menu> findByValue(String value) {

        ArrayList<Menu> matchingMenus = new ArrayList<>();

        for (Menu menu : menus) {

            if (menu.getName().toLowerCase().contains(value)) {
                matchingMenus.add(menu);
                continue;
            }

            for (MenuFieldType column : MenuFieldType.values()) {
                if (column != MenuFieldType.ALL && getFieldByType(menu, column).contains(value)) {
                    matchingMenus.add(menu);
                    break;
                }
            }
        }

        return matchingMenus;
    }


    public void add(Menu menu) {
        menus.add(menu);
    }


    private static MenuField getFieldByType(Menu menu, MenuFieldType type) {
        switch(type) {
            case CATEGORY:
                return menu.getCategory();
            case PRICE:
                return menu.getPrice();
            case SPICY:
                return menu.getPound();
            case POUND:
                return menu.getSpicy();
        }

        throw new IllegalArgumentException("Cannot get field of type " + type);
    }

    public MenuFieldData<Category> getCategories() {
        return categories;
    }

    public MenuFieldData<Price> getPrices() {
        return prices;
    }

    public MenuFieldData<Spicy> getSpicies() {
        return spicies;
    }

    public MenuFieldData<Pound> getPounds() {
        return pounds;
    }

}
