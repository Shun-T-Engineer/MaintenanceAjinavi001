package com.example.demo.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Restaurant;

import lombok.RequiredArgsConstructor;


@Repository
@RequiredArgsConstructor
public class RestaurantRepositoryImp implements RestaurantRepository {
	
	private final JdbcTemplate jdbcTemplate;

	@Override
	public void add(Restaurant restaurant) {
		
		String sql =
				" INSERT INTO m_restaurant" +
				" (restaurant_name, catch_phrase)" +
				" VALUES(?, ?)";
		
		jdbcTemplate.update(sql, restaurant.getRestaurantName(), restaurant.getCatchPhrase());
	}

}
