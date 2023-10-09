package com.lucaslima.vacation.adapters.web.out.feign.mapper;

import com.lucaslima.vacation.adapters.web.out.feign.dto.HolidayDto;
import com.lucaslima.vacation.application.domains.Holiday;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SearchHolidayMapper {

    public static Holiday toDomain(HolidayDto holidayDto) {
        return Holiday
                .builder()
                .withName(holidayDto.getName())
                .withDate(holidayDto.getDate())
                .build();
    }
}
