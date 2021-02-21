package de.rottstegge.restmenu.controller.v1;

import de.rottstegge.restmenu.mapper.RestaurantMapper;
import de.rottstegge.restmenu.model.Restaurant;
import de.rottstegge.restmenu.repository.RestaurantRepository;
import de.rottstegge.v1.model.RestaurantDto;
import de.rottstegge.v1.server.RestaurantsApi;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class RestaurantController implements RestaurantsApi {

    private final RestaurantRepository restaurantRepository;

    public RestaurantController(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public ResponseEntity<RestaurantDto> getRestaurants(@Valid String name) throws Exception {
        Restaurant restaurant = new Restaurant();
        restaurant.setName(name);
        restaurant = restaurantRepository.save(restaurant);

        return new ResponseEntity<>(RestaurantMapper.INSTANCE.map(restaurant), HttpStatus.OK);
    }
}
