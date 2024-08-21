package org.dmle.userfood.repo;

import org.dmle.userfood.domain.Rate;

import java.util.List;

public interface RateRepository {

    List<Rate> getRates();
    List<Rate> getRatesPagination(Integer start, Integer limit);
    Rate getRateById(String rateId);
    String addRate(Rate newRate);
}
