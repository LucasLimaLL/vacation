package com.lucaslima.vacation.application.domains;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder(toBuilder = true, setterPrefix = "with")
public class Holiday {

    private final String name;
    private final LocalDate date;
}
