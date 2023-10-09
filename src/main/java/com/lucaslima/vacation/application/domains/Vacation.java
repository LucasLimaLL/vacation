package com.lucaslima.vacation.application.domains;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder(toBuilder = true, setterPrefix = "with")
public class Vacation {

    private List<LocalDate> holidaysInPeriod;
    private List<Period> period;
    private List<Integer> combination;
}
