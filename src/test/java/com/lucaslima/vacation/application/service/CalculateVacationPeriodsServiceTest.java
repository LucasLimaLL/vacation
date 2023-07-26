package com.lucaslima.vacation.application.service;

import com.lucaslima.vacation.application.domains.City;
import com.lucaslima.vacation.application.domains.State;
import com.lucaslima.vacation.application.domains.VacationRequest;
import com.lucaslima.vacation.application.exceptions.BrokenRuleValidationException;
import com.lucaslima.vacation.application.ports.in.CalculateVacationPeriodsUseCase;
import com.lucaslima.vacation.application.ports.out.SearchHolidaysPort;
import com.lucaslima.vacation.application.service.rules.QuantityDaysIsInvalidRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class CalculateVacationPeriodsServiceTest {

    private CalculateVacationPeriodsUseCase calculateVacationPeriodsUseCase;

    @Mock
    private SearchHolidaysPort searchHolidaysPort;

    @BeforeEach
    void setUp() {
        this.calculateVacationPeriodsUseCase = new CalculateVacationPeriodsService(searchHolidaysPort, List.of(new QuantityDaysIsInvalidRule()));
    }

    @Test
    void givenVacationDataWithNoSplitWhenCalculateThenReturnVacationPeriods() {

        when(this.searchHolidaysPort.search(Mockito.any(LocalDate.class), Mockito.any(LocalDate.class), Mockito.any(State.class)))
                .thenReturn(List.of(
                        LocalDate.of(2023, 7, 7),
                        LocalDate.of(2023, 9, 7),
                        LocalDate.of(2023, 10, 12),
                        LocalDate.of(2023, 11, 2),
                        LocalDate.of(2023, 11, 15),
                        LocalDate.of(2023, 12, 25),
                        LocalDate.of(2024, 1, 1),
                        LocalDate.of(2024, 2, 12),
                        LocalDate.of(2024, 2, 13),
                        LocalDate.of(2024, 3, 29),
                        LocalDate.of(2024, 3, 31),
                        LocalDate.of(2024, 4, 21),
                        LocalDate.of(2024, 5, 1),
                        LocalDate.of(2024, 5, 30)));

        var vacationList = calculateVacationPeriodsUseCase.calculate(
                VacationRequest
                        .builder()
                        .withCity(City.builder().withName("SAO PAULO").withState(State.SP).build())
                        .withStart(LocalDate.now().plus(1, ChronoUnit.MONTHS))
                        .withEnd(LocalDate.now().plus(10, ChronoUnit.MONTHS))
                        .withDays(20)
                        .withSplit(0)
                        .withExtraDays(0)
                        .withWorkDays(List.of(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY))
                        .build()
        );

        assertThat(vacationList).isNotEmpty();
        assertThat(vacationList).hasSize(8);
        assertThat(vacationList.get(0)).isNotNull();
    }

    @Test
    void givenVacationDataWithInvalidDaysWhenCalculateThenThrowsException() {

        assertThatThrownBy(() -> calculateVacationPeriodsUseCase.calculate(
                VacationRequest
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