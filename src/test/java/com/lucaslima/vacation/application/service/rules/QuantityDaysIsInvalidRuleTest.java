package com.lucaslima.vacation.application.service.rules;

import com.lucaslima.vacation.application.domains.City;
import com.lucaslima.vacation.application.domains.State;
import com.lucaslima.vacation.application.domains.VacationRequest;
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
        assertThatCode(() -> RULE.validate(VacationRequest
                .builder()
                .withCity(City.builder().withName("SAO PAULO").withState(State.SP).build())
                .withStart(LocalDate.now().plus(1, ChronoUnit.MONTHS))
                .withEnd(LocalDate.now().plus(10, ChronoUnit.MONTHS))
                .withDays(20)
                .withSplit(0)
                .withExtraDays(0)
                .withWorkDays(List.of(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY))
                .build()))
                .doesNotThrowAnyException();
    }

    @Test
    void givenVacationDataWithInvalidDaysWhenCalculateThenThrowsException() {

        assertThatThrownBy(() -> RULE.validate(VacationRequest
                .builder()
                .withCity(City.builder().withName("SAO PAULO").withState(State.SP).build())
                .withStart(LocalDate.now().plus(1, ChronoUnit.MONTHS))
                .withEnd(LocalDate.now().plus(10, ChronoUnit.MONTHS))
                .withDays(2)
                .withSplit(0)
                .withExtraDays(0)
                .withWorkDays(List.of(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY))
                .build()))
                .isInstanceOf(BrokenRuleValidationException.class)
                .hasMessage("Quantidade de dias informado é inválido");
    }
}