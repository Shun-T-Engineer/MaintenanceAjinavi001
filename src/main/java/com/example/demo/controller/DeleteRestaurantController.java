package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Restaurant;
import com.example.demo.form.RestaurantEditForm;
import com.example.demo.service.DeleteRestaurantService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class DeleteRestaurantController {
	
	private final DeleteRestaurantService service;
	
	@PostMapping("/delete-restaurant")
	private String deleteRestaurant(@ModelAttribute RestaurantEditForm form) {
		return "delete-restaurant";
	}
	
	@PostMapping("/restaurant-delete")
	private String restaurantDelete(@Validated @ModelAttribute RestaurantEditForm form, 
														   BindingResult result, RedirectAttributes redirectAttributes) {
		
		if (result.hasErrors()) {
			return "restaurant-list";
		}
		
		Restaurant r = new Restaurant();
		
		r.setRestaurantId(form.getRestaurantId());
//		r.setRestaurantName(form.getRestaurantName());
//		r.setCatchPhrase(form.getCatchPhrase());
		service.delete(r);
		
		redirectAttributes.addFlashAttribute("msg","(レストラン削除)");
		return "redirect:/complete";
		
	}
	
}
