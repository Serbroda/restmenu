package de.rottstegge.restmenu.service.impl;

import de.rottstegge.restmenu.model.Restaurant;
import de.rottstegge.restmenu.repository.RestaurantRepository;
import de.rottstegge.restmenu.service.RestaurantService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public RestaurantServiceImpl(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public List<Restaurant> getRestaurants() {
        return restaurantRepository.findAll();
    }

    @Override
    public Restaurant getRestaurant(long id) {
        return restaurantRepository.getOne(id);
    }

    @Override
    public Restaurant createRestaurant(Restaurant restaurant) {
        if(restaurant.getId() > 0) {
            throw new IllegalArgumentException("Cannot create restaurant for existing id " + restaurant.getId());
        }
        return restaurantRepository.save(restaurant);
    }
}
