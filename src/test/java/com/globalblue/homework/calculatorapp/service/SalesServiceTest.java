package com.globalblue.homework.calculatorapp.service;

import com.globalblue.homework.calculatorapp.entity.Sale;
import com.globalblue.homework.calculatorapp.validator.SalesValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SalesServiceTest {

    @Mock
    SalesValidator salesValidator;

    SalesService salesService;


    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);

        salesService = new SalesServiceImpl(salesValidator);
    }

    @Test
    void testServiceCalculation_netValueGiven_rate20(){
        Sale sale = new Sale(20, null, new BigDecimal("20.0"), null);

        Sale responseSale = salesService.doValidateAndCalculate(sale);

        assertEquals(new BigDecimal("20.00"), responseSale.getNetValue());
        assertEquals(new BigDecimal("24.00"), responseSale.getGrossValue());
        assertEquals(new BigDecimal("4.00"), responseSale.getVatValue());
    }


    @Test
    void testServiceCalculation_netValueGiven_rate10(){
        Sale sale = new Sale(10, null, new BigDecimal("20.0"), null);

        Sale responseSale = salesService.doValidateAndCalculate(sale);

        assertEquals(new BigDecimal("20.00"), responseSale.getNetValue());
        assertEquals(new BigDecimal("22.00"), responseSale.getGrossValue());
        assertEquals(new BigDecimal("2.00"), responseSale.getVatValue());
    }

    @Test
    void testServiceCalculation_grossValueGiven_rate20(){
        Sale sale = new Sale(20, new BigDecimal("120.0"), null, null);

        Sale responseSale = salesService.doValidateAndCalculate(sale);

        assertEquals(new BigDecimal("100.00"), responseSale.getNetValue());
        assertEquals(new BigDecimal("120.00"), responseSale.getGrossValue());
        assertEquals(new BigDecimal("20.00"), responseSale.getVatValue());
    }

    @Test
    void testServiceCalculation_grossValueGiven_rate10(){
        Sale sale = new Sale(10, new BigDecimal("110.0"), null, null);

        Sale responseSale = salesService.doValidateAndCalculate(sale);

        assertEquals(new BigDecimal("100.00"), responseSale.getNetValue());
        assertEquals(new BigDecimal("110.00"), responseSale.getGrossValue());
        assertEquals(new BigDecimal("10.00"), responseSale.getVatValue());
    }

    @Test
    void testServiceCalculation_vatValueGiven_rate20(){
        Sale sale = new Sale(20, null, null, new BigDecimal("20.0"));

        Sale responseSale = salesService.doValidateAndCalculate(sale);

        assertEquals(new BigDecimal("100.00"), responseSale.getNetValue());
        assertEquals(new BigDecimal("120.00"), responseSale.getGrossValue());
        assertEquals(new BigDecimal("20.00"), responseSale.getVatValue());
    }

    @Test
    void testServiceCalculation_vatValueGiven_rate10(){
        Sale sale = new Sale(10, null, null, new BigDecimal("10.0"));

        Sale responseSale = salesService.doValidateAndCalculate(sale);

        assertEquals(new BigDecimal("100.00"), responseSale.getNetValue());
        assertEquals(new BigDecimal("110.00"), responseSale.getGrossValue());
        assertEquals(new BigDecimal("10.00"), responseSale.getVatValue());
    }

}
