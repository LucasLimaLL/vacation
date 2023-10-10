package com.lucaslima.vacation.application.service.rules;

import com.lucaslima.vacation.application.domains.VacationRequestSupport;
import com.lucaslima.vacation.application.exceptions.BrokenRuleValidationException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class QuantityDaysIsInvalidRuleTest {

    static final CalculateVacationPeriodsRule RULE = new QuantityDaysIsInvalidRuleCalculatePeriods();

    @Test
    void givenVacationDataWithNoSplitWhenCalculateThenReturnVacationPeriods() {
        assertThatCode(() -> RULE.validate(
                VacationRequestSupport
                        .get()
                        .build()))
                .doesNotThrowAnyException();
    }

    @Test
    void givenVacationDataWithInvalidDaysWhenCalculateThenThrowsException() {

        assertThatThrownBy(() -> RULE.validate(
                VacationRequestSupport
                        .get()
                        .withDays(2)
                        .build()))
                .isInstanceOf(BrokenRuleValidationException.class)
                .hasMessage("Quantidade de dias informado é inválido");
    }
}