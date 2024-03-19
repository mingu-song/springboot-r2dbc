package mingu.spring.springbootr2dbc.mapper;

import mingu.spring.springbootr2dbc.dto.ItemResource;
import mingu.spring.springbootr2dbc.dto.NewItemResource;
import mingu.spring.springbootr2dbc.model.Item;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", uses = {PersonMapper.class, TagMapper.class})
public abstract class ItemMapper {

    @Autowired
    private TagMapper tagMapper;

    public abstract ItemResource toResource(Item item);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    @Mapping(target = "version", ignore = true)
    @Mapping(target = "assignee", ignore = true)
    @Mapping(target = "tags", ignore = true)
    public abstract Item toModel(NewItemResource itemResource);

    @AfterMapping
    public void afterMapping(NewItemResource itemResource, @MappingTarget Item item) {
        item.setTags(tagMapper.toTags(itemResource.getTagIds()));
    }
}
