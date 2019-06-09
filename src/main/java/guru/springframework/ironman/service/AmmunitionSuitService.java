package guru.springframework.ironman.service;

import guru.springframework.ironman.domain.Ammunition;
import guru.springframework.ironman.domain.AmmunitionSuit;
import guru.springframework.ironman.domain.Suit;

import java.util.List;

public interface AmmunitionSuitService {
    void addSuitAmmunition(AmmunitionSuit ammunitionSuit);

    boolean isExistSuitAmmunition(Suit suit, Ammunition ammunition);

    List<AmmunitionSuit> findById(Long id);

    List<Ammunition> loadAllAmmunitionBySuit(Suit suit);

    double percentCompleteSuitWithAmmunition(Long suitId);

    int countLackOfAmmunition(AmmunitionSuit ammunitionSuit);

    AmmunitionSuit getAmmoSuitBySuitIdAndAmmoId(Suit suit, Ammunition ammunition);
}
