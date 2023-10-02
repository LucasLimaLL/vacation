package com.lucaslima.vacation.application.service.payments.rule.deductions;

import com.lucaslima.vacation.application.domains.payments.VacationPaymentSupport;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class IRRFRuleTest {

    private static final IRRFRule IRRF_RULE = new IRRFRule(List.of(
            Table.builder().withStart(BigDecimal.valueOf(0)).withEnd(BigDecimal.valueOf(1903.98)).withTax(BigDecimal.valueOf(0)).build(),
            Table.builder().withStart(BigDecimal.valueOf(1903.99)).withEnd(BigDecimal.valueOf(2826.65)).withTax(BigDecimal.valueOf(0.075)).build(),
            Table.builder().withStart(BigDecimal.valueOf(2826.66)).withEnd(BigDecimal.valueOf(3751.05)).withTax(BigDecimal.valueOf(0.15)).build(),
            Table.builder().withStart(BigDecimal.valueOf(3751.06)).withEnd(BigDecimal.valueOf(4664.68)).withTax(BigDecimal.valueOf(0.225)).build(),
            Table.builder().withStart(BigDecimal.valueOf(4664.68)).withEnd(BigDecimal.valueOf(99999999.99)).withTax(BigDecimal.valueOf(0.275)).build()
    ));

    @Test
    void givenSalaryFirstRateWhenCalculateReturnSalaryWithPercentage() {
        var vacationPayment =
                VacationPaymentSupport
                        .get()
                        .withSalary(BigDecimal.valueOf(1000))
                        .build();

        assertThat(vacationPayment.getDeductions()).isEmpty();
        IRRF_RULE.validate(vacationPayment);
        assertThat(vacationPayment.getDeductions()).hasSize(1);
        assertThat(vacationPayment.getDeductions().get(0).getValue().compareTo(BigDecimal.valueOf(0.00)) == 0).isTrue();
    }

    @Test
    void givenSalarySecondRateWhenCalculateReturnSalaryWithPercentage() {
        var vacationPayment =
                VacationPaymentSupport
                        .get()
                        .withSalary(BigDecimal.valueOf(2000))
                        .build();

        assertThat(vacationPayment.getDeductions()).isEmpty();
        IRRF_RULE.validate(vacationPayment);
        assertThat(vacationPayment.getDeductions()).hasSize(1);
        assertThat(vacationPayment.getDeductions().get(0).getValue().compareTo(BigDecimal.valueOf(150)) == 0).isTrue();
    }

    @Test
    void givenSalaryThirdRateWhenCalculateReturnSalaryWithPercentage() {
        var vacationPayment =
                VacationPaymentSupport
                        .get()
                        .withSalary(BigDecimal.valueOf(3000))
                        .build();

        assertThat(vacationPayment.getDeductions()).isEmpty();
        IRRF_RULE.validate(vacationPayment);
        assertThat(vacationPayment.getDeductions()).hasSize(1);
        assertThat(vacationPayment.getDeductions().get(0).getValue().compareTo(BigDecimal.valueOf(450)) == 0).isTrue();
    }

    @Test
    void givenSalaryFourthRateWhenCalculateReturnSalaryWithPercentage() {
        var vacationPayment =
                VacationPaymentSupport
                        .get()
                        .withSalary(BigDecimal.valueOf(4000))
                        .build();

        assertThat(vacationPayment.getDeductions()).isEmpty();
        IRRF_RULE.validate(vacationPayment);
        assertThat(vacationPayment.getDeductions()).hasSize(1);
        assertThat(vacationPayment.getDeductions().get(0).getValue().compareTo(BigDecimal.valueOf(900.00)) == 0).isTrue();
    }

    @Test
    void givenSalaryFifthRateWhenCalculateReturnSalaryWithPercentage() {
        var vacationPayment =
                VacationPaymentSupport
                        .get()
                        .withSalary(BigDecimal.valueOf(5000))
                        .build();

        assertThat(vacationPayment.getDeductions()).isEmpty();
        IRRF_RULE.validate(vacationPayment);
        assertThat(vacationPayment.getDeductions()).hasSize(1);
        assertThat(vacationPayment.getDeductions().get(0).getValue().compareTo(BigDecimal.valueOf(1375.0)) == 0).isTrue();
    }
}