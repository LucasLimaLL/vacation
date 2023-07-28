package com.lucaslima.vacation.adapters.web.out;

import com.lucaslima.vacation.application.domains.Holiday;
import com.lucaslima.vacation.application.domains.State;
import com.lucaslima.vacation.application.ports.out.SearchHolidaysPort;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class SearchHolidaysWebAdapter implements SearchHolidaysPort {
    @Override
    public List<Holiday> search(LocalDate start, LocalDate end, State state) {
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
