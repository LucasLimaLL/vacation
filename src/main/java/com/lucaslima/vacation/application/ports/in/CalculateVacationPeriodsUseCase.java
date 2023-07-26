package com.lucaslima.vacation.application.ports.in;

import com.lucaslima.vacation.application.domains.City;
import com.lucaslima.vacation.application.domains.Period;
import com.lucaslima.vacation.application.domains.Vacation;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

public interface CalculateVacationPeriodsUseCase {

    List<Vacation> calculate(City city,
                             LocalDate start,
                             LocalDate end,
                             int days,
                             int split,
                             int extraDays,
                             List<DayOfWeek> workDays);

    List<Period> calculate(City city,
                           LocalDate start,
                           LocalDate end,
                           Integer range,
                           List<DayOfWeek> workDays);
}
