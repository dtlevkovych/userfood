package org.dmle.userfood.service;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.micrometer.common.util.StringUtils;
import org.apache.commons.collections4.MapUtils;
import org.dmle.userfood.domain.Rate;
import org.dmle.userfood.domain.RateDTO;
import org.dmle.userfood.domain.User;
import org.dmle.userfood.repo.RateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Service
public class RateServiceImpl implements RateService {

    @Autowired
    private RateRepository rateRepository;

    @Autowired
    private MapperService mapperService;

    @Override
    public List<Rate> getRates() {
        return rateRepository.getRates();
    }

    @Override
    public List<Rate> getRatesPagination(Integer start, Integer limit) {
        return rateRepository.getRatesPagination(start, limit);
    }

    @Override
    public Rate getRateById(String rateId) {
        return rateRepository.getRateById(rateId);
    }

    @Override
    public String addRate(RateDTO newRate) {
        return rateRepository.addRate(validateRate(newRate));
    }

    @Override
    public Boolean updateRate(String rateId, RateDTO updateRate) {
        return rateRepository.updateRate(rateId, validateRate(rateId, updateRate));
    }

    private Rate validateRate(RateDTO rateDTO) {
        return validateRate(null, rateDTO);
    }

    private Rate validateRate(String rateId, RateDTO rateDTO) {

        Rate rate = Objects.nonNull(rateId)
                ? Optional.of(rateRepository.getRateById(rateId)).orElseThrow(() -> new IllegalArgumentException("Rate not found"))
                : new Rate();

        mapperService.updateValues(rate, rateDTO);

        if (StringUtils.isBlank(rate.getName())) {
            throw new IllegalArgumentException("Name");
        }

        if (Objects.isNull(rate.getValue())) {
            throw new IllegalArgumentException("Value");
        }

        if (StringUtils.isBlank(rate.getColorHex())) {
            throw new IllegalArgumentException("Color Hex");
        }

        checkIfRateExist(rateId, rate.getName(), rate.getValue());

        return rate;
    }

    @Override
    public Boolean deleteRate(String rateId) {
        if (rateRepository.getRateById(rateId) == null) {
            throw new IllegalArgumentException("Not Found");
        }

        return rateRepository.deleteRate(rateId);
    }

    private void checkIfRateExist(String rateId, String name, Integer value) {
        Rate rateByName = rateRepository.getRateByName(name);
        if (rateByName != null && ! Objects.equals(rateByName.getId(), rateId)) {
            throw new IllegalArgumentException("Rate with such name exist.");
        }

        Rate rateByValue = rateRepository.getRateByValue(value);
        if (rateByValue != null && ! Objects.equals(rateByValue.getId(), rateId)) {
            throw new IllegalArgumentException("Rate with such value exist.");
        }
    }
}
