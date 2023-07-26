package com.lucaslima.vacation.configuration.configuration;

import com.lucaslima.vacation.application.ports.in.CalculateVacationPeriodsUseCase;
import com.lucaslima.vacation.application.ports.out.SearchHolidaysPort;
import com.lucaslima.vacation.application.service.CalculateVacationPeriodsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VacationAppConfiguration {

    @Bean
    public CalculateVacationPeriodsUseCase calculateVacationPeriodsUseCase(SearchHolidaysPort searchHolidaysPort) {
        return new CalculateVacationPeriodsService(searchHolidaysPort);
    }
}
