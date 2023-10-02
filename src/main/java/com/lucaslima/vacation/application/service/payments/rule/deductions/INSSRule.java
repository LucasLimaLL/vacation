package com.lucaslima.vacation.application.service.payments.rule.deductions;

import com.lucaslima.vacation.application.domains.payments.Payment;
import com.lucaslima.vacation.application.domains.payments.VacationPayment;
import com.lucaslima.vacation.application.service.payments.CalculateVacationPaymentRule;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.util.List;


@AllArgsConstructor
public class INSSRule extends CalculateVacationPaymentRule {
    private static final String INSS = "INSS";
    private final List<Table> tables;


    @Override
    protected void include(VacationPayment vacationPayment) {
        vacationPayment.getDeductions().add(Payment
                .builder()
                .withName(INSS)
                .withValue(vacationPayment.getSalary().multiply(getValue(vacationPayment)))
                .build());
    }

    @Override
    protected boolean apply(VacationPayment vacationPayment) {
        return vacationPayment.getPayment().compareTo(BigDecimal.valueOf(0)) >= 0;
    }


    private BigDecimal getValue(VacationPayment vacationPayment) {
        return
                tables
                        .stream()
                        .filter(table -> vacationPayment.getSalary().compareTo(table.getStart()) >= 0)
                        .filter(table -> vacationPayment.getSalary().compareTo(table.getEnd()) <= 0)
                        .findAny()
                        .get()
                        .getTax();
    }
}
