package guru.springframework.ironman.domain.repo.impl;

import guru.springframework.ironman.domain.AmmunitionSuit;
import guru.springframework.ironman.domain.Suit;
import guru.springframework.ironman.service.AmmunitionService;
import guru.springframework.ironman.service.SuitService;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SuitMapper implements RowMapper<Suit> {
    private final JdbcTemplate jdbcTemplate;

    public SuitMapper(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public Suit mapRow(ResultSet resultSet, int i) throws SQLException {
        Long suitId = Long.parseLong(resultSet.getString("id"));
        Suit suit = new Suit();
        suit.setId(suitId);
        suit.setModel(resultSet.getString("model"));
        String query = String.format("select * from ammunition_suit where suit_id = '%s'", suitId);
        List<AmmunitionSuit> ammunitionSuitList = jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(AmmunitionSuit.class));
        suit.setAmmunitionSuitList(ammunitionSuitList);
        return suit;
    }
}
