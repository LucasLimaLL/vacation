package com.lucaslima.vacation.application.service.periods.rules;

import com.lucaslima.vacation.application.domains.periods.VacationRequest;
import com.lucaslima.vacation.application.service.periods.CalculateVacationPeriodsRule;
import org.springframework.stereotype.Component;

@Component
public class QuantityDaysIsInvalidRuleCalculatePeriods extends CalculateVacationPeriodsRule {

    @Override
    protected String message() {
        return "Quantidade de dias informado é inválido";
    }

    @Override
    protected boolean ruleWasBroken(VacationRequest vacationRequest) {
        return vacationRequest.getDays() < 5;
    }
}
