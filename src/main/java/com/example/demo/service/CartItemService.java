package com.example.demo.service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.CartItem;
import com.example.demo.entity.Product;
import com.example.demo.model.ItemInCart;
import com.example.demo.repository.CartItemRepository;

@Service
public class CartItemService {
	@Autowired
	private CartItemRepository cartItemRepository;
	
	public List<CartItem> getCartItemByCartId(Integer cartId){
		return cartItemRepository.findByCart_CartId(cartId);
	}
	
	public List<ItemInCart> getCartItemDetails(Integer cartId) {
	    List<CartItem> cartItems = cartItemRepository.findByCart_CartId(cartId);
	    return cartItems.stream().map(cartItem -> {
	    	Product product = cartItem.getProduct();
	    	return new ItemInCart(
	    		product.getProductId(),
	    		product.getName(),
	    		product.getImage(),
	    		product.getPrice(),
	    		cartItem.getQuantity()
	    		);
	    }).collect(Collectors.toList());
	}

}
