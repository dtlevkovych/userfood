package org.dmle.userfood.domain;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RateRowMapper implements RowMapper<Rate> {
    @Override
    public Rate mapRow(ResultSet rs, int rowNum) throws SQLException {

        Rate rate = new Rate();
        rate.setId(rs.getString("id"));
        rate.setName(rs.getString("name"));
        rate.setValue(rs.getInt("value"));
        rate.setColorHex(rs.getString("color_hex"));
        rate.setCreatedAt(rs.getLong("created_at"));

        return rate;
    }
}