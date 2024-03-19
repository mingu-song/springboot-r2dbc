package mingu.spring.springbootr2dbc.repository;

import mingu.spring.springbootr2dbc.model.Person;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends ReactiveCrudRepository<Person, Long> {
}
