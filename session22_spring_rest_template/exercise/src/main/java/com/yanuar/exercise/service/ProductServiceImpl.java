package com.yanuar.exercise.service;

import com.yanuar.exercise.model.Product;
import com.yanuar.exercise.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> allProducts() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> detailProduct(Long id) {
        return productRepository.findById(id);
    }
}
