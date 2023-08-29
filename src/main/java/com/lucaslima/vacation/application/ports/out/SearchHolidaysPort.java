package com.lucaslima.vacation.application.ports.out;

import com.lucaslima.vacation.application.domains.periods.Holiday;
import com.lucaslima.vacation.application.domains.periods.State;

import java.time.LocalDate;
import java.util.List;

public interface SearchHolidaysPort {
    List<Holiday> search(LocalDate start, LocalDate end, State state);
}
