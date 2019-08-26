package com.jpmorgan.utilities;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.time.LocalDate;

public class SettledInstruction {
    private String entity;
    private Double agreedFx;
    private String currency;
    private LocalDate instructionDate;
    private LocalDate instructedSettlementDate;
    private LocalDate actualSettlementDate;
    private Integer Units;
    private Double pricePerUnit;
    private BigDecimal tradeAmount;

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public Double getAgreedFx() {
        return agreedFx;
    }

    public void setAgreedFx(Double agreedFx) {
        this.agreedFx = agreedFx;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public LocalDate getInstructionDate() {
        return instructionDate;
    }

    public void setInstructionDate(LocalDate instructionDate) {
        this.instructionDate = instructionDate;
    }

    public LocalDate getInstructedSettlementDate() {
        return instructedSettlementDate;
    }

    public void setInstructedSettlementDate(LocalDate instructedSettlementDate) {
        this.instructedSettlementDate = instructedSettlementDate;
    }

    public LocalDate getActualSettlementDate() {
        return actualSettlementDate;
    }

    public void setActualSettlementDate(LocalDate actualSettlementDate) {
        this.actualSettlementDate = actualSettlementDate;
    }

    public Integer getUnits() {
        return Units;
    }

    public void setUnits(Integer units) {
        Units = units;
    }

    public Double getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(Double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public BigDecimal getTradeAmount() {
        return tradeAmount;
    }

    public void setTradeAmount(BigDecimal tradeAmount) {
        this.tradeAmount = tradeAmount;
    }

    @Override
    public String toString() {
        return "SettledInstruction{" +
                "entity='" + entity + '\'' +
                ", agreedFx=" + agreedFx +
                ", currency='" + currency + '\'' +
                ", instructionDate=" + instructionDate +
                ", instructedSettlementDate=" + instructedSettlementDate +
                ", actualSettlementDate=" + actualSettlementDate +
                ", Units=" + Units +
                ", pricePerUnit=" + pricePerUnit +
                ", tradeAmount=" + tradeAmount +
                '}';
    }
}
