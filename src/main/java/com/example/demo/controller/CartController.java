package com.example.demo.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.CartService;
import com.example.demo.service.ProductService;

@Controller
public class CartController {
    @Autowired
    CartService cartService;
    @Autowired
    ProductService productService;
    
    @RequestMapping("/cart")
    public String view(Model model) {
        model.addAttribute("items", cartService.getItems()); // Lấy danh sách ItemInCart
        model.addAttribute("count", cartService.getCount()); // Tổng số lượng sản phẩm
        model.addAttribute("totalPrice", cartService.getAmount()); // Tổng giá trị giỏ hàng
        model.addAttribute("getCount", cartService.getCount()); // Đếm tổng số mặt hàng
        
        return "/cart"; // Trả về view /cart
    }
    
    @RequestMapping("/cart/add/{id}")
	public String add(@PathVariable("id") Integer id, Model model) {
    	Product product = productService.findById(id);
    	if (product != null) {
            cartService.add(id, product); // Gọi phương thức add với cả id và product
        }
    	model.addAttribute("item", product);
        model.addAttribute("items", cartService.getItems()); // Danh sách sản phẩm trong giỏ
        model.addAttribute("count", cartService.getCount()); // Tổng số lượng
        model.addAttribute("totalPrice", cartService.getAmount()); // Tổng giá
        return "detail"; // Trả về lại view trang Detail
	}
    
    @RequestMapping("/cart/update/{id}/{pre}")
    public String update(@PathVariable("id") Integer id, @PathVariable("pre") Integer pre) {
        cartService.update(id, pre);
        return "redirect:/cart";
    }

    
    @RequestMapping("/cart/remove/{id}")
    public String removeItem(@PathVariable("id") Integer id) {
        // Gọi service để xóa item theo id
        cartService.remove(id);
        return "redirect:/cart"; // Chuyển hướng về trang giỏ hàng
    }
    
    @RequestMapping("/cart/clear")
    public String clear() {
        cartService.clear(); // Xóa toàn bộ giỏ hàng
        return "redirect:/cart"; // Quay lại trang giỏ hàng
    }
    
  
}