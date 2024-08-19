package org.dmle.userfood.controller;

import org.dmle.userfood.config.ApiPrefixController;
import org.dmle.userfood.domain.Rate;
import org.dmle.userfood.domain.ResponseEntity;
import org.dmle.userfood.service.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@ApiPrefixController
public class RateController {
    @Autowired
    private RateService rateService;

    @GetMapping(value = "rates")
    public ResponseEntity<List<Rate>> getRates() {
        return ResponseEntity.successResponse(rateService.getRates());
    }

    @GetMapping(value = "rates/pagination")
    public ResponseEntity<List<Rate>> getRatesPagination(@RequestParam int limit, @RequestParam int page) {
        if (limit == 0) {
            limit = 10;
        }

        if (page == 0) {
            page = 0;
        }

        Integer start = page * limit;

        return ResponseEntity.successResponse(rateService.getRatesPagination(start, limit));
    }
}
