package de.rottstegge.restmenu.mapper;

import de.rottstegge.restmenu.model.Restaurant;
import de.rottstegge.v1.model.RestaurantDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RestaurantMapper {

    RestaurantMapper INSTANCE = Mappers.getMapper(RestaurantMapper.class);

    RestaurantDto map(Restaurant source);

}
