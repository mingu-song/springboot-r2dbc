package mingu.spring.springbootr2dbc.service;

import lombok.RequiredArgsConstructor;
import mingu.spring.springbootr2dbc.mapper.TagMapper;
import mingu.spring.springbootr2dbc.model.Item;
import mingu.spring.springbootr2dbc.repository.ItemRepository;
import mingu.spring.springbootr2dbc.repository.ItemTagRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final ItemTagRepository itemTagRepository;
    private final TagMapper tagMapper;

    @Transactional
    public Mono<Item> create(Item item) {
        if (item.getId() != null || item.getVersion() != null) {
            return Mono.error(new IllegalArgumentException("When creating an item, the id and version must be null"));
        }
        return itemRepository.save(item)
                .flatMap(savedItem ->
                        itemTagRepository.saveAll(tagMapper.toItemTags(savedItem.getId(), savedItem.getTags()))
                                .collectList()
                                .then(Mono.just(savedItem)));
    }
}
