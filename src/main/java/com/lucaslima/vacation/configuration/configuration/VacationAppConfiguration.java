package com.lucaslima.vacation.configuration.configuration;

import com.lucaslima.vacation.application.ports.in.CalculateVacationPeriodsUseCase;
import com.lucaslima.vacation.application.ports.out.SearchHolidaysPort;
import com.lucaslima.vacation.application.service.CalculateVacationPeriodsService;
import com.lucaslima.vacation.application.service.VacationRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class VacationAppConfiguration {

    @Bean
    public CalculateVacationPeriodsUseCase calculateVacationPeriodsUseCase(SearchHolidaysPort searchHolidaysPort, List<VacationRule> rules) {
        return new CalculateVacationPeriodsService(searchHolidaysPort, rules);
    }
}
