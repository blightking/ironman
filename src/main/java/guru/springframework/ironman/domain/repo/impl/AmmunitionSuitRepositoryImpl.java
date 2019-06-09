package guru.springframework.ironman.domain.repo.impl;

import guru.springframework.ironman.domain.Ammunition;
import guru.springframework.ironman.domain.AmmunitionSuit;
import guru.springframework.ironman.domain.Suit;
import guru.springframework.ironman.domain.repo.AmmunitionSuitRepository;
import guru.springframework.ironman.service.AmmunitionService;
import guru.springframework.ironman.service.SuitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AmmunitionSuitRepositoryImpl implements AmmunitionSuitRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private SuitService suitService;

    @Autowired
    private AmmunitionService ammunitionService;

    @Override
    public List<AmmunitionSuit> findBySuitId(Long suitId) {
        String SQL_GET_BY_ID = "SELECT asuit.id, asuit.suit_id, asuit.ammo_id, asuit.count FROM ammunition_suit as asuit " +
                "join suit on suit.id = asuit.suit_id WHERE suit.id = ?";
        Object[] param = new Object[]{suitId};
        try {
            return jdbcTemplate.query(
                    SQL_GET_BY_ID,
                    param,
                    new AmmunitionSuitMapper(suitService, ammunitionService)
            );
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public AmmunitionSuit findBySuitAndAmmunition(Suit suit, Ammunition ammunition) {
//        String SQL_GET_BY_ID = "SELECT * FROM ammunition_suit as asuit " +
//                "join suit on suit.id = asuit.suit_id join ammo on ammo.id = asuit.ammo_id " +
//                "WHERE suit.id = ? and ammo.id = ?";
        String SQL_GET_BY_SUIT_AND_AMMO = "SELECT id, suit_id, ammo_id, count FROM ammunition_suit " +
                "WHERE suit_id = ? and ammo_id = ?";
        Object[] param = new Object[]{suit.getId(), ammunition.getId()};
        try {
            return jdbcTemplate.queryForObject(
                    SQL_GET_BY_SUIT_AND_AMMO,
                    param,
                    new AmmunitionSuitMapper(suitService, ammunitionService)
            );
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }

    }

    @Override
    public void save(AmmunitionSuit ammunitionSuit) {
        String INSERT_SUIT_ASUIT = "insert into ammunition_suit(id, suit_id, ammo_id, count) values(?, ?, ?, ?)";
        Object[] params = new Object[]{
                ammunitionSuit.getId(),
                ammunitionSuit.getSuit().getId(),
                ammunitionSuit.getAmmunition().getId(),
                ammunitionSuit.getCount()
        };
        jdbcTemplate.update(INSERT_SUIT_ASUIT, params);
    }
}
