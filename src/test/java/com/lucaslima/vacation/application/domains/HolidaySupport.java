package com.lucaslima.vacation.application.domains;

import java.time.LocalDate;
import java.util.List;

public class HolidaySupport {

    public static List<Holiday> get() {
        return List.of(
                Holiday
                        .builder()
                        .withDate(LocalDate.of(2023, 9, 7))
                        .withName("Independência do Brasil")
                        .build(),
                Holiday
                        .builder()
                        .withDate(LocalDate.of(2023, 10, 12))
                        .withName("Nossa Senhora Aparecida")
                        .build(),
                Holiday
                        .builder()
                        .withDate(LocalDate.of(2023, 11, 2))
                        .withName("Finados")
                        .build(),
                Holiday
                        .builder()
                        .withDate(LocalDate.of(2023, 11, 15))
                        .withName("Proclamação da República")
                        .build(),
                Holiday
                        .builder()
                        .withDate(LocalDate.of(2023, 12, 25))
                        .withName("Natal")
                        .build()
        );
    }
}
