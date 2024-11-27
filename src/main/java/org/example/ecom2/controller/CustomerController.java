package org.example.ecom2.controller;

import jakarta.servlet.http.HttpSession;
import org.example.ecom2.model.Customer;
import org.example.ecom2.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("/login")
    public String showLoginForm() {
        return "Login/login";
    }
    @GetMapping("/logout")
    public String Logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    @PostMapping("/login")
    public String processLogin(@RequestParam("username") String username, @RequestParam("password") String password,
                               Model model, HttpSession session) {
        Customer customerData = customerService.findCustomerByUsername(username);
        if (customerData != null) {
            if (password.equals(customerData.getPassword())) {
                session.setAttribute("customerData", customerData);
                return "redirect:/";
            } else {
                model.addAttribute("errorMessage", "Invalid password.");
                return "Login/login";
            }

        }else{
            model.addAttribute("errorMessage", "Invalid email or password.");
            return "Login/login";
        }
    }
    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "Login/register";
    }
    @PostMapping("/register")
    public String processRegister(@ModelAttribute("customer") Customer customer, Model model) {
        String result = customerService.registerCustomer(customer);
        if ("successfully".equals(result)) {
            model.addAttribute("message", "Customer registered successfully.");
            return "redirect:/login";
        }
        model.addAttribute("errorMessage", result);
        return "Login/register";
    }
}
