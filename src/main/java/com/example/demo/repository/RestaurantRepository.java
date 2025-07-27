package com.example.demo.repository;

import java.util.List;

import com.example.demo.entity.Restaurant;

public interface RestaurantRepository {
	void add(Restaurant restaurant);
	
	List<Restaurant> selectByNameWildcard(String restaurantName);
	
	void update(Restaurant restaurant);
	
	void destoroy(Restaurant restaurant);
}
