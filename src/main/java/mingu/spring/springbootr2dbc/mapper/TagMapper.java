package mingu.spring.springbootr2dbc.mapper;

import mingu.spring.springbootr2dbc.dto.TagResource;
import mingu.spring.springbootr2dbc.model.ItemTag;
import mingu.spring.springbootr2dbc.model.Tag;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface TagMapper {

    TagResource toResource(Tag person);

    default List<Tag> toTags(Collection<Long> tagsId) {
        if (tagsId == null) return new ArrayList<>();

        return tagsId.stream()
                .map(tagId -> new Tag().setId(tagId))
                .collect(Collectors.toList());
    }

    default Collection<ItemTag> toItemTags(Long itemId, Collection<Tag> tags) {
        if (tags == null) return new LinkedHashSet<>();
        return tags.stream()
                .map(tag -> new ItemTag(itemId, tag.getId()))
                .collect(Collectors.toList());
    }

}
