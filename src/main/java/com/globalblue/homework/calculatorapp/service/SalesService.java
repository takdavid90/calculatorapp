package com.globalblue.homework.calculatorapp.service;

import com.globalblue.homework.calculatorapp.entity.Sale;

public interface SalesService {
    Sale doValidateAndCalculate(Sale inputSale);
}
