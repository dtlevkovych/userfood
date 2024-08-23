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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public void getRatesPagination_returnsListWithRates() {
        List<Rate> rates = List.of(new Rate(), new Rate());
        Integer start = 2;
        Integer limit = 2;

        Mockito.when(rateRepository.getRatesPagination(start, limit)).thenReturn(rates);

        List<Rate> result = service.getRatesPagination(start, limit);
        Assertions.assertEquals(rates.size(), result.size());
    }

    @Test
    public void getRateById_returnsListInstance() {
        String rateId = "";

        Mockito.when(rateRepository.getRateById(rateId)).thenReturn(new Rate());

        Rate result = service.getRateById(rateId);
        Assertions.assertInstanceOf(Rate.class, result);
    }

    @Test
    public void getRateById_returnsListWithRates() {
        Rate rate = new Rate();

        Mockito.when(rateRepository.getRateById(Mockito.anyString())).thenReturn(rate);

        Rate result = service.getRateById("");
        Assertions.assertEquals(rate, result);
    }

    @Test
    public void addRate_validRate_returnsNewRateId() {
        String rateId = "123-293121";

        Map<String, Object> rateMap = new HashMap<>();
        rateMap.put(RateServiceImpl.NAME, "Some Rate Name");
        rateMap.put(RateServiceImpl.VALUE, 1);
        rateMap.put(RateServiceImpl.COLOR_HEX, "Some Color Hex");

        Mockito.when(rateRepository.addRate(Mockito.any())).thenReturn(rateId);

        String result = service.addRate(rateMap);

        Assertions.assertEquals(rateId, result);
    }

    @Test
    public void addRAte_nonValidName_throwsException() {
        String rateId = "123-293121";

        Map<String, Object> rateMap = new HashMap<>();
        rateMap.put(RateServiceImpl.VALUE, 1);
        rateMap.put(RateServiceImpl.COLOR_HEX, "Some Color Hex");

        Mockito.when(rateRepository.addRate(Mockito.any())).thenReturn(rateId);

        Assertions.assertThrows(IllegalArgumentException.class, () -> service.addRate(rateMap));
    }

    @Test
    public void addRate_nonValidValue_throwsException() {
        String rateId = "123-293121";

        Map<String, Object> rateMap = new HashMap<>();
        rateMap.put(RateServiceImpl.NAME, "Some Rate Name");
        rateMap.put(RateServiceImpl.COLOR_HEX, "Some Color Hex");

        Mockito.when(rateRepository.addRate(Mockito.any())).thenReturn(rateId);

        Assertions.assertThrows(IllegalArgumentException.class, () -> service.addRate(rateMap));
    }

    @Test
    public void addRate_nonValidColorHex_throwsException() {
        String rateId = "123-293121";

        Map<String, Object> rateMap = new HashMap<>();
        rateMap.put(RateServiceImpl.NAME, "Some Rate Name");
        rateMap.put(RateServiceImpl.VALUE, 1);

        Mockito.when(rateRepository.addRate(Mockito.any())).thenReturn(rateId);

        Assertions.assertThrows(IllegalArgumentException.class, () -> service.addRate(rateMap));
    }

    @Test
    public void addRate_validRateNotExist_returnsRateId() {
        String rateId = "123-293121";

        Map<String, Object> rateMap = new HashMap<>();
        rateMap.put(RateServiceImpl.NAME, "Some Rate Name");
        rateMap.put(RateServiceImpl.VALUE, 1);
        rateMap.put(RateServiceImpl.COLOR_HEX, "Some Color Hex");

        Mockito.when(rateRepository.addRate(Mockito.any())).thenReturn(rateId);

        String result = service.addRate(rateMap);

        Assertions.assertEquals(result, rateId);
    }

    @Test
    public void addRate_nonValidRateExist_throwsException() {
        String rateId = "123-293121";

        Map<String, Object> rateMap = new HashMap<>();
        rateMap.put(RateServiceImpl.NAME, "Some Rate Name");
        rateMap.put(RateServiceImpl.COLOR_HEX, "Some Color Hex");

        Mockito.when(rateRepository.addRate(Mockito.any())).thenReturn(rateId);

        Assertions.assertThrows(IllegalArgumentException.class, () -> service.addRate(rateMap));
    }

    @Test
    public void checkIfExistRate_existName_throwsException() {
        Mockito.when(rateRepository.getRateByName(Mockito.anyString())).thenReturn(new Rate());

        Assertions.assertThrows(IllegalArgumentException.class, () -> service.checkIfExistRate("", 1));
    }

    @Test
    public void checkIfExistRate_existValue_throwsException() {
        Mockito.when(rateRepository.getRateByValue(Mockito.anyInt())).thenReturn(new Rate());

        Assertions.assertThrows(IllegalArgumentException.class, () -> service.checkIfExistRate("", 1));
    }
}