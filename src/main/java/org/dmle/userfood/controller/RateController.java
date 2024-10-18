package org.dmle.userfood.controller;

import org.dmle.userfood.config.ApiPrefixController;
import org.dmle.userfood.domain.Rate;
import org.dmle.userfood.domain.RateDTO;
import org.dmle.userfood.domain.ResponseEntity;
import org.dmle.userfood.service.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    @GetMapping(value = "rate/{id}")
    public ResponseEntity<Rate> getRateById(@PathVariable("id") String rateId) {
        return ResponseEntity.successResponse(rateService.getRateById(rateId));
    }

    @PostMapping(value = "rate")
    public ResponseEntity<String> addRate(@RequestBody RateDTO newRate) {
        return ResponseEntity.successResponse(rateService.addRate(newRate));
    }

    @PutMapping(value = "rate/{id}")
    public ResponseEntity<Boolean> updateRate(@PathVariable("id") String rateId, @RequestBody RateDTO updateRate) {
        return ResponseEntity.successResponse(rateService.updateRate(rateId, updateRate));
    }

    @DeleteMapping(value = "rate/{id}")
    public ResponseEntity<Boolean> deleteRate(@PathVariable("id") String rateId) {
        return ResponseEntity.successResponse(rateService.deleteRate(rateId));
    }
}
