package guru.springframework.ironman.service.impl;

import guru.springframework.ironman.domain.Ammunition;
import guru.springframework.ironman.exceptions.NotFoundException;
import guru.springframework.ironman.repo.AmmunitionRepository;
import guru.springframework.ironman.service.AmmunitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AmmunitionServiceImpl implements AmmunitionService {
    @Autowired
    private AmmunitionRepository ammunitionRepository;

    @Override
    public Ammunition getDataById(Long id) {
        Optional<Ammunition> ammunitionOptional = ammunitionRepository.findById(id);
        if (!ammunitionOptional.isPresent()) {
            throw new NotFoundException("ammunition not found");
        }
        return ammunitionOptional.get();
    }

    @Override
    public Ammunition save(Ammunition ammunition) {
        return ammunitionRepository.save(ammunition);
    }

    @Override
    public List<Ammunition> findAll() {
        return (List<Ammunition>) ammunitionRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        ammunitionRepository.deleteById(id);
    }
}
