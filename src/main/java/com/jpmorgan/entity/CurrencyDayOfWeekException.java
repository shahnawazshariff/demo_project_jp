package com.jpmorgan.entity;

import javax.persistence.*;

@Entity
public class CurrencyDayOfWeekException {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String currency;

    @Column(nullable = false)
    private Integer dayOfWeek;

    public CurrencyDayOfWeekException(){
    }

    public CurrencyDayOfWeekException(String currency, Integer dayOfWeek) {
        this.currency = currency;
        this.dayOfWeek = dayOfWeek;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Integer getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(Integer dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    @Override
    public String toString() {
        return "CurrencyDayOfWeekHolidayException{" +
                "id=" + id +
                ", currency='" + currency + '\'' +
                ", dayOfWeek=" + dayOfWeek +
                '}';
    }
}
