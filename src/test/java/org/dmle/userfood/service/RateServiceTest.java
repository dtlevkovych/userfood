package org.dmle.userfood.service;

import org.dmle.userfood.domain.Rate;
import org.dmle.userfood.repo.RateRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class RateServiceTest {

    private static final Logger log = LoggerFactory.getLogger(UserServiceTest.class);

    @InjectMocks
    private RateServiceImpl service;

    @Mock
    private RateRepository rateRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getRates_returnsListInstance() {
        List<Rate> result = service.getRates();
        Assertions.assertInstanceOf(List.class, result);
    }

    @Test
    public void getRates_returnsListWithRates() {
        List<Rate> rates = List.of(new Rate(), new Rate());

        Mockito.when(rateRepository.getRates()).thenReturn(rates);

        List<Rate> result = service.getRates();
        Assertions.assertEquals(rates.size(), result.size());
    }

    @Test
    public void getRatesPagination_returnsListInstance() {
        Integer start = 2;
        Integer limit = 2;

        List<Rate> result = service.getRatesPagination(start, limit);
        Assertions.assertInstanceOf(List.class, result);
    }

    @Test
    public void getRatesPagination_returnsListWitRates() {
        List<Rate> rates = List.of(new Rate(), new Rate());
        Integer start = 2;
        Integer limit = 2;

        Mockito.when(rateRepository.getRatesPagination(start, limit)).thenReturn(rates);

        List<Rate> result = service.getRatesPagination(start, limit);
        Assertions.assertEquals(rates.size(), result.size());
    }
}
