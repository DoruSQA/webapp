package com.example.demo.controler;




import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Product;
import com.example.demo.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin("*")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping
    public Product add(@RequestBody Product product) {
        return service.save(product);
    }

    @GetMapping("/search")
    public List<Product> search(@RequestParam String name) {
        return service.search(name);
    }
}