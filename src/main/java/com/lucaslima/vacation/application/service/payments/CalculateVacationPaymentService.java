package com.lucaslima.vacation.application.service.payments;

import com.lucaslima.vacation.application.domains.payments.VacationPayment;
import com.lucaslima.vacation.application.ports.in.CalculateVacationPaymentUseCase;

import java.math.BigDecimal;
import java.util.List;

public class CalculateVacationPaymentService implements CalculateVacationPaymentUseCase {

    private List<CalculateVacationPaymentRule> rules;

    @Override
    public VacationPayment calculate(BigDecimal salary, Integer quantityDays) {

        var vacationPayment = VacationPayment
                .builder()
                .withSalary(salary)
                .withQuantityDays(quantityDays)
                .build();

        rules.forEach(rule -> rule.validate(vacationPayment));

        return vacationPayment;
    }
}
