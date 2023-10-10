package com.lucaslima.vacation.adapters.web.in.vacations.dto.mapper;

import com.lucaslima.vacation.adapters.web.in.vacations.dto.CalculateVacationPeriodsDto;
import com.lucaslima.vacation.application.domains.Vacation;

import java.util.List;
import java.util.stream.Collectors;

public class CalculateVacationPeriodsMapper {
    public static List<CalculateVacationPeriodsDto> toListDto(List<Vacation> vacations) {
        return vacations
                .stream()
                .map(vacation -> toDto(vacation))
                .collect(Collectors.toList());
    }

    private static CalculateVacationPeriodsDto toDto(Vacation vacation) {
        return CalculateVacationPeriodsDto
                .builder()
                .withHolidaysInPeriod(vacation.getHolidaysInPeriod())
                .withPeriod(PeriodsMapper.toListDto(vacation.getPeriod()))
                .withCombinations(vacation.getCombination())
                .build();
    }
}
