package com.jpmorgan.repository;

import com.jpmorgan.entity.CurrencyDayOfWeekException;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyDayOfWeekHolidayExceptionRepository extends JpaRepository<CurrencyDayOfWeekException, Long> {
}
