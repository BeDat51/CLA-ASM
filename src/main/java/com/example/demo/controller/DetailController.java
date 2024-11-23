package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Product;
import com.example.demo.repository.DetailRepository;

@Controller
public class DetailController {
	@Autowired
	DetailRepository detailrepo;
	
	
	@RequestMapping("/detail/{id}")
	public String viewDetail(@PathVariable("id") Integer id, Model model) {
		Product product = detailrepo.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Sản phẩm không tồn tại"));
		model.addAttribute("item", product);
		return "detail";
	}
}
