package com.lucaslima.vacation.adapters.web.in.vacations.dto;


import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder(toBuilder = true, setterPrefix = "with")
public class CalculateVacationPeriodsDto {

    private List<LocalDate> holidaysInPeriod;
    private List<PeriodDto> period;
    private List<Integer> combinations;

}
