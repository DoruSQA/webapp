package com.example.demo.controler;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/add-product")
    public String addProductPage() {
        return "add-product";
    }

    @GetMapping("/search-product")
    public String searchProductPage() {
        return "search-product";
    }
}