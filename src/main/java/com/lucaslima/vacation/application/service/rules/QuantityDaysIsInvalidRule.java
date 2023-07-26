package com.lucaslima.vacation.application.service.rules;

import com.lucaslima.vacation.application.domains.VacationRequest;
import com.lucaslima.vacation.application.service.VacationRule;
import org.springframework.stereotype.Component;

@Component
public class QuantityDaysIsInvalidRule extends VacationRule {

    @Override
    protected String message() {
        return "Quantidade de dias informado é inválido";
    }

    @Override
    protected boolean ruleWasBroken(VacationRequest vacationRequest) {
        return vacationRequest.getDays() < 5;
    }
}
