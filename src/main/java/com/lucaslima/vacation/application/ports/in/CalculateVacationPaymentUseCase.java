package com.lucaslima.vacation.application.ports.in;

import com.lucaslima.vacation.application.domains.payments.VacationPayment;

import java.math.BigDecimal;

public interface CalculateVacationPaymentUseCase {

    VacationPayment calculate(BigDecimal salary,
                              Integer quantityDays,
                              BigDecimal averageValue,
                              Integer dependents,
                              boolean cashAllowance,
                              BigDecimal alimony,
                              boolean salaryAdvance);
}
