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

    @Test
    public void getRatesPagination_returnsResponseEntityInstance() {
        Integer limit = 2;
        Integer page = 1;

        ResponseEntity<List<Rate>> result = controller.getRatesPagination(limit, page);
        Assertions.assertInstanceOf(ResponseEntity.class, result);
    }

    @Test
    public void getRatesPagination_returnsResponseEntityOKStatus() {
        Integer limit = 2;
        Integer page = 1;

        ResponseEntity<List<Rate>> result = controller.getRatesPagination(limit, page);
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void getRatesPagination_returnsResponseEntityWithRates() {
        List<Rate> rates = List.of(new Rate(), new Rate());
        Integer start = 2;
        Integer limit = 2;
        Integer page = 1;

        Mockito.when(rateService.getRatesPagination(start, limit)).thenReturn(rates);

        ResponseEntity<List<Rate>> result = controller.getRatesPagination(limit, page);
        Assertions.assertEquals(rates.size(), result.getData().size());
    }

    @Test
    public void getRateById_returnsResponseEntityInstance() {
        String rateId = "";

        ResponseEntity<Rate> result = controller.getRateById(rateId);
        Assertions.assertInstanceOf(ResponseEntity.class, result);
    }

    @Test
    public void getRateById_returnsResponseEntityOKStatus() {
        String rateId = "";

        ResponseEntity<Rate> result = controller.getRateById(rateId);
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void getRateById_returnsResponseEntityWithRates() {
        Rate rate = new Rate();
        String rateId = "";

        Mockito.when(rateService.getRateById(rateId)).thenReturn(rate);

        ResponseEntity<Rate> result = controller.getRateById(rateId);
        Assertions.assertEquals(rate, result.getData());
    }

    @Test
    public void addRate_returnsResponseEntityInstance() {
        ResponseEntity<String> result = controller.addRate(Mockito.anyMap());
        Assertions.assertInstanceOf(ResponseEntity.class, result);
    }

    @Test
    public void addRate_returnsResponseEntityOKStatus() {
        ResponseEntity<String> result = controller.addRate(Mockito.anyMap());
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void addRate_returnsResponseEntityWithId() {
        String rateId = "1254-14535";

        Mockito.when(rateService.addRate(Mockito.anyMap())).thenReturn(rateId);

        ResponseEntity<String> result = controller.addRate(Mockito.anyMap());
        Assertions.assertEquals(rateId, result.getData());
    }
}
