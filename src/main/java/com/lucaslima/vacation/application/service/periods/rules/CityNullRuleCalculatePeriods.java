package com.lucaslima.vacation.application.service.periods.rules;

import com.lucaslima.vacation.application.domains.periods.VacationRequest;
import com.lucaslima.vacation.application.service.periods.CalculateVacationPeriodsRule;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class CityNullRuleCalculatePeriods extends CalculateVacationPeriodsRule {

    @Override
    protected String message() {
        return "Cidade deve vir preenchida!";
    }

    @Override
    protected boolean ruleWasBroken(VacationRequest vacationRequest) {
        return Objects.isNull(vacationRequest.getCity());
    }
}
