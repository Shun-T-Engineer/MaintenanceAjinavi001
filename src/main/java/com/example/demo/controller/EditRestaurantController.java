package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Restaurant;
import com.example.demo.form.RestaurantEditForm;
import com.example.demo.service.EditRestaurantService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class EditRestaurantController {
	
	private final EditRestaurantService service;

	@PostMapping("/edit-restaurant-form")
	private String editRestaurantForm(@ModelAttribute RestaurantEditForm form) {
	System.out.println(form);
		return "edit-restaurant-form";
	}
	
	@PostMapping("/edit-restaurant-confirm")
	private String editRestaurantConfirm(@Validated @ModelAttribute RestaurantEditForm form, BindingResult result) {
		
		if (result.hasErrors()){
			return "edit-restaurant-form";
		}
		return "edit-restaurant-confirm";
	}
	
	@PostMapping("/restaurant-edit")
	private String restaurantEdit(@Validated @ModelAttribute RestaurantEditForm form, 
													  BindingResult result, RedirectAttributes redirectAttributes) {
		
		if (result.hasErrors()){
			return "edit-restaurant-form";
		}
		
		Restaurant r = new Restaurant();
		r.setRestaurantId(form.getRestaurantId());
		r.setRestaurantName(form.getRestaurantName());
		r.setCatchPhrase(form.getCatchPhrase());
		service.edit(r);
		
		redirectAttributes.addFlashAttribute("msg","(レストラン編集)");
		return "redirect:/complete";
	}
}
