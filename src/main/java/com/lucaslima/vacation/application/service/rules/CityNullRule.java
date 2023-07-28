package com.lucaslima.vacation.application.service.rules;

import com.lucaslima.vacation.application.domains.VacationRequest;
import com.lucaslima.vacation.application.service.VacationRule;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class CityNullRule extends VacationRule {

    @Override
    protected String message() {
        return "Cidade deve vir preenchida!";
    }

    @Override
    protected boolean ruleWasBroken(VacationRequest vacationRequest) {
        return Objects.isNull(vacationRequest.getCity());
    }
}
