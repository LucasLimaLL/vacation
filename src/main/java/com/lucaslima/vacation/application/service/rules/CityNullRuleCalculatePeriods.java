package com.lucaslima.vacation.application.service.rules;

import com.lucaslima.vacation.application.domains.Request;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class CityNullRuleCalculatePeriods extends CalculateVacationPeriodsRule {

    @Override
    protected String message() {
        return "Cidade deve vir preenchida!";
    }

    @Override
    protected boolean ruleWasBroken(Request request) {
        return Objects.isNull(request.getCity());
    }
}
