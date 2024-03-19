package mingu.spring.springbootr2dbc.mapper;

import mingu.spring.springbootr2dbc.dto.PersonResource;
import mingu.spring.springbootr2dbc.model.Person;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    PersonResource toResource(Person person);
}
