package guru.springframework.ironman.service.impl;

import guru.springframework.ironman.domain.AmmunitionSuit;
import guru.springframework.ironman.domain.Suit;
import guru.springframework.ironman.exceptions.NotFoundException;
import guru.springframework.ironman.repo.AmmunitionRepository;
import guru.springframework.ironman.repo.AmmunitionSuitRepository;
import guru.springframework.ironman.repo.SuitRepository;
import guru.springframework.ironman.service.SuitService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SuitServiceImpl implements SuitService {

    private final SuitRepository suitRepository;
    private final AmmunitionSuitRepository ammunitionSuitRepository;

    public SuitServiceImpl(SuitRepository suitRepository, AmmunitionSuitRepository ammunitionSuitRepository) {
        this.suitRepository = suitRepository;
        this.ammunitionSuitRepository = ammunitionSuitRepository;
    }

    @Override
    public Suit getDataById(Long id) {
        Optional<Suit> suitOptional = suitRepository.findById(id);
        if (!suitOptional.isPresent()) {
            throw new NotFoundException("Suit not found");
        }
        return suitOptional.get();
    }

    @Override
    public Suit save(Suit suit) {
        return suitRepository.save(suit);
    }

    @Override
    public List<Suit> findAll() {
        return (List<Suit>) suitRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        suitRepository.deleteById(id);
    }

    @Override
    public Suit findSuitByModel(String model) {
        Suit suit = suitRepository.findByModel(model);
        if (suit == null) {
            throw new NotFoundException("Suit not found");
        }
        return suit;
    }

    @Override
    public double percentCompleteSuit(Long suitId) {
        List<AmmunitionSuit> ammunitionSuitList = ammunitionSuitRepository.findBySuitId(suitId);
        double percentSum = 0;
//        double result = 0;

        for (AmmunitionSuit ammunitionSuit : ammunitionSuitList) {
            if (ammunitionSuit.getCount() <= ammunitionSuit.getAmmunition().getCount()) {
                percentSum += 100;
            } else {
                double percentForAmmo = (ammunitionSuit.getAmmunition().getCount() * 100) / ammunitionSuit.getCount();
                percentSum += percentForAmmo;
            }
        }
        return (percentSum / (ammunitionSuitList.size() * 100)) * 100;
    }


}
