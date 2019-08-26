package com.jpmorgan;

import com.jpmorgan.repository.CurrencyDayOfWeekHolidayExceptionRepository;
import com.jpmorgan.repository.InstructionRepository;
import com.jpmorgan.service.InstructionServiceImpl;
import com.jpmorgan.utilities.SettledInstruction;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class InstructionServiceTest {
    @InjectMocks
    private InstructionServiceImpl instructionServiceImpl;

    @Mock
    private InstructionRepository instructionRepository;
    @Mock
    private CurrencyDayOfWeekHolidayExceptionRepository currencyDayOfWeekHolidayExceptionRepository;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        when(instructionRepository.findAll()).thenReturn(TestStubs.getInstructionStubs());
        when(currencyDayOfWeekHolidayExceptionRepository.findAll()).thenReturn(TestStubs.getCurrencyDayOfWeekExceptionList());
    }

    @Test
    public void testListSettledInstructionIncomingHasData(){
        List<SettledInstruction> settledInstructionList = instructionServiceImpl.listSettledInstruction("S", LocalDate.now());
        assertNotNull(settledInstructionList);
    }

    @Test
    public void testListSettledInstructionOutgoingHasData(){
        List<SettledInstruction> settledInstructionList = instructionServiceImpl.listSettledInstruction("B", LocalDate.now());
        assertNotNull(settledInstructionList);
    }

    @Test
    public void testListSettledInstructionIncomingIsSortedInReverse(){
        List<SettledInstruction> settledInstructionList = instructionServiceImpl.listSettledInstruction("S", LocalDate.now());
        for (int i = 0; i < settledInstructionList.size()-1; i++) {
            assertTrue(settledInstructionList.get(i).getTradeAmount().compareTo(settledInstructionList.get(i + 1).getTradeAmount()) > 0);
        }
    }

    @Test
    public void testListSettledInstructionOutgoingIsSortedInReverse(){
        List<SettledInstruction> settledInstructionList = instructionServiceImpl.listSettledInstruction("B", LocalDate.now());
        for (int i = 0; i < settledInstructionList.size()-1; i++) {
            assertTrue(settledInstructionList.get(i).getTradeAmount().compareTo(settledInstructionList.get(i + 1).getTradeAmount()) > 0);
        }
    }

    @Test
    public void testActualSettlementDateCalculations(){
        List<SettledInstruction> settledInstructionList = instructionServiceImpl.listSettledInstruction("B", LocalDate.of(2019, 8, 29));
        for (SettledInstruction settledInstruction : settledInstructionList){
            // Normal currency where instructed settlement date falls on a Saturday
            if (settledInstruction.getEntity().equalsIgnoreCase("Europe Petroleum")){
                assertTrue(LocalDate.of(2019, 9, 2).isEqual(settledInstruction.getActualSettlementDate()));
            }
            // Saudi currency where instructed settlement date falls on a Friday
            if (settledInstruction.getEntity().equalsIgnoreCase("Saudi Petroleum")){
                assertTrue(LocalDate.of(2019, 9, 1).isEqual(settledInstruction.getActualSettlementDate()));
            }
            // Emirates currency where instructed settlement date falls on a Saturday
            if (settledInstruction.getEntity().equalsIgnoreCase("Saudi Chemicals")){
                assertTrue(LocalDate.of(2019, 9, 1).isEqual(settledInstruction.getActualSettlementDate()));
            }
            // Emirates currency where instructed settlement date falls on a Sunday
            if (settledInstruction.getEntity().equalsIgnoreCase("Saudi Fishing")){
                assertTrue(LocalDate.of(2019, 9, 1).isEqual(settledInstruction.getActualSettlementDate()));
            }
        }
    }
}
