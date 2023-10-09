package com.lucaslima.vacation.application.service.rules;

import com.lucaslima.vacation.application.domains.Request;
import org.springframework.stereotype.Component;

@Component
public class QuantityDaysIsInvalidRuleCalculatePeriods extends CalculateVacationPeriodsRule {

    public static final int MINIMUM = 5;

    @Override
    protected String message() {
        return "Quantidade de dias informado é inválido";
    }

    @Override
    protected boolean ruleWasBroken(Request request) {
        return request.getDays() < MINIMUM;
    }
}
