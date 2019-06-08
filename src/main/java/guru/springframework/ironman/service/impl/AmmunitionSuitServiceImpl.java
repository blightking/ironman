package guru.springframework.ironman.service.impl;

import guru.springframework.ironman.domain.Ammunition;
import guru.springframework.ironman.domain.AmmunitionSuit;
import guru.springframework.ironman.domain.Suit;
import guru.springframework.ironman.repo.AmmunitionSuitRepository;
import guru.springframework.ironman.service.AmmunitionSuitService;
import org.springframework.stereotype.Service;

@Service
public class AmmunitionSuitServiceImpl implements AmmunitionSuitService {
    private final AmmunitionSuitRepository ammunitionSuitRepository;

    public AmmunitionSuitServiceImpl(AmmunitionSuitRepository ammunitionSuitRepository) {
        this.ammunitionSuitRepository = ammunitionSuitRepository;
    }

    @Override
    public void addSuitAmmunition(AmmunitionSuit ammunitionSuit) {
        ammunitionSuitRepository.save(ammunitionSuit);
    }

    @Override
    public boolean isExistSuitAmmunition(Suit suit, Ammunition ammunition) {
        AmmunitionSuit ammunitionSuit = ammunitionSuitRepository.findBySuitAndAmmunition(suit, ammunition);
        if (ammunitionSuit != null) {
            return true;
        }
        return false;
    }

}
