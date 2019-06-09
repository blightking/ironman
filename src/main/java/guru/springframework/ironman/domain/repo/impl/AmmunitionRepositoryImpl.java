package guru.springframework.ironman.domain.repo.impl;

import guru.springframework.ironman.domain.Ammunition;
import guru.springframework.ironman.domain.repo.AmmunitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AmmunitionRepositoryImpl implements AmmunitionRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public Ammunition getAmmunitionById(Long id) {
        String SQL_GET_BY_ID = "SELECT * FROM ammo WHERE id = ?";
        Object[] param = new Object[]{id};
        return jdbcTemplate.queryForObject(
                SQL_GET_BY_ID,
                param,
                new AmmunitionMapper(jdbcTemplate)
        );
    }

    @Override
    public Ammunition save(Ammunition ammunition) {
        String INSERT_AMMO_QUERY = "insert into ammo(id, name, description, count) values(?, ?, ?, ?)";
        Object[] params = new Object[]{
                ammunition.getId(),
                ammunition.getName(),
                ammunition.getDescription(),
                ammunition.getCount()
        };
        jdbcTemplate.update(INSERT_AMMO_QUERY, params);
        return ammunition;
    }

    @Override
    public Ammunition update(Ammunition ammunition) {
        String QUERY_AMMO_UPDATE = "update ammo set name = ?, description = ?, count = ? where id = ?";
        Object[] params = new Object[]{
                ammunition.getName(),
                ammunition.getDescription(),
                ammunition.getCount(),
                ammunition.getId()
        };
        jdbcTemplate.update(QUERY_AMMO_UPDATE, params);
        return ammunition;
    }

    @Override
    public List<Ammunition> getAllAmmunition() {
        String SQL_GET_ALL_AMMO = "select * from ammo";
        return jdbcTemplate.query(SQL_GET_ALL_AMMO, new AmmunitionMapper(jdbcTemplate));
    }

    @Override
    public void deleteById(Long id) {
        String SQL_DELETE_AMMUNITION_SUIT_BY_SUIT_ID = "DELETE FROM ammunition_suit WHERE ammo_id = ?";
        String SQL_DELETE_AMMO = "DELETE FROM ammo WHERE ID = ?";

        Object[] params = new Object[]{id};
        jdbcTemplate.update(SQL_DELETE_AMMUNITION_SUIT_BY_SUIT_ID, params);
        jdbcTemplate.update(SQL_DELETE_AMMO, params);
    }
}
