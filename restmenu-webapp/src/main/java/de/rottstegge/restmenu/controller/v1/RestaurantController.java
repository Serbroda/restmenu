package de.rottstegge.restmenu.controller.v1;

import de.rottstegge.restmenu.mapper.RestaurantMapper;
import de.rottstegge.restmenu.model.Restaurant;
import de.rottstegge.restmenu.service.RestaurantService;
import de.rottstegge.v1.model.RestaurantDto;
import de.rottstegge.v1.server.RestaurantsApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class RestaurantController implements RestaurantsApi {

    private static final Logger LOG = LoggerFactory.getLogger(RestaurantController.class);

    private final RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @Override
    public ResponseEntity<RestaurantDto> getRestaurantById(@PathVariable("restaurantId") Long restaurantId) throws Exception {
        return new ResponseEntity<>(RestaurantMapper.INSTANCE.map(restaurantService.getRestaurant(restaurantId)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<RestaurantDto>> getRestaurants() throws Exception {
        List<Restaurant> restaurants = restaurantService.getRestaurants();
        LOG.info("Found {} restaurants", restaurants.size());
        return new ResponseEntity<>(RestaurantMapper.INSTANCE.mapAll(restaurants), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<RestaurantDto> createRestaurant(@Valid @RequestBody RestaurantDto restaurantDto) throws Exception {
        Restaurant restaurant = RestaurantMapper.INSTANCE.map(restaurantDto);
        restaurant = restaurantService.createRestaurant(restaurant);
        return new ResponseEntity<>(RestaurantMapper.INSTANCE.map(restaurant), HttpStatus.OK);
    }
}
