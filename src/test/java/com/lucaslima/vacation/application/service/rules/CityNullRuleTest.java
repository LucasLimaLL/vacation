package com.lucaslima.vacation.application.service.rules;

import com.lucaslima.vacation.application.domains.VacationRequestSupport;
import com.lucaslima.vacation.application.exceptions.BrokenRuleValidationException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class CityNullRuleTest {

    static final CalculateVacationPeriodsRule RULE = new CityNullRuleCalculatePeriods();

    @Test
    void givenVacationDataWithNoSplitWhenCalculateThenReturnVacationPeriods() {
        assertThatCode(() -> RULE.validate(
                VacationRequestSupport
                        .get()
                        .build()))
                .doesNotThrowAnyException();
    }

    @Test
    void givenVacationDataWithCityNullWhenCalculateThenThrowsException() {

        assertThatThrownBy(() -> RULE.validate(
                VacationRequestSupport
                        .get()
                        .withCity(null)
                        .build()))
                .isInstanceOf(BrokenRuleValidationException.class)
                .hasMessage("Cidade deve vir preenchida!");
    }
}