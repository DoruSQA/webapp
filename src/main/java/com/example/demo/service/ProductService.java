package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;


@Service
public class ProductService {

	@Autowired
	ProductRepository repo;

	public ProductService(ProductRepository repo) {
		this.repo = repo;
	}

	public Product save(Product product) {
		return repo.save(product);
	}

	public List<Product> search(String name) {
		return repo.findByNameContainingIgnoreCase(name);
	}
}
