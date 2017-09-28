package com.tantseafoodrestaurant.controllers;

import com.tantseafoodrestaurant.models.Menu;
import com.tantseafoodrestaurant.models.MenuFieldType;
import com.tantseafoodrestaurant.models.forms.SearchForm;
import com.tantseafoodrestaurant.models.data.MenuItemData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
@RequestMapping("search")
public class SearchController {

    private MenuItemData menuItemData = MenuItemData.getInstance();

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute(new SearchForm());
        return "search";
    }

    @RequestMapping(value = "results")
    public String search(Model model,
                         @ModelAttribute SearchForm searchForm) {

        ArrayList<Menu> menus;

        if (searchForm.getSearchField().equals(MenuFieldType.ALL)) {
            menus = menuItemData.findByValue(searchForm.getKeyword());
        } else {
            menus = menuItemData.findByColumnAndValue(searchForm.getSearchField(), searchForm.getKeyword());
        }

        model.addAttribute("menus", menus);

        return "search";
    }

}
