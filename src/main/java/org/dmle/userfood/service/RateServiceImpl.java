package org.dmle.userfood.service;

import org.dmle.userfood.domain.Rate;
import org.dmle.userfood.repo.RateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RateServiceImpl implements RateService {

    @Autowired
    private RateRepository rateRepository;

    @Override
    public List<Rate> getRates() {
        return rateRepository.getRates();
    }
}
