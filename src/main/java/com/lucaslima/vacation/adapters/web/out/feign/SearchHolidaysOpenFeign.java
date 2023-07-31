package com.lucaslima.vacation.adapters.web.out.feign;

import com.lucaslima.vacation.adapters.web.out.feign.dto.HolidayDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@FeignClient(value = "holidays", url = "https://api.invertexto.com/v1/holidays")
public interface SearchHolidaysOpenFeign {

    @RequestMapping(method = RequestMethod.GET, value = "/{year}", produces = "application/json")
    List<HolidayDto> getHolidays(@PathVariable("year") Integer year,
                                 @RequestParam("token") String token,
                                 @RequestParam("state") String state);
}
