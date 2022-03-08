package com.globalblue.homework.calculatorapp.rest;

import com.globalblue.homework.calculatorapp.entity.Sale;
import com.globalblue.homework.calculatorapp.service.SalesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Optional;

@RestController
public class SalesController {

    private final SalesServiceImpl salesService;

    @Autowired
    public SalesController(SalesServiceImpl salesService) {
        this.salesService = salesService;
    }

    @GetMapping("/sales")
    public Sale doCalculate(@RequestParam(required = false) String rate,
                            @RequestParam(required = false) String gross,
                            @RequestParam(required = false) String net,
                            @RequestParam(required = false) String vat) {

        Sale sale = createSale(rate, gross, net, vat);

        return salesService.doValidateAndCalculate(sale);
    }

    private static Sale createSale(String vatRateParam, String grossValueParam,
                                   String netValueParam, String vatValueParam) {

        Integer vatRate = Optional.ofNullable(vatRateParam).map(Integer::parseInt).orElse(null);
        BigDecimal grossValue = Optional.ofNullable(grossValueParam).map(BigDecimal::new).orElse(null);
        BigDecimal netValue = Optional.ofNullable(netValueParam).map(BigDecimal::new).orElse(null);
        BigDecimal vatValue = Optional.ofNullable(vatValueParam).map(BigDecimal::new).orElse(null);

        return new Sale(vatRate, grossValue, netValue, vatValue);
    }
}
