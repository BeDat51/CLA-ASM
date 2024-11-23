package com.example.demo.service;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import com.example.demo.entity.Product;
import com.example.demo.model.ItemInCart;


@SessionScope
@Service
public class CartServiceImpl implements CartService {
	Map<Integer, ItemInCart> map = new HashMap<>();
	
	@Override
	public ItemInCart add(Integer id, Product product) {
		// TODO Auto-generated method stub
		ItemInCart item = map.get(id);
		if (item == null) {//chua co trong gio hang
			item = new ItemInCart();
			item.setProductId(product.getProductId());
			item.setProductName(product.getName());
			item.setProductImage(product.getImage());
			item.setProductPrice(product.getPrice());
			item.setQuantity(1);
			map.put(id, item);
		}else {
			item.setQuantity(item.getQuantity()+1);
		}
		return item;
	}

	@Override
	public void remove(Integer id) {
		// TODO Auto-generated method stub
		map.remove(id);
		
	}

	@Override
	public ItemInCart update(Integer id, Integer qty) {
	    ItemInCart item = map.get(id);
	    if (item == null) return null; // Nếu sản phẩm không tồn tại, không làm gì

	    if (qty == -1 && item.getQuantity() > 0) { 
	        item.setQuantity(item.getQuantity() - 1);
	        if (item.getQuantity() == 0) this.remove(id); // Xóa nếu số lượng = 0
	    } else if (qty == 1 && item.getQuantity() < 100) {
	        item.setQuantity(item.getQuantity() + 1);
	    }

	    return item;
	}


	@Override
	public void clear() {
		// TODO Auto-generated method stub
		map.clear();
		
	}

	@Override
	public Collection<ItemInCart> getItems() {
		// TODO Auto-generated method stub
		return map.values();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return map.values().stream().mapToInt(item -> item.getQuantity()).sum();
	}

	@Override
	public double getAmount() {
		// TODO Auto-generated method stub
		return map.values().stream().map(item -> item.getProductPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
				.reduce(BigDecimal.ZERO, BigDecimal::add).doubleValue();
	}
	
}
