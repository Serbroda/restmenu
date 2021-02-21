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
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class RestaurantController implements RestaurantsApi {

    private final RestaurantRepository restaurantRepository;

    public RestaurantController(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public ResponseEntity<RestaurantDto> getRestaurantById(Long restaurantId) throws Exception {
        return new ResponseEntity<>(RestaurantMapper.INSTANCE.map(restaurantRepository.getOne(restaurantId)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<RestaurantDto>> getRestaurants() throws Exception {
        return new ResponseEntity<>(RestaurantMapper.INSTANCE.mapAll(restaurantRepository.findAll()), HttpStatus.OK);
    }
}
