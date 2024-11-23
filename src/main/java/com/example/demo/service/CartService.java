package com.example.demo.service;

import java.util.Collection;

import com.example.demo.entity.Product;
import com.example.demo.model.ItemInCart;

public interface CartService {
	/**
	* Thêm mặt hàng vào giỏ hoặc tăng số lượng lên 1 nếu đã tồn tại
	* @param id là mã mặt hàng cần thêm
	* @return mặt hàng đã được thêm vào hoặc cập nhật số lượng
	*/
	ItemInCart add(Integer id, Product product);
	/**
	* Xóa mặt hàng khỏi giỏ
	* @param id mã mặt hàng cần xóa
	*/
	void remove(Integer id);
	/**
	* Thay đổi số lượng lên của mặt hàng trong giỏ
	* @param id mã mặt hàng
	* @param qty số lượng mới
	* @return mặt hàng đã được thay đổi số lượng
	*/
	ItemInCart update(Integer id, Integer qty);
	/**
	* Xóa sạch các mặt hàng trong giỏ
	*/
	void clear();
	/**
	* Lấy tất cả các mặt hàng trong giỏ
	*/
	Collection<ItemInCart> getItems();
	/**
	* Lấy tổng số lượng các mặt hàng trong giỏ
	*/
	int getCount();
	/**
	* Lấy tổng số tiền tất cả các mặt hàng trong giỏ
	*/
	double getAmount();
}
