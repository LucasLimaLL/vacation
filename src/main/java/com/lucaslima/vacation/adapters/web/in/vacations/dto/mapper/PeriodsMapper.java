package com.lucaslima.vacation.adapters.web.in.vacations.dto.mapper;

import com.lucaslima.vacation.adapters.web.in.vacations.dto.PeriodDto;
import com.lucaslima.vacation.application.domains.periods.Period;

import java.util.List;

public class PeriodsMapper {
    public static List<PeriodDto> toListDto(List<Period> periods) {
        return periods
                .stream()
                .map(period -> toDto(period))
                .toList();
    }

    private static PeriodDto toDto(Period period) {
        return PeriodDto
                .builder()
                .withStart(period.getStart())
                .withEnd(period.getEnd())
                .withExtra(period.getExtra())
                .withQuantity(period.getQuantity())
                .build();
    }
}
