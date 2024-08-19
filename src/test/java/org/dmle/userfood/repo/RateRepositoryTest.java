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
}
