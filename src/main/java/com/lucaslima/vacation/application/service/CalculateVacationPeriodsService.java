package com.lucaslima.vacation.application.service;

import com.lucaslima.vacation.application.domains.City;
import com.lucaslima.vacation.application.domains.Period;
import com.lucaslima.vacation.application.domains.Vacation;
import com.lucaslima.vacation.application.ports.in.CalculateVacationPeriodsUseCase;
import com.lucaslima.vacation.application.ports.out.SearchHolidaysPort;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

public class CalculateVacationPeriodsService implements CalculateVacationPeriodsUseCase {

    private static final int MINIMUM = 5;
    private static final int MAXIMUM_PERIODS = 3;
    private final SearchHolidaysPort searchHolidaysPort;

    @Autowired
    public CalculateVacationPeriodsService(SearchHolidaysPort searchHolidaysPort) {
        this.searchHolidaysPort = searchHolidaysPort;
    }

    @Override
    public List<Vacation> calculate(City city, LocalDate start, LocalDate end, int days, int split, int extraDays, List<DayOfWeek> workDays) {

        List<Period> periodsByRange = new ArrayList<>();
        Set<Integer> ranges = new HashSet<>();
        List<List<Integer>> combinationsList =
                findCombinations(days + extraDays).stream()
                        .filter(combination -> split == 0 || combination.size() == split)
                        .toList();

        combinationsList.forEach(combination -> ranges.addAll(combination));
        ranges.forEach(range -> periodsByRange.addAll(calculate(city, start, end, range, workDays)));

        return combinationsList
                .stream()
                .map(combination ->
                        Vacation
                                .builder()
                                .withPeriod(periodsByRange.stream().filter(period -> combination.contains(period.getQuantity())).collect(Collectors.toList()))
                                .withCombination(combination)
                                .build())
                .collect(Collectors.toList());
    }

    @Override
    public List<Period> calculate(City city, LocalDate start, LocalDate end, Integer range, List<DayOfWeek> workDays) {
        var holidayList = this.searchHolidaysPort.search(start, end, city.getState());
        return calculate(start, end, range, holidayList, workDays);
    }

    private List<Period> calculate(LocalDate start, LocalDate end, Integer range, List<LocalDate> holidayList, List<DayOfWeek> workDays) {
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

    private boolean isHoliday(LocalDate date, List<LocalDate> holidayList) {
        return holidayList.contains(date);
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
