package com.tantseafoodrestaurant.controllers;

import com.tantseafoodrestaurant.models.*;
import com.tantseafoodrestaurant.models.data.MenuItemData;
import com.tantseafoodrestaurant.models.forms.MenuForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "menu")
public class MenuItemController {

    private MenuItemData menuItemData = MenuItemData.getInstance();

    // The detail display for a given Menu at URLs like /menu?id=17
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(Model model, int id) {

        // get the Menu with the given ID and pass it into the view
        model.addAttribute("menu", menuItemData.findById(id));

        return "menuitem-detail";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute(new MenuForm());
        return "new-menuitem";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(Model model, @Valid MenuForm menuForm, Errors errors) {

        if(errors.hasErrors()){
            model.addAttribute("errors", errors);
            return "new-menuitem";
        }

        Category anCategory = menuItemData.getCategories().findById(menuForm.getCategoriesId());
        Price aPrice = menuItemData.getPrices().findById(menuForm.getPricesId());
        Spicy aSpicy = menuItemData.getSpicies().findById(menuForm.getSpiciesId());
        Pound aPound = menuItemData.getPounds().findById(menuForm.getPoundsId());

        Menu newMenu = new Menu(menuForm.getName(), anCategory, aPrice, aSpicy, aPound);
        menuItemData.add(newMenu);
        int id = newMenu.getId();

        // Validate the MenuForm model, and if valid, create a
        // new Menu and add it to the menuItemData data store. Then
        // redirect to the menu detail view for the new Menu.

        return "redirect:?id=" + id;
    }
}
