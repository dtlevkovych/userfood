package org.dmle.userfood.domain;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RateReportRowMapper implements RowMapper<RateReport> {
    @Override
    public RateReport mapRow(ResultSet rs, int rowNum) throws SQLException {

        RateReport rateReport = new RateReport();
        rateReport.setId(rs.getString("id"));
        rateReport.setName(rs.getString("name"));
        rateReport.setValue(rs.getInt("value"));
        rateReport.setColorHex(rs.getString("color_hex"));
        rateReport.setCreatedAt(rs.getLong("created_at"));
        rateReport.setCount(rs.getInt("count"));

        return rateReport;
    }
}
