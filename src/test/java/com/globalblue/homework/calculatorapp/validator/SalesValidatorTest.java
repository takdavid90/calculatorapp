package com.globalblue.homework.calculatorapp.validator;

import com.globalblue.homework.calculatorapp.exceptions.InvalidArgumentCountException;
import com.globalblue.homework.calculatorapp.exceptions.InvalidVatRateException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SalesValidatorTest {

    SalesValidator salesValidator;

    @BeforeEach
    void setUp(){

        List<Integer> validRates = Arrays.asList(10, 13, 20);
        salesValidator = new SalesValidator(validRates);
    }

    @Test
    void testValidRate_nullParam_throwsInvalidVatRateException(){
        InvalidVatRateException thrown = Assertions.assertThrows(InvalidVatRateException.class,
                () -> {
                    salesValidator.validateRate(null);
                });

        assertEquals(thrown.getMessage(), "You must add a valid parameter of 'rate' to the API");
    }

    @Test
    void testValidRate_invalidTaxKey_throwsInvalidVatRateException(){

        InvalidVatRateException thrown = Assertions.assertThrows(InvalidVatRateException.class,
                () -> {
                    salesValidator.validateRate(11);
                });

        assertEquals(thrown.getMessage(), "Invalid rate value. Rate must be 10% or 13% or 20%");
    }

    @Test
    void testValidRate_validTaxKey_doesNotThrowAnyException(){
        assertDoesNotThrow(() -> {
            salesValidator.validateRate(10);
        });
    }

    @Test
    void testValidRate_nonNumericInput_throwsNumberFormatException(){
        assertThrows(NumberFormatException.class, () -> {
            salesValidator.validateRate(Integer.parseInt("1f"));
        });
    }

    @Test
    void testOnlyOneInput_noParam_throwsInvalidArgumentCountException(){
        InvalidArgumentCountException thrown = Assertions.assertThrows(InvalidArgumentCountException.class,
                () -> {
                    salesValidator.validateOnlyOneInput(null, null, null);
                });

        assertEquals(thrown.getMessage(), "You should give one more param ('gross' | 'net' | 'vat')");
    }

    @Test
    void testOnlyOneInput_grossParam_doesNotThrowAnyException(){
        assertDoesNotThrow(() -> {
            salesValidator.validateOnlyOneInput(new BigDecimal("10.0"), null, null);
        });
    }

    @Test
    void testOnlyOneInput_netParam_doesNotThrowAnyException(){
        assertDoesNotThrow(() -> {
            salesValidator.validateOnlyOneInput(null,new BigDecimal("10.0"),null);
        });
    }

    @Test
    void testOnlyOneInput_vatParam_doesNotThrowAnyException(){
        assertDoesNotThrow(() -> {
            salesValidator.validateOnlyOneInput(null, null, new BigDecimal("10.0"));
        });
    }

    @Test
    void testOnlyOneInput_twoParams_throwsIllegalArgumentCountException(){
        InvalidArgumentCountException thrown = Assertions.assertThrows(InvalidArgumentCountException.class,
                () -> {
                    salesValidator.validateOnlyOneInput(new BigDecimal("10.0"), null, new BigDecimal("10.0"));
                });

        assertEquals(thrown.getMessage(), "You should give only one of those: Gross, NET or Vat value ('gross' | 'net' | 'vat')");
    }

    @Test
    void testOnlyOneInput_threeParams_throwsIllegalArgumentCountException(){
        InvalidArgumentCountException thrown = Assertions.assertThrows(InvalidArgumentCountException.class,
                () -> {
                    salesValidator.validateOnlyOneInput(new BigDecimal("10.0"), new BigDecimal("10.0"), new BigDecimal("10.0"));
                });

        assertEquals(thrown.getMessage(), "You should give only one of those: Gross, NET or Vat value ('gross' | 'net' | 'vat')");
    }


}
