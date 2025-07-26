package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

	@Override
	public List<Restaurant> selectByNameWildcard(String restaurantName) {
		String sql =" SELECT mr.restaurant_id, mr.restaurant_name, mr.catch_phrase, count(tr.review_id) AS review_count " +
							" FROM m_restaurant mr LEFT OUTER JOIN t_review tr ON mr.restaurant_id = tr.restaurant_id" +
							" WHERE restaurant_name LIKE ?" +
							" GROUP BY mr.restaurant_id, mr.restaurant_name, mr.catch_phrase" +
							" ORDER BY restaurant_id";
		
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, "%" + restaurantName + "%");
		
		List<Restaurant> restaurantList = new ArrayList<Restaurant>();
		for (Map<String, Object> one : list) {
			Restaurant restaurant = new Restaurant();
			restaurant.setRestaurantId((int)one.get("restaurant_id"));
			restaurant.setRestaurantName((String)one.get("restaurant_name"));
			restaurant.setCatchPhrase((String)one.get("catch_phrase"));
			int d = ((Long)one.get("review_count")).intValue();
			restaurant.setReviewCount(d);
			restaurantList.add(restaurant);
		}
		return restaurantList;
	}

}
