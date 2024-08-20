package org.dmle.userfood.repo;

import org.dmle.userfood.domain.Rate;
import org.dmle.userfood.domain.RateRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
public class RateRepositoryImpl implements RateRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Rate> getRates() {
        String sql = "select * from rate";
        try {
            return jdbcTemplate.query(sql, new RateRowMapper());
        } catch (EmptyResultDataAccessException ignored) {}
        return Collections.emptyList();
    }

    @Override
    public List<Rate> getRatesPagination(Integer start, Integer limit) {
        String sql = "select * from rate order by value limit ?,?";
        try {
            return jdbcTemplate.query(sql , new RateRowMapper(), start, limit);
        } catch (EmptyResultDataAccessException ignored) {}
        return Collections.emptyList();
    }

    @Override
    public Rate getRateById(String rateId) {
        String sql = "select * from rate where id=? limit 0,1";
        try {
            return jdbcTemplate.queryForObject(sql, new RateRowMapper(), rateId);
        } catch (EmptyResultDataAccessException ignored) {}
        return null;
    }
}
