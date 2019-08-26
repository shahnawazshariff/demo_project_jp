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
@RequestMapping("/incoming")
public class IncomingController {
    @Autowired
    private InstructionService instructionService;

    @GetMapping("")
    public ResponseEntity<List<SettledInstruction>> getIncomingInstructions() {
        List<SettledInstruction> list = instructionService.listSettledInstruction("S", LocalDate.now());
        return new ResponseEntity<List<SettledInstruction>>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<SettledInstruction>> getIncomingInstructionsBySpecificDate(@PathVariable("id") @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate dateParam) {
        List<SettledInstruction> list = instructionService.listSettledInstruction("S", dateParam);
        return new ResponseEntity<List<SettledInstruction>>(list, HttpStatus.OK);
    }

    @GetMapping("/amount")
    public ResponseEntity<String> getIncomingAmount() {
        BigDecimal totalSettledAmount = instructionService.totalSettledAmount("S", LocalDate.now());
        return new ResponseEntity<String>(totalSettledAmount.toString(), HttpStatus.OK);
    }

    @GetMapping("/amount/{id}")
    public ResponseEntity<String> getIncomingAmountBySpecificDate(@PathVariable("id") @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate dateParam) {
        BigDecimal totalSettledAmount = instructionService.totalSettledAmount("S", dateParam);
        return new ResponseEntity<String>(totalSettledAmount.toString(), HttpStatus.OK);
    }

}
