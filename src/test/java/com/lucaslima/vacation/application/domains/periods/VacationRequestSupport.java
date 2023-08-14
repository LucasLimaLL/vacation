package com.lucaslima.vacation.application.domains.periods;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class VacationRequestSupport {

    public static VacationRequest.VacationRequestBuilder get() {
        return VacationRequest
                .builder()
                .withCity(City.builder().withName("SAO PAULO").withState(State.SP).build())
                .withStart(LocalDate.now().plus(1, ChronoUnit.MONTHS))
                .withEnd(LocalDate.now().plus(10, ChronoUnit.MONTHS))
                .withDays(20)
                .withSplit(0)
                .withExtraDays(0)
                .withWorkDays(List.of(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY));
    }

}