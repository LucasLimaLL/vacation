package com.lucaslima.vacation.configuration.configuration;

import com.lucaslima.vacation.application.ports.in.CalculateVacationPeriodsUseCase;
import com.lucaslima.vacation.application.ports.out.SearchHolidaysPort;
import com.lucaslima.vacation.application.service.payments.rule.INSSRule;
import com.lucaslima.vacation.application.service.payments.rule.Table;
import com.lucaslima.vacation.application.service.periods.CalculateVacationPeriodsService;
import com.lucaslima.vacation.application.service.periods.CalculateVacationPeriodsRule;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class VacationAppConfiguration {

    @Bean
    public CalculateVacationPeriodsUseCase calculateVacationPeriodsUseCase(SearchHolidaysPort searchHolidaysPort, List<CalculateVacationPeriodsRule> rules) {
        return new CalculateVacationPeriodsService(searchHolidaysPort, rules);
    }

    @Bean
    public INSSRule inssRule(@Value("#{'${inss.table}'.split(',')}") String[] inssValues) {
        var list = Arrays
                .stream(inssValues)
                .map(table -> {
                    String[] values = table.split("\\|");
                    return Table
                            .builder()
                            .withStart(BigDecimal.valueOf(Double.valueOf(values[0])))
                            .withEnd(BigDecimal.valueOf(Double.valueOf(values[1])))
                            .withTax(BigDecimal.valueOf(Double.valueOf(values[2])))
                            .build();
                })
                .collect(Collectors.toList());
        return new INSSRule(list);
    }

}
