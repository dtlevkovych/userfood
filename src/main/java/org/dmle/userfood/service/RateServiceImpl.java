package org.dmle.userfood.service;

import io.micrometer.common.util.StringUtils;
import org.apache.commons.collections4.MapUtils;
import org.dmle.userfood.domain.Rate;
import org.dmle.userfood.repo.RateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RateServiceImpl implements RateService {

    public static final String NAME = "name";
    public static final String VALUE = "value";
    public static final String COLOR_HEX = "colorHex";

    @Autowired
    private RateRepository rateRepository;

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
    public String addRate(Map<String, Object> newRate) {
        return rateRepository.addRate(validateRate(newRate));
    }

    @Override
    public Boolean updateRate(String rateId, Map<String, Object> updateRate) {
        return rateRepository.updateRate(rateId, validateRate(updateRate));
    }

    private Rate validateRate(Map<String, Object> rateMap) {
        Rate rate = new Rate();

        rate.setName(MapUtils.getString(rateMap, NAME));
        if (StringUtils.isBlank(rate.getName())) {
            throw new IllegalArgumentException("Name");
        }


        rate.setValue(MapUtils.getInteger(rateMap, VALUE, 0));
        if (rate.getValue() == 0) {
            throw new IllegalArgumentException("Value");
        }

        rate.setColorHex(MapUtils.getString(rateMap, COLOR_HEX));
        if (StringUtils.isBlank(rate.getColorHex())) {
            throw new IllegalArgumentException("Color Hex");
        }

        checkIfExistRate(rate.getName(), rate.getValue());

        return rate;
    }

    @Override
    public void checkIfExistRate(String name, Integer value) {
        if (rateRepository.getRateByName(name) != null) {
            throw new IllegalArgumentException("Rate with such name exist.");
        }

        if (rateRepository.getRateByValue(value) != null) {
            throw new IllegalArgumentException("Rate with such value exist.");
        }
    }
}
