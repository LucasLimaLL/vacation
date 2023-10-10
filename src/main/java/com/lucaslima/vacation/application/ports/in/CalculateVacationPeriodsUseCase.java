package com.lucaslima.vacation.application.ports.in;

import com.lucaslima.vacation.application.domains.Vacation;
import com.lucaslima.vacation.application.domains.Request;

import java.util.List;

public interface CalculateVacationPeriodsUseCase {

    List<Vacation> calculate(Request request);
}
