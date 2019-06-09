package guru.springframework.ironman.domain.repo;

import guru.springframework.ironman.domain.Suit;

import java.util.List;

public interface SuitRepository {
    Suit getSuitById(Long id);

    Suit save(Suit suit);

    Suit update(Suit suit);

    List<Suit> getAllSuit();

    void deleteById(Long id);

    Suit getSuitByModel(String model);

}
