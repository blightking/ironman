package guru.springframework.ironman.service;

import guru.springframework.ironman.domain.Suit;
import guru.springframework.ironman.service.asbt.BaseGenericService;

public interface SuitService extends BaseGenericService<Suit> {
    Suit findSuitByModel(String model);

    double percentCompleteSuit(Long suitId);
}
