package com.lucaslima.vacation.application.service.payments.rule.deductions;

import com.lucaslima.vacation.application.domains.payments.VacationPaymentSupport;
import com.lucaslima.vacation.application.service.payments.rule.deductions.INSSRule;
import com.lucaslima.vacation.application.service.payments.rule.deductions.Table;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class INSSRuleTest {

    private static final INSSRule INSS_RULE = new INSSRule(List.of(
            Table.builder().withTax(BigDecimal.valueOf(0.075)).withStart(BigDecimal.valueOf(0)).withEnd(BigDecimal.valueOf(1302.00)).build(),
            Table.builder().withTax(BigDecimal.valueOf(0.09)).withStart(BigDecimal.valueOf(1302.01)).withEnd(BigDecimal.valueOf(2571.28)).build(),
            Table.builder().withTax(BigDecimal.valueOf(0.12)).withStart(BigDecimal.valueOf(2571.29)).withEnd(BigDecimal.valueOf(3856.94)).build(),
            Table.builder().withTax(BigDecimal.valueOf(0.14)).withStart(BigDecimal.valueOf(3856.95)).withEnd(BigDecimal.valueOf(99999999.99)).build()
    ));

    @Test
    void givenSalaryFirstRateWhenCalculateReturnSalaryWithPercentage() {
        var vacationPayment =
                VacationPaymentSupport
                        .get()
                        .withSalary(BigDecimal.valueOf(1000))
                        .build();

        assertThat(vacationPayment.getDeductions()).isEmpty();
        INSS_RULE.validate(vacationPayment);
        assertThat(vacationPayment.getDeductions()).hasSize(1);
        assertThat(vacationPayment.getDeductions().get(0).getValue().compareTo(BigDecimal.valueOf(75.00)) == 0).isTrue();
    }

    @Test
    void givenSalarySecondRateWhenCalculateReturnSalaryWithPercentage() {
        var vacationPayment =
                VacationPaymentSupport
                        .get()
                        .withSalary(BigDecimal.valueOf(2000))
                        .build();

        assertThat(vacationPayment.getDeductions()).isEmpty();
        INSS_RULE.validate(vacationPayment);
        assertThat(vacationPayment.getDeductions()).hasSize(1);
        assertThat(vacationPayment.getDeductions().get(0).getValue().compareTo(BigDecimal.valueOf(180)) == 0).isTrue();
    }

    @Test
    void givenSalaryThirdRateWhenCalculateReturnSalaryWithPercentage() {
        var vacationPayment =
                VacationPaymentSupport
                        .get()
                        .withSalary(BigDecimal.valueOf(3000))
                        .build();

        assertThat(vacationPayment.getDeductions()).isEmpty();
        INSS_RULE.validate(vacationPayment);
        assertThat(vacationPayment.getDeductions()).hasSize(1);
        assertThat(vacationPayment.getDeductions().get(0).getValue().compareTo(BigDecimal.valueOf(360.00)) == 0).isTrue();
    }

    @Test
    void givenSalaryFourthRateWhenCalculateReturnSalaryWithPercentage() {
        var vacationPayment =
                VacationPaymentSupport
                        .get()
                        .withSalary(BigDecimal.valueOf(4000))
                        .build();

        assertThat(vacationPayment.getDeductions()).isEmpty();
        INSS_RULE.validate(vacationPayment);
        assertThat(vacationPayment.getDeductions()).hasSize(1);
        assertThat(vacationPayment.getDeductions().get(0).getValue().compareTo(BigDecimal.valueOf(560.00)) == 0).isTrue();
    }

}