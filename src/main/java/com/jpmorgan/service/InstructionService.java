package com.jpmorgan.service;

import com.jpmorgan.utilities.SettledInstruction;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface InstructionService {
    public List<SettledInstruction> listSettledInstruction(String flag, LocalDate passedDate);
    public BigDecimal totalSettledAmount(String flag, LocalDate passedDate);
}
