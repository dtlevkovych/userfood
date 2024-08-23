package org.dmle.userfood.service;

import org.dmle.userfood.domain.Rate;

import java.util.List;
import java.util.Map;

public interface RateService {

    List<Rate> getRates();
    List<Rate> getRatesPagination(Integer start, Integer limit);
    Rate getRateById(String rateId);
    String addRate(Map<String, Object> newRate);
    Boolean updateRate(String rateId, Map<String, Object> updateRate);
    void checkIfExistRate(String name, Integer value);
}
