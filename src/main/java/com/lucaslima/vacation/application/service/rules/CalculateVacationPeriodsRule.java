package com.lucaslima.vacation.application.service.rules;

import com.lucaslima.vacation.application.domains.Request;
import com.lucaslima.vacation.application.exceptions.BrokenRuleValidationException;

public abstract class CalculateVacationPeriodsRule {

    public void validate(final Request request) {
        if (ruleWasBroken(request)) {
            throw new BrokenRuleValidationException(message());
        }
    }

    protected abstract String message();

    protected abstract boolean ruleWasBroken(Request request);
}
