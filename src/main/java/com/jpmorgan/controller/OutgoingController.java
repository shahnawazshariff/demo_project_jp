package com.jpmorgan.controller;

import com.jpmorgan.service.InstructionService;
import com.jpmorgan.utilities.SettledInstruction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/outgoing")
public class OutgoingController {
    @Autowired
    private InstructionService instructionService;

    @GetMapping("")
    public ResponseEntity<List<SettledInstruction>> getOutgoingInstructions() {
        List<SettledInstruction> list = instructionService.listSettledInstruction("B", LocalDate.now());
        return new ResponseEntity<List<SettledInstruction>>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<SettledInstruction>> getOutgoingInstructionsBySpecificDate(@PathVariable("id") @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate dateParam) {
        List<SettledInstruction> list = instructionService.listSettledInstruction("B", dateParam);
        return new ResponseEntity<List<SettledInstruction>>(list, HttpStatus.OK);
    }

    @GetMapping("/amount")
    public ResponseEntity<BigDecimal> getOutgoingAmount() {
        BigDecimal totalSettledAmount = instructionService.totalSettledAmount("B", LocalDate.now());
        return new ResponseEntity<BigDecimal>(totalSettledAmount, HttpStatus.OK);
    }

    @GetMapping("/amount/{id}")
    public ResponseEntity<BigDecimal> getOutgoingAmountBySpecificDate(@PathVariable("id") @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate dateParam) {
        BigDecimal totalSettledAmount = instructionService.totalSettledAmount("B", dateParam);
        return new ResponseEntity<BigDecimal>(totalSettledAmount, HttpStatus.OK);
    }
}
