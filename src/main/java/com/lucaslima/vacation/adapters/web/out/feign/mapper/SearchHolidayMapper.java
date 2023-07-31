package com.lucaslima.vacation.adapters.web.out.feign.mapper;

import com.lucaslima.vacation.adapters.web.out.feign.dto.HolidayDto;
import com.lucaslima.vacation.application.domains.Holiday;

import java.util.List;
import java.util.stream.Collectors;

public class SearchHolidayMapper {

    public static List<Holiday> toDomain(List<HolidayDto> holidayDtos) {
        return holidayDtos
                .stream()
                .map(holidayDto -> toDomain(holidayDto))
                .collect(Collectors.toList());
    }
    public static Holiday toDomain(HolidayDto holidayDto) {
        return Holiday
                .builder()
                .withName(holidayDto.getName())
                .withDate(holidayDto.getDate())
                .build();
    }
}
