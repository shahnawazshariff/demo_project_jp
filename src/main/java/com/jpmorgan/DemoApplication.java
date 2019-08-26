package com.jpmorgan;

import com.jpmorgan.entity.CurrencyDayOfWeekException;
import com.jpmorgan.entity.Instruction;
import com.jpmorgan.repository.CurrencyDayOfWeekHolidayExceptionRepository;
import com.jpmorgan.repository.InstructionRepository;
import com.jpmorgan.service.InstructionService;
import com.jpmorgan.utilities.SettledInstruction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class DemoApplication {

    private static final Logger log = LoggerFactory.getLogger(DemoApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner databaseStubCreator(InstructionRepository repository) {
        // Just adding test stubs for instructions whenever application is run, data stored in memory only in H2 database
        return (args) -> {
            
            repository.save(new Instruction("Singapore Petroleum","B",0.50, "SGP",
                    LocalDate.now(), LocalDate.now().plusDays(1l), 200, 100.25));
            repository.save(new Instruction("Saudi Insurance Company","S",0.30, "SAR",
                    LocalDate.now(), LocalDate.now().plusDays(5l), 400, 210.75));
            repository.save(new Instruction("HSBC","S",1.0, "USD",
                    LocalDate.now(), LocalDate.now().plusDays(2l), 750, 89.95));
            repository.save(new Instruction("Past Company 1","B",1.2, "GBP",
                    LocalDate.of(2019, 8, 18), LocalDate.of(2019, 8, 19), 1200, 0.77));
            repository.save(new Instruction("Past Company 2","S",1.1, "EUR",
                    LocalDate.of(2019, 8, 19), LocalDate.of(2019, 8, 21), 45, 2.98));
            repository.save(new Instruction("Johnson & Johnson","S",1.0, "USD",
                    LocalDate.now(), LocalDate.now().plusDays(1l), 90, 45.88));
            repository.save(new Instruction("Saudi Petroluem","B",0.7, "SAR",
                    LocalDate.now(), LocalDate.now().plusDays(2l), 600, 97.25));
            repository.save(new Instruction("Formula 1 Racing Co","S",1.1, "EUR",
                    LocalDate.now(), LocalDate.now().plusDays(2l), 250, 100.10));
            repository.save(new Instruction("Iron Bank of Braavos","B",10.8, "GTC",
                    LocalDate.now(), LocalDate.now().plusDays(1l), 1000, 780.10));
            repository.save(new Instruction("Pizza Palace","S",0.10, "INR",
                    LocalDate.now(), LocalDate.now().plusDays(2l), 550, 300.00));
            repository.save(new Instruction("Dubai Company","B",0.45, "AED",
                    LocalDate.now(), LocalDate.now().plusDays(3l), 700, 24.30));

            for (Instruction instruction : repository.findAll()) {
                log.info("The stub instructions are: " + instruction.toString());
            }
        };
    }

    @Bean
    public CommandLineRunner currencyWeekendRuleExceptionCreator(CurrencyDayOfWeekHolidayExceptionRepository repository) {
        // Load the day of week exception currencies. Example currencies SAR and AED will have a weekend on
        // days 5 and 6 instead of 6 1n 7 (Integer values of days taken into account)

        return (args) -> {
            repository.save(new CurrencyDayOfWeekException("AED", 5));
            repository.save(new CurrencyDayOfWeekException("AED", 6));
            repository.save(new CurrencyDayOfWeekException("SAR", 5));
            repository.save(new CurrencyDayOfWeekException("SAR", 6));
        };
    }

    @Bean
    public CommandLineRunner outputReportToConsole(InstructionService instructionService) {
        return (args) -> {
            System.out.println("--- Total Amount Settled in USD (Incoming) ---");
            System.out.println("" + instructionService.totalSettledAmount("S", LocalDate.now()));

            System.out.println("--- Ranking By Incoming ---");
            for (SettledInstruction x : instructionService.listSettledInstruction("S", LocalDate.now())){
                System.out.println(x.toString());
            }

            System.out.println("--- Total Amount Settled in USD (Outgoing) ---");
            System.out.println("" + instructionService.totalSettledAmount("B", LocalDate.now()));

            System.out.println("--- Ranking By Outgoing ---");
            for (SettledInstruction x : instructionService.listSettledInstruction("B", LocalDate.now())){
                System.out.println(x.toString());
            }
        };
    }
}

