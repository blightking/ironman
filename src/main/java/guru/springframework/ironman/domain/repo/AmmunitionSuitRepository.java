package guru.springframework.ironman.domain.repo;

import guru.springframework.ironman.domain.Ammunition;
import guru.springframework.ironman.domain.AmmunitionSuit;
import guru.springframework.ironman.domain.Suit;

import java.util.List;

public interface AmmunitionSuitRepository {
    List<AmmunitionSuit> findBySuitId(Long suitId);

    AmmunitionSuit findBySuitAndAmmunition(Suit suit, Ammunition ammunition);

    void save(AmmunitionSuit ammunitionSuit);
}
