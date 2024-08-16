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
}
