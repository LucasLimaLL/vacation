package com.lucaslima.vacation.application.domains;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder(toBuilder = true, setterPrefix = "with")
public class Period {

    private LocalDate start;
    private LocalDate end;
    private int quantity;
    private int extra;
}
