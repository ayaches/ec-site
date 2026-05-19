package com.example.ecsite.mapper;

import com.example.ecsite.model.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {

    List<Product> findAll();

    Product findById(int id);

    void insert(Product product);

    void update(Product product);

    void deleteById(int id);
}