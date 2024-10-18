package org.dmle.userfood.service;

import org.dmle.userfood.domain.Rate;
import org.dmle.userfood.domain.RateDTO;

import java.util.List;
import java.util.Map;

public interface RateService {

    List<Rate> getRates();
    List<Rate> getRatesPagination(Integer start, Integer limit);
    Rate getRateById(String rateId);
    String addRate(RateDTO newRate);
    Boolean updateRate(String rateId, RateDTO updateRate);
    Boolean deleteRate(String rateId);
}
