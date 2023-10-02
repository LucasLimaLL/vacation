package com.lucaslima.vacation.application.service.payments.rule.additions;

import com.lucaslima.vacation.application.domains.payments.Payment;
import com.lucaslima.vacation.application.domains.payments.VacationPayment;
import com.lucaslima.vacation.application.service.payments.CalculateVacationPaymentRule;

import java.math.BigDecimal;

public class ThirdRule extends CalculateVacationPaymentRule {
    private static final String THIRD = "Terço de Férias";
    private static final int MONTH_DAYS = 30;

    @Override
    protected void include(VacationPayment vacationPayment) {
        var vacationSalary = vacationPayment.getSalary()
                .divide(BigDecimal.valueOf(MONTH_DAYS))
                .multiply(BigDecimal.valueOf(vacationPayment.getQuantityDays()))
                .multiply(BigDecimal.valueOf(1.0 / 3.0)).setScale(2, BigDecimal.ROUND_HALF_UP);

        vacationPayment.getAdditions().add(Payment
                .builder()
                .withName(THIRD)
                .withValue(vacationSalary)
                .build());
    }

    @Override
    protected boolean apply(VacationPayment vacationPayment) {
        return vacationPayment.getPayment().compareTo(BigDecimal.valueOf(0)) >= 0;
    }
}
