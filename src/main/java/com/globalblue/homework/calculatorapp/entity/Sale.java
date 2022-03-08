package com.globalblue.homework.calculatorapp.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class Sale {

    private Integer vatRate;
    private BigDecimal grossValue;
    private BigDecimal netValue;
    private BigDecimal vatValue;

    public Sale() {

    }

    public Sale(Integer vatRate, BigDecimal grossValue, BigDecimal netValue, BigDecimal vatValue) {
        this.vatRate = vatRate;
        this.grossValue = grossValue;
        this.netValue = netValue;
        this.vatValue = vatValue;
    }

    @JsonProperty("VatRate (Tax key %)")
    public Integer getVatRate() {
        return vatRate;
    }

    public void setVatRate(Integer vatRate) {
        this.vatRate = vatRate;
    }

    @JsonProperty("Gross amount")
    public BigDecimal getGrossValue() {
        return grossValue;
    }

    public void setGrossValue(BigDecimal grossValue) {
        this.grossValue = grossValue;
    }

    @JsonProperty("NET amount")
    public BigDecimal getNetValue() {
        return netValue;
    }

    public void setNetValue(BigDecimal netValue) {
        this.netValue = netValue;
    }

    @JsonProperty("tax")
    public BigDecimal getVatValue() {
        return vatValue;
    }

    public void setVatValue(BigDecimal vatValue) {
        this.vatValue = vatValue;
    }

    @Override
    public String toString() {
        return "Sale [vatRate=" + vatRate + ", grossValue=" + grossValue + ", netValue=" + netValue + ", vatValue="
                + vatValue + "]";
    }
}
