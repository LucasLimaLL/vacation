package com.lucaslima.vacation.application.service;

import com.lucaslima.vacation.application.domains.*;
import com.lucaslima.vacation.application.ports.in.CalculateVacationPeriodsUseCase;
import com.lucaslima.vacation.application.ports.out.SearchHolidaysPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

@Service
public class CalculateVacationPeriodsService implements CalculateVacationPeriodsUseCase {

    private static final int MINIMUM = 5;
    private static final int MAXIMUM_PERIODS = 3;
    private final SearchHolidaysPort searchHolidaysPort;
    private final List<VacationRule> rules;

    @Autowired
    public CalculateVacationPeriodsService(SearchHolidaysPort searchHolidaysPort,
                                           List<VacationRule> rules) {
        this.searchHolidaysPort = searchHolidaysPort;
        this.rules = rules;
    }

    @Override
    public List<Vacation> calculate(VacationRequest vacationRequest) {

        rules.forEach(rule -> rule.validate(vacationRequest));

        List<Period> periodsByRange = new ArrayList<>();
        var holidayList = this.searchHolidaysPort.search(
                vacationRequest.getStart(),
                vacationRequest.getEnd(),
                vacationRequest.getCity().getState());

        Set<Integer> ranges = new HashSet<>();
        List<List<Integer>> combinationsList =
                findCombinations(vacationRequest.getDays() + vacationRequest.getExtraDays())
                        .stream()
                        .filter(combination -> vacationRequest.getSplit() == 0 || combination.size() == vacationRequest.getSplit())
                        .toList();

        combinationsList.forEach(combination -> ranges.addAll(combination));
        ranges.forEach(range -> periodsByRange.addAll(calculate(vacationRequest.getCity(), vacationRequest.getStart(), vacationRequest.getEnd(), range, vacationRequest.getWorkDays(), holidayList)));

        return combinationsList
                .stream()
                .map(combination ->
                        Vacation
                                .builder()
                                .withPeriod(periodsByRange.stream().filter(period -> combination.contains(period.getQuantity())).collect(Collectors.toList()))
                                .withCombination(combination)
                                .withHolidaysInPeriod(holidayList.stream().map(holiday -> holiday.getDate()).collect(Collectors.toList()))
                                .build())
                .collect(Collectors.toList());
    }

    private List<Period> calculate(City city, LocalDate start, LocalDate end, Integer range, List<DayOfWeek> workDays, List<Holiday> holidayList) {
        return calculate(start, end, range, holidayList, workDays);
    }

    private List<Period> calculate(LocalDate start, LocalDate end, Integer range, List<Holiday> holidayList, List<DayOfWeek> workDays) {
        List<Period> periods = new ArrayList<>();

        for (int i = 0; i < ChronoUnit.DAYS.between(start, end) - range; i++) {

            var startRange = start.plus(i, ChronoUnit.DAYS);
            var endRange = startRange.plus(range, ChronoUnit.DAYS);
            var startExtra = startRange;
            var endExtra = endRange;

            while (!isWorkDay(startExtra, workDays) || isHoliday(startExtra, holidayList)) {
                startExtra = startExtra.minus(1, ChronoUnit.DAYS);
            }

            while (!isWorkDay(endExtra, workDays) || isHoliday(endExtra, holidayList)) {
                endExtra = endExtra.plus(1, ChronoUnit.DAYS);
            }

            periods.add(
                    Period
                            .builder()
                            .withStart(startRange)
                            .withEnd(endRange)
                            .withQuantity((int) ChronoUnit.DAYS.between(startRange, endRange))
                            .withExtra((int) ChronoUnit.DAYS.between(startExtra, startRange) + (int) ChronoUnit.DAYS.between(endRange, endExtra))
                            .build());
        }

        var extra = periods.stream().max(comparing(Period::getExtra)).get().getExtra();

        periods = periods.stream()
                .filter(period -> period.getExtra() >= extra)
                .limit(5)
                .sorted(comparing(Period::getStart))
                .collect(Collectors.toList());

        return periods;
    }

    private boolean isWorkDay(LocalDate date, List<DayOfWeek> workDays) {
        return workDays.contains(date.getDayOfWeek());
    }

    private boolean isHoliday(LocalDate date, List<Holiday> holidayList) {
        return holidayList.stream().map(holiday -> holiday.getDate()).collect(Collectors.toList()).contains(date);
    }

    private List<List<Integer>> findCombinations(int quantityOfDays) {
        List<List<Integer>> result = new ArrayList<>();

        for (int split = 1; split <= MAXIMUM_PERIODS; split++) {
            findCombinations(quantityOfDays, split, MINIMUM, new ArrayList<>(), result);
        }
        return result;
    }

    private void findCombinations(int quantityOfDays,
                                  int split,
                                  int start,
                                  List<Integer> current,
                                  List<List<Integer>> result) {
        if (quantityOfDays == 0 && current.size() == split) {
            result.add(new ArrayList<>(current));
            return;
        }
        for (int i = start; i <= quantityOfDays; i++) {
            if (i >= MINIMUM) {
                current.add(i);
                findCombinations(quantityOfDays - i, split, i + 1, current, result);
                current.remove(current.size() - 1);
            }
        }
    }

}
