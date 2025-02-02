package org.example.ecom2.controller;

import jakarta.servlet.http.HttpSession;
import org.example.ecom2.model.Customer;
import org.example.ecom2.model.Item;
import org.example.ecom2.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private ItemService itemService;

    //show home form and search item
    @GetMapping("/")
    public String homeForm(@RequestParam(value = "keyword", required = false) String keyword, Model model, HttpSession session) {
        List<Item> items;
        if (keyword != null && !keyword.isEmpty()) {
            items = itemService.searchItem(keyword);
        } else {
            items = itemService.getAllItems();
        }
        Customer customerData = (Customer) session.getAttribute("customerData");
        model.addAttribute("customerData", customerData);
        model.addAttribute("items", items);
        model.addAttribute("keyword", keyword);
        return "home";
    }



}
