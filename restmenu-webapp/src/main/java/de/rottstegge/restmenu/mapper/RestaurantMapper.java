package de.rottstegge.restmenu.mapper;

import de.rottstegge.restmenu.model.Restaurant;
import de.rottstegge.v1.model.RestaurantCreateDto;
import de.rottstegge.v1.model.RestaurantDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface RestaurantMapper {

    RestaurantMapper INSTANCE = Mappers.getMapper(RestaurantMapper.class);

    RestaurantDto map(Restaurant source);

    List<RestaurantDto> mapAll(List<Restaurant> source);

    Restaurant map(RestaurantDto source);

    Restaurant map(RestaurantCreateDto source);

}
