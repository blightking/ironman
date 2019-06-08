package guru.springframework.ironman.repo;

import guru.springframework.ironman.domain.Ammunition;
import org.springframework.data.repository.CrudRepository;

public interface AmmunitionRepository extends CrudRepository<Ammunition, Long> {
}
