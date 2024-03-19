package mingu.spring.springbootr2dbc.controller;


import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mingu.spring.springbootr2dbc.dto.NewItemResource;
import mingu.spring.springbootr2dbc.mapper.ItemMapper;
import mingu.spring.springbootr2dbc.service.ItemService;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/items")
@RequiredArgsConstructor
@Slf4j
public class ItemController {

    private final ItemService itemService;
    private final ItemMapper itemMapper;

    @Operation(summary = "Create a new item")
    @PostMapping
    public Mono<ResponseEntity<Void>> create(@Valid @RequestBody final NewItemResource newItemResource) {
        log.info("NewItemResource = {}", newItemResource);
        return itemService.create(itemMapper.toModel(newItemResource))
                .map(item -> ResponseEntity.created(WebMvcLinkBuilder.linkTo(ItemController.class).slash(item.getId()).toUri()).build());
    }
}
