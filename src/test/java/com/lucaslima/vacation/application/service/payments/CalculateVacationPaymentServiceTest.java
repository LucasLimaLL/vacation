package com.lucaslima.vacation.application.service.payments;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class CalculateVacationPaymentServiceTest {

    @Test
    void givenTest() {

        var third = BigDecimal.valueOf(1500)
                .divide(BigDecimal.valueOf(30))
                .multiply(BigDecimal.valueOf(15))
                .multiply(BigDecimal.valueOf(1.0/3.0)).setScale(2, BigDecimal.ROUND_HALF_UP);
        assertThat(third).isEqualTo(BigDecimal.valueOf(250.00).compareTo(third) == 0);
    }
}