package guru.springframework.ironman.repo;

import guru.springframework.ironman.domain.Suit;
import org.springframework.data.repository.CrudRepository;

public interface SuitRepository extends CrudRepository<Suit, Long> {
    Suit findByModel(String modelName);
}
