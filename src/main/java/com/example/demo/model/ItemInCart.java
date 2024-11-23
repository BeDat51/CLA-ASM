package com.example.demo.model;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemInCart {
	private Integer productId;
    private String productName;
    private String productImage;
    private BigDecimal productPrice;
    private Integer quantity;

    public BigDecimal getTotalPrice() {
    	return productPrice.multiply(BigDecimal.valueOf(quantity));
    }
}
