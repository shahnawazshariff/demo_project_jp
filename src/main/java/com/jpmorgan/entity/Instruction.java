package com.jpmorgan.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Instruction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="instruction_id")
    private Long id;

    @Column(nullable = false)
    private String entity;

    @Column(nullable = false)
    private String flag;

    @Column(nullable = false)
    private Double agreedFx;

    @Column(nullable = false)
    private String currency;

    @Column(nullable = false)
    private LocalDate instructionDate;

    @Column(nullable = false)
    private LocalDate settlementDate;

    @Column(nullable = false)
    private Integer units;

    @Column(nullable = false)
    private Double pricePerUnit;

    public Instruction() {
    }

    public Instruction(String entity, String flag, Double agreedFx, String currency,
                       LocalDate instructionDate, LocalDate settlementDate, Integer units, Double pricePerUnit) {
        this.entity = entity;
        this.flag = flag;
        this.agreedFx = agreedFx;
        this.currency = currency;
        this.instructionDate = instructionDate;
        this.settlementDate = settlementDate;
        this.units = units;
        this.pricePerUnit = pricePerUnit;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
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

    public LocalDate getSettlementDate() {
        return settlementDate;
    }

    public void setSettlementDate(LocalDate settlementDate) {
        this.settlementDate = settlementDate;
    }

    public Integer getUnits() {
        return units;
    }

    public void setUnits(Integer units) {
        this.units = units;
    }

    public Double getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(Double priceperUnit) {
        this.pricePerUnit = priceperUnit;
    }

    @Override
    public String toString() {
        return "Instruction{" +
                "id=" + id +
                ", entity='" + entity + '\'' +
                ", flag='" + flag + '\'' +
                ", agreedFx=" + agreedFx +
                ", currency='" + currency + '\'' +
                ", instructionDate=" + instructionDate +
                ", settlementDate=" + settlementDate +
                ", Units=" + units +
                ", priceperUnit=" + pricePerUnit +
                '}';
    }
}
