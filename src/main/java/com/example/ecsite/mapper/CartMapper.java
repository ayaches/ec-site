package com.example.ecsite.mapper;

import com.example.ecsite.model.Cart;
import com.example.ecsite.model.CartItem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CartMapper {

    Cart findByUserId(int userId);

    void insertCart(Cart cart);

    List<CartItem> findItemsByCartId(int cartId);

    CartItem findItemByCartIdAndProductId(int cartId, int productId);

    void insertItem(CartItem item);

    void updateItemQuantity(CartItem item);

    void deleteItemById(int id);

    void deleteItemsByCartId(int cartId);
}