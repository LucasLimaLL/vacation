package com.lucaslima.vacation.adapters.web.in.vacations.dto.mapper;


import com.lucaslima.vacation.application.domains.periods.City;
import com.lucaslima.vacation.application.domains.periods.State;
import com.lucaslima.vacation.application.domains.periods.VacationRequest;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class VacationRequestDomainMapper {

    public static VacationRequest toDomain(String cityName,
                                           String state,
                                           LocalDate start,
                                           LocalDate end,
                                           int quantityDays,
                                           int slice,
                                           List<Integer> workDays) {
        return VacationRequest
                .builder()
                .withCity(City
                        .builder()
                        .withName(cityName)
                        .withState(State.fromAbbreviation(state))
                        .build())
                .withStart(start)
                .withEnd(end)
                .withDays(quantityDays)
                .withSplit(slice)
                .withWorkDays(workDays.stream()
                        .map(DayOfWeek::of)
                        .collect(Collectors.toList()))
                .build();
    }
}
