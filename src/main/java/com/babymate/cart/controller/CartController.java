package com.babymate.cart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;

import com.babymate.cart.service.CartService;

@Controller
@Validated
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	CartService cartService;

}
