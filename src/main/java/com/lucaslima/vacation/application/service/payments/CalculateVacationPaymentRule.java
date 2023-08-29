package com.lucaslima.vacation.application.service.payments;

import com.lucaslima.vacation.application.domains.payments.VacationPayment;

public abstract class CalculateVacationPaymentRule {

    public void validate(final VacationPayment vacationPayment) {
        if (apply(vacationPayment)) {
            include(vacationPayment);
        }
    }

    protected abstract void include(VacationPayment vacationPayment);

    protected abstract boolean apply(VacationPayment vacationPayment);
}
