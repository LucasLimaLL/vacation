package com.lucaslima.vacation.application.service;

import com.lucaslima.vacation.application.domains.*;
import com.lucaslima.vacation.application.exceptions.BrokenRuleValidationException;
import com.lucaslima.vacation.application.ports.in.CalculateVacationPeriodsUseCase;
import com.lucaslima.vacation.application.ports.out.SearchHolidaysPort;
import com.lucaslima.vacation.application.service.rules.CityNullRule;
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
        this.calculateVacationPeriodsUseCase = new CalculateVacationPeriodsService(searchHolidaysPort, List.of(new CityNullRule(), new QuantityDaysIsInvalidRule()));
    }

    @Test
    void givenVacationDataWithNoSplitWhenCalculateThenReturnVacationPeriods() {

        when(this.searchHolidaysPort.search(Mockito.any(LocalDate.class), Mockito.any(LocalDate.class), Mockito.any(State.class)))
                .thenReturn(List.of(
                        Holiday
                                .builder()
                                .withDate(LocalDate.of(2023, 9, 7))
                                .withName("Independência do Brasil")
                                .build(),
                        Holiday
                                .builder()
                                .withDate(LocalDate.of(2023, 10, 12))
                                .withName("Nossa Senhora Aparecida")
                                .build(),
                        Holiday
                                .builder()
                                .withDate(LocalDate.of(2023, 11, 2))
                                .withName("Finados")
                                .build(),
                        Holiday
                                .builder()
                                .withDate(LocalDate.of(2023, 11, 15))
                                .withName("Proclamação da República")
                                .build(),
                        Holiday
                                .builder()
                                .withDate(LocalDate.of(2023, 12, 25))
                                .withName("Natal")
                                .build()
                ));

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
                VacationRequestSupport
                        .get()
                        .withDays(2)
                        .build()))
                .isInstanceOf(BrokenRuleValidationException.class)
                .hasMessage("Quantidade de dias informado é inválido");
    }

    @Test
    void givenVacationDataWithCityNullWhenCalculateThenThrowsException() {

        assertThatThrownBy(() -> calculateVacationPeriodsUseCase.calculate(
                VacationRequestSupport
                        .get()
                        .withCity(null)
                        .build()))
                .isInstanceOf(BrokenRuleValidationException.class)
                .hasMessage("Cidade deve vir preenchida!");
    }
}