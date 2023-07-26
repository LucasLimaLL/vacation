package com.lucaslima.vacation.application.ports.in;

import com.lucaslima.vacation.application.domains.Vacation;
import com.lucaslima.vacation.application.domains.VacationRequest;

import java.util.List;

public interface CalculateVacationPeriodsUseCase {

    List<Vacation> calculate(VacationRequest vacationRequest);
}
