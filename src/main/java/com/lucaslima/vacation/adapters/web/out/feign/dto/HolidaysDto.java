package com.lucaslima.vacation.adapters.web.out.feign.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class HolidaysDto {

    private List<HolidayDto> holidays;
}
