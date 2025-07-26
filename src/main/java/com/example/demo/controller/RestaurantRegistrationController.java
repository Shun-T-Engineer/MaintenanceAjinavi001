package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Restaurant;
import com.example.demo.form.RestaurantRegistForm;
import com.example.demo.service.RegistRestaurantService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class RestaurantRegistrationController {
	
	private final RegistRestaurantService service;
	
	@PostMapping("/mt-show-regist")
	private String registRestaurantForm(@ModelAttribute 
																	RestaurantRegistForm form) {
		return "mt-show-regist";
	}
	
	@PostMapping("/mt-restaurant-regist-confirm")
	private String restaurantRegistConfirm(@Validated @ModelAttribute RestaurantRegistForm form, 
			                                                           BindingResult result) {
		
		if(result.hasErrors()) {
			return "mt-show-regist";
		}
		
		return "mt-restaurant-regist-confirm";
	}

	@PostMapping("/mt-restaurant-regist")
	private String restaurantRegist(@Validated @ModelAttribute RestaurantRegistForm form,
														 BindingResult result, RedirectAttributes redirectAttributes) {
		
		if(result.hasErrors()) {
			return "mt-show-regist";
		}
		
		Restaurant r = new Restaurant();
		r.setRestaurantName(form.getRestaurantName());
		r.setCatchPhrase(form.getCatchPhrase());
		service.regist(r);
		
		redirectAttributes.addFlashAttribute("msg", "(レストラン登録)");
		return "redirect:/complete";
	}
}
