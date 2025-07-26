package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Restaurant {
	private Integer restaurantId;
	private String restaurantName;
	private String catchPhrase;
	private Integer reviewCount;
}
