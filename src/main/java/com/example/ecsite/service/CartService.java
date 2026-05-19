package com.example.ecsite.service;

import com.example.ecsite.dto.CartItemDto;
import com.example.ecsite.mapper.CartMapper;
import com.example.ecsite.mapper.ProductMapper;
import com.example.ecsite.model.Cart;
import com.example.ecsite.model.CartItem;
import com.example.ecsite.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartMapper cartMapper;
    private final ProductMapper productMapper;

    @Transactional
    public Cart getOrCreateCart(int userId) {
        Cart cart = cartMapper.findByUserId(userId);
        if (cart == null) {
            cart = new Cart();
            cart.setUserId(userId);
            cartMapper.insertCart(cart);
        }
        return cart;
    }

    public List<CartItemDto> getCartItems(int userId) {
        Cart cart = getOrCreateCart(userId);
        List<CartItem> items = cartMapper.findItemsByCartId(cart.getId());
        return items.stream()
                .map(item -> {
                    Product product = productMapper.findById(item.getProductId());
                    CartItemDto dto = new CartItemDto();
                    dto.setCartItemId(item.getId());
                    dto.setProductId(product.getId());
                    dto.setProductName(product.getName());
                    dto.setPrice(product.getPrice());
                    dto.setQuantity(item.getQuantity());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    public int getTotalPrice(List<CartItemDto> items) {
        return items.stream().mapToInt(CartItemDto::getSubtotal).sum();
    }

    @Transactional
    public void addItem(int userId, int productId, int quantity) {
        Cart cart = getOrCreateCart(userId);
        CartItem existing = cartMapper.findItemByCartIdAndProductId(cart.getId(), productId);
        if (existing != null) {
            existing.setQuantity(existing.getQuantity() + quantity);
            cartMapper.updateItemQuantity(existing);
        } else {
            CartItem item = new CartItem();
            item.setCartId(cart.getId());
            item.setProductId(productId);
            item.setQuantity(quantity);
            cartMapper.insertItem(item);
        }
    }

    @Transactional
    public void updateQuantity(int cartItemId, int quantity) {
        CartItem item = new CartItem();
        item.setId(cartItemId);
        item.setQuantity(quantity);
        cartMapper.updateItemQuantity(item);
    }

    @Transactional
    public void removeItem(int cartItemId) {
        cartMapper.deleteItemById(cartItemId);
    }

    @Transactional
    public void clear(int userId) {
        Cart cart = cartMapper.findByUserId(userId);
        if (cart != null) {
            cartMapper.deleteItemsByCartId(cart.getId());
        }
    }
}