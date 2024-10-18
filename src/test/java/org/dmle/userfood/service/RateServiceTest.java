package org.dmle.userfood.service;

import org.dmle.userfood.domain.Rate;
import org.dmle.userfood.domain.RateDTO;
import org.dmle.userfood.domain.User;
import org.dmle.userfood.domain.UserDTO;
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

        RateDTO rateDTO = new RateDTO();
        rateDTO.setName("Some Rate Name");
        rateDTO.setValue(1);
        rateDTO.setColorHex("Some Color Hex");

        Mockito.when(rateRepository.addRate(Mockito.any())).thenReturn(rateId);

        String result = service.addRate(rateDTO);

        Assertions.assertEquals(rateId, result);
    }

    @Test
    public void addRAte_nonValidName_throwsException() {
        String rateId = "123-293121";

        RateDTO rateDTO = new RateDTO();
        rateDTO.setValue(1);
        rateDTO.setColorHex("Some Color Hex");

        Mockito.when(rateRepository.addRate(Mockito.any())).thenReturn(rateId);

        Assertions.assertThrows(IllegalArgumentException.class, () -> service.addRate(rateDTO));
    }

    @Test
    public void addRate_nonValidValue_throwsException() {
        String rateId = "123-293121";

        RateDTO rateDTO = new RateDTO();
        rateDTO.setName("Some Rate Name");
        rateDTO.setColorHex("Some Color Hex");

        Mockito.when(rateRepository.addRate(Mockito.any())).thenReturn(rateId);

        Assertions.assertThrows(IllegalArgumentException.class, () -> service.addRate(rateDTO));
    }

    @Test
    public void addRate_nonValidColorHex_throwsException() {
        String rateId = "123-293121";

        RateDTO rateDTO = new RateDTO();
        rateDTO.setName("Some Rate Name");
        rateDTO.setValue(1);

        Mockito.when(rateRepository.addRate(Mockito.any())).thenReturn(rateId);

        Assertions.assertThrows(IllegalArgumentException.class, () -> service.addRate(rateDTO));
    }

    @Test
    public void addRate_rateNotExist_returnsRateId() {
        String rateId = "123-293121";

        RateDTO rateDTO = new RateDTO();
        rateDTO.setName("Some Rate Name");
        rateDTO.setValue(1);
        rateDTO.setColorHex("Some Color Hex");

        Mockito.when(rateRepository.addRate(Mockito.any())).thenReturn(rateId);

        String result = service.addRate(rateDTO);

        Assertions.assertEquals(result, rateId);
    }

    @Test
    public void addRate_rateExist_throwsException() {
        String rateId = "123-293121";

        RateDTO rateDTO = new RateDTO();
        rateDTO.setName("Some Rate Name");
        rateDTO.setValue(1);
        rateDTO.setColorHex("Some Color Hex");

        Rate existingRate = new Rate();
        existingRate.setId(rateId+"21");

        Mockito.when(rateRepository.getRateByName(Mockito.anyString())).thenReturn(existingRate);

        Assertions.assertThrows(IllegalArgumentException.class, () -> service.addRate(rateDTO));
    }

    @Test
    public void updateRate_returnsBooleanInstance() {
        Boolean result = true;
        String rateId = "123-293121";

        RateDTO rateDTO = new RateDTO();
        rateDTO.setName("Some Rate Name");
        rateDTO.setValue(1);
        rateDTO.setColorHex("Some Color Hex");

        Rate existingRate = new Rate();
        existingRate.setId(rateId);

        Mockito.when(rateRepository.updateRate(Mockito.anyString(), Mockito.any())).thenReturn(result);
        Mockito.when(rateRepository.getRateById(rateId)).thenReturn(existingRate);

        Boolean serviceResult = service.updateRate(rateId, rateDTO);

        Assertions.assertEquals(result, serviceResult);
    }

    @Test
    public void deleteRate_returnsBooleanInstance() {
        Mockito.when(rateRepository.getRateById(Mockito.anyString())).thenReturn(new Rate());

        Boolean result = service.deleteRate(Mockito.anyString());
        Assertions.assertInstanceOf(Boolean.class, result);
    }

    @Test
    public void deleteRate_nonExistRate_throwsException() {
        Mockito.when(rateRepository.getRateById(Mockito.anyString())).thenReturn(null);

        Assertions.assertThrows(IllegalArgumentException.class, () -> service.deleteRate(Mockito.anyString()));
    }

    @Test
    public void deleteRate_existRate_returnsTrue() {
        Boolean status = true;

        Mockito.when(rateRepository.getRateById(Mockito.anyString())).thenReturn(new Rate());
        Mockito.when(rateRepository.deleteRate(Mockito.anyString())).thenReturn(status);

        Boolean result = service.deleteRate(Mockito.anyString());
        Assertions.assertEquals(result, status);
    }
}