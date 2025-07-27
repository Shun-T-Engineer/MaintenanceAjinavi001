package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Restaurant;
import com.example.demo.repository.RestaurantRepository;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class DeleteRestaurantServiceImp implements DeleteRestaurantService {

	private final RestaurantRepository repository;
	
	@Override
	public void delete(Restaurant restaurant) {
		 repository.destoroy(restaurant);
	}

}
