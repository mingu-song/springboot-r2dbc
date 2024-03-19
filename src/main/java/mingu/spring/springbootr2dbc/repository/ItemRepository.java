package mingu.spring.springbootr2dbc.repository;

import mingu.spring.springbootr2dbc.model.Item;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends ReactiveCrudRepository<Item, Long> {
}
