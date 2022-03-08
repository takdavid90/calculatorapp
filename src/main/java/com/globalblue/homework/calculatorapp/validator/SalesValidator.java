package com.globalblue.homework.calculatorapp.validator;

import com.globalblue.homework.calculatorapp.exceptions.InvalidArgumentCountException;
import com.globalblue.homework.calculatorapp.exceptions.InvalidVatRateException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Component
public class SalesValidator {

    private final List<Integer> validRatesList;

    public SalesValidator(@Value("${validAustrianVatRates}") List<Integer> validRatesList) {
        this.validRatesList = validRatesList;
    }

    public void validateRate(Integer vatRate) {
        if(vatRate == null) {
            throw new InvalidVatRateException("You must add a valid parameter of 'rate' to the API");
        }

        if(!validRatesList.contains(vatRate)) {
            throw new InvalidVatRateException("Invalid rate value. Rate must be 10% or 13% or 20%");
        }
    }

    public void validateOnlyOneInput(BigDecimal... input) {
        if(Arrays.stream(input).noneMatch(Objects::nonNull)) {
            throw new InvalidArgumentCountException("You should give one more param ('gross' | 'net' | 'vat')");
        }
        if(Arrays.stream(input).filter(Objects::nonNull).count() > 1) {
            throw new InvalidArgumentCountException("You should give only one of those: Gross, NET or Vat value ('gross' | 'net' | 'vat')");
        }
    }
}
