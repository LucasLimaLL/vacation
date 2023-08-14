package com.lucaslima.vacation.application.domains.payments;

public class VacationPaymentSupport {

    public static VacationPayment.VacationPaymentBuilder get() {
        return VacationPayment
                .builder()
                .withQuantityDays(30);
    }
}