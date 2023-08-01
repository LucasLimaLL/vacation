package com.lucaslima.vacation.adapters.web.out.support;

import com.lucaslima.vacation.adapters.web.out.feign.dto.HolidayDto;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

public class HolidayDtoSupport {

    private final static String TYPE = "feriado";
    private final static String NATIONAL = "nacional";
    private final static String STATE = "estadual";
    private final static LocalDate TODAY = LocalDate.now();
    public static List<HolidayDto> get() {
        return List.of(
                HolidayDto
                        .builder()
                        .withDate(LocalDate.of(TODAY.getYear(), Month.JANUARY, 1))
                        .withName("Confraternização Universal")
                        .withLevel(NATIONAL)
                        .withType(TYPE)
                        .build(),
                HolidayDto
                        .builder()
                        .withDate(LocalDate.of(TODAY.getYear(), Month.APRIL, 21))
                        .withName("Tiradentes")
                        .withLevel(NATIONAL)
                        .withType(TYPE)
                        .build(),
                HolidayDto
                        .builder()
                        .withDate(LocalDate.of(TODAY.getYear(), Month.MAY, 1))
                        .withName("Dia do Trabalhador")
                        .withLevel(NATIONAL)
                        .withType(TYPE)
                        .build(),
                HolidayDto
                        .builder()
                        .withDate(LocalDate.of(TODAY.getYear(), Month.JULY, 9))
                        .withName("Revolução Constitucionalista")
                        .withLevel(STATE)
                        .withType(TYPE)
                        .build()
        );
    }
}
