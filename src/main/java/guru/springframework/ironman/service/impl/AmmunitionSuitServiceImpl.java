package guru.springframework.ironman.service.impl;

import guru.springframework.ironman.domain.Ammunition;
import guru.springframework.ironman.domain.AmmunitionSuit;
import guru.springframework.ironman.domain.Suit;
import guru.springframework.ironman.domain.repo.AmmunitionSuitRepository;
import guru.springframework.ironman.exceptions.NotFoundException;
import guru.springframework.ironman.service.AmmunitionSuitService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<AmmunitionSuit> findById(Long id) {
        return ammunitionSuitRepository.findBySuitId(id);
    }

    @Override
    public List<Ammunition> loadAllAmmunitionBySuit(Suit suit) {
        List<AmmunitionSuit> ammunitionSuitList = findById(suit.getId());
        List<Ammunition> result = new ArrayList<>();

        for (AmmunitionSuit ammunitionSuit : ammunitionSuitList) {
            result.add(ammunitionSuit.getAmmunition());
        }
        return result;
    }

    @Override
    public double percentCompleteSuitWithAmmunition(Long suitId) {
        List<AmmunitionSuit> ammunitionSuitList = findById(suitId);
        double percentSum = 0;
        for (AmmunitionSuit ammunitionSuit : ammunitionSuitList) {
            if (ammunitionSuit.getCount() <= ammunitionSuit.getAmmunition().getCount()) {
                percentSum += 100;
            } else if (ammunitionSuit.getAmmunition().getCount() > 0 && ammunitionSuit.getAmmunition().getCount() < ammunitionSuit.getCount()) {
                double percentForAmmo = (ammunitionSuit.getAmmunition().getCount() * 100) / ammunitionSuit.getCount();
                percentSum += percentForAmmo;
            }
        }
        if (percentSum == 0) {
            return 0;
        }
        return (percentSum / (ammunitionSuitList.size() * 100)) * 100;
    }

    @Override
    public int countLackOfAmmunition(AmmunitionSuit ammunitionSuit) {
        return ammunitionSuit.getCount() - ammunitionSuit.getAmmunition().getCount();
    }

    @Override
    public AmmunitionSuit getAmmoSuitBySuitIdAndAmmoId(Suit suit, Ammunition ammunition) {
        AmmunitionSuit ammunitionSuit = ammunitionSuitRepository.findBySuitAndAmmunition(suit, ammunition);
        if (ammunitionSuit == null) {
            throw new NotFoundException("Ammunition not found");
        }
        return ammunitionSuit;
    }

}
