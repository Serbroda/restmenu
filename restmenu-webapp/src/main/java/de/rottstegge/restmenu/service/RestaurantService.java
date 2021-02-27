package de.rottstegge.restmenu.service;

import de.rottstegge.restmenu.model.Restaurant;

import java.util.List;

public interface RestaurantService {

    List<Restaurant> getRestaurants();

    Restaurant getRestaurant(long id);

    Restaurant createRestaurant(Restaurant restaurant);
}
