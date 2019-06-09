package guru.springframework.ironman.service.impl;

import guru.springframework.ironman.domain.Ammunition;
import guru.springframework.ironman.domain.repo.AmmunitionRepository;
import guru.springframework.ironman.exceptions.NotFoundException;
import guru.springframework.ironman.service.AmmunitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AmmunitionServiceImpl implements AmmunitionService {
//    @Autowired
//    private AmmunitionRepository ammunitionRepository;

    //    @Autowired
//    private AmmunitionSuitService ammunitionSuitService;
    @Autowired
    private AmmunitionRepository ammunitionRepository;

    @Override
    public Ammunition getDataById(Long id) {
        Ammunition ammunition = ammunitionRepository.getAmmunitionById(id);
        if (ammunition == null) {
            throw new NotFoundException("ammunition not found");
        }
        return ammunition;
    }

    @Override
    public Ammunition save(Ammunition ammunition) {
        return ammunitionRepository.save(ammunition);
    }

    @Override
    public List<Ammunition> findAll() {
        return ammunitionRepository.getAllAmmunition();
    }

    @Override
    public void deleteById(Long id) {
        ammunitionRepository.deleteById(id);
    }

    @Override
    public Ammunition update(Ammunition ammunition) {
        return ammunitionRepository.update(ammunition);
    }

}
