package com.example.ecsite.service;

import com.example.ecsite.mapper.ProductMapper;
import com.example.ecsite.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductMapper productMapper;

    public List<Product> findAll() {
        return productMapper.findAll();
    }

    public Product findById(int id) {
        return productMapper.findById(id);
    }

    @Transactional
    public void insert(Product product) {
        productMapper.insert(product);
    }

    @Transactional
    public void update(Product product) {
        productMapper.update(product);
    }

    @Transactional
    public void deleteById(int id) {
        productMapper.deleteById(id);
    }
}