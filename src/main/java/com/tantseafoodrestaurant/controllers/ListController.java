package com.tantseafoodrestaurant.controllers;

import com.tantseafoodrestaurant.models.MenuFieldType;
import com.tantseafoodrestaurant.models.MenuField;
import com.tantseafoodrestaurant.models.data.MenuItemData;
import com.tantseafoodrestaurant.models.Menu;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
@RequestMapping(value = "list")
public class ListController {

    private MenuItemData menuItemData = MenuItemData.getInstance();

    @RequestMapping(value = "")
    public String list(Model model) {
        MenuFieldType[] fields = MenuFieldType.values();
        model.addAttribute("fields", fields);
        return "list";
    }

    @RequestMapping(value = "values")
    public String listColumnValues(Model model, @RequestParam MenuFieldType column) {

        if (column.equals(MenuFieldType.ALL)) {
            return "redirect:/list/all";
        }


        ArrayList<? extends MenuField> items;

        switch(column) {
            case CATEGORY:
                items = menuItemData.getCategories().findAll();
                break;
            case PRICE:
                items = menuItemData.getPrices().findAll();
                break;
            case SPICY:
                items = menuItemData.getSpicies().findAll();
                break;
            case POUND:
            default:
                items = menuItemData.getPounds().findAll();
        }

        model.addAttribute("title", "All " + column.getName() + " Values");
        model.addAttribute("column", column);
        model.addAttribute("items", items);

        return "list-column";
    }

    @RequestMapping(value = "menuitems")
    public String listJobsByColumnAndValue(Model model,
                                           @RequestParam MenuFieldType column, @RequestParam String name) {

        ArrayList<Menu> menus = menuItemData.findByColumnAndValue(column, name);

        model.addAttribute("title", "Menu items with " + column.getName() + ": " + name);
        model.addAttribute("menus", menus);

        return "list-menus";
    }

    @RequestMapping(value = "all")
    public String listAllJobs(Model model) {

        ArrayList<Menu> menus = menuItemData.findAll();

        model.addAttribute("title", "All Jobs");
        model.addAttribute("menus", menus);

        return "list-menus";
    }
}
