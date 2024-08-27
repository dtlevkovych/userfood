package org.dmle.userfood.repo;

import org.dmle.userfood.domain.Rate;
import org.dmle.userfood.domain.RateRowMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class RateRepositoryTest {

    @InjectMocks
    private RateRepositoryImpl repository;

    @Mock
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getRates_returnsListInstance() {
        List<Rate> result = repository.getRates();
        Assertions.assertInstanceOf(List.class, result);
    }

    @Test
    public void getRates_returnsListWithRates() {
        List<Rate> rates = List.of(new Rate(), new Rate());

        Mockito.when(jdbcTemplate.query(Mockito.anyString(), Mockito.any(RateRowMapper.class))).thenReturn(rates);

        List<Rate> result = repository.getRates();

        Assertions.assertEquals(rates.size(), result.size());
    }

    @Test
    public void getRatesPagination_returnsListInstance() {
        Integer start = 2;
        Integer limit = 2;

        List<Rate> result = repository.getRatesPagination(start, limit);
        Assertions.assertInstanceOf(List.class, result);
    }

    @Test
    public void getRatesPagination_returnsListWithRates() {
        List<Rate> rates = List.of(new Rate(), new Rate());
        Integer start = 2;
        Integer limit = 2;

        Mockito.when(jdbcTemplate.query(Mockito.anyString(), Mockito.any(RateRowMapper.class), Mockito.any(Object[].class))).thenReturn(rates);

        List<Rate> result = repository.getRatesPagination(start, limit);
        Assertions.assertEquals(rates.size(), result.size());
    }

    @Test
    public void getRateById_returnsRateInstance() {
        Mockito.when(jdbcTemplate.queryForObject(Mockito.anyString(), Mockito.any(RateRowMapper.class), Mockito.any(Object[].class))).thenReturn(new Rate());

        Rate result = repository.getRateById("");
        Assertions.assertInstanceOf(Rate.class, result);
    }

    @Test
    public void getRateById_returnsListWithRates() {
        Rate rate = new Rate();
        String rateId = "";

        Mockito.when(jdbcTemplate.queryForObject(Mockito.anyString(), Mockito.any(RateRowMapper.class), Mockito.any(Object[].class))).thenReturn(rate);

        Rate result = repository.getRateById(rateId);
        Assertions.assertEquals(rate, result);
    }

    @Test
    public void addRate_returnsStringInstance() {
        Rate newRate = new Rate();

        String result = repository.addRate(newRate);
        Assertions.assertInstanceOf(String.class, result);
    }

    @Test
    public void addRate_returnsRateId() {
        Rate newRate = new Rate();

        Mockito.when(jdbcTemplate.update(Mockito.anyString(), Mockito.anyString(), Mockito.anyInt(), Mockito.anyString(), Mockito.anyLong())).thenReturn(1);

        String result = repository.addRate(newRate);
        Assertions.assertNotNull(result);
    }

    @Test
    public void getRateByName_returnsRateInstance() {
        Mockito.when(jdbcTemplate.queryForObject(Mockito.anyString(), Mockito.any(RateRowMapper.class), Mockito.any(Object[].class))).thenReturn(new Rate());

        Rate result = repository.getRateByName("");
        Assertions.assertInstanceOf(Rate.class, result);
    }

    @Test
    public void getRateByName_returnsRate() {
        Rate rate = new Rate();

        Mockito.when(jdbcTemplate.queryForObject(Mockito.anyString(), Mockito.any(RateRowMapper.class), Mockito.any(Object[].class))).thenReturn(rate);

        Rate result = repository.getRateByName("");
        Assertions.assertEquals(rate, result);
    }

    @Test
    public void getRateByValue_returnsRateInstance() {
        Mockito.when(jdbcTemplate.queryForObject(Mockito.anyString(), Mockito.any(RateRowMapper.class), Mockito.any(Object[].class))).thenReturn(new Rate());

        Rate result = repository.getRateByValue(1);
        Assertions.assertInstanceOf(Rate.class, result);
    }

    @Test
    public void getRateByValue_returnsRate() {
        Rate rate = new Rate();

        Mockito.when(jdbcTemplate.queryForObject(Mockito.anyString(), Mockito.any(RateRowMapper.class), Mockito.any(Object[].class))).thenReturn(rate);

        Rate result = repository.getRateByValue(1);
        Assertions.assertEquals(rate, result);
    }

    @Test
    public void updateRate_returnsBooleanInstance() {
        Boolean result = repository.updateRate("", new Rate());
        Assertions.assertInstanceOf(Boolean.class, result);
    }

    @Test
    public void updateRate_returnsTrue() {
        Mockito.when(jdbcTemplate.update(Mockito.anyString(), Mockito.anyString(), Mockito.anyInt(), Mockito.anyString())).thenReturn(1);

        Boolean result = repository.updateRate("", new Rate());
        Assertions.assertEquals(result, true);
    }

    @Test
    public void deleteRate_returnsBooleanInstance() {
        Boolean result = repository.deleteRate("");
        Assertions.assertInstanceOf(Boolean.class, result);
    }

    @Test
    public void deleteRate_returnsTrue() {
        Mockito.when(jdbcTemplate.update(Mockito.anyString(), Mockito.anyString())).thenReturn(1);

        Boolean result = repository.deleteRate("");
        Assertions.assertEquals(result, true);
    }
}