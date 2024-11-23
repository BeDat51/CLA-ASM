package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Integer>{
	List<CartItem> findByCart_CartId(Integer cartId);
}
