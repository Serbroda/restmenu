package de.rottstegge.restmenu.mapper;

import de.rottstegge.restmenu.model.Greeting;
import de.rottstegge.v1.model.GreetingDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface GreetingMapper {

    GreetingMapper INSTANCE = Mappers.getMapper(GreetingMapper.class);

    GreetingDto map(Greeting source);


}
