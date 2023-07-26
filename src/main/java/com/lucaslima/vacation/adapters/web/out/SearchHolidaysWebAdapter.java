package com.lucaslima.vacation.adapters.web.out;

import com.lucaslima.vacation.application.domains.State;
import com.lucaslima.vacation.application.ports.out.SearchHolidaysPort;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class SearchHolidaysWebAdapter implements SearchHolidaysPort {
    @Override
    public List<LocalDate> search(LocalDate start, LocalDate end, State state) {
        return List.of(
                LocalDate.of(2023, 7, 7),
                LocalDate.of(2023, 9, 7),
                LocalDate.of(2023, 10, 12),
                LocalDate.of(2023, 11, 2),
                LocalDate.of(2023, 11, 15),
                LocalDate.of(2023, 12, 25),
                LocalDate.of(2024, 1, 1),
                LocalDate.of(2024, 2, 12),
                LocalDate.of(2024, 2, 13),
                LocalDate.of(2024, 3, 29),
                LocalDate.of(2024, 3, 31),
                LocalDate.of(2024, 4, 21),
                LocalDate.of(2024, 5, 1),
                LocalDate.of(2024, 5, 30));
    }
}
