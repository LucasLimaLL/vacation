package com.lucaslima.vacation.adapters.web.out;

import com.lucaslima.vacation.adapters.web.out.feign.SearchHolidaysOpenFeign;
import com.lucaslima.vacation.adapters.web.out.support.HolidayDtoSupport;
import com.lucaslima.vacation.application.domains.periods.State;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class SearchHolidaysWebAdapterTest {

    private SearchHolidaysWebAdapter searchHolidaysWebAdapter;

    @Mock
    private SearchHolidaysOpenFeign searchHolidaysOpenFeign;

    @BeforeEach
    void setUp() {
        searchHolidaysWebAdapter = new SearchHolidaysWebAdapter(searchHolidaysOpenFeign, UUID.randomUUID().toString());
    }

    @Test
    void givenSearchHolidaysScenarioWhenSearchThenReturnLIstOfHolidays() {

        when(searchHolidaysOpenFeign
                .getHolidays(Mockito.anyInt(), Mockito.anyString(), Mockito.anyString()))
                .thenReturn(HolidayDtoSupport.get());
        assertThat(searchHolidaysWebAdapter.search(
                LocalDate.of(2023, 1, 1),
                LocalDate.of(2024, 1, 1),
                State.SP)).isNotEmpty();
    }

}