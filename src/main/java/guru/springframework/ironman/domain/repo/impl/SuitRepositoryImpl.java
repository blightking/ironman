package guru.springframework.ironman.domain.repo.impl;

import guru.springframework.ironman.domain.Suit;
import guru.springframework.ironman.domain.repo.SuitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class SuitRepositoryImpl implements SuitRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Suit getSuitById(Long id) {
        String SQL_GET_BY_ID = "SELECT id, model FROM suit WHERE id = ?";
        Object[] param = new Object[]{id};
        return jdbcTemplate.queryForObject(
                SQL_GET_BY_ID,
                param,
                new SuitMapper(jdbcTemplate)
        );
    }

    @Override
    public Suit save(Suit suit) {
        String INSERT_SUIT_QUERY = "insert into suit(id, model) values(?, ?)";
        Object[] params = new Object[]{suit.getId(), suit.getModel()};
        jdbcTemplate.update(INSERT_SUIT_QUERY, params);
        return suit;
    }

    @Override
    public Suit update(Suit suit) {
        String QUERY_SUIT_UPDATE = "update suit set model = ? where id = ?";
        Object[] params = new Object[]{suit.getModel(), suit.getId()};
        jdbcTemplate.update(QUERY_SUIT_UPDATE, params);
        return suit;
    }

    @Override
    public List<Suit> getAllSuit() {
        String SQL_GET_ALL_SUIT = "select * from suit";
        return jdbcTemplate.query(SQL_GET_ALL_SUIT, new SuitMapper(jdbcTemplate));
    }

    @Override
    public void deleteById(Long id) {
        String SQL_DELETE_AMMUNITION_SUIT_BY_SUIT_ID = "DELETE FROM ammunition_suit WHERE suit_id = ?";
        String SQL_DELETE_SUIT = "DELETE FROM suit WHERE ID = ?";

        Object[] params = new Object[]{id};
        jdbcTemplate.update(SQL_DELETE_AMMUNITION_SUIT_BY_SUIT_ID, params);
        jdbcTemplate.update(SQL_DELETE_SUIT, params);
    }

    @Override
    public Suit getSuitByModel(String model) {
        String SQL_GET_BY_ID = "SELECT * FROM suit WHERE model = ?";
        Object[] param = new Object[]{model};
        return jdbcTemplate.queryForObject(
                SQL_GET_BY_ID,
                param,
                new SuitMapper(jdbcTemplate)
        );
    }
}
