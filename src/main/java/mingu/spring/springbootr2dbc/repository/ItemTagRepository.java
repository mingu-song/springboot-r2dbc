package mingu.spring.springbootr2dbc.repository;

import mingu.spring.springbootr2dbc.model.ItemTag;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ItemTagRepository extends ReactiveCrudRepository<ItemTag, Long> {

    Flux<ItemTag> findAllByItemId(Long itemId);

    Mono<ItemTag> deleteAllByItemId(Long itemId);
}
