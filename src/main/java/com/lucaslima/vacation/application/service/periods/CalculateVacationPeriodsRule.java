package com.lucaslima.vacation.application.service.periods;

import com.lucaslima.vacation.application.domains.periods.VacationRequest;
import com.lucaslima.vacation.application.exceptions.BrokenRuleValidationException;

public abstract class CalculateVacationPeriodsRule {

    public void validate(final VacationRequest vacationRequest) {
        if (ruleWasBroken(vacationRequest)) {
            throw new BrokenRuleValidationException(message());
        }
    }

    protected abstract String message();

    protected abstract boolean ruleWasBroken(VacationRequest vacationRequest);
}