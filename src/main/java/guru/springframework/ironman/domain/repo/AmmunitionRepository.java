package guru.springframework.ironman.domain.repo;

import guru.springframework.ironman.domain.Ammunition;

import java.util.List;

public interface AmmunitionRepository {
    Ammunition getAmmunitionById(Long id);

    Ammunition save(Ammunition ammunition);

    Ammunition update(Ammunition ammunition);

    List<Ammunition> getAllAmmunition();

    void deleteById(Long id);

}
