package mingu.spring.springbootr2dbc.dto;

import lombok.Data;
import lombok.experimental.Accessors;
import mingu.spring.springbootr2dbc.model.ItemStatus;
import mingu.spring.springbootr2dbc.model.Tag;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Accessors(chain = true)
public class ItemResource {
    private Long id;
    private Long version;

    private String description;
    private ItemStatus status;

    private PersonResource assignee;
    private List<Tag> tags;

    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;
}
