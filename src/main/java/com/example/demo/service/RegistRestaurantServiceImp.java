package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Restaurant;
import com.example.demo.repository.RestaurantRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RegistRestaurantServiceImp implements RegistRestaurantService {

	private final RestaurantRepository repository;
	
	@Override
	public void regist(Restaurant restaurant) {
		repository.add(restaurant);

	}

}
