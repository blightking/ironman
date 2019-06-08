package guru.springframework.ironman.service;

import guru.springframework.ironman.domain.Ammunition;
import guru.springframework.ironman.domain.AmmunitionSuit;
import guru.springframework.ironman.domain.Suit;

public interface AmmunitionSuitService {
    void addSuitAmmunition(AmmunitionSuit ammunitionSuit);
    boolean isExistSuitAmmunition(Suit suit, Ammunition ammunition);
}
