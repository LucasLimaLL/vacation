package com.lucaslima.vacation.adapters.web.out.feign.mapper;

import com.lucaslima.vacation.adapters.web.out.feign.dto.HolidayDto;
import com.lucaslima.vacation.application.domains.Holiday;

public class SearchHolidayMapper {

    public static Holiday toDomain(HolidayDto holidayDto) {
        return Holiday
                .builder()
                .withName(holidayDto.getName())
                .withDate(holidayDto.getDate())
                .build();
    }
}
