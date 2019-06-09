package guru.springframework.ironman.service.impl;

import guru.springframework.ironman.domain.Suit;
import guru.springframework.ironman.domain.repo.SuitRepository;
import guru.springframework.ironman.exceptions.NotFoundException;
import guru.springframework.ironman.service.SuitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SuitServiceImpl implements SuitService {

    //    private final SuitRepository suitRepository;
//
//    public SuitServiceImpl(SuitRepository suitRepository) {
//        this.suitRepository = suitRepository;
//    }
    @Autowired
    private SuitRepository suitRepository;

    @Override
    public Suit getDataById(Long id) {
        Suit suit = suitRepository.getSuitById(id);
        if (suit == null) {
            throw new NotFoundException("Suit not found");
        }
        return suit;
    }

    @Override
    public Suit save(Suit suit) {
        return suitRepository.save(suit);
    }

    @Override
    public List<Suit> findAll() {
        return suitRepository.getAllSuit();
    }

    @Override
    public void deleteById(Long id) {
        suitRepository.deleteById(id);
    }

    @Override
    public Suit update(Suit suit) {
        return suitRepository.update(suit);
    }

    @Override
    public Suit findSuitByModel(String model) {
        Suit suit = suitRepository.getSuitByModel(model);
        if (suit == null) {
            throw new NotFoundException("Suit not found");
        }
        return suit;
    }
}
