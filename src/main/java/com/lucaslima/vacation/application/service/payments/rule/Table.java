package com.lucaslima.vacation.application.service.payments.rule;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Builder(toBuilder = true, setterPrefix = "with")
@Getter
public class Table {

    private BigDecimal start;
    private BigDecimal end;
    private BigDecimal tax;
}
