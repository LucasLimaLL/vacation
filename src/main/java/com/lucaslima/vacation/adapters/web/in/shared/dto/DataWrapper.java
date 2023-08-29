package com.lucaslima.vacation.adapters.web.in.shared.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class DataWrapper<T> {

    @JsonProperty("data")
    private final T data;
}
