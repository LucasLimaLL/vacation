package com.lucaslima.vacation.adapters.web.out.feign;

import com.lucaslima.vacation.adapters.web.out.feign.dto.HolidaysDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;


@FeignClient(value = "holidays", url = "https://api.invertexto.com/v1/holidays")
public interface SearchHolidaysOpenFeign {

    @RequestMapping(method = RequestMethod.GET, value = "/{year}", produces = "application/json")
    public HolidaysDto getHolidays(@PathVariable("year") Integer year,
                                   @RequestParam("token") String token,
                                   @RequestParam("state") String state);
}
