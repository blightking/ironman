package guru.springframework.ironman.repo;

import guru.springframework.ironman.domain.Ammunition;
import guru.springframework.ironman.domain.AmmunitionSuit;
import guru.springframework.ironman.domain.Suit;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AmmunitionSuitRepository extends CrudRepository<AmmunitionSuit, Long> {
    List<AmmunitionSuit> findBySuitId(Long suitId);

    AmmunitionSuit findBySuitAndAmmunition(Suit suit, Ammunition ammunition);
}
