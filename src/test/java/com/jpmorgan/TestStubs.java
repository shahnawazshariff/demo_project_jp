package com.jpmorgan;

import com.jpmorgan.entity.CurrencyDayOfWeekException;
import com.jpmorgan.entity.Instruction;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TestStubs {

    public static List<Instruction> getInstructionStubs(){
        List<Instruction> instructionList = new ArrayList<>();
        instructionList.add(new Instruction("Singapore Petroleum","B",0.50, "SGP",
                LocalDate.now(), LocalDate.now().plusDays(1l), 200, 100.25));
        instructionList.add(new Instruction("Saudi Insurance Company","S",0.30, "SAR",
                LocalDate.now(), LocalDate.now().plusDays(5l), 400, 210.75));
        instructionList.add(new Instruction("HSBC","S",1.0, "USD",
                LocalDate.now(), LocalDate.now().plusDays(2l), 750, 89.95));
        instructionList.add(new Instruction("Past Company 1","B",1.2, "GBP",
                LocalDate.of(2019, 8, 18), LocalDate.now().minusDays(26l), 1200, 0.77));
        instructionList.add(new Instruction("Iron Bank of Braavos","B",10.8, "GTC",
                LocalDate.now(), LocalDate.now().plusDays(1l), 1000, 780.10));
        instructionList.add(new Instruction("Pizza Palace","S",0.10, "INR",
                LocalDate.now(), LocalDate.now().plusDays(2l), 550, 300.00));
        instructionList.add(new Instruction("Europe Petroleum","B",1.1, "EUR",
                LocalDate.of(2019, 8, 29), LocalDate.of(2019, 8, 31), 45, 2.98));
        instructionList.add(new Instruction("Saudi Petroleum","B",0.7, "SAR",
                LocalDate.of(2019, 8, 29), LocalDate.of(2019, 8, 30), 600, 97.25));
        instructionList.add(new Instruction("Saudi Chemicals","B",0.7, "SAR",
                LocalDate.of(2019, 8, 29), LocalDate.of(2019, 8, 31), 600, 50.50));
        instructionList.add(new Instruction("Saudi Fishing","B",0.7, "SAR",
                LocalDate.of(2019, 8, 29), LocalDate.of(2019, 9, 1), 600, 50.50));
        instructionList.add(new Instruction("Johnson & Johnson","S",1.0, "USD",
                LocalDate.now(), LocalDate.now().plusDays(1l), 90, 45.88));
        instructionList.add(new Instruction("Formula 1 Racing Co","S",1.1, "EUR",
                LocalDate.now(), LocalDate.now().plusDays(2l), 250, 100.10));
        instructionList.add(new Instruction("Dubai Company","B",0.45, "AED",
                LocalDate.now(), LocalDate.now().plusDays(3l), 700, 24.30));

        return instructionList;
    }

    public static List<CurrencyDayOfWeekException> getCurrencyDayOfWeekExceptionList(){
        List<CurrencyDayOfWeekException> list = new ArrayList<>();
        list.add(new CurrencyDayOfWeekException("AED", 5));
        list.add(new CurrencyDayOfWeekException("AED", 6));
        list.add(new CurrencyDayOfWeekException("SAR", 5));
        list.add(new CurrencyDayOfWeekException("SAR", 6));

        return list;
    }

}
