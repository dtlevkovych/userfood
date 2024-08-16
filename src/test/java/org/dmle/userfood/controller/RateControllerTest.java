package org.dmle.userfood.controller;

import org.dmle.userfood.domain.Rate;
import org.dmle.userfood.domain.ResponseEntity;
import org.dmle.userfood.service.RateService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import java.util.List;

public class RateControllerTest {

    @InjectMocks
    private RateController controller;

    @Mock
    private RateService rateService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getRates_returnsResponseEntityInstance() {
        ResponseEntity<List<Rate>> result = controller.getRates();
        Assertions.assertInstanceOf(ResponseEntity.class, result);
    }

    @Test
    public void getRates_returnsResponseEntityOKStatus() {
        ResponseEntity<List<Rate>> result = controller.getRates();
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void getRates_returnsResponseEntityWithRates() {
        List<Rate> rates = List.of(new Rate(), new Rate());

        Mockito.when(rateService.getRates()).thenReturn(rates);

        ResponseEntity<List<Rate>> result = controller.getRates();
        Assertions.assertEquals(rates.size(), result.getData().size());
    }
}
