package com.lucaslima.vacation.application.domains.payments;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
@Builder(toBuilder = true, setterPrefix = "with")
public class VacationPayment {

    private final BigDecimal salary;
    private final Integer quantityDays;
    private final BigDecimal averageValue;
    private final Integer dependents;
    private final boolean cashAllowance;
    private final BigDecimal alimony;
    private final boolean salaryAdvance;

    @Builder.Default
    private List<Payment> deductions = new ArrayList<>();

    @Builder.Default
    private List<Payment> additions = new ArrayList<>();

    public BigDecimal getPayment() {
        var salaryVacation = salary;
        Optional.ofNullable(additions)
                .ifPresent(
                        additions -> additions
                                .stream()
                                .forEach(addition -> salaryVacation.add(addition.getValue()))
                );
        Optional.ofNullable(deductions)
                .ifPresent(
                        deductions -> deductions
                                .stream()
                                .forEach(deduction -> salaryVacation.subtract(deduction.getValue()))
                );
        return salaryVacation;
    }

}
