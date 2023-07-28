package com.lucaslima.vacation.application.service.rules;

import com.lucaslima.vacation.application.domains.City;
import com.lucaslima.vacation.application.domains.State;
import com.lucaslima.vacation.application.domains.VacationRequest;
import com.lucaslima.vacation.application.domains.VacationRequestSupport;
import com.lucaslima.vacation.application.exceptions.BrokenRuleValidationException;
import com.lucaslima.vacation.application.service.VacationRule;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class QuantityDaysIsInvalidRuleTest {

    static final VacationRule RULE = new QuantityDaysIsInvalidRule();

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