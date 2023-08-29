package com.lucaslima.vacation.application.domains.payments;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder(toBuilder = true, setterPrefix = "with")
public class Payment {

    private final String name;
    private final BigDecimal value;
}
