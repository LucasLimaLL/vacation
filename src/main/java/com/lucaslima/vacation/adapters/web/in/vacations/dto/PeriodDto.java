package com.lucaslima.vacation.adapters.web.in.vacations.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder(toBuilder = true, setterPrefix = "with")
public class PeriodDto {

    private LocalDate start;
    private LocalDate end;
    private int quantity;
    private int extra;
}
