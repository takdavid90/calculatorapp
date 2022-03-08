package com.globalblue.homework.calculatorapp.service;

import com.globalblue.homework.calculatorapp.entity.Sale;
import com.globalblue.homework.calculatorapp.validator.SalesValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class SalesServiceImpl implements SalesService {

    private final SalesValidator salesValidator;
    private static final RoundingMode ROUNDING_MODE = RoundingMode.FLOOR;
    private static final int DECIMALS = 2;

    private static Sale saleCalculated;

    @Autowired
    public SalesServiceImpl(SalesValidator salesValidator) {
        this.salesValidator = salesValidator;
    }

    @Override
    public Sale doValidateAndCalculate(Sale inputSale) {

        salesValidator.validateRate(inputSale.getVatRate());
        salesValidator.validateOnlyOneInput(inputSale.getGrossValue(), inputSale.getNetValue(), inputSale.getVatValue());

        saleCalculated = new Sale();
        saleCalculated.setVatRate(inputSale.getVatRate());

        if(inputSale.getGrossValue() != null) {
            processCalculationByGross(inputSale);
        }

        if(inputSale.getNetValue() != null) {
           processCalculationByNet(inputSale);
        }


        if(inputSale.getVatValue() != null) {
           processCalculationByVat(inputSale);
        }

        return saleCalculated;
    }

    private static void processCalculationByGross(Sale inputSale){
        saleCalculated.setGrossValue(inputSale
                .getGrossValue()
                .setScale(DECIMALS, ROUNDING_MODE));
        saleCalculated.setNetValue(saleCalculated
                .getGrossValue()
                .divide(BigDecimal.ONE.add(BigDecimal.valueOf(saleCalculated.getVatRate())
                        .divide(new BigDecimal(100), DECIMALS, ROUNDING_MODE)), DECIMALS, ROUNDING_MODE)
                .setScale(DECIMALS, ROUNDING_MODE));
        saleCalculated.setVatValue(saleCalculated.getGrossValue()
                .subtract(saleCalculated.getNetValue()).setScale(DECIMALS, ROUNDING_MODE));
    }

    private static void processCalculationByNet(Sale inputSale){
        saleCalculated.setNetValue(inputSale
                .getNetValue()
                .setScale(DECIMALS, ROUNDING_MODE));
        saleCalculated.setGrossValue(inputSale.getNetValue()
                .add(inputSale.getNetValue()
                        .multiply(BigDecimal.valueOf(inputSale.getVatRate())
                                .divide(new BigDecimal(100), DECIMALS, ROUNDING_MODE)))
                .setScale(DECIMALS, ROUNDING_MODE));
        saleCalculated.setVatValue(saleCalculated
                .getGrossValue()
                .subtract(saleCalculated.getNetValue())
                .setScale(DECIMALS, ROUNDING_MODE));
    }

    private static void processCalculationByVat(Sale inputSale){
        saleCalculated.setVatValue(inputSale
                .getVatValue()
                .setScale(DECIMALS, ROUNDING_MODE));
        saleCalculated.setNetValue(inputSale.getVatValue()
                .multiply(BigDecimal.valueOf(100))
                .divide(BigDecimal.valueOf(inputSale.getVatRate()), DECIMALS, ROUNDING_MODE)
                .setScale(DECIMALS, ROUNDING_MODE));
        saleCalculated.setGrossValue(saleCalculated
                .getNetValue()
                .add(inputSale.getVatValue())
                .setScale(DECIMALS, ROUNDING_MODE));
    }
}
