package com.example.ecsite.mapper;

import com.example.ecsite.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    List<User> findAll();

    User findById(int id);

    User findByEmail(String email);

    void insert(User user);

    void update(User user);
}