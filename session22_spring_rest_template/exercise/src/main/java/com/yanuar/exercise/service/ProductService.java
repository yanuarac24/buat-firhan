package com.yanuar.exercise.service;

import com.yanuar.exercise.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> allProducts();
    Optional<Product> detailProduct(Long id);
}
