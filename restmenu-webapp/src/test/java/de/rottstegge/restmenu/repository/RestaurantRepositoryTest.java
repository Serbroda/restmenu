package de.rottstegge.restmenu.repository;

import de.rottstegge.restmenu.model.Restaurant;
import de.rottstegge.restmenu.util.TransactionalProfileSpringBootTest;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@TransactionalProfileSpringBootTest
public class RestaurantRepositoryTest extends AbstractRepositoryTest<Restaurant> {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Override
    protected JpaRepository<Restaurant, Long> getRepository() {
        return restaurantRepository;
    }

    @Override
    protected Restaurant getCreateEntity() {
        Restaurant restaurant = new Restaurant();
        restaurant.setName("Musterrestaurant ABC");
        restaurant.setDescription("A italian restaurant");
        return restaurant;
    }

    @Override
    protected void modifyUpdateEntity(Restaurant entity) {
        entity.setName("Feinkost Laden");
    }

    @Override
    protected Example<Restaurant> getExample() {
        ExampleMatcher matcher = ExampleMatcher.matchingAny().withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());
        Restaurant restaurant = new Restaurant();
        restaurant.setName("Musterrestaurant ABC");
        return Example.of(restaurant, matcher);
    }

}
