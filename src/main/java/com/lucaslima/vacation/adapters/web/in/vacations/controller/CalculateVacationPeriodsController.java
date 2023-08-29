package com.lucaslima.vacation.adapters.web.in.vacations.controller;

import com.lucaslima.vacation.adapters.web.in.shared.constants.WebAdapterConstants;
import com.lucaslima.vacation.adapters.web.in.vacations.dto.CalculateVacationPeriodsDto;
import com.lucaslima.vacation.adapters.web.in.shared.dto.DataWrapper;
import com.lucaslima.vacation.adapters.web.in.vacations.dto.mapper.CalculateVacationPeriodsMapper;
import com.lucaslima.vacation.adapters.web.in.vacations.dto.mapper.VacationRequestDomainMapper;
import com.lucaslima.vacation.application.domains.periods.Vacation;
import com.lucaslima.vacation.application.domains.periods.VacationRequest;
import com.lucaslima.vacation.application.ports.in.CalculateVacationPeriodsUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CalculateVacationPeriodsController {

    private final CalculateVacationPeriodsUseCase calculateVacationPeriodsUseCase;

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @RequestMapping(
            method = RequestMethod.GET,
            path = WebAdapterConstants.PATH
    )
    public ResponseEntity<DataWrapper<List<CalculateVacationPeriodsDto>>> calculate(
            @RequestParam(name = "cidade") String city,
            @RequestParam(name = "estado") String state,
            @RequestParam(name = "inicio_periodo") String start,
            @RequestParam(name = "fim_periodo") String end,
            @RequestParam(name = "qtd_dias") int quantityDays,
            @RequestParam(name = "valor_divisao") int slice,
            @RequestParam(name = "dias_trabalho") List<Integer> workDays
    ) {

        LocalDate startPeriod = LocalDate.parse(start, DATE_FORMATTER);
        LocalDate endPeriod = LocalDate.parse(end, DATE_FORMATTER);

        VacationRequest request = VacationRequestDomainMapper.toDomain(city, state, startPeriod, endPeriod, quantityDays, slice, workDays);
        List<Vacation> vacations = calculateVacationPeriodsUseCase.calculate(request);

        return ResponseEntity.ok(new DataWrapper(CalculateVacationPeriodsMapper.toListDto(vacations)));
    }

}
