package com.yanuar.exercise.presenter;

import com.yanuar.exercise.model.Product;
import com.yanuar.exercise.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class ProductPresenter {
    @Autowired
    ProductServiceImpl productService;

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productService.allProducts();
    }
}
