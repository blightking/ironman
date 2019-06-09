package guru.springframework.ironman.domain.repo.impl;

import guru.springframework.ironman.domain.AmmunitionSuit;
import guru.springframework.ironman.service.AmmunitionService;
import guru.springframework.ironman.service.SuitService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AmmunitionSuitMapper implements RowMapper<AmmunitionSuit> {
    private final SuitService suitService;
    private final AmmunitionService ammunitionService;

    public AmmunitionSuitMapper(SuitService suitService, AmmunitionService ammunitionService) {
        this.suitService = suitService;
        this.ammunitionService = ammunitionService;
    }

    @Override
    public AmmunitionSuit mapRow(ResultSet resultSet, int i) throws SQLException {
            AmmunitionSuit ammunitionSuit = new AmmunitionSuit();
            ammunitionSuit.setId(resultSet.getLong("id"));
            ammunitionSuit.setSuit(suitService.getDataById(resultSet.getLong("suit_id")));
            ammunitionSuit.setAmmunition(ammunitionService.getDataById(resultSet.getLong("ammo_id")));
            ammunitionSuit.setCount(resultSet.getInt("count"));
            return ammunitionSuit;
    }
}
