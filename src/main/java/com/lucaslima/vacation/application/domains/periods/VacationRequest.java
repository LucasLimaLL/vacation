package com.lucaslima.vacation.application.domains.periods;

import lombok.Builder;
import lombok.Getter;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

@Getter
@Builder(toBuilder = true, setterPrefix = "with")
public class VacationRequest {

    private final City city;
    private final LocalDate start;
    private final LocalDate end;
    private final int days;
    private final int split;
    private final int extraDays;
    private final List<DayOfWeek> workDays;
}
