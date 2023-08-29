package com.lucaslima.vacation.adapters.web.in.vacations.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(toBuilder = true, setterPrefix = "with")
public class CityDto {

    private final String name;
    private final String state;
}
