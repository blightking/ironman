package guru.springframework.ironman.domain.repo.impl;

import guru.springframework.ironman.domain.Ammunition;
import guru.springframework.ironman.domain.AmmunitionSuit;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AmmunitionMapper implements RowMapper<Ammunition> {
    private final JdbcTemplate jdbcTemplate;

    public AmmunitionMapper(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Ammunition mapRow(ResultSet resultSet, int i) throws SQLException {
        Long ammunitionId = Long.parseLong(resultSet.getString("id"));
        Ammunition ammunition = new Ammunition();
        ammunition.setId(ammunitionId);
        ammunition.setName(resultSet.getString("name"));
        ammunition.setDescription(resultSet.getString("description"));
        String query = String.format("select * from ammunition_suit where ammo_id = '%s'", ammunitionId);
        List<AmmunitionSuit> ammunitionSuitList = jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(AmmunitionSuit.class));
        ammunition.setAmmunitionSuitList(ammunitionSuitList);
        ammunition.setCount(Integer.parseInt(resultSet.getString("count")));
        return ammunition;
    }
}
