package com.lucaslima.vacation.adapters.web.out;

import com.lucaslima.vacation.adapters.web.out.feign.SearchHolidaysOpenFeign;
import com.lucaslima.vacation.adapters.web.out.feign.mapper.SearchHolidayMapper;
import com.lucaslima.vacation.application.domains.Holiday;
import com.lucaslima.vacation.application.domains.State;
import com.lucaslima.vacation.application.ports.out.SearchHolidaysPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SearchHolidaysWebAdapter implements SearchHolidaysPort {

    private final SearchHolidaysOpenFeign searchHolidaysOpenFeign;
    private final String token;

    @Autowired
    public SearchHolidaysWebAdapter(SearchHolidaysOpenFeign searchHolidaysOpenFeign,
                                    @Value("${holidays.search}") String token) {
        this.searchHolidaysOpenFeign = searchHolidaysOpenFeign;
        this.token = token;
    }

    @Override
    public List<Holiday> search(LocalDate start, LocalDate end, State state) {

        List<Integer> yearsList = new ArrayList<>();

        int startYear = start.getYear();
        int endYear = end.getYear();

        for (int year = startYear; year <= endYear; year++) {
            yearsList.add(year);
        }

        return yearsList
                .stream()
                .map(year -> this.searchHolidaysOpenFeign.getHolidays(year, token, state.getName()))
                .flatMap(holidays -> holidays.stream())
                .map(holiday -> SearchHolidayMapper.toDomain(holiday))
                .collect(Collectors.toList());
    }
}
