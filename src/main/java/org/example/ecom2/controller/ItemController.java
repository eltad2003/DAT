package org.example.ecom2.controller;

import jakarta.servlet.http.HttpSession;
import org.example.ecom2.model.Customer;
import org.example.ecom2.model.Item;
import org.example.ecom2.repository.ItemRepository;
import org.example.ecom2.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/item")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemRepository itemRepository;

    //show item by id
    @GetMapping("/{id}")
    public String detailItem(@PathVariable Long id, Model model) {
        Optional<Item> item = itemRepository.findById(id);
        model.addAttribute("item", item.get());
        return "item_detail";
    }

    //add new item
    @GetMapping("/add")
    public String showFormAdd(Model model, HttpSession session) {
        Customer customerData = (Customer) session.getAttribute("customerData");
        if (customerData == null) {
            return "redirect:/login";
        }
        model.addAttribute("customerData", customerData);
        model.addAttribute("item", new Item());
        return "new_item";
    }
    @PostMapping("/save_item")
    public String saveItem(@ModelAttribute("item") Item item) {
        itemService.addItem(item);
        return "redirect:/";
    }

}

