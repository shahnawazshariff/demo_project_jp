package com.jpmorgan.service;

import com.jpmorgan.entity.CurrencyDayOfWeekException;
import com.jpmorgan.entity.Instruction;
import com.jpmorgan.repository.CurrencyDayOfWeekHolidayExceptionRepository;
import com.jpmorgan.repository.InstructionRepository;
import com.jpmorgan.utilities.SettledInstruction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class InstructionServiceImpl implements InstructionService{
    @Autowired
    private InstructionRepository instructionRepository;
    @Autowired
    private CurrencyDayOfWeekHolidayExceptionRepository currencyDayOfWeekHolidayExceptionRepository;

    @Override
    public List<SettledInstruction> listSettledInstruction(String flag, LocalDate passedDate){
        List<SettledInstruction> returnList = new ArrayList<>();
        List<Instruction> instructionList = (List<Instruction>) instructionRepository.findAll();
        List<CurrencyDayOfWeekException> currencyExceptionList=
                (List<CurrencyDayOfWeekException>) currencyDayOfWeekHolidayExceptionRepository.findAll();
        for (Instruction instruction : instructionList){
            SettledInstruction settledInstruction = new SettledInstruction();
            if (instruction.getFlag().equals(flag) && instruction.getInstructionDate().isEqual(passedDate) ){
                settledInstruction.setEntity(instruction.getEntity());
                settledInstruction.setAgreedFx(instruction.getAgreedFx());
                settledInstruction.setCurrency(instruction.getCurrency());
                settledInstruction.setInstructionDate(instruction.getInstructionDate());
                settledInstruction.setUnits(instruction.getUnits());
                settledInstruction.setPricePerUnit(instruction.getPricePerUnit());
                settledInstruction.setInstructedSettlementDate(instruction.getSettlementDate());
                settledInstruction.setActualSettlementDate(computeActualSettlementDate(instruction.getCurrency(),
                        instruction.getSettlementDate(), currencyExceptionList));
                BigDecimal pricePerUnit = new BigDecimal(String.valueOf(instruction.getPricePerUnit()));
                BigDecimal units = new BigDecimal(String.valueOf(instruction.getUnits()));
                BigDecimal agreedFx = new BigDecimal(String.valueOf(instruction.getAgreedFx()));
                BigDecimal tradeAmount = pricePerUnit.multiply(units).multiply(agreedFx);
                tradeAmount = tradeAmount.setScale(2, BigDecimal.ROUND_HALF_UP);
                settledInstruction.setTradeAmount(tradeAmount);
                returnList.add(settledInstruction);
            }
        }

        returnList.sort(Comparator.comparing(SettledInstruction::getTradeAmount).reversed());
        return returnList;
    }

    @Override
    public BigDecimal totalSettledAmount(String flag, LocalDate passedDate) {
        BigDecimal totalTradeAmount = new BigDecimal(String.valueOf("0.00"));
        totalTradeAmount = totalTradeAmount.setScale(2, BigDecimal.ROUND_HALF_UP);
        List<Instruction> instructionList = (List<Instruction>) instructionRepository.findAll();
        for (Instruction instruction : instructionList){
            SettledInstruction settledInstruction = new SettledInstruction();
            if (instruction.getFlag().equals(flag) && instruction.getInstructionDate().isEqual(passedDate)){
                BigDecimal pricePerUnit = new BigDecimal(String.valueOf(instruction.getPricePerUnit()));
                BigDecimal units = new BigDecimal(String.valueOf(instruction.getUnits()));
                BigDecimal agreedFx = new BigDecimal(String.valueOf(instruction.getAgreedFx()));
                BigDecimal tradeAmount = pricePerUnit.multiply(units).multiply(agreedFx);
                tradeAmount = tradeAmount.setScale(2, BigDecimal.ROUND_HALF_UP);
                totalTradeAmount = totalTradeAmount.add(tradeAmount);
            }
        }
        return totalTradeAmount;
    }

    // Calculate the actual settlement date for the given currency if input settlement date falls on a weekend
    public LocalDate computeActualSettlementDate(String currency, LocalDate settlementDate, List<CurrencyDayOfWeekException> currencyExceptionList) {
        LocalDate returnDate = settlementDate; // Set it as per client instruction initially
        Integer returnDateDayOfWeek = returnDate.getDayOfWeek().getValue();
        List<Integer> listWeekendDays = new ArrayList<>();

        for (CurrencyDayOfWeekException currencyDayOfWeekException : currencyExceptionList) {
            if (currencyDayOfWeekException.getCurrency().equals(currency)){
                listWeekendDays.add(currencyDayOfWeekException.getDayOfWeek());
            }
        }
        // If no record exists in above list it implies that the currency has no exception rule, i.e.
        // it follows the normal Saturday & Sunday weekend
        if (listWeekendDays.size() == 0) {
            switch (settlementDate.getDayOfWeek().getValue()){
                case 6: return returnDate.plusDays(2L);
                case 7: return returnDate.plusDays(1L);
                default: return returnDate;
            }
        }
        // At this stage we know that the currency has a weekend rule specified
        Collections.sort(listWeekendDays);
        for (Integer weekendDay: listWeekendDays) {
            if (returnDateDayOfWeek == weekendDay){
                returnDate = returnDate.plusDays(1L);
                returnDateDayOfWeek = returnDate.getDayOfWeek().getValue();
            }
        }
        return returnDate;
    }
}
