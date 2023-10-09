package com.lucaslima.vacation.application.domains;

import lombok.Builder;
import lombok.Getter;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

@Getter
@Builder(toBuilder = true, setterPrefix = "with")
public class Request {

    private City city;
    private LocalDate start;
    private LocalDate end;
    private int days;
    private int split;
    private int extraDays;
    private List<DayOfWeek> workDays;
}
