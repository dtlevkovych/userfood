package org.dmle.userfood.service;

import org.dmle.userfood.domain.Rate;

import java.util.List;

public interface RateService {

    List<Rate> getRates();
    List<Rate> getRatesPagination(Integer start, Integer limit);
    Rate getRateById(String rateId);
}
