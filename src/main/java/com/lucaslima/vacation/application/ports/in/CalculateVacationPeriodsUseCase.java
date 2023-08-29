package com.lucaslima.vacation.application.ports.in;

import com.lucaslima.vacation.application.domains.periods.Vacation;
import com.lucaslima.vacation.application.domains.periods.VacationRequest;

import java.util.List;

public interface CalculateVacationPeriodsUseCase {

    List<Vacation> calculate(VacationRequest vacationRequest);
}
